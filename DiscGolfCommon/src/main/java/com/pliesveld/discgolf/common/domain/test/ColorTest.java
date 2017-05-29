package com.pliesveld.discgolf.common.domain.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import java.util.EnumSet;

import com.pliesveld.discgolf.common.domain.Color;
import org.hibernate.validator.constraints.NotEmpty;

@Document
public class ColorTest {

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @NotEmpty
    private EnumSet<Color> colors;

    public ColorTest() {}

    public String getId() { return id; }

    public EnumSet<Color> getColors() { return colors; }

    public void setColors(EnumSet<Color> colors) { this.colors = colors; }
}
