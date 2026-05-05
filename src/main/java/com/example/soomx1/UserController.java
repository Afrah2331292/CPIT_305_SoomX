package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import soomXDatabase.DBConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {


    @FXML
    private TextField Lab;

   @FXML
   private TextField Active_Auctions_text;



    @FXML
    private FlowPane productsContainer;




    @FXML
    void clic(ActionEvent event) {

    }


    @FXML

    public void initialize() {



            try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Product");
                ResultSet rs = ps.executeQuery();
                InputStream input = getClass().getResourceAsStream("/com/example/soomx1/Products_Info.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(input))
        ) {

            String productLine;

            while (rs.next() && (productLine = br.readLine()) != null) {

                // من الداتابيس
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                // من الملف فقط للصورة
                String[] parts = productLine.split("\\|");
                String imagePath = getClass()
                        .getResource(parts[0] + ".jpg")
                        .toExternalForm();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                Parent card = loader.load();

                ProductCardController controller = loader.getController();


                controller.setData(
                        name,                    // من DB
                        imagePath,               // من الملف
                        description,             // من DB
                        "Current Bid",
                        "Total Bids",
                        String.valueOf(price),   // من DB
                        "0"
                );

                controller.setProductID(id);

                productsContainer.getChildren().add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void Active_Auctions(){
        Active_Auctions_text.setText("d");
    }


    @FXML
    void Log_Out_Action(ActionEvent event) throws IOException{


        Parent root = FXMLLoader.load(Main.class.getResource("SignUp.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}