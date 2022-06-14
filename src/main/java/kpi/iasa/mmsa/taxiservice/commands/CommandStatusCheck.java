package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.entities.Ride;
import kpi.iasa.mmsa.taxiservice.entities.RideStatusEnum;
import kpi.iasa.mmsa.taxiservice.services.*;

import java.io.IOException;
import java.util.Locale;

public class CommandStatusCheck implements ICommand{

    private static final String rideId = "rideId";
    private static final String driverId = "driverId";
    private static final String userLogin = "login";
    private static final String statusPage = "passengerRideStatus";
    private static final String path = "/jsp/";
    private static final String statusMessage = "status_message";
    private static final String goBack = "goBack";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        HttpSession session = rq.getSession();
        Long driverId_ = (Long)session.getAttribute(driverId);
        Long rideId_ = (Long)session.getAttribute(rideId);
        String login = (String)session.getAttribute(userLogin);

        StatusMessageService statusMessageService = new StatusMessageService();
        RideService rideService = new RideService();

        Ride ride = rideService.getRideById(rideId_);
        String message = statusMessageService.getMessage(ride, driverId_, login);

        rq.setAttribute(statusMessage, message);
        rq.setAttribute(goBack, ride.getStatus().toString().toLowerCase().replace('_', ' '));

        return path+statusPage+".jsp";
    }
}
