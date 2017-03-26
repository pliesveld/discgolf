package com.pliesveld.discgolf.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@Ignore("Not a real test.")
public abstract class BaseSQLTest extends AbstractJUnit4SpringContextTests {
    private static final Logger LOG = LogManager.getLogger();

    @Configuration
    public static class Confg {}

}

