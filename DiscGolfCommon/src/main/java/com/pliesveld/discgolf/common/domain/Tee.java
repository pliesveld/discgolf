package com.pliesveld.discgolf.common.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotNull;

public class Tee {

   @NotNull
   private Color color;

   private GeoJsonPoint location;

   public Tee(Color color) { this.color = color; }

   public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public GeoJsonPoint getLocation() { return location; }

    public void setLocation(GeoJsonPoint location) { this.location = location; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tee tee = (Tee) o;

        return getColor() == tee.getColor();
    }

    @Override
    public int hashCode() {
        return getColor().hashCode();
    }
}

