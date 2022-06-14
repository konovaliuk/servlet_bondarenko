package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.iasa.mmsa.taxiservice.services.*;

import java.io.IOException;

public class CommandDriverRegister implements ICommand{

    private final static String WORK_PAGE = "driverWorkPage";
    private final static String folder = "/jsp/";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        HttpSession session = rq.getSession();
        String login = (String)session.getAttribute("login");

        String telegramUsername = rq.getParameter("telegram_username");
        String carNumber = rq.getParameter("car_number");
        String carModel = rq.getParameter("car_model");
        String carColor = rq.getParameter("car_color");
        String carType = rq.getParameter("car_type");
        Float averageSpeed = Float.parseFloat(rq.getParameter("average_speed"));
        Long capacity = Long.parseLong(rq.getParameter("car_capacity"));

        CarService carService = new CarService();
        DriverService driverService = new DriverService();
        UserService userService = new UserService();
        CoordinatesService coordinatesService = new CoordinatesService();

        carService.addCar(carNumber, carModel, carColor, carType, averageSpeed, capacity);
        Long carId = carService.getCarByNumber(carNumber).getId();

        Long userId = userService.getUserId(login);
        driverService.addDriver(telegramUsername, carId, 4f, userId, coordinatesService.getFirstStreet());

        rq.setAttribute("available_rides", driverService
                                .getAvailableRides(
                                        driverService
                                                .getDriverIdByTelegramUsername(telegramUsername)));

        return folder+WORK_PAGE+".jsp";
    }
}
