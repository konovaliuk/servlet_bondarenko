package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoCoordinates;
import kpi.iasa.mmsa.taxiservice.entities.Car;
import kpi.iasa.mmsa.taxiservice.entities.CarTypeEnum;
import kpi.iasa.mmsa.taxiservice.entities.Coordinates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCoordinates implements IDaoCoordinates {

    private final static String columnID = "id";
    private final static String columnNameOfStreet = "name_of_street";
    private final static String columnX = "x";
    private final static String columnY = "y";

    private static final String SQLSelect = "SELECT * FROM `coordinates`";
    private static final String SQLInsert = "INSERT INTO `coordinates`(" +
            columnNameOfStreet+
            ","+columnX+
            ","+columnY+ ") VALUES (?, ? ,?)";
    private static final String SQLSelectById = "SELECT * FROM `coordinates` WHERE id = ?";
    private static final String SQLSelectWhere = "SELECT * FROM `coordinates` WHERE "+columnNameOfStreet+" = ?";
    private static final String SQLUpdate = "UPDATE `coordinates` SET " +
            columnNameOfStreet+ "= ?, "+
            columnX+"= ?, "+
            columnY+"= ?, "+ " WHERE id = ?";
    private static final String SQLDelete = "DELETE FROM `coordinates` WHERE id = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Coordinates getCoordinates(ResultSet rs) throws SQLException {
        Long id = rs.getLong(columnID);
        String nameOfStreet = rs.getString(columnNameOfStreet);
        Float x = rs.getFloat(columnX);
        Float y = rs.getFloat(columnY);

        return new Coordinates(id, nameOfStreet, x, y);
    }


    public PreparedStatement setCoordinatesParams(PreparedStatement prStm,
                                          String nameOfStreet,
                                          Float x,
                                          Float y) throws SQLException {

        prStm.setString(1, nameOfStreet);
        prStm.setFloat(2, x);
        prStm.setFloat(3, y);

        return prStm;
    }

    @Override
    public List<Coordinates> findAll() {
        List<Coordinates> coordinates = new ArrayList<>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                coordinates.add(getCoordinates(rs));
            }

        }
        catch(SQLException e){
            System.err.print(e.getMessage()+"\n");
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                }
                catch(SQLException e){
                    System.err.print(e.getMessage() + "\n");
                }
            }
            else{
                System.out.print("Coordinates table is empty.\n");
            }
        }
        return coordinates;
    }

    @Override
    public int createCoordinates( String nameOfStreet,
                                  Float x,
                                  Float y) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setCoordinatesParams(prStm, nameOfStreet, x, y);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }


    @Override
    public Coordinates findCoordinatesById(Long id) {

        Coordinates coordinates = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectById)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            coordinates = getCoordinates(rs);

        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                }
                catch(SQLException e){
                    System.err.print(e.getMessage() + "\n");
                }
            }
            else{
                System.out.print("There is no street with that id.\n");
            }
        }

        return coordinates;
    }


    @Override
    public Coordinates findCoordinatesByName(String name) {

        Coordinates coordinates = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setString(1, name);
            rs = prStm.executeQuery();

            rs.next();
            coordinates = getCoordinates(rs);

        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                }
                catch(SQLException e){
                    System.err.print(e.getMessage() + "\n");
                }
            }
            else{
                System.out.print("There is no street with that name.\n");
            }
        }

        return coordinates;
    }


    @Override
    public int updateCoordinatesById(Long coordinatesId, Coordinates newCoordinates) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setCoordinatesParams(prStm, newCoordinates.getNameOfStreet(), newCoordinates.getX(), newCoordinates.getY());
            prStm.setLong(4, coordinatesId);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no street with that id.\n");
            }
        }
        return rs;
    }


    @Override
    public int deleteCoordinatesById(Long id) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLDelete)){

            prStm.setLong(1, id);
            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        return rs;
    }
}

