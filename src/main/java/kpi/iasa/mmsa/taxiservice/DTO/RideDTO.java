package kpi.iasa.mmsa.taxiservice.DTO;

public class RideDTO {

    private String name;
    private String departurePoint;
    private String destination;
    private String cost;
    private Long rideId;

    public RideDTO(String name, String departurePoint, String destination, String cost, Long rideId) {
        this.name = name;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.cost = cost;
        this.rideId=rideId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
}
