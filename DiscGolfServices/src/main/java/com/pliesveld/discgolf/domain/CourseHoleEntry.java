package com.pliesveld.discgolf.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.*;

public class CourseHoleEntry {

   static public class HoleInfo {
        private int par;
        private int distance;

        public HoleInfo(int par, int distance) {
            this.par = par;
            this.distance = distance;
        }

        public int getPar() { return par; }

        public void setPar(int par) { this.par = par; }

        public int getDistance() { return distance; }

        public void setDistance(int distance) { this.distance = distance; }
    }

    static public class TeeBasket {
        @NotNull
        private Tee tee;
        @NotNull
        private Basket basket;

        public TeeBasket(Tee tee, Basket basket) {
            this.tee = tee;
            this.basket = basket;
        }

        public Tee getTee() { return tee; }

        public void setTee(Tee tee) { this.tee = tee; }

        public Basket getBasket() { return basket; }

        public void setBasket(Basket basket) { this.basket = basket; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TeeBasket teeBasket = (TeeBasket) o;

            if (!getTee().equals(teeBasket.getTee())) return false;
            return getBasket().equals(teeBasket.getBasket());
        }

        @Override
        public int hashCode() {
            int result = getTee().hashCode();
            result = 31 * result + getBasket().hashCode();
            return result;
        }
    }

    @NotNull @NotEmpty
    private Set<Tee> tees = new HashSet<>();

    @NotNull @NotEmpty
    private Set<Basket> baskets = new HashSet<>();

    private Map<TeeBasket, HoleInfo> info = new LinkedHashMap<>();

    private GeoJsonPolygon bounds;

    public void addHole(Tee tee, Basket basket, HoleInfo holeInfo) {
        baskets.add(basket);
        tees.add(tee);
        TeeBasket teeBasket = new TeeBasket(tee,basket);
        info.put(teeBasket, holeInfo);
    }


}
