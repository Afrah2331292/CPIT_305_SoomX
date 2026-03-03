package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpController {



    @FXML

    private Button cancelButton;
    private Scene scene;
    private Stage stage;
    private Parent root;




    public void AdminLogin(ActionEvent event) throws IOException {

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

        Parent root = FXMLLoader.load(Main.class.getResource("hello-view.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 950, 700));
        stage.centerOnScreen();
        stage.show();
    }

    // cancel login
    public  void  Cancel(ActionEvent event){
        stage= (Stage) cancelButton.getScene().getWindow();


        stage.close();


    }



}
