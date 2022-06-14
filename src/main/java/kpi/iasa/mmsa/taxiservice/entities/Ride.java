package kpi.iasa.mmsa.taxiservice.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Ride implements Serializable {

    private Long id;
    private Long userId;
    private Long driverId;
    private String destination;
    private String departurePoint;
    private Float driverClientDistance;
    private Float departureDestinationDistance;
    private Timestamp driverArrivalTime;
    private Timestamp startRideTime;
    private RideStatusEnum status;

    public Ride(Long id, Long userId, Long driverId, String destination, String departurePoint,
                Float driverClientDistance, Float departureDestinationDistance, Timestamp driverArrivalTime,
                Timestamp startRideTime, RideStatusEnum status) {
        this.id = id;
        this.userId = userId;
        this.driverId = driverId;
        this.destination = destination;
        this.departurePoint = departurePoint;
        this.driverClientDistance = driverClientDistance;
        this.departureDestinationDistance = departureDestinationDistance;
        this.driverArrivalTime = driverArrivalTime;
        this.startRideTime = startRideTime;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public Float getDriverClientDistance() {
        return driverClientDistance;
    }

    public void setDriverClientDistance(Float driverClientDistance) {
        this.driverClientDistance = driverClientDistance;
    }

    public Float getDepartureDestinationDistance() {
        return departureDestinationDistance;
    }

    public void setDepartureDestinationDistance(Float departureDestinationDistance) {
        this.departureDestinationDistance = departureDestinationDistance;
    }

    public Timestamp getDriverArrivalTime() {
        return driverArrivalTime;
    }

    public void setDriverArrivalTime(Timestamp driverArrivalTime) {
        this.driverArrivalTime = driverArrivalTime;
    }

    public Timestamp getStartRideTime() {
        return startRideTime;
    }

    public void setStartRideTime(Timestamp startRideTime) {
        this.startRideTime = startRideTime;
    }

    public RideStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RideStatusEnum status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return Objects.equals(id, ride.id) && Objects.equals(userId, ride.userId) &&
                Objects.equals(driverId, ride.driverId) && Objects.equals(destination, ride.destination) &&
                Objects.equals(departurePoint, ride.departurePoint) &&
                Objects.equals(driverClientDistance, ride.driverClientDistance) &&
                Objects.equals(departureDestinationDistance, ride.departureDestinationDistance) &&
                Objects.equals(driverArrivalTime, ride.driverArrivalTime) &&
                Objects.equals(startRideTime, ride.startRideTime) && status == ride.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, driverId, destination, departurePoint, driverClientDistance,
                departureDestinationDistance, driverArrivalTime, startRideTime, status);
    }
}
