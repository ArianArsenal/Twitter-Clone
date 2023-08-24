//package com.example.firstscene;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Separator;
//import javafx.scene.image.Image;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.shape.Circle;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Base64;
//
//public class viewUser extends AnchorPane {
//    static Stage stage;
//    Label username;
//    Circle ProfileCircle;
//    Label bio;
//    Button follow;
//    Separator separator1;
//    Separator separator2;
//    User user;
//    User userMain;
//    // ArrayList<User> followerList;
//// ArrayList<User> followingList;
//    public viewUser(User userMain,User user) throws IOException, ClassNotFoundException {
//        this.userMain = userMain;
//        this.user = user;
//        username = new Label(user.getId());
//        IO.out.writeObject(user);
//        bio = new Label((String) user.getBio());
//        IO.out.writeObject("check follow");
//        IO.out.writeObject(userMain);
//        IO.out.writeObject(user);
//        if ((Boolean)IO.in.readObject()){
//            follow = new Button("following");
//        }else {
//            follow = new Button("follow");
//        }
//        ProfileCircle = new Circle(25);
//        //byte[] receivedImageInByte = Base64.getDecoder().decode(tweet.getImageInByte());
//        ProfileCircle.setStroke(Color.BLACK);
//        ProfileCircle.setFill(Color.BLACK);
//        IO.out.writeObject("send Profile");
//        IO.out.writeObject(this.user);
//        String receivedImage =(String) IO.in.readObject();
//        byte[] imageData = Base64.getDecoder().decode(receivedImage);
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
//        Image image = new Image(inputStream);
//        ProfileCircle.setFill(new ImagePattern(image));
//        separator1 = new Separator();
//        separator2 = new Separator();
//        this.getChildren().addAll(ProfileCircle,username,separator1,separator2,bio,follow);
//        setConfig();
//        setLocation();
////    IO.out.writeObject("send followers");
////    IO.out.writeObject(user);
////    followerList = (ArrayList<User>) IO.in.readObject();
////    IO.out.writeObject("send following");
////    IO.out.writeObject(user);
////    followingList = (ArrayList<User>) IO.in.readObject();
//    }
//    public void setLocation(){
//        AnchorPane.setTopAnchor(ProfileCircle,0.0);
//        AnchorPane.setLeftAnchor(ProfileCircle,30.0);
//        AnchorPane.setTopAnchor(username,0.0);
//        AnchorPane.setLeftAnchor(username,90.0);
//        AnchorPane.setTopAnchor(bio,25.0);
//        AnchorPane.setLeftAnchor(bio,90.0);
//        AnchorPane.setTopAnchor(separator1,60.0);
//        AnchorPane.setLeftAnchor(separator1,0.0);
//        AnchorPane.setTopAnchor(separator2,60.0);
//        AnchorPane.setLeftAnchor(separator2,0.0);
//        AnchorPane.setTopAnchor(follow,20.0);
//        AnchorPane.setLeftAnchor(follow,500.0);
//    }
//    public void setConfig(){
//        this.setPrefSize(580,60);
//        this.setMinSize(580,60);
//        this.setMaxSize(580,60);
//        this.setStyle("-fx-background-color:#FFFFFF;");
//        username.setFont(Font.font("Roboto-Bold", FontWeight.BOLD,24));
//        username.setStyle("-fx-text-fill:#000000;");
//        separator1.setPrefSize(580.0,1.0);
//        separator1.setStyle("-fx-background-color:#000000;");
//        separator2.setPrefSize(580.0,1.0);
//        separator2.setStyle("-fx-background-color:#000000;");
//        separator1.setOpacity(0.15);
//        separator2.setOpacity(0.15);
//        bio.setFont(Font.font("Roboto",FontWeight.NORMAL,24));
//        bio.setStyle("-fx-text-fill:#000000;");
//        follow.setStyle("-fx-text-fill:#1DA1F2;");
//        follow.setPrefSize(80,30);
//    }
////    public void setAction(){
////        ProfileCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
////            @Override
////            public void handle(MouseEvent mouseEvent) {
////                try {
////                    IO.out.writeObject("sendUsers");
////                    IO.out.writeObject(userMain);
////                    IO.out.writeObject(user);
////                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("othersProfile.fxml"));
////                    Scene scene = new Scene(fxmlLoader.load());
////                    stage.setTitle("twitter2.0");
////                    Image icon = new Image("D:/apps/twitter2.0/Client/src/main/resources/com/example/client/download.png");
////                    stage.getIcons().add(icon);
////                    stage.setScene(scene);
////                    stage.show();
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
////            }
////        });
////        follow.setOnAction(new EventHandler<ActionEvent>() {
////            @Override
////            public void handle(ActionEvent event) {
////                try {
////                    IO.out.writeObject("check follow");
////                    IO.out.writeObject(userMain);
////                    IO.out.writeObject(user);
////                    if ((Boolean) IO.in.readObject()){
////                        IO.out.writeObject("unfollow");
////                        IO.out.writeObject(userMain);
////                        IO.out.writeObject(user);
////                        follow.setText("follow");
////                    }else {
////                        IO.out.writeObject("follow");
////                        IO.out.writeObject(userMain);
////                        IO.out.writeObject(user);
////                        follow.setText("following");
////                        System.out.println("followed");
////                    }
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                } catch (ClassNotFoundException e) {
////                    throw new RuntimeException(e);
////                }
////            }
////        });
////    }
//}
