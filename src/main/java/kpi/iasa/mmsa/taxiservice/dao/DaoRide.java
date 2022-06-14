package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoRide;
import kpi.iasa.mmsa.taxiservice.entities.Ride;
import kpi.iasa.mmsa.taxiservice.entities.RideStatusEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRide implements IDaoRide {

    private final static String columnID = "id";
    private final static String columnUserID = "user_id";
    private final static String columnDriverID = "driver_id";
    private final static String columnDestination = "destination";
    private final static String columnDeparturePoint = "departure_point";
    private final static String columnDriverClientDistance = "driver_client_distance";
    private final static String columnDepartureDestinationDistance = "departure_destination_distance";
    private final static String columnDriverArrivalTime = "driver_arrival_time";
    private final static String columnStartRideTime = "start_ride_time";
    private final static String columnStatus = "status_";

    private static final String SQLSelect = "SELECT * FROM `rides`";
    private static final String SQLInsert = "INSERT INTO `rides`(" +
            columnUserID+
            ","+columnDriverID+
            ","+columnDestination+
            ","+columnDeparturePoint+
            ","+columnDriverClientDistance+
            ","+columnDepartureDestinationDistance+
            ","+columnDriverArrivalTime+
            ","+columnStartRideTime+
            ","+columnStatus + ") VALUES (?, ? ,?, ?, ?, ?, ?, ?, ?)";
    private static final String SQLSelectWhere = "SELECT * FROM `rides` WHERE id = ?";
    private static final String SQLSelectByDriverId = "SELECT * FROM `rides` WHERE " +
            columnDriverID+" = ?";
    private static final String SQLUpdate = "UPDATE `rides` SET " +
            columnUserID+"= ?, "+
            columnDriverID+"= ?, "+
            columnDestination+"= ?, "+
            columnDeparturePoint+"= ?, "+
            columnDriverClientDistance+"= ?, "+
            columnDepartureDestinationDistance+"= ?, "+
            columnDriverArrivalTime+"= ?, "+
            columnStartRideTime+"= ?, "+
            columnStatus +"= ? " + " WHERE id = ?";
    private static final String SQLDelete = "DELETE FROM `rides` WHERE id = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Ride getRide(ResultSet rs) throws SQLException {
        Long id = rs.getLong(columnID);
        Long userId = rs.getLong(columnUserID);
        Long driverId = rs.getLong(columnDriverID);
        String destination = rs.getString(columnDestination);
        String departurePoint = rs.getString(columnDeparturePoint);
        Float driverClientDistance = rs.getFloat(columnDriverClientDistance);
        Float departureDestinationDistance = rs.getFloat(columnDepartureDestinationDistance);
        Timestamp driverArrivalTime = rs.getTimestamp(columnDriverArrivalTime);
        Timestamp startRideTime = rs.getTimestamp(columnStartRideTime);
        String status = rs.getString(columnStatus);

        RideStatusEnum rideStatus = RideStatusEnum.valueOf(status.toUpperCase().replace(' ', '_'));

        return new Ride(id, userId, driverId, destination, departurePoint, driverClientDistance,
                departureDestinationDistance,driverArrivalTime,startRideTime,rideStatus);
    }


    public PreparedStatement setRideParams(PreparedStatement prStm,
                                           Long userId,
                                           Long driverId,
                                           String destination,
                                           String departurePoint,
                                           Float driverClientDistance,
                                           Float departureDestinationDistance,
                                           Timestamp driverArrivalTime,
                                           Timestamp startRideTime,
                                           RideStatusEnum rideStatus) throws SQLException {

        prStm.setLong(1, userId);
        if (driverId == null){
            prStm.setNull(2, Types.BIGINT);
        }
        else{
            prStm.setLong(2, driverId);
        }
        prStm.setString(3, destination);
        prStm.setString(4, departurePoint);
        if(driverClientDistance == null){
            prStm.setNull(5, Types.FLOAT);
        }
        else{
            prStm.setFloat(5, driverClientDistance);
        }
        prStm.setFloat(6, departureDestinationDistance);
        if (driverArrivalTime == null){
            prStm.setNull(7, Types.TIMESTAMP);
        }
        else{
            prStm.setTimestamp(7, driverArrivalTime);
        }
        if(startRideTime == null){
            prStm.setNull(8, Types.TIMESTAMP);
        }
        else{
            prStm.setTimestamp(8, startRideTime);
        }
        prStm.setString(9, rideStatus.toString().toLowerCase().replace('_', ' '));


        return prStm;
    }

    @Override
    public List<Ride> findAll() {
        List<Ride> rides = new ArrayList<>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                rides.add(getRide(rs));
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
                System.out.print("Rides table is empty.\n");
            }
        }
        return rides;
    }

    @Override
    public int createRide(Long userId,
                          Long driverId,
                          String destination,
                          String departurePoint,
                          Float driverClientDistance,
                          Float departureDestinationDistance,
                          Timestamp driverArrivalTime,
                          Timestamp startRideTime,
                          RideStatusEnum rideStatus) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setRideParams(prStm, userId, driverId, destination, departurePoint, driverClientDistance,
                    departureDestinationDistance,driverArrivalTime,startRideTime,rideStatus);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }

    @Override
    public Ride findRideById(Long id) {

        Ride ride = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            ride = getRide(rs);

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
                System.out.print("There is no ride with that id.\n");
            }
        }

        return ride;
    }

    @Override
    public List<Ride> findRidesByDriverId(Long id) {

        List<Ride> rides = new ArrayList<>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectByDriverId)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            while(rs.next()){
                rides.add(getRide(rs));
            }

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
                System.out.print("There is no ride with that driver id.\n");
            }
        }

        return rides;
    }

    @Override
    public int updateRideById(Long rideId, Ride newRide) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setRideParams(prStm, newRide.getUserId(), newRide.getDriverId(),newRide.getDestination(),
                    newRide.getDeparturePoint(), newRide.getDriverClientDistance(),
                    newRide.getDepartureDestinationDistance(),newRide.getDriverArrivalTime(),newRide.getStartRideTime(),
                    newRide.getStatus());
            prStm.setLong(10, rideId);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no ride with that id.\n");
            }
        }
        return rs;
    }

    @Override
    public int deleteRideById(Long id) {

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
