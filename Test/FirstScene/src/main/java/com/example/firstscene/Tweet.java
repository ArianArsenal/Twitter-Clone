package com.example.firstscene;

import javafx.scene.image.Image;

import java.util.Date;

public class Tweet {
    private User user;
    private boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public User getUser() {
        return user;
    }

    private Date date;
    private int likes;
    private int retweets;
    private int quotes;
    private String text;
    private Image image;

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public int getQuotes() {
        return quotes;
    }

    public String getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    public Tweet(User user, Date date, int likes, int retweets, int quotes, String text, Image image) {
        this.user = user;
        this.date = date;
        this.likes = likes;
        this.retweets = retweets;
        this.quotes = quotes;
        this.text = text;
        this.image = image;
    }
}
