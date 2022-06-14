package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.services.DriverService;
import kpi.iasa.mmsa.taxiservice.services.UserService;

import java.io.IOException;

public class CommandLogin implements ICommand{

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException{
        String login = rq.getParameter(USERNAME);
        String password = rq.getParameter(PASSWORD);

        UserService userService = new UserService();
        DriverService driverService = new DriverService();

        Long userId = userService.getUserId(login);

        HttpSession session = rq.getSession();
        if(userService.isLogged(login, password)) {
            System.out.print("I'm here");
            session.setAttribute("login", login);

            if(userService.getUserByLogin(login).getRole().equals("passenger")){
                return "/jsp/Welcome.jsp";
            } else {
                rq.setAttribute("available_rides", driverService
                        .getAvailableRides(
                                driverService.getDriverByUserId(userId).getId()));
                return "/jsp/driverWorkPage.jsp";
            }

        }

        rq.setAttribute("message", "Not authorized");
        return "/jsp/Error.jsp";
    }
}
