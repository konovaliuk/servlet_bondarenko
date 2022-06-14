package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

public class CommandGoTo implements ICommand {

    private final static String ADD_RIDE = "addNewRide";
    private final static String REGISTRATION = "registration";
    private final static String LOGIN = "login";
    private final static String GO_BACK = "goBack";
    private final static String GO_BACK_DRIVER = "goBackDriver";
    private final static String WORK_PAGE = "driverWorkPage";
    private final static String START_PAGE="Welcome";
    private final static String folder = "/jsp/";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String page = null;
        HttpSession session = rq.getSession();

        switch ((String)session.getAttribute("where")) {
            case (ADD_RIDE):
                page = folder + ADD_RIDE + ".jsp";
                break;
            case (REGISTRATION):
                page = folder + REGISTRATION + ".jsp";
                break;
            case (GO_BACK):
                page = folder + START_PAGE + ".jsp";
                break;
            case (GO_BACK_DRIVER):
                page = folder + WORK_PAGE + ".jsp";
                break;
            default:
                page = folder + LOGIN + ".jsp";
                break;
        }
        return page;
    }
}
