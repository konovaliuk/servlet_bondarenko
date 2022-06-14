package kpi.iasa.mmsa.taxiservice.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kpi.iasa.mmsa.taxiservice.commands.ICommand;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name= "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    protected void processRequest(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String page = null;
        try {
            ICommand command = controllerHelper.getCommand(rq);
            page = command.execute(rq, rs);
        }
        catch (ServletException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        RequestDispatcher rqDispatcher = getServletContext().getRequestDispatcher(page);
        rqDispatcher.forward(rq, rs);
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
            processRequest(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
            processRequest(rq, rs);
    }
}
