package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

/**
 * Created by paige on 9/14/16.
 * @author Paige Yahnke
 */
@Entity
@Table(name = "memory")
public class Memory {
    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @Column(name = "image")
    private String imageName;

    @Column(name = "memo")
    private String memo;

    @Column(name = "user_name")
    private String username;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int memoryId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memory", fetch = FetchType.EAGER)
    private Set<Tag> tags;

    /**
     * Instantiates a new memory.
     */
    public Memory() {

    }

    /**
     * Instantiates a new memory.
     *
     * @param name memory name/ title
     * @param rating rating on scale of 1-5
     * @param imageName name of the image
     * @param memo memo to future self
     */
    public Memory(String name, int rating, String imageName, String memo, String username) {
        this.name = name;
        this.rating = rating;
        this.username = username;
        this.imageName = imageName;
        this.memo = memo;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imagePath) {
        this.imageName = imagePath;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }



    public String getRatingHtml() {
        String ratingString = "";
        for (int i = 1; i <= 5; i++) {
            ratingString += (i <= rating) ? "&#x2605" : "&#x2606";
        }

        return "<span class='rating'>" + ratingString + "</span>";
    }

    public String getTagList() {
        // Convert list of tags to list of keywords
//        List<String> keywords = tags.stream().map(Tag::getKeyword).collect(Collectors.toList());
//        return String.join(",", keywords);
        if (tags.size() > 0) {
            final String openTag = "<span class='tag'>";
            final String closeTag = "</span>";
            String tagHTML = "";
            for (Tag tag : tags) {
                tagHTML += openTag + tag.getKeyword() + closeTag;
            }

            int endIndex = tagHTML.length() - closeTag.length();
            return tagHTML.substring(0, endIndex);
        } else {
            return "";
        }

    }

    public String getImagePath() {
        return (imageName != null) ? username + "-" + memoryId + "-" + imageName : null;
    }

    @Override
    public String toString() {
        return "ViewMemory{" +
                "memoryId='" + memoryId + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", imagePath=" + imageName + '\'' +
                ", memo= " + memo +
                '}';
    }
}
