package kpi.iasa.mmsa.taxiservice.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CommandLogout implements ICommand{

    public final static String folder = "/jsp/";
    public final static String LOGIN = "login";

    @Override
    public String execute(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        HttpSession session = rq.getSession();

        session.invalidate();

        return folder+LOGIN+".jsp";
    }
}
