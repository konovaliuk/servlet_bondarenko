package kpi.iasa.mmsa.taxiservice.DTO;

public class CarDTO {

    private String carNumber;
    private String model;
    private String color;


    public CarDTO(String carNumber, String model, String color, Long capacity) {
        this.carNumber = carNumber;
        this.model = model;
        this.color = color;
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
}
