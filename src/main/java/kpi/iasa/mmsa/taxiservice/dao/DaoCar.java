package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoCar;
import kpi.iasa.mmsa.taxiservice.entities.Car;
import kpi.iasa.mmsa.taxiservice.entities.CarTypeEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCar implements IDaoCar {

    private final static String columnID = "id";
    private final static String columnNumber = "car_number";
    private final static String columnModel = "model";
    private final static String columnColor = "color";
    private final static String columnType = "car_type";
    private final static String columnAverageSpeed = "average_speed";
    private final static String columnCapacity = "capacity";

    private static final String SQLSelect = "SELECT * FROM `cars`";
    private static final String SQLInsert = "INSERT INTO `cars`(" +
            columnNumber+
            ","+columnModel+
            ","+columnColor+
            ","+columnType+
            ","+columnAverageSpeed+
            ","+columnCapacity+ ") VALUES (?, ? ,?, ?, ?, ?)";
    private static final String SQLSelectWhere = "SELECT * FROM `cars` WHERE id = ?";
    private static final String SQLSelectByNumber = "SELECT * FROM `cars` WHERE " +
            columnNumber + "= ?";
    private static final String SQLUpdate = "UPDATE `users` SET " +
            columnNumber+ "= ?, "+
            columnModel+"= ?, "+
            columnColor+"= ?, "+
            columnType+"= ?, "+
            columnAverageSpeed+"= ?, "+
            columnCapacity+"= ?, "+ " WHERE id = ?";
    private static final String SQLDelete = "DELETE FROM `cars` WHERE id = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Car getCar(ResultSet rs) throws SQLException {
        Long id = rs.getLong(columnID);
        String number = rs.getString(columnNumber);
        String model = rs.getString(columnModel);
        String color = rs.getString(columnColor);
        String type = rs.getString(columnType);
        Float averageSpeed = rs.getFloat(columnAverageSpeed);
        Long capacity = rs.getLong(columnCapacity);

        CarTypeEnum carType = CarTypeEnum.valueOf(type.toUpperCase());

        return new Car(id, number, model, color, carType, averageSpeed, capacity);
    }


    public PreparedStatement setCarParams(PreparedStatement prStm,
                                           String number,
                                           String model,
                                           String color,
                                           CarTypeEnum type,
                                           Float averageSpeed,
                                           Long capacity) throws SQLException {

        prStm.setString(1, number);
        prStm.setString(2, model);
        prStm.setString(3, color);
        prStm.setString(4, type.toString().toLowerCase());
        prStm.setFloat(5, averageSpeed);
        prStm.setLong(6, capacity);

        //System.out.print(type.toString().toLowerCase());
        return prStm;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                cars.add(getCar(rs));
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
                System.out.print("Car table is empty.\n");
            }
        }
        return cars;
    }

    @Override
    public int createCar( String number,
                          String model,
                          String color,
                          CarTypeEnum type,
                          Float averageSpeed,
                          Long capacity) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setCarParams(prStm, number, model, color, type, averageSpeed, capacity);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }


    @Override
    public Car findCarById(Long id) {

        Car car = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            car = getCar(rs);

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
                System.out.print("There is no car with that id.\n");
            }
        }

        return car;
    }


    @Override
    public Car findCarByNumber(String number) {

        Car car = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectByNumber)) {

            prStm.setString(1, number);
            rs = prStm.executeQuery();

            rs.next();
            car = getCar(rs);

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
                System.out.print("There is no car with that number.\n");
            }
        }

        return car;
    }

    @Override
    public int updateCarById(Long carId, Car newCar) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setCarParams(prStm, newCar.getCarNumber(), newCar.getColor(), newCar.getModel(),
                    newCar.getType(), newCar.getAverageSpeed(), newCar.getCapacity());
            prStm.setLong(7, carId);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no car with that id.\n");
            }
        }
        return rs;
    }


    @Override
    public int deleteCarById(Long id) {

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
