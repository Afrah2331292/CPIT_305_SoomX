package com.example.soomx1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import soomXDatabase.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminController {
    @FXML private HBox productsBar;
    @FXML private Label selectedProductLabel;





    @FXML private TableView<Bid> tableView;

    // Number of rows the table should always display
    private static final int DESIRED_ROWS = 10;

    // Runs automatically after FXML loads
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductAdmainCard.fxml"));
                Parent card = loader.load();

                ProductAdmainCardController controller = loader.getController();

                controller.setData(
                        name,                    // من DB
                        imagePath,               // من الملف
                        description
                );

                controller.setProductID(id);

                productsBar.getChildren().add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    @FXML
    private void goToSignIn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}

