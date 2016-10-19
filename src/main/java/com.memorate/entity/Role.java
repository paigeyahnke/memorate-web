package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by paige on 10/19/16.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    public Role() {
    }

    public Role(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
