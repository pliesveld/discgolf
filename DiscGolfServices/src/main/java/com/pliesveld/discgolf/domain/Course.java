package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Document
public class Course {

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @Size(min = 4, max = 64)
    private String name;

    private GeoJsonPoint location;

    @Size(min = 18, max = 18)
    private List<Basket> basketList = new ArrayList<>();

    @Size(min = 18, max = 18)
    private List<Tee> teeList = new ArrayList<>();

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Basket> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }


}
