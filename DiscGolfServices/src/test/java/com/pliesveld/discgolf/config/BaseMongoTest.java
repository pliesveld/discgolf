package com.pliesveld.discgolf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pliesveld.discgolf.domain.Basket;
import com.pliesveld.discgolf.domain.Color;
import com.pliesveld.discgolf.test.MongoTestExecutionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import java.util.EnumSet;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {MongoTestExecutionListener.class}, mergeMode = MERGE_WITH_DEFAULTS)
@Ignore("Not a real test.")
public abstract class BaseMongoTest extends AbstractJUnit4SpringContextTests {
    private static final Logger LOG = LogManager.getLogger();

    @Configuration
    @Import(MongoConfig.class)
    public static class Confg {}


	protected Basket newBasket() {
		Basket basket = new Basket();
		basket.setColors(EnumSet.of(Color.WHITE));
		return basket;
	}
}
