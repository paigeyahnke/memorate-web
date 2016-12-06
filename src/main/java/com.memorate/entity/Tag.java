package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;

/**
 * Created by paige on 10/19/16.
 */
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "keyword")
    private String keyword;


    @ManyToOne(cascade = CascadeType.ALL)
    private Memory memory;

    public Tag() {

    }

    public Tag(String keyword, Memory memory) {
        this.keyword = keyword;
        this.memory = memory;
    }

    public int getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

}
