package com.pliesveld.discgolf.persistence.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.validation.ConstraintViolationException;

import java.util.EnumSet;

import com.pliesveld.discgolf.common.domain.Color;
import com.pliesveld.discgolf.common.domain.test.ColorTest;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test(expected = ConstraintViolationException.class)
    public void whenNull_shouldSave() throws Exception {
        ColorTest colorTest  = new ColorTest();
        colorTest.setColors(null);
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
