package com.pliesveld.discgolf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pliesveld.discgolf.domain.*;
import com.pliesveld.discgolf.test.MongoTestExecutionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners(listeners = {MongoTestExecutionListener.class}, mergeMode = MERGE_WITH_DEFAULTS)
@Ignore("Not a real test.")
public abstract class BaseMongoTest extends AbstractJUnit4SpringContextTests {
    private static final Logger LOG = LogManager.getLogger();

    @Configuration
    @Import(MongoConfig.class)
    public static class Confg {}

	protected Basket newBasket() {
		Basket basket = new Basket('A');
		return basket;
	}

    protected Player newPlayer() {
        Player player1 = new Player();
        player1.setName("Player1");
        return player1;
    }

    protected CourseHoleEntry newCourseHoleEntry(Tee tee, Basket basket) {
        CourseHoleEntry courseHoleEntry = new CourseHoleEntry();
        CourseHoleEntry.HoleInfo holeInfo = new CourseHoleEntry.HoleInfo(3, 200);
        courseHoleEntry.addHole(tee, basket, holeInfo);
        return courseHoleEntry;
    }


    @Deprecated
    protected CourseHoleEntry newCourseHoleEntry(Basket basket, Tee tee) {
        CourseHoleEntry courseHoleEntry = new CourseHoleEntry();
        CourseHoleEntry.HoleInfo holeInfo = new CourseHoleEntry.HoleInfo(3, 200);
        courseHoleEntry.addHole(tee, basket, holeInfo);
        return courseHoleEntry;
    }

    protected Course newCourse() {
        Course course = new Course();
        course.setName("SampleGameCourse");


        Map<Integer, CourseHoleEntry> holes = new LinkedHashMap<>();
        for(int i = 1 ; i <= 18; i++) {
            Basket basket = newBasket();
            Tee tee = new Tee(Color.WHITE);
            holes.put(i, newCourseHoleEntry(tee, basket));
        }

        course.setHoles(holes);
        return course;
    }
}

