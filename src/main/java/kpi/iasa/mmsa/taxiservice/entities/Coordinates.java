package kpi.iasa.mmsa.taxiservice.entities;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {

    private Long id;
    private String nameOfStreet;
    private Float x;
    private Float y;

    public Coordinates(Long id, String nameOfStreet, Float x, Float y) {
        this.id = id;
        this.nameOfStreet = nameOfStreet;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public String getNameOfStreet() {
        return nameOfStreet;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setNameOfStreet(String nameOfStreet) {
        this.nameOfStreet = nameOfStreet;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(id, that.id) && Objects.equals(nameOfStreet, that.nameOfStreet) &&
                Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfStreet, x, y);
    }
}
