package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.DTO.DriverDTO;
import kpi.iasa.mmsa.taxiservice.entities.Driver;
import kpi.iasa.mmsa.taxiservice.services.DriverService;
import kpi.iasa.mmsa.taxiservice.services.RideService;
import kpi.iasa.mmsa.taxiservice.services.UserService;

import java.io.IOException;
import java.util.List;

public class CommandAddNewRide implements ICommand{

    private final static String my_location = "my location";
    private final static String destination_point = "destination";
    private final static String errorPage = "Error";
    private final static String chooseDriverPage = "chooseDriver";
    private final static String path = "/jsp/";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException{
        String myLocation = rq.getParameter(my_location);
        String destination = rq.getParameter(destination_point);

        HttpSession session = rq.getSession();
        RideService rideService = new RideService();
        UserService userService = new UserService();
        DriverService driverService = new DriverService();

        if(myLocation.isEmpty() || destination.isEmpty() || !rideService.streetExist(myLocation)
                || !rideService.streetExist(destination)){
            rq.setAttribute("Message", "Something wrong with location or destination. Please try again=((((((");
            return path+errorPage+".jsp";
        }

        String login = (String)session.getAttribute("login");
        Long userId = userService.getUserId(login);
        Float distance = rideService.getDistance(myLocation, destination);
        rideService.addNewRide(userId, destination, myLocation, distance);


        List<DriverDTO> fullListOfDrivers = driverService.getAllAvailableDrivers(myLocation, destination);
        rq.setAttribute("listOfDrivers", fullListOfDrivers);

        return path+chooseDriverPage+".jsp";
    }
}
