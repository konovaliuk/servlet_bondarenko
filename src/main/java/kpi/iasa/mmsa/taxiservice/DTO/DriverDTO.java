package kpi.iasa.mmsa.taxiservice.DTO;

import kpi.iasa.mmsa.taxiservice.entities.CarTypeEnum;


public class DriverDTO {

    private String name;
    private String telephoneNumber;
    private String telegramUsername;
    private Float rating;
    private CarTypeEnum type;
    private Long capacity;
    private String arrivalTime;
    private String cost;

    public DriverDTO(String name, String telephoneNumber, String telegramUsername, Float rating, CarTypeEnum type,
                     Long capacity, String arrivalTime, String cost) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.telegramUsername = telegramUsername;
        this.rating = rating;
        this.type = type;
        this.capacity=capacity;
        this.arrivalTime =arrivalTime;
        this.cost=cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public CarTypeEnum getType() {
        return type;
    }

    public void setType(CarTypeEnum type) {
        this.type = type;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
