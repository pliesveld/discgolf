package com.pliesveld.discgolf.persistence.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.persistence.domain.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    public Course findByName(String name);
}
