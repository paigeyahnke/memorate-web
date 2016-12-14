package com.memorate.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

/**
 * Represents one memory
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

    /**
     * Getter for memory id
     * @return memory id
     */
    public int getMemoryId() {
        return memoryId;
    }

    /**
     * Setter for memory id
     * @param memoryId memory id
     */
    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    /**
     * Getter for memory name
     * @return memory name/ title
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for memory name
     * @param name memory name/ title
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for memory rating
     * @return rating for memory
     */
    public int getRating() {
        return rating;
    }

    /**
     * Setter for memory rating
     * @param rating rating for mememory
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Getter for image name
     * @return image name
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Setter for image name
     * @param imagePath image name
     */
    public void setImageName(String imagePath) {
        this.imageName = imagePath;
    }

    /**
     * Getter for memory memo
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Setter fo memory memo
     * @param memo memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * Getter for username
     * @return username of user who created memory
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username username of user who created memory
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for memory tags
     * @return set of associated tags
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Setter for memory tags
     * @param tags set of associated tags
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Returns html with rating stars
     * @return
     */
    public String getRatingHtml() {
        String ratingString = "";
        for (int i = 1; i <= 5; i++) {
            ratingString += (i <= rating) ? "&#x2605" : "&#x2606";
        }

        return "<span class='rating'>" + ratingString + "</span>";
    }

    /**
     * Returns a list of tags formatted as html
     * @return
     */
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

    /**
     * Returns full image name
     * @return username + memory id + image name
     */
    public String getFullImageName() {
        return (imageName != null) ? username + "-" + memoryId + "-" + imageName : null;
    }

    /**
     * Returns a representation of the memory
     * @return string representing memory
     */
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
