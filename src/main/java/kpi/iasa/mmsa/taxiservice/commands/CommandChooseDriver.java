package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.services.DriverService;
import kpi.iasa.mmsa.taxiservice.services.RideService;
import kpi.iasa.mmsa.taxiservice.services.UserService;

import java.io.IOException;

public class CommandChooseDriver implements ICommand{

    private final static String login = "login";
    private final static String rideStatusPage = "passengerRideStatus";
    private final static String folder = "/jsp/";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        HttpSession session = rq.getSession();

        String login_ = (String)session.getAttribute(login);
        String driverTelegramUsername = rq.getParameter("chosenDriver");

        RideService rideService = new RideService();
        DriverService driverService = new DriverService();
        UserService userService = new UserService();

        Long driverId = driverService.getDriverIdByTelegramUsername(driverTelegramUsername);
        session.setAttribute("driverId", driverId);

        Long rideId = rideService.findActiveRideId(userService.getUserId(login_));
        session.setAttribute("rideId", rideId);

        rq.setAttribute("status_message", "Trying to connect to the driver " +
                driverService.getDriverLoginById(driverId) +
                ". Please wait ...");

        String start = rideService.getRideById(rideId).getDeparturePoint();

        rideService.updateRide(driverId, start, login_);

        System.out.print(start);

        return folder+rideStatusPage+".jsp";
    }
}
