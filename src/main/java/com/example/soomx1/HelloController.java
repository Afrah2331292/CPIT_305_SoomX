package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.*;

public class HelloController  {








    @FXML
    private TextField Lab;

    @FXML
    private Label Head_Lable;

    @FXML
    private Label welcomeText;


    @FXML
    private FlowPane productsContainer;

    @FXML
    private Spinner<Integer> Spinner_Price_Min_Teller;

    SpinnerValueFactory<Integer> SVF
             = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,11,1);


    @FXML
    void clic(ActionEvent event) {

    }





    @FXML
    public void initialize() {
        BufferedReader br=null;
        try {
            InputStream input = getClass().getResourceAsStream(
                    "/com/example/soomx1/Products_Info.txt"
            );

            br = new BufferedReader(new InputStreamReader(input));
            String productLine;

            while ((productLine = br.readLine()) != null) {

                String[] parts = productLine.split("\\|");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                Parent card = loader.load();

                ProductCardController controller = loader.getController();

                controller.setData(
                        parts[1], // name
                        getClass().getResource(parts[0] + ".jpg").toExternalForm(), // image
                        parts[2], // description
                        "Current Bid", // ثابت
                        "Total Bids",  // ثابت
                        parts[3], // price
                        parts[4]  // bids count
                );

                productsContainer.getChildren().add(card);
            }

        } catch (IOException e) {
            e.printStackTrace();
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
