package mc.apps.spring.jpa;

import javax.persistence.*;

@Entity(name="users")
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;
    private String login;
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "[" +  id +  "] " + this.firstname + " " + this.lastname;
    }

    public User(int id, String firstname, String lastname, String login, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
