package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Represents a user
 * @author Paige Yahnke
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    /**
     * Creates a user
     */
    public User() {
    }

    /**
     * Creates a user
     * @param userName username
     * @param password password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Getter for the username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the username
     * @param userName username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the user first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the user first name
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the user last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
