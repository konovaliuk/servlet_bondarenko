package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoDriver;
import kpi.iasa.mmsa.taxiservice.entities.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoDriver implements IDaoDriver {

    private final static String columnID = "id";
    private final static String columnTelegramUsername = "telegram_username";
    private final static String columnCarID = "car_id";
    private final static String columnRating = "rating";
    private final static String columnUserId = "user_id";
    private final static String columnDriverPoint = "driver_point";

    private static final String SQLSelect = "SELECT * FROM `drivers`";
    private static final String SQLInsert = "INSERT INTO `drivers`(" +
            columnTelegramUsername+
            ","+columnCarID+
            ","+columnRating+
            ","+columnUserId+
            ","+columnDriverPoint+") VALUES (?, ? ,?, ?, ?)";
    private static final String SQLSelectWhere = "SELECT * FROM `drivers` WHERE id = ?";
    private static final String SQLSelectByUserId = "SELECT * FROM `drivers` WHERE user_id = ?";
    private static final String SQLSelectByTelegramUsername = "SELECT * FROM `drivers` WHERE telegram_username = ?";
    private static final String SQLUpdate = "UPDATE `drivers` SET " +
            columnTelegramUsername+"= ?, "+
            columnCarID+"= ?, "+
            columnRating+"= ? "+
            columnUserId+"= ? "+
            columnDriverPoint+"= ?"+" WHERE id = ?";
    private static final String SQLDelete = "DELETE FROM `drivers` WHERE id = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Driver getDriver(ResultSet rs) throws SQLException {
        Long id = rs.getLong(columnID);
        String telegramUsername = rs.getString(columnTelegramUsername);
        Long carId = rs.getLong(columnCarID);
        Float rating = rs.getFloat(columnRating);
        Long userId = rs.getLong(columnUserId);
        String driverPoint = rs.getString(columnDriverPoint);

        return new Driver(id, telegramUsername, carId, rating, userId, driverPoint);
    }


    public PreparedStatement setDriverParams(PreparedStatement prStm,
                                           String telegramUsername,
                                           Long carId,
                                           Float rating,
                                           Long userId,
                                             String driverPoint) throws SQLException {

        prStm.setString(1, telegramUsername);
        prStm.setLong( 2, carId);
        prStm.setFloat(3, rating);
        prStm.setLong(4, userId);
        prStm.setString(5, driverPoint);

        return prStm;

    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                drivers.add(getDriver(rs));
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
                    System.err.print(e.getMessage()+"\n");
                }
            }
            else{
                System.out.print("Driver table is empty.\n");
            }
        }
        return drivers;
    }


    @Override
    public int createDriver( String telegramUsername,
                             Long carId,
                             Float rating,
                             Long userId,
                                String driverPoint) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setDriverParams(prStm, telegramUsername, carId, rating, userId, driverPoint);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }


    @Override
    public Driver findDriverById(Long id) {

        Driver driver = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            driver = getDriver(rs);

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
                System.out.print("There is no driver with that id.\n");
            }
        }

        return driver;
    }

    @Override
    public Driver findDriverByUserId(Long id) {

        Driver driver = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectByUserId)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            driver = getDriver(rs);

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
                System.out.print("There is no driver with that user id.\n");
            }
        }

        return driver;
    }

    @Override
    public Driver findDriverByTelegramUsername(String telegramUsername) {

        Driver driver = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectByTelegramUsername)) {

            prStm.setString(1, telegramUsername);
            rs = prStm.executeQuery();

            rs.next();
            driver = getDriver(rs);

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
                System.out.print("There is no driver with that id.\n");
            }
        }

        return driver;
    }

    @Override
    public int updateDriverById(Long driverId, Driver newDriver) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setDriverParams(prStm, newDriver.getTelegramUsername(), newDriver.getCarId(),
                    newDriver.getRating(), newDriver.getUserId(), newDriver.getDriverPoint());
            prStm.setLong(6, driverId);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no driver with that id.\n");
            }
        }
        return rs;
    }


    @Override
    public int deleteDriverById(Long id) {

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
