package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Represents one user role
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    /**
     * Creates a user role
     */
    public Role() {
    }

    /**
     * Creates a user role
     * @param name role name
     * @param userName username
     */
    public Role(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    /**
     * Getter for the role id
     * @return role id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for role id
     * @param id role id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for role name
     * @return name of role
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for role name
     * @param name name of role
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for username
     * @param userName username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
