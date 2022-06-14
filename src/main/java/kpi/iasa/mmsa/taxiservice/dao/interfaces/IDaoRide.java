package kpi.iasa.mmsa.taxiservice.dao.interfaces;

import kpi.iasa.mmsa.taxiservice.entities.Ride;
import kpi.iasa.mmsa.taxiservice.entities.RideStatusEnum;

import java.sql.Timestamp;
import java.util.List;

public interface IDaoRide {

    List<Ride> findAll();

    int createRide(Long userId,
                   Long driverId,
                   String destination,
                   String departurePoint,
                   Float driverClientDistance,
                   Float departureDestinationDistance,
                   Timestamp driverArrivalTime,
                   Timestamp startRideTime,
                   RideStatusEnum rideStatus);

    Ride findRideById(Long id);

    List<Ride> findRidesByDriverId(Long id);

    int updateRideById(Long rideId, Ride newRide);

    int deleteRideById(Long id);

}
