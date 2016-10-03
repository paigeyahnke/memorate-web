package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by paige on 9/14/16.
 * @author Paige Yahnke
 */
@Entity
@Table(name = "memory")
public class Memory {
    @Column(name = "name")
    private String name;

    @Column(name="rating")
    private int rating;

    @Column(name="image")
    private String imagePath;

    @Column(name="memo")
    private String memo;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private int memoryId;

    /**
     * Instantiates a new memory.
     */
    public Memory() {

    }

    /**
     * Instantiates a new memory.
     *
     * @param name memory name/ title
     * @param rating on scale of 1-5
     */
    public Memory(String name, int rating) {
        this();
        this.name = name;
        this.rating = rating;
    }

    /**
     * Instantiates a new memory.
     *
     * @param name memory name/ title
     * @param rating rating on scale of 1-5
     * @param imagePath path to image
     * @param memo memo to future self
     */
    public Memory(String name, int rating, String imagePath, String memo) {
        this(name, rating);
        this.imagePath = imagePath;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getRatingId() {
        return memoryId;
    }

    public void setRatingId(int memoryId) {
        this.memoryId = memoryId;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "memoryId='" + memoryId + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", imagePath=" + imagePath + '\'' +
                ", memo= " + memo +
                '}';
    }
}