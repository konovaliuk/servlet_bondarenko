package kpi.iasa.mmsa.taxiservice.services;

import kpi.iasa.mmsa.taxiservice.DTO.CarDTO;
import kpi.iasa.mmsa.taxiservice.dao.DAOFactory;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoCar;
import kpi.iasa.mmsa.taxiservice.entities.Car;
import kpi.iasa.mmsa.taxiservice.entities.CarTypeEnum;

public class CarService {

    public CarDTO getCarInformation(Long carId){

        IDaoCar daoCar = DAOFactory.createDaoCar();

        Car car = daoCar.findCarById(carId);

        return new CarDTO(car.getCarNumber(), car.getModel(), car.getColor(), car.getCapacity());
    }

    public boolean addCar(String carNumber,
                          String carModel,
                          String carColor,
                          String carType,
                          Float averageSpeed,
                          Long capacity){

        IDaoCar daoCar = DAOFactory.createDaoCar();
        int rs = daoCar.createCar(carNumber, carModel, carColor,
                                  CarTypeEnum.valueOf(carType.toUpperCase()), averageSpeed, capacity);

        return rs == 1;
    }

    public Car getCarByNumber(String number){
        IDaoCar daoCar = DAOFactory.createDaoCar();
        return daoCar.findCarByNumber(number);
    }
}
