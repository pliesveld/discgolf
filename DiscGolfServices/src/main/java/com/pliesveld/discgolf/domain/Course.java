package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Document
public class Course {

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @Size(min = 4, max = 64)
    private String name;

    private Map<Integer,List<Basket>> availableBaskets = new LinkedHashMap<>();

    private Map<Integer,List<Tee>> availableTees = new LinkedHashMap<>();

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Map<Integer,List<Basket>> getAvailableBaskets() {
        return availableBaskets;
    }

    public void setAvailableBaskets(Map<Integer,List<Basket>> availableBaskets) {
        this.availableBaskets = availableBaskets;
    }

    public Map<Integer,List<Tee>> getAvailableTees() {
        return availableTees;
    }

    public void setAvailableTees(Map<Integer,List<Tee>> availableTees) {
        this.availableTees = availableTees;
    }
}

