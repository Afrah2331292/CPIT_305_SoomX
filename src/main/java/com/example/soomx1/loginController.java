package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    private Stage stage;
    private Parent root;
    private Scene scene;



    @FXML
    private TextField userNameTextFiled;
    @FXML
    private PasswordField passwordPasswordFiled;
//    public Label warnning;


//    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
    private Button adminLoginButton;

// signup to login  page




    // from login to admin page

    @FXML
    public void switchtoAdmin(ActionEvent event) throws IOException {

        String username = userNameTextFiled.getText();
        String password = passwordPasswordFiled.getText();

        if (soomXDatabase.UserDAO.login(username, password)) {

            Session.currentUsername = username;

            System.out.println("Logged in as: " + username);
            int roleValue = soomXDatabase.UserDAO.getRole(username);


            // The Admain his role is equal to 1
            if(roleValue == 1){
                Parent root = FXMLLoader.load(Main.class.getResource("adman2.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 950, 700));
                stage.centerOnScreen();
                stage.show();

              // The Normal User his role equal to 2
            } else if (roleValue == 2) {
                Parent root = FXMLLoader.load(Main.class.getResource("hello-view.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 950, 700));
                stage.centerOnScreen();
                stage.show();

            }
        } else {
            System.out.println("❌ Wrong username or password");
        }
    }



//    public Label warnning;

    public void  loginButtonAction(ActionEvent event){
//        warnning.setText("You need to enter data!");

    }


    @FXML
    private Button cancelButton;



    // cancel login
    public  void  switchtoSignin(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("signUp.fxml"));

        root = fxmlLoader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();


    }
}
