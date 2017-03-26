package com.pliesveld.discgolf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pliesveld.discgolf.domain.Basket;
import com.pliesveld.discgolf.domain.Color;
import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.CourseHoleEntry;
import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.domain.Tee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@Ignore("Not a real test.")
public abstract class BaseSQLTest extends AbstractJUnit4SpringContextTests {
    private static final Logger LOG = LogManager.getLogger();

    @Configuration
    public static class Confg {}

}

