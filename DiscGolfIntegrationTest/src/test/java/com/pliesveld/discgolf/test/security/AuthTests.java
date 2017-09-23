package com.pliesveld.discgolf.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pliesveld.discgolf.config.SpringMailConfig;
import com.pliesveld.discgolf.config.SpringSecurityConfig;
import com.pliesveld.discgolf.config.SpringCacheConfig;

import com.pliesveld.discgolf.persistence.config.PersistenceContext;
import com.pliesveld.discgolf.security.repository.PlayerAccountRepository;
import com.pliesveld.discgolf.security.service.AccountRegistrationService;
import com.pliesveld.discgolf.test.core.AbstractDiscGolfIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.notNullValue;


//@SpringBootTest(classes = {SpringCacheConfig.class, SpringSecurityConfig.class, SpringMailConfig.class, PersistenceContext.class, AuthTests.class})
@RunWith(SpringRunner.class)
@Ignore("disabled authentication tests")
public class AuthTests extends AbstractDiscGolfIntegrationTest {
    private static final Logger LOG = LogManager.getLogger();

    private StopWatch stopWatch = new StopWatch();

    @LocalServerPort
    String port;

    @Autowired
    AccountRegistrationService registrationService;


    @Autowired
    PlayerAccountRepository playerAccountRepository;


    @Before
    public void saveUser() {
        if (null == playerAccountRepository.findOneByEmail("student@example.com"))
            registrationService.createStudent("newuser", "student@example.com", "password");

        stopWatch.start();
    }

    @After
    public void afterTest() {
        stopWatch.stop();
        LOG.debug("Authentication took: {}ms", stopWatch.getLastTaskTimeMillis());
    }

    @Test
    @Repeat(value = 10)
    @Ignore("disabled authentication tests")
    public void contextLoad() {
        int port = Integer.valueOf(this.port);
        String URL_BASE = "http://localhost:" + port;
        String URL_AUTH = URL_BASE + "/auth";

        RestAssured.port = port;
        RestAssured.baseURI = URL_BASE;

        String token = RestAssured.given()
                .body(new AuthToken("student@example.com", "password"))
                .contentType(ContentType.JSON)
                .when().post("/auth")
                .then()
                .log().ifStatusCodeMatches(isOneOf(400, 401, 402, 403))
                .statusCode(200)
                .body("token", notNullValue())
                .extract().path("token");

        Authorities authorities = RestAssured.given().header("X-AUTH-TOKEN", token)
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("username", notNullValue())
                .body("authorities", notNullValue())
                .extract().as(Authorities.class);

        LOG.debug(authorities.getAuthorities());

    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class AuthorityExtractor implements GrantedAuthority {
    String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Authorities {
    private Set<AuthorityExtractor> authorities;

    public Authorities() {
    }

    public Set<AuthorityExtractor> getAuthorities() {
        return authorities;
    }
}


class SystemOutFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger();

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        final Response response = ctx.next(requestSpec, responseSpec); // Invoke the request by delegating to the next filter in the filter chain.
        LOG.debug(response::asString);
        return response;
    }
}
