package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by paige on 10/19/16.
 */
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "memory_id")
    private int memoryId;

    public Tag() {

    }

    public Tag(String keyword, int memoryId) {
        this.keyword = keyword;
        this.memoryId = memoryId;
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

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }
}
