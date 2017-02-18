package com.pliesveld.discgolf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.datalayer.CourseRepository;
import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.Hole;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseRepoTest {

    @Autowired
	private CourseRepository courseRepository;

	@Test
	public void givenContext_shouldLoad() {}

	@Test
	public void givenCourse_whenSave_thenCorrect() {
		Course course = new Course();
		List<Hole> holeList = Arrays.asList( newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole());
		course.setHoleList(holeList);
		Course savedCourse = courseRepository.save(course);
        assertNotNull(savedCourse);
        assertNotNull(savedCourse.getHoleList());
        assertEquals(18, savedCourse.getHoleList().size());
	}

	@Test(expected = ConstraintViolationException.class)
	public void givenInvalid19HoleCourse_whenSave_thenCorrect() {
		Course course = new Course();
		List<Hole> holeList = Arrays.asList( newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole());
		course.setHoleList(holeList);
		courseRepository.save(course);
	}

    @Test(expected = ConstraintViolationException.class)
	public void givenInvalid17HoleCourse_whenSave_thenCorrect() {
		Course course = new Course();
		List<Hole> holeList = Arrays.asList( newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole());
		course.setHoleList(holeList);
		courseRepository.save(course);
	}

	private Hole newHole() {
		Hole hole = new Hole();
		hole.setPar(3);
		hole.setDistance(250);
        return hole;
	}
}
