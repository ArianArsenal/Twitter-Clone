package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Common.IO;
import Common.connection;



public class SignUpController implements Initializable {
    @FXML
    TextField firstNameTF;
    @FXML
    TextField lastNameTF;
    @FXML
    TextField usernameTF;

    @FXML
    TextField emailTF;

    @FXML
    TextField numberTF;

    @FXML
    PasswordField passwordTF;
    @FXML
    PasswordField confirmTF;
    @FXML
    ChoiceBox<String> country;



    ObservableList<String> temp = FXCollections.observableArrayList("Afghanistan","Albania","Algeria","Andorra",
            "Angola","Antigua & Deps","Argentina","Armenia","Australia","Austria","Azerbaijan","Bahamas","Bahrain",
            "Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia Herzegovina",
            "Botswana","Brazil","Brunei","Bulgaria","Burkina","Burundi","Cambodia","Cameroon","Canada","Cape Verde",
            "Central African Rep","Chad","Chile","China","Colombia","Comoros","Congo","Congo {Democratic Rep}","" +
                    "Costa Rica","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica",
            "Dominican Republic","East Timor","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia"
            ,"Ethiopia","Fiji","Finland","France","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Grenada",
            "Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia",
            "Iran","Iraq","Ireland {Republic}","Israel","Italy","Ivory Coast","Jamaica","Japan","Jordan","Kazakhstan"
            ,"Kenya","Kiribati","Korea North","Korea South","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon",
            "Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macedonia","Madagascar","Malakhestan"
            ,"Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico",
            "Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar, {Burma}","Namibia",
            "Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan",
            "Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Qom",
            "Romania","Russian Federation","Rwanda","St Kitts & Nevis","St Lucia","Saint Vincent & the Grenadines",
            "Samoa","San Marino","Sao Tome & Principe","Senegal","Serbia","Seychelles","Sierra Leone","Singapore",
            "Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Sudan","Spain","Sri Lanka","Sudan"
            ,"Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Togo",
            "Tonga","Trinidad & Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine",
            "United Arab Emirates","United Kingdom","United States","Uruguay","Uzbekistan","Vanuatu","Vatican City",
            "Venezuela","Vietnam","Yemen","Zambia","Zimbabwe");
    @FXML
    Label label;
    @FXML
    CheckBox checkBox;
    @FXML
    Button signUp;
    @FXML
    DatePicker datePicker;


    public void clickSignUp(ActionEvent actionEvent) throws IOException {

        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String confirm = confirmTF.getText();
        String email = emailTF.getText();
        String number = numberTF.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = datePicker.getValue().format(formatter);
        String countryoption = country.getValue();

        boolean terms = checkBox.isSelected();

        // Combine all the data into a single string using a delimiter (e.g., comma)
        String data = firstName + "," + lastName + "," + username + "," + password + "," + confirm + "," + email + "," + number + "," + date + "," + countryoption;

        ///@ Send the combined string
        connection.ClientSend(IO.out, data);


        ///@receives 
        String response = connection.ClientRecieve(IO.in);

        if(response.equals("username-already-exists")){
            label.setText("Username Already Exists");
            label.setTextFill(Color.RED);
        }
        else if(response.equals("password-not-valid")){
            label.setText("Password Not Valid)");
            label.setTextFill(Color.RED);
        }
        else if(response.equals("password-not-match")){
            label.setText("Password Not Match");
            label.setTextFill(Color.RED);

        }
        else if(response.equals("email-not-valid")){
            label.setText("Email Not Valid");
            label.setTextFill(Color.RED);
        }
        else if(response.equals("number-already-exists")){
            label.setText("Number Already Exists");
            label.setTextFill(Color.RED);
        }
        else if(response.equals("enter-email-or-number")){
            label.setText("Enter Email or Number");
            label.setTextFill(Color.RED);
        }
        else if(terms == false){
            label.setText("Please Check Terms and Conditions");
            label.setTextFill(Color.RED);
        }
        else if(response.equals("success")){
            label.setText("Signed Up Successfully");
            label.setTextFill(Color.GREEN);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                
            }


        
            ///@shows timeline scene

            

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        country.setItems(temp);
    }
}
