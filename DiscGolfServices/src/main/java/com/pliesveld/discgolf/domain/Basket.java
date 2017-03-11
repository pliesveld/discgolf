package com.pliesveld.discgolf.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.EnumSet;

public class Basket {
    private Character tag;

    @NotEmpty
    private EnumSet<Color> colors;

    private GeoJsonPoint location;

    public Basket() {}

    public Character getTag() { return tag; }

    public void setTag(Character tag) { this.tag = tag; }

    public GeoJsonPoint getLocation() { return location; }

    public void setLocation(GeoJsonPoint location) { this.location = location; }

    public EnumSet<Color> getColors() { return colors; }

    public void setColors(EnumSet<Color> colors) { this.colors = colors; }
}

