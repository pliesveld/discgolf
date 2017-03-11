package com.pliesveld.discgolf.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pliesveld.discgolf.config.BaseMongoTest;
import com.pliesveld.discgolf.domain.test.ColorTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolationException;
import java.util.EnumSet;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ColorUnitTest extends BaseMongoTest {

    @Autowired
    private MongoOperations mongoTemplate;

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(mongoTemplate);
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenNone_shouldSave() throws Exception {
        ColorTest colorTest  = new ColorTest();
        colorTest.setColors(EnumSet.noneOf(Color.class));
        mongoTemplate.save(colorTest);
    }

    @Test
    public void whenRedOnly_shouldSave() throws Exception {
        ColorTest colorTest  = new ColorTest();
        colorTest.setColors(EnumSet.of(Color.RED));
        mongoTemplate.save(colorTest);
    }

    @Test
    public void whenTwo_shouldSave() throws Exception {
        ColorTest colorTest  = new ColorTest();
        colorTest.setColors(EnumSet.of(Color.RED,Color.BLUE));
        mongoTemplate.save(colorTest);
    }


}
