package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.services.UserService;

import java.io.IOException;

public class CommandUserRegistration implements ICommand {

    private static final String NAME = "name";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String TELEPHONE_NUMBER = "telephone number";
    private static final String ROLE = "role";
    private static final String folder = "/jsp/";
    private static final String WELCOME_PAGE = "Welcome";
    private static final String ERROR_PAGE = "Error";
    private static final String DRIVER_REGISTRATION = "driverRegistration";
    private static final String PASSENGER_REGISTRATION = "passengerRegistration";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String page = null;
        String name = rq.getParameter(NAME);
        String username = rq.getParameter(USERNAME);
        String password = rq.getParameter(PASSWORD);
        String telephoneNumber = rq.getParameter(TELEPHONE_NUMBER);
        String role = rq.getParameter(ROLE);

        HttpSession session = rq.getSession();
        UserService userService = new UserService();

        if(userService.registerUser(name, username, password, telephoneNumber, role)){
            session.setAttribute("login", username);
            rq.setAttribute("login", username);
            if(role.equals("driver")){
                page = folder+DRIVER_REGISTRATION+".jsp";
            }else{
                //page=folder+PASSENGER_REGISTRATION+".jsp";
                page = folder+WELCOME_PAGE+".jsp";
            }
        }
        else{
            page = folder+ERROR_PAGE+".jsp";
        }
        return page;
    }
}
