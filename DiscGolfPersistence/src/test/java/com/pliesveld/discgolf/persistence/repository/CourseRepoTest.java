package com.pliesveld.discgolf.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.persistence.AbstractMongoTest;
import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.repository.mongo.CourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseRepoTest extends AbstractMongoTest {

    @Autowired
	private CourseRepository courseRepository;

	@Test
	public void givenContext_shouldLoad() {}

	@Test
	public void givenCourse_whenSave_thenCorrect() {
		Course course = newCourse();
		course.setName("Bull Run Regional Park");
        Course savedCourse = courseRepository.save(course);
        assertNotNull(savedCourse);
        assertNotNull(savedCourse.getHoles());
        assertEquals(18, savedCourse.getHoles().size());
	}
}

