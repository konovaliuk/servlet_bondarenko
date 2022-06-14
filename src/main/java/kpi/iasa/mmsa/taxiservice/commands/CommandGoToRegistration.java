package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CommandGoToRegistration implements ICommand{

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        return "/jsp/registration.jsp";
    }
}
