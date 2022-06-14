package kpi.iasa.mmsa.taxiservice.services;

import kpi.iasa.mmsa.taxiservice.DTO.DriverDTO;
import kpi.iasa.mmsa.taxiservice.dao.DAOFactory;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.*;
import kpi.iasa.mmsa.taxiservice.entities.*;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RideService {

    private final HashMap<CarTypeEnum, Float> carTypeCoefficients= new HashMap<>();

    public RideService() {
        carTypeCoefficients.put(CarTypeEnum.ECONOM, 1f);
        carTypeCoefficients.put(CarTypeEnum.STANDART, 1.25f);
        carTypeCoefficients.put(CarTypeEnum.COMFORT, 1.75f);
        carTypeCoefficients.put(CarTypeEnum.BUISNESS, 2.25f);
    }

    public boolean streetExist(String name) {

        IDaoCoordinates daoCoordinates = DAOFactory.createDaoCoordinates();

        return daoCoordinates.findCoordinatesByName(name) != null;
    }

    public Float getDistance(String start, String finish) {

        if (start.equals(finish)){
            return 0f;
        }

        IDaoCoordinates daoCoordinates = DAOFactory.createDaoCoordinates();

        Coordinates startPoint = daoCoordinates.findCoordinatesByName(start);
        Coordinates finishPoint = daoCoordinates.findCoordinatesByName(finish);

        Float X_start = startPoint.getX();
        Float Y_start = startPoint.getY();

        Float X_finish = finishPoint.getX();
        Float Y_finish = finishPoint.getY();

        return (float) Math.sqrt(Math.pow((X_finish - X_start), 2) + Math.pow((Y_finish - Y_start), 2));
    }

    public String getArrivalTime(Float distance, Long carId) {

        IDaoCar daoCar = DAOFactory.createDaoCar();
        Float averageSpeed = daoCar.findCarById(carId).getAverageSpeed();

        if (distance.equals(0f)){
            return "0 seconds";
        }
        int arrivalTimeInSeconds = (int)Math.floor(distance / averageSpeed);

        String arrivalTime = "";
        List<String> hms = Arrays.asList(" hours", " minutes", " seconds");
        for(int i = 0; i < 3; i++){
            arrivalTime += (String.valueOf(arrivalTimeInSeconds % 60) + hms.get(2 - i) + " ");
            if (arrivalTimeInSeconds / 60 == 0){
                break;
            }
            arrivalTimeInSeconds = arrivalTimeInSeconds / 60;
        }

        return arrivalTime;
    }

    public String getCost(Float distance,
                          Float driverDepartureDistance,
                          Float driverCoefficient,
                          int downTimeInMinutes,
                          Float downtimeCoefficient){
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
        return decimalFormat.format(downTimeInMinutes * downtimeCoefficient +
                (distance+driverDepartureDistance) * driverCoefficient);
    }

    public DriverDTO getRideInformation(Driver driver,
                                        String departurePoint,
                                        String destinationPoint,
                                        Float driverCoefficient,
                                        Float downtimeCoefficient){

        IDaoUser daoUser = DAOFactory.createDaoUser();
        IDaoCar daoCar = DAOFactory.createDaoCar();

        User driverUserAccount = daoUser.findUserById(driver.getUserId());
        Car driverCar = daoCar.findCarById(driver.getCarId());

        Float driverPassengerDistance = getDistance(driver.getDriverPoint(), departurePoint);
        Float departureDestinationDistance = getDistance(departurePoint, destinationPoint);

        DriverDTO driverDTO = new DriverDTO(driverUserAccount.getName(),
                driverUserAccount.getTelephoneNumber(),
                driver.getTelegramUsername(),
                driver.getRating(),
                driverCar.getType(),
                driverCar.getCapacity(),
                getArrivalTime(driverPassengerDistance, driver.getCarId()),
                getCost(departureDestinationDistance, driverPassengerDistance, driverCoefficient, 0,
                        downtimeCoefficient));

        return driverDTO;
    }

    public int addNewRide(Long userId,
                          String destination,
                          String departurePoint,
                          Float departureDestinationDistance) {

        IDaoRide daoRide = DAOFactory.createDaoRide();
        int rs = daoRide.createRide(userId, null, destination, departurePoint, null,
                departureDestinationDistance, null, null, RideStatusEnum.SEARCHING_DRIVER);
        return rs;
    };

    public int updateRide(Long driverId, String departurePoint, String login){

        IDaoRide daoRide =  DAOFactory.createDaoRide();
        IDaoDriver daoDriver = DAOFactory.createDaoDriver();
        IDaoUser daoUser = DAOFactory.createDaoUser();


        Driver driver = daoDriver.findDriverById(driverId);
        User passenger = daoUser.findUserByLogin(login);

        Ride ride = daoRide.findRideById(findActiveRideId(passenger.getId()));

        System.out.print(driver.getDriverPoint());
        ride.setDriverId(driver.getId());
        ride.setDriverClientDistance(getDistance(driver.getDriverPoint(), departurePoint));


        return daoRide.updateRideById(ride.getId(), ride);
    }

    public Long findActiveRideId(Long passengerId) {
        IDaoRide daoRide = DAOFactory.createDaoRide();

        List<Ride> rides = daoRide.findAll();
        rides.removeIf(ride -> !ride.getUserId().equals(passengerId) | (ride.getDriverId()!= 0));

        return rides.get(0).getId();
    }

    public Ride getRideById(Long rideId) {
        IDaoRide daoRide = DAOFactory.createDaoRide();
        return daoRide.findRideById(rideId);
    }

    public int getDowntimeInMinutes(Long rideId){
        Ride ride = getRideById(rideId);

        Timestamp driverArrivalTime = ride.getDriverArrivalTime();
        Timestamp startRideTime = ride.getStartRideTime();

        if(driverArrivalTime == null & startRideTime == null){
            return 0;
        }

        int rs = Math.round((startRideTime.getTime() - driverArrivalTime.getTime())/60000f);
        return rs;

    }

    public void updateRideStatus(Long rideId, String status) {

        IDaoRide daoRide = DAOFactory.createDaoRide();

        Ride ride = getRideById(rideId);
        ride.setStatus(RideStatusEnum.valueOf(status.toUpperCase().replace(' ', '_')));

        if (status.equals("on the way")){
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            ride.setStartRideTime(timestamp);
        }

        if(status.equals("driver has arrived")){
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            ride.setDriverArrivalTime(timestamp);
        }

        daoRide.updateRideById(rideId, ride);
    }
}
