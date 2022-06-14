package kpi.iasa.mmsa.taxiservice.services;

import kpi.iasa.mmsa.taxiservice.dao.DAOFactory;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoCoordinates;
import kpi.iasa.mmsa.taxiservice.entities.Coordinates;

import java.util.List;
import java.util.Random;

public class CoordinatesService {

    public String getFirstStreet(){
        IDaoCoordinates daoCoordinates = DAOFactory.createDaoCoordinates();
        return daoCoordinates.findCoordinatesById(1L).getNameOfStreet();

    }
}
