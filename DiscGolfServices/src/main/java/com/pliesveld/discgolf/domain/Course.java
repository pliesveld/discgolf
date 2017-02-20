package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Document
public class Course {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String longitude;

    private String latitude;

    @Size(min = 18, max = 18)
    private List<Hole> holeList = new ArrayList<>();

    public List<Hole> getHoleList() {
        return holeList;
    }

    public void setHoleList(List<Hole> holeList) {
        this.holeList = holeList;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }
}
