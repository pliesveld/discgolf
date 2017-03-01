package com.pliesveld.discgolf.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class Tee {

   private Color color;

   private GeoJsonPoint location;

   public Tee(Color color, GeoJsonPoint location) {
      this.color = color;
      this.location = location;
   }

   public Color getColor() { return color; }

   public GeoJsonPoint getLocation() { return location; }

}

