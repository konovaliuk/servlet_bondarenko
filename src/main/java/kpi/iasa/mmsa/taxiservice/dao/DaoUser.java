package kpi.iasa.mmsa.taxiservice.dao;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoUser;
import kpi.iasa.mmsa.taxiservice.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoUser implements IDaoUser {

    private final static String columnID = "id";
    private final static String columnName = "name";
    private final static String columnLogin = "login";
    private final static String columnHashPassword = "hash_password";
    private final static String columnTelephoneNumber = "telephone_number";
    private final static String columnRole = "role";

    private static final String SQLSelect = "SELECT * FROM `users`";
    private static final String SQLInsert = "INSERT INTO `users`(" +
            columnName+
            ","+columnLogin+
            ","+columnHashPassword+
            ","+columnTelephoneNumber+
            ","+columnRole+") VALUES (?, ? ,?, ?, ?)";
    private static final String SQLSelectWhere = "SELECT * FROM `users` WHERE id = ?";
    private static final String SQLSelectByUsernameAndPassword = "SELECT * FROM `users` WHERE " +
            columnLogin + "= ?"+
            " AND " + columnHashPassword +"= ?";
    private static final String SQLSelectByUsername = "SELECT * FROM `users` WHERE " +columnLogin + "= ?";
    private static final String SQLUpdate = "UPDATE `users` SET " +
            columnName+ "= ?, "+
            columnLogin+"= ?, "+
            columnHashPassword+"= ?, "+
            columnTelephoneNumber+"= ?, "+
            columnRole+"= ? "+ " WHERE id = ?";
    private static final String SQLDelete = "DELETE FROM `users` WHERE id = ?";

    private static final DataSource dataSource = DataSource.getInstance();



    public User getUser(ResultSet rs) throws SQLException {
        Long id = rs.getLong(columnID);
        String name = rs.getString(columnName);
        String login = rs.getString(columnLogin);
        String hashPassword = rs.getString(columnHashPassword);
        String telephoneNumber = rs.getString(columnTelephoneNumber);
        String role = rs.getString(columnRole);

        return new User(id, name, login, hashPassword, telephoneNumber, role);
    }


    public PreparedStatement setUserParams(PreparedStatement prStm,
                                           String name,
                                           String login,
                                           String hashPassword,
                                           String telephoneNumber,
                                           String role) throws SQLException {

        prStm.setString(1, name);
        prStm.setString(2, login);
        prStm.setString(3, hashPassword);
        prStm.setString(4, telephoneNumber);
        prStm.setString( 5, role);

        return prStm;

    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                users.add(getUser(rs));
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
                System.out.print("User table is empty.\n");
            }
        }
        return users;
    }


    @Override
    public int createUser( String name,
                                  String login,
                                  String hashPassword,
                                  String telephoneNumber,
                                  String role) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setUserParams(prStm, name, login, hashPassword, telephoneNumber, role);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
        }


    @Override
    public User findUserById(Long id) {

        User user = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, id);
            rs = prStm.executeQuery();

            rs.next();
            user = getUser(rs);

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
                System.out.print("There is no user with that id.\n");
            }
        }

        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {

        User user = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectByUsernameAndPassword)) {

            prStm.setString(1, login);
            prStm.setString(2, password);
            rs = prStm.executeQuery();

            rs.next();
            user = getUser(rs);

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
                System.out.print("There is no user with that login and password.\n");
            }
        }

        return user;
    }

    @Override
    public User findUserByLogin(String login) {

        User user = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectByUsername)) {

            prStm.setString(1, login);
            rs = prStm.executeQuery();

            rs.next();
            user = getUser(rs);

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
                System.out.print("There is no user with that login.\n");
            }
        }

        return user;
    }


    @Override
    public int updateUserById(Long userId, User newUser) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setUserParams(prStm, newUser.getName(), newUser.getLogin(), newUser.getHashPassword(),
                          newUser.getTelephoneNumber(), newUser.getRole());
            prStm.setLong(6, userId);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no user with that id.\n");
            }
        }
        return rs;
    }


    @Override
    public int deleteUserById(Long id) {

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
