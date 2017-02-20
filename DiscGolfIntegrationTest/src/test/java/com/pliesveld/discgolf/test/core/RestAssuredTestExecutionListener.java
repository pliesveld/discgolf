package com.pliesveld.discgolf.test.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.mongodb.DBCollection;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

import static org.junit.Assert.*;

public class RestAssuredTestExecutionListener extends AbstractTestExecutionListener {

    private Logger LOG = LogManager.getLogger();

    @Autowired
    protected EmbeddedWebApplicationContext server;

    @LocalServerPort
    protected int port;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        AutowireCapableBeanFactory beanFactory = testContext.getApplicationContext().getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(this,AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        beanFactory.initializeBean(this,this.getClass().getName());

        LOG.debug("LocalServerPort: {}", port);
        LOG.debug("EmbeddedServerletContainer port: {}",server.getEmbeddedServletContainer().getPort());

        RestAssured.port = port;

    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        super.afterTestMethod(testContext);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        super.afterTestClass(testContext);
    }
}
