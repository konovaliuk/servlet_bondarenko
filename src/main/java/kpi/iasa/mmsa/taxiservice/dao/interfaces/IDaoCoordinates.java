package kpi.iasa.mmsa.taxiservice.dao.interfaces;

import kpi.iasa.mmsa.taxiservice.entities.Coordinates;

import java.util.List;

public interface IDaoCoordinates {
    List<Coordinates> findAll();

    int createCoordinates(String nameOfStreet,
                          Float x,
                          Float y);

    Coordinates findCoordinatesById(Long id);

    Coordinates findCoordinatesByName(String name);

    int updateCoordinatesById(Long coordinatesId, Coordinates newCoordinates);

    int deleteCoordinatesById(Long id);
}
