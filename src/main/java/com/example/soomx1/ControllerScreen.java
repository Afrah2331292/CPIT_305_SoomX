package com.example.soomx1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerScreen {

   private Stage stage;
   private String data;





    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button displayy;


   @FXML
   private Button back;


    @FXML
    public void buttonBck(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }
}
