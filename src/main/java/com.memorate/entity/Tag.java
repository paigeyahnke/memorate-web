package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;

/**
 * Represents one tag for a memory
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

    /**
     * Creates a tag
     */
    public Tag() {

    }

    /**
     * Creates a tag
     * @param keyword keyword/ tag
     * @param memory associated memory
     */
    public Tag(String keyword, Memory memory) {
        this.keyword = keyword;
        this.memory = memory;
    }

    /**
     * Getter for tag id
     * @return tag id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for keyword
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Setter for keyword
     * @param keyword keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Getter for memory
     * @return associated memory
     */
    public Memory getMemory() {
        return memory;
    }

    /**
     * Setter for memory
     * @param memory associated memory
     */
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

}
