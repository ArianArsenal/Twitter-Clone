package com.example.firstscene;

import javafx.scene.image.Image;

import java.nio.file.Path;

public class User {
    private String name;
    private String id;
    private Image profileImage;
    private Image header;


    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public Image getHeader() {
        return header;
    }

    public void setHeader(Image header) {

        this.header = header;
    }

    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User(String name, String id, Image profileImage) {
        this.name = name;
        this.id = id;
        this.profileImage = profileImage;
        if (profileImage == null) {
            this.profileImage = new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\5.png").toUri().toString());
        }

        this.header = new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\twitter-banner (5).jpg").toUri().toString());
        this.bio = "";
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Image getProfileImage() {
        return profileImage;
    }
}
