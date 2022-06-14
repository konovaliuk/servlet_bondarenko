package kpi.iasa.mmsa.taxiservice.connection;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = null;//new HikariConfig("/database.properties");
    private static HikariDataSource ds = null; //new HikariDataSource(config);
    private static DataSource dataSource = null;

    private DataSource() {
        config = new HikariConfig("/database.properties");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        //config.setDriverClassName("com.mysql.jdbc.Driver");
        //ds = new HikariDataSource(config);
        return ds.getConnection();
    }

    public static DataSource getInstance(){
        if(dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    public static void closeConnection() throws SQLException{
        ds.close();
    }
}
