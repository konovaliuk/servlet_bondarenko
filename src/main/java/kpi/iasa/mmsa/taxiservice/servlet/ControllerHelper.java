package kpi.iasa.mmsa.taxiservice.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import kpi.iasa.mmsa.taxiservice.commands.*;

import java.io.IOException;
import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper controllerHelper = null;
    private HashMap<String, ICommand> hashCommands = new HashMap<>();

    private ControllerHelper(){

        hashCommands.put("registration", new CommandUserRegistration());
        hashCommands.put("login", new CommandLogin());
        hashCommands.put("goToRegistration", new CommandGoToRegistration());
        hashCommands.put("goTo", new CommandGoTo());
        hashCommands.put("addRide", new CommandAddNewRide());
        hashCommands.put("chooseDriver", new CommandChooseDriver());
        hashCommands.put("statusCheck", new CommandStatusCheck());
        hashCommands.put("driverRegistration", new CommandDriverRegister());
        hashCommands.put("chooseRide", new CommandDriverHasChosedRide());
        hashCommands.put("changeRideStatus", new CommandDriverChangeRideStatus());
        hashCommands.put("logout", new CommandLogout());
    }

    public ICommand getCommand(HttpServletRequest rq) throws ServletException, IOException {
        return hashCommands.get(rq.getParameter("command"));
    }

    public static ControllerHelper getInstance(){
        if (controllerHelper == null){
            controllerHelper = new ControllerHelper();
        }

        return controllerHelper;
    }
}
