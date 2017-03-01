package com.pliesveld.discgolf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.config.BaseMongoTest;
import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseRepoTest extends BaseMongoTest {

    @Autowired
	private CourseRepository courseRepository;

	@Test
	public void givenContext_shouldLoad() {}

	@Test
	public void givenCourse_whenSave_thenCorrect() {
		Course course = new Course();
		List<Basket> basketList = Arrays.asList( newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket());
		course.setName("Bull Run Regional Park");
		course.setBasketList(basketList);
//		course.setLongitude("38.8016155");
//		course.setLatitude("-77.4779218");
		Course savedCourse = courseRepository.save(course);
        assertNotNull(savedCourse);
        assertNotNull(savedCourse.getBasketList());
        assertEquals(18, savedCourse.getBasketList().size());
	}

	@Test(expected = ConstraintViolationException.class)
	public void givenInvalid19HoleCourse_whenSave_thenCorrect() {
		Course course = new Course();
		List<Basket> basketList = Arrays.asList( newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket());
		course.setBasketList(basketList);
		courseRepository.save(course);
	}

    @Test(expected = ConstraintViolationException.class)
	public void givenInvalid17HoleCourse_whenSave_thenCorrect() {
		Course course = new Course();
		List<Basket> basketList = Arrays.asList( newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket(), newBasket());
		course.setBasketList(basketList);
		courseRepository.save(course);
	}
}

