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



    @FXML

    private Scene scene;
    private Stage stage;
    private Parent root;
//    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
    private Button adminLoginButton;

// signup to login  page




    // from login to admin page

    @FXML
    public void switchtoAdmin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("adman2.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }









    @FXML
    private TextField userNameTextFiled;
    @FXML
    private PasswordField passwordPasswordFiled;
//    public Label warnning;

    public void  loginButtonAction(ActionEvent event){
//        warnning.setText("You need to enter data!");

    }


    @FXML
    private Button cancelButton;



    // cancel login
    public  void  Cancel(ActionEvent event){
        stage= (Stage) cancelButton.getScene().getWindow();


        stage.close();


    }
}
