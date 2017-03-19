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
        private HoleInfo holeInfo;

        public TeeBasket(Tee tee, Basket basket, HoleInfo holeInfo) {
            this.tee = tee;
            this.basket = basket;
            this.holeInfo = holeInfo;
        }

        public Tee getTee() { return tee; }

        public void setTee(Tee tee) { this.tee = tee; }

        public Basket getBasket() { return basket; }

        public void setBasket(Basket basket) { this.basket = basket; }

        public HoleInfo getHoleInfo() { return holeInfo; }

        public void setHoleInfo(HoleInfo holeInfo) { this.holeInfo = holeInfo; }

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

    private Map<Color, Set<TeeBasket>> colorTeeBasketMap = new LinkedHashMap<>();

    private GeoJsonPolygon bounds;

    public Set<Tee> getTees() { return tees; }

    public void setTees(Set<Tee> tees) { this.tees = tees; }

    public Set<Basket> getBaskets() { return baskets; }

    public void setBaskets(Set<Basket> baskets) { this.baskets = baskets; }

    public Map<Color,Set<TeeBasket>> getColorTeeBasketMap() { return colorTeeBasketMap; }

    public void setColorTeeBasketMap(Map<Color,Set<TeeBasket>> colorTeeBasketMap) { this.colorTeeBasketMap = colorTeeBasketMap; }

    public GeoJsonPolygon getBounds() { return bounds; }

    public void setBounds(GeoJsonPolygon bounds) { this.bounds = bounds; }

    public void addHole(Tee tee, Basket basket, HoleInfo holeInfo) {
        baskets.add(basket);
        tees.add(tee);
        TeeBasket teeBasket = new TeeBasket(tee,basket,holeInfo);
        Set<TeeBasket> teeBaskets = colorTeeBasketMap.getOrDefault(tee.getColor(), new HashSet<>());
        teeBaskets.add(teeBasket);
        colorTeeBasketMap.put(tee.getColor(), teeBaskets);
    }
}

