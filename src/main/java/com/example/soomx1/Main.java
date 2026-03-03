package com.example.soomx1;
/*
*هذا الكلاس يتعامل مع الواجههات
*
*
* */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setResizable(false);
        stage.getIcons().clear();
        stage.setScene(scene);
        stage.show();
    }
}
