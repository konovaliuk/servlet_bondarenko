package kpi.iasa.mmsa.taxiservice.entities;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private Long id;
    private String carNumber;
    private String model;
    private String color;
    private CarTypeEnum type;
    private Float averageSpeed;
    private Long capacity;

    public Car(Long id, String carNumber, String model, String color, CarTypeEnum type, Float averageSpeed, Long capacity) {
        this.id = id;
        this.carNumber = carNumber;
        this.model = model;
        this.color = color;
        this.type = type;
        this.averageSpeed = averageSpeed;
        this.capacity = capacity;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public CarTypeEnum getType() {
        return type;
    }

    public void setType(CarTypeEnum type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(carNumber, car.carNumber)
                && Objects.equals(model, car.model) && Objects.equals(color, car.color)
                && type == car.type && Objects.equals(averageSpeed, car.averageSpeed)
                && Objects.equals(capacity, car.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carNumber, model, color, type, averageSpeed, capacity);
    }
}
