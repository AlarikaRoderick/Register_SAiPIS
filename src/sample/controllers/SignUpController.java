package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseHandler;
import sample.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField SignUpName;

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUpCountry;

    @FXML
    private CheckBox SignUpCheckBoxMale;

    @FXML
    private CheckBox SignUpCheckBowFemale;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(event -> {


        signUpNewUser();

        });

    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String FirstName = SignUpName.getText();
        String LastName = SignUpLastName.getText();
        String UserName = LoginField.getText();
        String Password = PasswordField.getText();
        String Location = SignUpCountry.getText();
        String Gender = "";

        if(SignUpCheckBoxMale.isSelected())
            Gender = "Male";
        else Gender = "Female";

        User user = new User(FirstName, LastName, UserName, Password, Location, Gender);

        dbHandler.SignUpUser(user);
    }
}

