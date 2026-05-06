package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import soomXDatabase.UserDAO;

import java.io.IOException;

public class signUpController {

    @FXML
    private TextField passwordPasswordFiled11;

    @FXML
    private TextField EmailTextFiled;

    @FXML
    private TextField phonenumberTextFiled;

    @FXML
    private TextField userNameTextFiled;

    @FXML

    private Button cancelButton;
    private Scene scene;
    private Stage stage;
    private Parent root;




    public void Login(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("login.fxml"));

        root = fxmlLoader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    // from login to user page
    @FXML
    public void switchtoUser(ActionEvent event) throws IOException {
        String email = EmailTextFiled.getText();
        String password = passwordPasswordFiled11.getText();
        String phone = phonenumberTextFiled.getText();
        String username = userNameTextFiled.getText();

        if (validateInput(email, password, phone)) {
            UserDAO.insertUser(username, email, phone, password, 2);



            Parent root = FXMLLoader.load(Main.class.getResource("hello-view.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 950, 700));
            stage.centerOnScreen();
            stage.show();
        }


        }








    public boolean validateInput(String email, String password, String phone) {
        // Check for the email
        if (!email.endsWith("@gmail.com")) {
            showAlert("Email must be a Gmail (@gmail.com)");
            return false;
        }


        // Password check if it is equals 4 elements
        if (password.length() < 4) {
            showAlert("Password must be at least 4 characters");
            return false;
        }

        // Phone number must start with "05" and has 10 degits;
        if (!phone.matches("05\\d{8}")) {
            showAlert("Phone must be 10 digits and start with 05");
            return false;
        }

        return true;
    }
 // Show alert message
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
