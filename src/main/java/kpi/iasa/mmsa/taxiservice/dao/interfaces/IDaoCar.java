package kpi.iasa.mmsa.taxiservice.dao.interfaces;

import kpi.iasa.mmsa.taxiservice.entities.Car;
import kpi.iasa.mmsa.taxiservice.entities.CarTypeEnum;

import java.util.List;

public interface IDaoCar {

    List<Car> findAll();

    int createCar(String number,
                  String model,
                  String color,
                  CarTypeEnum type,
                  Float averageSpeed,
                  Long capacity);

    Car findCarById(Long id);

    Car findCarByNumber(String number);

    int updateCarById(Long carId, Car newCar);

    int deleteCarById(Long id);
}
