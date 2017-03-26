package com.pliesveld.discgolf.common.domain.test;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class Location {

    private GeoJsonPoint location;

    public Location() { }

    public Location(final GeoJsonPoint location) { this.location = location; }

    public void setLocation(GeoJsonPoint location) { this.location = location; }

    public GeoJsonPoint getLocation() { return location; }

    @Override
    public String toString() {
        return String.format("Location {latitude=%f, longitude=%f} ",
            location.getCoordinates().get(0),
            location.getCoordinates().get(1));
    }
}
