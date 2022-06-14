package kpi.iasa.mmsa.taxiservice.dao.interfaces;

import kpi.iasa.mmsa.taxiservice.entities.User;

import java.util.List;

public interface IDaoUser {

    List<User> findAll();

    User findUserById(Long id);

    int createUser(String name,
                   String login,
                   String hashPassword,
                   String telephoneNumber,
                   String role);

    User findUserByLoginAndPassword(String login, String password);

    User findUserByLogin(String login);

    int updateUserById(Long id, User newUser);

    int deleteUserById(Long id);

}
