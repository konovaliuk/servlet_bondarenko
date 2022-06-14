package kpi.iasa.mmsa.taxiservice.entities;

import java.io.Serializable;
import java.util.Objects;

public class Driver implements Serializable {

    private Long id;
    private String telegramUsername;
    private Long carId;
    private Float rating;
    private Long userId;
    private String driverPoint;

    public Driver(Long id, String telegramUsername, Long carId, Float rating, Long userId, String driverPoint) {
        this.id = id;
        this.telegramUsername = telegramUsername;
        this.carId = carId;
        this.rating = rating;
        this.userId = userId;
        this.driverPoint = driverPoint;
    }

    public Long getId() {
        return id;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDriverPoint() {
        return driverPoint;
    }

    public void setDriverPoint(String driverPoint) {
        this.driverPoint = driverPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(telegramUsername, driver.telegramUsername) &&
                Objects.equals(carId, driver.carId) && Objects.equals(rating, driver.rating) &&
                Objects.equals(userId, driver.userId) && Objects.equals(driverPoint, driver.driverPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telegramUsername, carId, rating, userId, driverPoint);
    }

}
