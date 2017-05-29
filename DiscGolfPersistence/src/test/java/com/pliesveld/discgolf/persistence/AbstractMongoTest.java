package com.pliesveld.discgolf.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.pliesveld.discgolf.DiscgolfApplication;
import com.pliesveld.discgolf.common.domain.Basket;
import com.pliesveld.discgolf.common.domain.Color;
import com.pliesveld.discgolf.common.domain.CourseHoleEntry;
import com.pliesveld.discgolf.common.domain.Tee;
import com.pliesveld.discgolf.persistence.config.MongoConfig;
import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.domain.Player;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
@SpringBootTest(classes = {DiscgolfApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class AbstractMongoTest {

    @Configuration
    @Import(MongoConfig.class)
    static public class Config {

        @Bean
        public Validator localValidatorFactoryBean() {
            LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
            localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
            localValidatorFactoryBean.afterPropertiesSet();
            return localValidatorFactoryBean.getValidator();
        }

        @Bean
        @Autowired
        public ValidatingMongoEventListener validatingMongoEventListener(javax.validation.Validator validator) { return new ValidatingMongoEventListener(validator); }
    }

    @Autowired
    private Validator validator;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(mongoOperations);
        assertNotNull(validator);
    }

    protected Basket newBasket() {
        Basket basket = new Basket('A');
        return basket;
    }

    protected Player newPlayer() {
        Player player1 = new Player();
        player1.setName("Player1");
        player1.setId(UUID.randomUUID().toString());
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
        course.setId(UUID.randomUUID().toString());


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
