package com.pliesveld.discgolf.common.domain.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pliesveld.discgolf.common.domain.Color;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import java.util.EnumSet;

@Document
public class ColorTest {

    @Id
    @GeneratedValue
    private String id;

    private EnumSet<Color> colors;

    public ColorTest() {}

    public String getId() { return id; }

    @NotEmpty
    public EnumSet<Color> getColors() { return colors; }

    public void setColors(EnumSet<Color> colors) { this.colors = colors; }
}
