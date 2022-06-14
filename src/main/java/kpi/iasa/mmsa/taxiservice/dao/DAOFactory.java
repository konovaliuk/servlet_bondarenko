package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.dao.interfaces.*;

public class DAOFactory {

    public static IDaoUser createDaoUser() { return new DaoUser();}

    public static IDaoDriver createDaoDriver() { return new DaoDriver();}

    public static IDaoCar createDaoCar() { return new DaoCar();}

    public static IDaoRide createDaoRide() { return new DaoRide();}

    public static IDaoPassenger createDaoPassenger() {return new DaoPassenger();}

    public static IDaoCoordinates createDaoCoordinates() {return new DaoCoordinates();}
}
