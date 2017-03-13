package com.pliesveld.discgolf.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.EnumSet;

public class Basket {
    @NotNull
    private Character tag;

    private GeoJsonPoint location;

    public Basket(Character tag) { this.tag = tag; }

    public Character getTag() { return tag; }

    public void setTag(Character tag) { this.tag = tag; }

    public GeoJsonPoint getLocation() { return location; }

    public void setLocation(GeoJsonPoint location) { this.location = location; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        return getTag().equals(basket.getTag());
    }

    @Override
    public int hashCode() {
        return getTag().hashCode();
    }
}

