package com.example.firstscene;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ProfileComponent extends HBox {

    private ImageView avatarImageView;
    private Label usernameLabel;
    private Label nameLabel;
    private Label bioLabel;

    public ProfileComponent(Image avatarUrl, String username, String name, String bio) {
        avatarImageView = new ImageView(avatarUrl);
        avatarImageView.setFitWidth(50);
        avatarImageView.setFitHeight(50);
        avatarImageView.setPreserveRatio(true);

        usernameLabel = new Label(username);
        nameLabel = new Label(name);
        bioLabel = new Label(bio);

        HBox.setMargin(avatarImageView, new Insets(5, 10, 5, 5));
        HBox.setMargin(usernameLabel, new Insets(5, 5, 0, 0));
        HBox.setMargin(nameLabel, new Insets(0, 5, 5, 0));
        HBox.setMargin(bioLabel, new Insets(0, 5, 6, 0));

        getChildren().addAll(avatarImageView, usernameLabel, nameLabel, bioLabel);
    }
}
