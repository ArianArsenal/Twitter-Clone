package com.example.firstscene;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Date;

public class PollComponent extends GridPane {
    private VBox nameVBox;
    private HBox pictureAndNameHBox;
    private Circle circle;
    private Label nameLabel;
    private Label usernameLabel;
    private Poll poll;
    private Label date;
    private Label twitterForWindows;
    private Label questionLabel;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;
    private JFXButton submitButton;
    private JFXButton resultsButton;
    private ProgressBar option1ProgressBar;
    private ProgressBar option2ProgressBar;
    private ProgressBar option3ProgressBar;
    private ProgressBar option4ProgressBar;
    private ToggleGroup optionGroup;
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;

    public PollComponent(Poll poll) {
        this.setPadding(new Insets(10,10,10,10));
        this.circle = new Circle(17);
        circle.setFill(new ImagePattern(poll.getUser().getProfileImage()));
        this.usernameLabel = new Label("@" + poll.getUser().getId());
        this.nameLabel = new Label(poll.getUser().getName());
        this.nameVBox = new VBox(1, nameLabel, usernameLabel);
        this.pictureAndNameHBox = new HBox(5,circle, nameVBox);
        nameLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 18));
        nameLabel.setTextFill(Paint.valueOf("#fafafa"));
        usernameLabel.setTextFill(Paint.valueOf("#53545c"));
        usernameLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 10));
        pictureAndNameHBox.setPadding(new Insets(20,20,20,20));
        this.twitterForWindows = new Label("Twitter For Windows");
        this.poll = poll;
        this.date = new Label("2023-06-30");
        this.setStyle("-fx-border-color: #14193d; -fx-border-radius: 30; -fx-background-radius: 30; -fx-background-color: #171616");
        // set up the view components
        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);
        questionLabel = new Label(poll.getQuestion());
        questionLabel.setTextFill(Color.WHITE);
        questionLabel.setFont(Font.font("System", FontWeight.BLACK, 12));
        option1RadioButton = new RadioButton(poll.getOption1());
        option1RadioButton.setTextFill(Color.WHITE);
        option2RadioButton = new RadioButton(poll.getOption2());
        option2RadioButton.setTextFill(Color.WHITE);
        option3RadioButton = new RadioButton(poll.getOption3());
        option3RadioButton.setTextFill(Color.WHITE);
        option4RadioButton = new RadioButton(poll.getOption4());
        option4RadioButton.setTextFill(Color.WHITE);
        optionGroup = new ToggleGroup();
        option1RadioButton.setToggleGroup(optionGroup);
        option2RadioButton.setToggleGroup(optionGroup);
        option3RadioButton.setToggleGroup(optionGroup);
        option4RadioButton.setToggleGroup(optionGroup);
        HBox optionBox = new HBox(10, option1RadioButton, option2RadioButton, option3RadioButton, option4RadioButton);
        submitButton = new JFXButton("Submit");
        submitButton.setStyle("-fx-background-color:#232957");
        resultsButton = new JFXButton("Show Results");
        resultsButton.setStyle("-fx-background-color:#232957");
        submitButton.setTextFill(Color.WHITE);
        resultsButton.setTextFill(Color.WHITE);
        option1ProgressBar = new ProgressBar();
        option2ProgressBar = new ProgressBar();
        option3ProgressBar = new ProgressBar();
        option4ProgressBar = new ProgressBar();
        option1ProgressBar.setPrefWidth(200);
        option2ProgressBar.setPrefWidth(200);
        option3ProgressBar.setPrefWidth(200);
        option4ProgressBar.setPrefWidth(200);
        option1ProgressBar.setProgress(0);
        option2ProgressBar.setProgress(0);
        option3ProgressBar.setProgress(0);
        option4ProgressBar.setProgress(0);
        twitterForWindows.setTextFill(Paint.valueOf("#106bcc"));
        // add the view components to the grid pane
        label1 = new Label(poll.getOption1());
        label1.setTextFill(Color.WHITE);
        label2 = new Label(poll.getOption2());
        label2.setTextFill(Color.WHITE);
        label3 = new Label(poll.getOption3());
        label3.setTextFill(Color.WHITE);
        label4 = new Label(poll.getOption4());
        label4.setTextFill(Color.WHITE);
        date.setFont(Font.font("system", FontWeight.BLACK, 9));
        date.setTextFill(Paint.valueOf("#53545c"));
        add(pictureAndNameHBox, 0, 0);
        add(questionLabel, 0, 1, 2, 1);
        add(optionBox, 0, 2, 2, 1);
        add(submitButton, 0, 3);
        add(resultsButton, 1, 3);
        add(label1, 0, 4);
        add(option1ProgressBar, 1, 4);
        add(label2, 0, 5);
        add(option2ProgressBar, 1, 5);
        add(label3, 0, 6);
        add(option3ProgressBar, 1, 6);
        add(label4, 0, 7);
        add(option4ProgressBar, 1, 7);
        add(date, 0, 8);
        add(twitterForWindows, 1, 8);
        setAction();
    }
    public void setAction(){
        option1RadioButton.setOnAction(event -> {
            poll.incrementOption1Votes();
        });

        option2RadioButton.setOnAction(event -> {
            poll.incrementOption2Votes();
        });

        option3RadioButton.setOnAction(event -> {
            poll.incrementOption3Votes();
        });

        option4RadioButton.setOnAction(event -> {
            poll.incrementOption4Votes();
        });
        submitButton.setOnAction(event -> {
            RadioButton selectedOption = (RadioButton) optionGroup.getSelectedToggle();
            if (selectedOption != null) {
                if (selectedOption.equals(option1RadioButton)) {
                    poll.incrementOption1Votes();
                } else if (selectedOption.equals(option2RadioButton)) {
                    poll.incrementOption2Votes();
                } else if (selectedOption.equals(option3RadioButton)) {
                    poll.incrementOption3Votes();
                } else if (selectedOption.equals(option4RadioButton)) {
                    poll.incrementOption4Votes();
                }
                option1RadioButton.setDisable(true);
                option2RadioButton.setDisable(true);
                option3RadioButton.setDisable(true);
                option4RadioButton.setDisable(true);
            }
        });
        resultsButton.setOnAction(event -> {
            int totalVotes = poll.getTotalVotes();
            double option1Percentage = (double) poll.getOption1Votes() / totalVotes;
            double option2Percentage = (double) poll.getOption2Votes() / totalVotes;
            double option3Percentage = (double) poll.getOption3Votes() / totalVotes;
            double option4Percentage = (double) poll.getOption4Votes() / totalVotes;
            option1ProgressBar.setProgress(option1Percentage);
            option2ProgressBar.setProgress(option2Percentage);
            option3ProgressBar.setProgress(option3Percentage);
            option4ProgressBar.setProgress(option4Percentage);
        });
    }

    public RadioButton getOption2RadioButton() {
        return option2RadioButton;
    }

    public RadioButton getOption3RadioButton() {
        return option3RadioButton;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getResultsButton() {
        return resultsButton;
    }

    public ProgressBar getOption1ProgressBar() {
        return option1ProgressBar;
    }

    public ProgressBar getOption2ProgressBar() {
        return option2ProgressBar;
    }

    public ProgressBar getOption3ProgressBar() {
        return option3ProgressBar;
    }
}
