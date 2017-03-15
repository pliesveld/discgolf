package com.pliesveld.discgolf.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pliesveld.discgolf.config.BaseMongoTest;
import com.pliesveld.discgolf.domain.test.ColorTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolationException;
import java.util.EnumSet;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DomainUnitTest extends BaseMongoTest {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private MongoOperations mongoTemplate;

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(mongoTemplate);
    }

    private <T> String toJson(T obj) throws Exception {
        Player player = newPlayer();
        MongoConverter converter = mongoTemplate.getConverter();
        DBObject sink = new BasicDBObject();
        converter.write(obj, sink);
        return sink.toString();
    }

    @Test
    public void givenPlayer_shouldJSON() throws Exception {
        Player player = newPlayer();
        LOG.debug(toJson(player));
    }


    @Test
    public void givenBasket_shouldJSON() throws Exception {
        Basket basket = newBasket();
        LOG.debug(toJson(basket));
    }

    @Test
    public void givenTee_shouldJSON() throws Exception {
        Tee tee = new Tee(Color.WHITE);
        LOG.debug(toJson(tee));
    }

    @Test
    public void givenCourse_shouldJSON() throws Exception {
        Course course = newCourse();
        LOG.debug(toJson(course));
    }



}
