package com.pliesveld.discgolf.test.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StopWatch;

import com.pliesveld.discgolf.security.repository.PlayerAccountRepository;
import com.pliesveld.discgolf.security.service.AccountRegistrationService;
import com.pliesveld.discgolf.test.core.AbstractDiscGolfIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.*;

import static io.restassured.config.LogConfig.logConfig;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AuthTokenTests extends AbstractDiscGolfIntegrationTest {

    @Configuration
    static public class Config {
        @Autowired
        private Environment environment;

        @Bean
        public JavaMailSender mailSender() {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost(environment.getRequiredProperty("spring.mail.host"));
            sender.setPort(environment.getRequiredProperty("spring.mail.port", Short.class));
            sender.setUsername(environment.getRequiredProperty("spring.mail.username"));
            sender.setPassword(environment.getRequiredProperty("spring.mail.password"));
            return sender;
        }
    }


    private static final Logger LOG = LogManager.getLogger();

    private StopWatch stopWatch = new StopWatch();

    @Autowired
    private AccountRegistrationService registrationService;

    @Autowired
    private PlayerAccountRepository playerAccountRepository;

    String token;

    @Before
    public void saveUser() {
        if (null == playerAccountRepository.findOneByEmail("student@example.com"))
            registrationService.createStudent("newuser", "student@example.com", "password");

        if (token == null) {

            int port = Integer.valueOf(this.port);
            String URL_BASE = "http://localhost:" + port;
            String URL_AUTH = URL_BASE + "/auth";

            RestAssured.port = port;
            RestAssured.baseURI = URL_BASE;
            config = config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));

            token =
            given()
                .body(new AuthToken("newuser", "password"))
                .contentType(ContentType.JSON)
            .when()
                .post("/auth")
            .then()
                .statusCode(200)
                .body("token", notNullValue())
            .and()
                .extract().path("token");
        }

        stopWatch.start();
    }

    @After
    public void afterTest() {
        stopWatch.stop();
        LOG.debug("Authentication took: {}ms", stopWatch.getLastTaskTimeMillis());
    }

    @Test
    public void givenToken_whenEndpointUser_shouldHaveAuthorities() {

        Authorities authorities =
        given()
            .header("X-AUTH-TOKEN", token)
        .when()
            .get("/user")
        .then()
            .statusCode(200)
            .body("username", notNullValue())
            .body("authorities", notNullValue())
        .and()
            .extract().as(Authorities.class);

        LOG.debug(authorities.getAuthorities());
    }

}


