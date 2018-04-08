package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;
import sample.animation.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button AuthSignInButton;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    void initialize() {

        AuthSignInButton.setOnAction(event ->{
            String LoginText = LoginField.getText().trim();
            String LoginPassword = PasswordField.getText().trim();

            if(!LoginText.equals("") && !LoginPassword.equals("")) {
                try {
                    LoginUser(LoginText, LoginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Login and password are empty");
        });

        LoginSignUpButton.setOnAction(event -> {
            openNewScene("/sample/view/SignUp.fxml");
        });

    }

    private void LoginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);

        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while(result.next()){
            counter++;

        }

        if(counter>=1) {
            openNewScene("/sample/view/app.fxml");
        }
        else {
            Shake userLoginAnim = new Shake(LoginField);
            Shake userPassAnim = new Shake(PasswordField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    public void openNewScene(String window){
        LoginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
