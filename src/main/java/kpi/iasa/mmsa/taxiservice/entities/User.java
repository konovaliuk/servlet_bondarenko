package kpi.iasa.mmsa.taxiservice.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private Long id;
    private String name;
    private String login;
    private String hashPassword;
    private String telephoneNumber;
    private String role;

    public User(Long id, String name, String login, String hashPassword, String telephoneNumber, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.hashPassword = hashPassword;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getRole() { return role; }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setRole(String role) { this.role = role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(login, user.login) &&
                Objects.equals(hashPassword, user.hashPassword) && Objects.equals(telephoneNumber, user.telephoneNumber) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, hashPassword, telephoneNumber, role);
    }
}
