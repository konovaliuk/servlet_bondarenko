package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.services.RideService;
import kpi.iasa.mmsa.taxiservice.services.UserService;

import java.io.IOException;

public class CommandDriverHasChosedRide implements ICommand{

    private final static String folder = "/jsp/";
    private final static String RIDE_STATUS_PAGE = "driverRideStatusPage";


    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        HttpSession session = rq.getSession();

        Long rideId = Long.parseLong(rq.getParameter("chosenRide"));
        session.setAttribute("chosenRideId", rideId);

        RideService rideService = new RideService();
        UserService userService = new UserService();

        rideService.updateRideStatus(rideId, "driver was found");
        session.setAttribute("passenger", userService
                                            .getUserById(rideService
                                                    .getRideById(rideId)
                                                    .getUserId()));

        return folder+RIDE_STATUS_PAGE+".jsp";
    }
}
