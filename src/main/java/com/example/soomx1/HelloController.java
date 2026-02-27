package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {







    @FXML
    private TextField Lab;

    @FXML
    private Label Head_Lable;

    @FXML
    private Label welcomeText;


    @FXML
    private FlowPane productsContainer;




    @FXML
    void clic(ActionEvent event) {

    }





    @FXML
    public void initialize() {

        for (int i = 1; i <= 6; i++) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                Parent card = loader.load();


                ProductCardController controller = loader.getController();

                controller.setData("Vintage Luxury Watch",
                        getClass().getResource("/com/example/soomx1/images/Saudi_Arabia_Ai.jpg").toExternalForm()
                        ,"Rare 1960s timepiece in mint condition with original box");



                        productsContainer.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void move(ActionEvent event)  throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("screen.fxml"));
        Parent root = loader.load();

        ControllerScreen controller = (ControllerScreen) loader.getController();


        Scene scene = new Scene(root);
        String txt = Lab.getText();
       // scene.setUserData(txt);

       // controller.setData(Lab.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();


    }





    @FXML
    void clickbutton(ActionEvent event) {
         welcomeText.setText("Welcome");
         Lab.setText("welcome Afraho");
    }

}
