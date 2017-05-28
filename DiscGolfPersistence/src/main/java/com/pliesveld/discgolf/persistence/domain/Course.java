package com.pliesveld.discgolf.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pliesveld.discgolf.common.domain.CourseHoleEntry;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.Map;

@Document
public class Course {

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @Size(min = 4, max = 64)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 32)
    private Map<Integer,CourseHoleEntry> holes = new LinkedHashMap<>();

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Map<Integer,CourseHoleEntry> getHoles() { return holes; }

    public void setHoles(Map<Integer,CourseHoleEntry> holes) { this.holes = holes; }
}

