package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoPassenger;
import kpi.iasa.mmsa.taxiservice.entities.Driver;
import kpi.iasa.mmsa.taxiservice.entities.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoPassenger implements IDaoPassenger {

    private final static String columnID = "id";
    private final static String columnNumberOfRides = "number_of_rides";
    private final static String columnCardNumber = "card_number";
    private final static String columnCardCVV = "card_cvv";
    private final static String columnUserId = "user_id";

    private static final String SQLSelect = "SELECT * FROM `passengers`";
    private static final String SQLInsert = "INSERT INTO `passengers`(" +
            columnNumberOfRides+
            ","+columnCardNumber+
            ","+columnCardCVV+
            ","+columnUserId+") VALUES (?, ? ,?, ?)";
    private static final String SQLSelectWhere = "SELECT * FROM `passengers` WHERE id = ?";
    private static final String SQLUpdate = "UPDATE `passengers` SET " +
            columnNumberOfRides+"= ?, "+
            columnCardNumber+"= ?, "+
            columnCardCVV+"= ? "+
            columnUserId+"= ? "+" WHERE id = ?";
    private static final String SQLDelete = "DELETE FROM `passengers` WHERE id = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Passenger getPassenger(ResultSet rs) throws SQLException {
        Long id = rs.getLong(columnID);
        Long numberOfRides = rs.getLong(columnNumberOfRides);
        String cardNumber = rs.getString(columnCardNumber);
        String cardCVV = rs.getString(columnCardCVV);
        Long userId = rs.getLong(columnUserId);

        return new Passenger(id, numberOfRides, cardNumber, cardCVV, userId);
    }


    public PreparedStatement setPassengerParams(PreparedStatement prStm,
                                                Long numberOfRides,
                                                String cardNumber,
                                                String cardCVV,
                                                Long userId) throws SQLException {

        prStm.setLong(1, numberOfRides);
        prStm.setString( 2, cardNumber);
        prStm.setString(3, cardCVV);
        prStm.setLong(4, userId);

        return prStm;

    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> passengers = new ArrayList<>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                passengers.add(getPassenger(rs));
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
                System.out.print("Passenger table is empty.\n");
            }
        }
        return passengers;
    }


    @Override
    public int createPassenger( Long numberOfRides,
                                String cardNumber,
                                String cardCVV,
                                Long userId) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setPassengerParams(prStm, numberOfRides, cardNumber, cardCVV, userId);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }


    @Override
    public Passenger findPassengerById(Long id) {

        Passenger passenger = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            passenger = getPassenger(rs);

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
                System.out.print("There is no passenger with that id.\n");
            }
        }

        return passenger;
    }


    @Override
    public int updatePassengerById(Long passengerId, Passenger newPassenger) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setPassengerParams(prStm, newPassenger.getNumberOfRides(), newPassenger.getCardNumber(),
                               newPassenger.getCardCVV(), newPassenger.getUserId());
            prStm.setLong(5, passengerId);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no passenger with that id.\n");
            }
        }
        return rs;
    }


    @Override
    public int deletePassengerById(Long id) {

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
