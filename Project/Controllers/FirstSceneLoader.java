package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Common.IO;
import Common.connection;



public class FirstSceneLoader extends VBox implements Initializable{

    @FXML
    Button loginBTN;
    @FXML
    Button signUpBTN;
    @FXML
    Button backBTN;
    @FXML
    ImageView imageView;

    @FXML
    AnchorPane anchorPane;

    @FXML
    VBox vBox = new VBox();

    @FXML
    public void exit() throws IOException {

        sendButtonClickedToServer("exit");

        System.exit(0);
    }

    @FXML
    public void login(ActionEvent event) throws IOException {

        sendButtonClickedToServer("login");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = null;
        if (root != null) {
            scene = new Scene(root, 400, 400);
        }
        stage.setScene(scene);
        stage.show();

    }

    

    @FXML
    public void signUp(ActionEvent actionEvent) throws IOException{

        sendButtonClickedToServer("signup");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if (root != null) {
            scene = new Scene(root, 600, 600);
        }
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle){
        
        try {
            IO.start();
        } catch (Exception e) {
            //we can add a page later
            System.out.println("Client Can not Connect");
        }

    }

    private void sendButtonClickedToServer(String buttonClicked)throws IOException{

        //IO.out.writeObject(buttonClicked);
        connection.ClientSend(IO.out, buttonClicked);

    }
}
