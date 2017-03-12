package com.pliesveld.discgolf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.repository.CourseRepository;
import com.pliesveld.discgolf.web.controller.base.AbstractDiscGolfController;

@RestController
@RequestMapping("/course")
public class CourseController extends AbstractDiscGolfController{

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Course> handleCourseById(@PathVariable("id") String courseId) {
        final Course course = courseRepository.findOne(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Course> handleCourseByName(@PathVariable("name") String name){
        final Course course = courseRepository.findByName(name);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping(path = "/list")
    public HttpEntity<PagedResources<Course>> handleCourseSearch(Pageable pageable, PagedResourcesAssembler assembler) {
        final Page<Course> courses = courseRepository.findAll(pageable);
        return new ResponseEntity<>(assembler.toResource(courses), HttpStatus.OK);
    }
}
