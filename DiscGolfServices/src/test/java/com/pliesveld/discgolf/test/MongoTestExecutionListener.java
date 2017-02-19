package com.pliesveld.discgolf.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.mongodb.DBCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

import static org.junit.Assert.*;

public class MongoTestExecutionListener extends AbstractTestExecutionListener {

    private Logger LOG = LogManager.getLogger();

    @Autowired
    private MongoOperations mongoTemplate;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        AutowireCapableBeanFactory beanFactory = testContext.getApplicationContext().getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(this,AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        beanFactory.initializeBean(this,this.getClass().getName());

        assertNotNull("MongoTemplate not wired.", mongoTemplate);
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        LOG.debug("Removing collections: {}", collectionNames);
        collectionNames.stream().map(mongoTemplate::getCollection).forEach(DBCollection::drop);
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
