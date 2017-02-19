package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@Document
public class Player {

    @Id
    @GeneratedValue
    private String id;

    @Indexed
    private String name;

    public Player() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
