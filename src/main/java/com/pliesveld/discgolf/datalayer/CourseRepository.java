package com.pliesveld.discgolf.datalayer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.Player;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {
    public Player findByName(String name);
}
