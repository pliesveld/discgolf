package com.pliesveld.discgolf.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecordScoreUnitTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Configuration
    public static class Confg {
        @Bean
        public MongoTemplate mongoTemplate() throws Exception {
            return new MongoTemplate(new MongoClient("10.2.2.4"), "discgolf");
        }
    }

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(mongoTemplate);
    }


    @Test
    public void givenRound_shouldSave() throws Exception {
        Course course = new Course();
        course.setName("TestCourse");
        course.getHoleList().add(new Hole(3, 250));
        mongoTemplate.save(course);
    }
}
