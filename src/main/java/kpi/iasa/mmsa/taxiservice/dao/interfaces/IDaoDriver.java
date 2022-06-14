package kpi.iasa.mmsa.taxiservice.dao.interfaces;

import kpi.iasa.mmsa.taxiservice.entities.Driver;

import java.util.List;

public interface IDaoDriver {

    List<Driver> findAll();

    int createDriver( String telegramUsername,
                      Long carId,
                      Float rating,
                      Long userId,
                      String driverPoint);

    Driver findDriverById(Long id);

    Driver findDriverByUserId(Long id);

    Driver findDriverByTelegramUsername(String telegramUsername);

    int updateDriverById(Long driverId, Driver newDriver);

    int deleteDriverById(Long id);
}
