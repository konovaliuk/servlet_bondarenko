package kpi.iasa.mmsa.taxiservice.dao.interfaces;

import kpi.iasa.mmsa.taxiservice.entities.Passenger;

import java.util.List;

public interface IDaoPassenger {

    List<Passenger> findAll();

    int createPassenger(Long numberOfRides,
                        String cardNumber,
                        String cardCVV,
                        Long userId);

    Passenger findPassengerById(Long id);

    int updatePassengerById(Long passengerId, Passenger newPassenger);

    int deletePassengerById(Long id);
}
