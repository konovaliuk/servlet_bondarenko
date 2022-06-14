package kpi.iasa.mmsa.taxiservice.services;

import kpi.iasa.mmsa.taxiservice.DTO.DriverDTO;
import kpi.iasa.mmsa.taxiservice.DTO.RideDTO;
import kpi.iasa.mmsa.taxiservice.dao.DAOFactory;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.*;
import kpi.iasa.mmsa.taxiservice.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DriverService {

    private Float driverCoefficient = 0.1f;
    private Float downTimeCoefficient = 0.05f;

    public List<DriverDTO> getAllAvailableDrivers(String start, String finish){

        IDaoDriver daoDriver = DAOFactory.createDaoDriver();
        IDaoRide daoRide = DAOFactory.createDaoRide();

        List<Driver> listOfDrivers = daoDriver.findAll();
        listOfDrivers.removeIf(driver -> !isAvailable(daoRide.findRidesByDriverId(driver.getId())));

        List<DriverDTO> fullListOfDrivers = new ArrayList<>();
        RideService rideService = new RideService();


        for(Driver driver : listOfDrivers){
            fullListOfDrivers.add(rideService.getRideInformation(driver, start, finish, driverCoefficient,
                    downTimeCoefficient));
        }

        return fullListOfDrivers;

    }

    public boolean isAvailable(List<Ride> rides){

        for(Ride ride : rides){
            if (!(ride.getStatus().equals(RideStatusEnum.COMPLETED) |
                ride.getStatus().equals(RideStatusEnum.SEARCHING_DRIVER))){ return false; }
        }

        return true;
    }

    public Long getDriverIdByTelegramUsername(String telegramUsername){

        IDaoDriver daoDriver = DAOFactory.createDaoDriver();

        Driver driver = daoDriver.findDriverByTelegramUsername(telegramUsername);

        return driver.getId();

    }
    public Driver getDriverById(Long id){
        IDaoDriver daoDriver = DAOFactory.createDaoDriver();

        Driver driver = daoDriver.findDriverById(id);

        return driver;
    }

    public String getDriverLoginById(Long id){

        UserService userService = new UserService();
        DriverService driverService = new DriverService();

        return userService
                .getUserById(driverService.getDriverById(id).getUserId())
                .getLogin();
    }

    public Float getDriverCoefficient() {
        return driverCoefficient;
    }

    public Float getDownTimeCoefficient() {
        return downTimeCoefficient;
    }

    public boolean addDriver(String telegramUsername,
                             Long car_id,
                             Float rating,
                             Long user_id,
                             String driverPoint){

        IDaoDriver daoDriver = DAOFactory.createDaoDriver();

        int rs = daoDriver.createDriver(telegramUsername, car_id, rating, user_id, driverPoint);

        return rs == 1;
    }

    public List<RideDTO> getAvailableRides(Long driverId){

        IDaoRide daoRide = DAOFactory.createDaoRide();

        UserService userService = new UserService();
        RideService rideService = new RideService();

        List<Ride> rideList = daoRide.findAll();
        rideList.removeIf(ride -> !(Objects.equals(ride.getDriverId(), driverId) &&
                ride.getStatus().equals(RideStatusEnum.SEARCHING_DRIVER)));



        List<RideDTO> availableRides = new ArrayList<>();
        for(Ride ride : rideList){
            String nameOfUser= userService.getUserById(ride.getUserId()).getName();
            String cost = rideService.getCost(ride.getDepartureDestinationDistance(),
                    ride.getDriverClientDistance(),
                    driverCoefficient,
                    0,
                    downTimeCoefficient);

            availableRides.add(new RideDTO(nameOfUser,
                                            ride.getDeparturePoint(),
                                            ride.getDestination(),
                                            cost,
                                            ride.getId()));
        }

        return availableRides;
    }

    public Driver getDriverByUserId(Long id){
        IDaoDriver daoDriver = DAOFactory.createDaoDriver();
        return daoDriver.findDriverByUserId(id);
    }
}
