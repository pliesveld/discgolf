package com.pliesveld.discgolf.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.EnumSet;

public class Tee {

   @NotEmpty
   private EnumSet<Color> color;

   public Tee(Color color) {
      this.color = EnumSet.of(color);
   }

   public EnumSet<Color> getColor() { return color; }

}

