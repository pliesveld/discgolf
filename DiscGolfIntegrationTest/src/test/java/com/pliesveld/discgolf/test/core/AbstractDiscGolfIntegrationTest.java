package com.pliesveld.discgolf.test.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.pliesveld.discgolf.DiscgolfApplication;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiscgolfApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(listeners= {DependencyInjectionTestExecutionListener.class, RestAssuredTestExecutionListener.class})
public class AbstractDiscGolfIntegrationTest {
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    protected EmbeddedWebApplicationContext server;

    @LocalServerPort
    protected int port;

    @Test
    public void whenContextLoad_thenCorrect() throws Exception {
        LOG.debug("Listening on port: {}", port);
        assertEquals(port, RestAssured.port);
    }

    protected RequestSpecification defaultRequest() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setAccept(ContentType.JSON);
        builder.setContentType(ContentType.JSON);
        builder.log(LogDetail.ALL);

        RestAssuredConfig config = RestAssured.config();
        config.getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails().enablePrettyPrinting(true);

        builder.setConfig(config);
        return builder.build();
    }

}
