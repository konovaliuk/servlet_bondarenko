package kpi.iasa.mmsa.taxiservice.services;

import kpi.iasa.mmsa.taxiservice.connection.DataSource;
import kpi.iasa.mmsa.taxiservice.dao.DAOFactory;
import kpi.iasa.mmsa.taxiservice.dao.DaoPassenger;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoDriver;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoPassenger;
import kpi.iasa.mmsa.taxiservice.dao.interfaces.IDaoUser;
import kpi.iasa.mmsa.taxiservice.entities.User;

import java.sql.SQLException;

public class UserService {

    public boolean registerUser(String name,
                                String username,
                                String password,
                                String telephoneNumber,
                                String role) {

        IDaoUser daoUser = DAOFactory.createDaoUser();
        int rs = daoUser.createUser(name, username, password, telephoneNumber, role);

        return rs == 1;
    }

    public boolean isLogged(String login, String password){
        IDaoUser daoUser = DAOFactory.createDaoUser();
        User user = daoUser.findUserByLoginAndPassword(login, password);
        return !(user == null);
    }

    public Long getUserId(String login){
        IDaoUser daoUser = DAOFactory.createDaoUser();
        User user = daoUser.findUserByLogin(login);
        return user.getId();
    }

    public User getUserById(Long id){
        IDaoUser daoUser = DAOFactory.createDaoUser();
        return daoUser.findUserById(id);
    }

    public User getUserByLogin(String login){
        IDaoUser daoUser = DAOFactory.createDaoUser();
        return daoUser.findUserByLogin(login);
    }
}
