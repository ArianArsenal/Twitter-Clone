package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import Common.IO;
import Common.connection;



public class SecondSceneController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView imageView;

    @FXML
    Label label;

    @FXML
    VBox vBox;

    @FXML
    TextField usernameTF;
    @FXML
    PasswordField passwordTF;

    @FXML
    Button loginBTN;

    @FXML
    public void buttonClicked() throws IOException{

        String tempUser = usernameTF.getText();
        String tempPass = passwordTF.getText();

        String data = tempUser + "," + tempPass;

        connection.ClientSend(IO.out, data);

        String condition = connection.ClientRecieve(IO.in);

        if(condition.equals("not-found")){
            label.setText("Username not found");
            label.setTextFill(Color.RED);
        }

        else if (condition.equals("wrong-pass")){
            label.setText("Wrong Password");
            label.setTextFill(Color.RED);
        }
        else if(condition.equals("success")){

            label.setText("Logged in");
            label.setTextFill(Color.GREEN);

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {
                System.err.println("Exception occurred");
            }

            ///@show the timeline scene

        }
        
    }


    @FXML
    public void exit(ActionEvent actionEvent)throws IOException{

        connection.ClientSend(IO.out, "return");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,600,600);
        }
        stage.setScene(scene);
        stage.show();
    }
}
