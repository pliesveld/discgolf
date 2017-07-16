package com.pliesveld.discgolf.test.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pliesveld.discgolf.common.domain.CourseHoleEntry;
import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.repository.mongo.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CourseLoader implements ApplicationListener<ApplicationReadyEvent> {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private CourseRepository courseRepository;

    private List<Course> courses = new ArrayList<>(10);

    @PostConstruct
    public void onInit() {
        Course course = new Course();
        course.setName("sample");
        CourseHoleEntry courseHoleEntry = new CourseHoleEntry();
        Map<Integer, CourseHoleEntry> entryMap = new HashMap<>();
        entryMap.putIfAbsent(1, courseHoleEntry);
        course.setHoles(entryMap);
        courseRepository.save(course);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }
}
