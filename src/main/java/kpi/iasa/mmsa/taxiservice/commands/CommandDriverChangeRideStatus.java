package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.services.RideService;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class CommandDriverChangeRideStatus implements ICommand{

    private final static String folder = "/jsp/";
    private final static String CHANGE_STATUS_PAGE="driverRideStatusPage";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        HttpSession session = rq.getSession();
        Long rideId = (Long)session.getAttribute("chosenRideId");

        RideService rideService = new RideService();

        String newRideStatus = rq.getParameter("driverStatusChange");
        rideService.updateRideStatus(rideId, newRideStatus);

        return folder+CHANGE_STATUS_PAGE+".jsp";
    }
}
