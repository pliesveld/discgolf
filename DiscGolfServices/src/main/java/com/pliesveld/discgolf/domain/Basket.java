package com.pliesveld.discgolf.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class Basket {

    public enum PinLocation { A, B, C }

    static public class Pin {
        final private Color pinColor;
        final private PinLocation pinLocation;

        public Pin(final Color pinColor, final PinLocation pinLocation) {
            this.pinColor = pinColor;
            this.pinLocation = pinLocation;
        }

        public Color getPinColor() { return pinColor; }

        public PinLocation getPinLocation() { return pinLocation; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pin pin = (Pin) o;

            if (getPinColor() != pin.getPinColor()) return false;
            return getPinLocation() == pin.getPinLocation();
        }

        @Override
        public int hashCode() {
            int result = getPinColor() != null ? getPinColor().hashCode() : 0;
            result = 31 * result + (getPinLocation() != null ? getPinLocation().hashCode() : 0);
            return result;
        }
    }

    final private Pin pin;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_HAYSTACK)
    private GeoJsonPoint location;

    public Basket(final Pin pin) {this.pin = pin;}

    public Pin getPin() { return pin; }

    public GeoJsonPoint getLocation() { return location; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        return getPin() != null ? getPin().equals(basket.getPin()) : basket.getPin() == null;
    }

    @Override
    public int hashCode() {
        return getPin() != null ? getPin().hashCode() : 0;
    }
}

