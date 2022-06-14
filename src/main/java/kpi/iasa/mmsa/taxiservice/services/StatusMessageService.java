package kpi.iasa.mmsa.taxiservice.services;

import kpi.iasa.mmsa.taxiservice.DTO.CarDTO;
import kpi.iasa.mmsa.taxiservice.entities.Driver;
import kpi.iasa.mmsa.taxiservice.entities.Ride;

public class StatusMessageService {

    public String getMessage(Ride ride, Long driverId, String userLogin){
        RideService rideService = new RideService();
        CarService carService = new CarService();
        DriverService driverService = new DriverService();

        Driver driver = driverService.getDriverById(driverId);

        String driverLogin = driverService.getDriverLoginById(driverId);
        String arrivalTime = rideService.getArrivalTime(
                rideService.getDistance(ride.getDeparturePoint(), driver.getDriverPoint()),
                driver.getCarId()
        );
        String timeOfRide = rideService.getArrivalTime(
                rideService.getDistance(ride.getDeparturePoint(), ride.getDestination()),
                driver.getCarId()
        );

        CarDTO car = carService.getCarInformation(driver.getCarId());

        String message = "";


        switch(ride.getStatus()) {

            case SEARCHING_DRIVER:
                message = "Trying to connect to the driver " + driverLogin + ". Please wait ...";
                break;
            case DRIVER_WAS_FOUND:
                message = driverLogin + " has taken your order. He will pick you up on " + ride.getDeparturePoint() +
                        " in " + arrivalTime + ". The car is " + car.getColor() + " color " + car.getModel() +
                        ". With number " + car.getCarNumber() + ". Please wait ...";
                break;
            case DRIVER_HAS_ARRIVED:
                message = "Thanks for your patience. " + driverLogin + " has arrived. Hope you'll enjoy your ride)";
                break;
            case DOWNTIME:
                message = "Please, " + userLogin + " hurry up. Driver is waiting for you. Your lateness will be added to " +
                        "the total cost of ride";
                break;
            case ON_THE_WAY:
                message = "Total time of your ride will be " + timeOfRide +
                        ". Enjoy your ride with " + driverLogin + ")";
                break;
            default:
                message = "You have arrived at your destination point " + ride.getDestination() + ". Total cost is " +
                        rideService.getCost(ride.getDepartureDestinationDistance(),
                                ride.getDriverClientDistance(),
                                driverService.getDriverCoefficient(),
                                rideService.getDowntimeInMinutes(ride.getId()),
                                driverService.getDownTimeCoefficient());
                break;
        }

        return message;
    }
}
