package com.pliesveld.discgolf.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pliesveld.discgolf.config.BaseMongoTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecordScoreUnitTest extends BaseMongoTest {

    @Autowired
    private MongoOperations mongoTemplate;

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(mongoTemplate);
    }

    @Test
    public void givenCourse_shouldSave() throws Exception {
        Course course = new Course();
        course.setName("TestCourse");
        course.getHoleList().addAll(Arrays.asList(
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250),
                new Hole(3, 250))
        );
        mongoTemplate.save(course);
    }
}
