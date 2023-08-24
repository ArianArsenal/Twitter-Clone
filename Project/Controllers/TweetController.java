package Controllers;


import com.jfoenix.controls.JFXButton;

import Common.IO;
import Common.connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class TweetController implements Initializable {
    @FXML
    private JFXButton fileChooserButton;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton submitButton;
    @FXML
    private TextArea textArea = new TextArea();
    @FXML
    private Label label;


    private File selectedFile;   //this field gets the selected file from fileChooserButton (Image)



    @FXML
    private void onFileChooserButtonClick(ActionEvent event) {  //button clicked to choose file for Image

        ///@ conditions should be here  ??

        FileChooser fileChooser = new FileChooser();
        label.setText("");

        this.selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile.getName().endsWith(".png") || selectedFile.getName().endsWith(".jpg") || selectedFile.getName().endsWith(".jpeg")){

            label.setText("File Selected");
            label.setTextFill(Color.GREEN);

            //File selectedFile = fileChooser.showOpenDialog(null);
        }
        else {
            label.setText("File format not correct!");
            label.setTextFill(Color.RED);
        }

    }

    @FXML
    public void submitClicked(ActionEvent actionEvent) throws InterruptedException, IOException {   //button clicked to tweet

        //!TEST
        //User user = new User("Akbar", "Akbar123", "Test", null, "gmail@gmail.com", null, null, 0, 0, 0, null);
        
        
        if (textArea.getText().isEmpty()){
            label.setText("Text cannot be Empty");
            label.setTextFill(Color.RED);
        }

        else {

            //Text + Image tweets
            if (selectedFile != null) {

                String tweetText = textArea.getText();
                String image = selectedFile.getPath();

                String datas = tweetText + "$" + image;

                ///@ Sends tweet detail to server
                connection.ClientSend(IO.out, datas);

            }

            //Only text tweets
            else {

                String tweetText = textArea.getText();

                String datas = tweetText +"$" + null;
                
                ///@ Sends tweet detail to server
                connection.ClientSend(IO.out, datas);

                //Tweet tweet = new Tweet(textArea.getText(), null, null, null, null);
                //HelloApplication.tweets.add(tweet);

            }

            label.setText("Successful!");
            label.setTextFill(Color.GREEN);

            //Thread.sleep(2500); // delays the execution for 2.5 seconds
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("meow.fxml"));
            Parent root = null;


            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = null;

            if(root != null) {
                scene = new Scene(root,800,600);
            }

            stage.setScene(scene);
            stage.show();
        }
    
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageView.setImage(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\twitter-icon-download-18.png").toUri().toString()));
    }
}
