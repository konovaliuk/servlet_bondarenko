package kpi.iasa.mmsa.taxiservice;

import com.zaxxer.hikari.HikariDataSource;
import kpi.iasa.mmsa.taxiservice.DTO.DriverDTO;
import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.DAOFactory;
import kpi.iasa.mmsa.taxiservice.dao.DaoRide;
import kpi.iasa.mmsa.taxiservice.dao.DaoUser;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoCar;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoDriver;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoRide;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoUser;
import kpi.iasa.mmsa.taxiservice.entities.*;
import kpi.iasa.mmsa.taxiservice.services.DriverService;
import kpi.iasa.mmsa.taxiservice.services.RideService;
import kpi.iasa.mmsa.taxiservice.services.UserService;

import java.nio.file.FileStore;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Runner {

   /* public static void main(String[] args) {

        IDaoUser daoUser = DAOFactory.createDaoUser();

        List<User> allUsers = daoUser.findAll();
        System.out.print("findAll() function: \n");
        for (User allUser : allUsers) {
            System.out.format("%s\t%s\n", allUser.getName(), allUser.getLogin());
        }

        System.out.print("\nfindUserById() function: \n");
        User user = daoUser.findUserById(2L);
        System.out.format("%s\t%s\n", user.getName(), user.getLogin());

        System.out.print("\ncreateUser() function: \n");
        int rs1 = daoUser.createUser("Maks Koval", "MultiSmith", "8a364eb03fcc0d997edba9cff4ea3778",
                "+380995678901", 24L, "1111222233334444", "123");
        System.out.format("rs = %d\n", rs1);

        System.out.print("\nupdateUserById() function: \n");
        User updatedUser = new User(1L, "Daniil Bondarenko", "danbond02", "e958f98ec7d548caa2d5c5c109111243",
                "+380980000000", 25L, "1111000022223333", "388");
        int rs2 = daoUser.updateUserById(1L, updatedUser);
        System.out.format("rs = %d\n", rs2);

        // исправить базу(наследование таблиц); проверить закрыввается ли result set при закрытии коннекшена

        /*System.out.print("\ndeleteUserById() function: \n");
        int rs3 = daoUser.deleteUserById(3L);
        System.out.format("rs = %d\n", rs3);*/

        /*IDaoCar daoCar = DAOFactory.createDaoCar();
        List<Car> allCars = daoCar.findAll();
        for (Car allCarr : allCars) {
            System.out.format("%s\t%s\n", allCarr.getCarNumber(), allCarr.getModel());
        }

        int rs1 = daoCar.createCar("AA2427BB", "BMW X5", "black", CarTypeEnum.BUISNESS, 65.0F,4L);
        System.out.format("rs = %d", rs1);*/

        /*IDaoRide daoRide = DAOFactory.createDaoRide();
        List<Ride> allRides = daoRide.findAll();
        for (Ride allRide : allRides) {
            System.out.format("%s\t%s\n", allRide.getDeparturePoint(), allRide.getDestination());
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Ride updateRide = new Ride(1L, 1L, 2L, "str.Ivana-Franka 14", "str.Avramenka 90",
                "str.Pochtova 34", 23.0F, 24.0F, timestamp,
                timestamp, RideStatusEnum.COMPLETED);
        int rs4 = daoRide.updateRideById(1l, updateRide);
        System.out.format("rs = %d", rs4);*/

       /* try{
            DataSource.closeConnection();
            System.out.print("\nConnection was successfully closed\n");
        }
        catch (SQLException e){
            System.err.print(e.getMessage());
        }

    }*/
       public static void main(String[] args) {

           UserService userService = new UserService();
           System.out.print(userService.isLogged("architect0r", "waiTForIt"));


       }
}