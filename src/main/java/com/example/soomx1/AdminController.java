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

    @FXML private Label highestBidLabel;
    @FXML private Label totalBidsLabel;

    @FXML private Label highestBidLabel2;
    @FXML private Label totalBidsLabel2;

    @FXML private Label highestBidLabel3;
    @FXML private Label totalBidsLabel3;

    @FXML private DatePicker product1EndDatePicker;
    @FXML private TextField product1TimeField;

    @FXML private DatePicker product2EndDatePicker;
    @FXML private TextField product2TimeField;

    @FXML private DatePicker product3EndDatePicker;
    @FXML private TextField product3TimeField;

    @FXML private ImageView productImage1;
    @FXML private ImageView productImage2;
    @FXML private ImageView productImage3;

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
                        description,             // من DB
                        "Current Bid",
                        "Total Bids",
                        String.valueOf(price),   // من DB
                        "0"
                );

                controller.setProductID(id);

                productsBar.getChildren().add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Change label when Product 1 is clicked
    @FXML
    private void selectProduct1(MouseEvent event) {
        selectedProductLabel.setText("White Rare Falcon");
    }

    // Change label when Product 2 is clicked
    @FXML
    private void selectProduct2(MouseEvent event) {
        selectedProductLabel.setText("Desert Camel");
    }

    // Change label when Product 3 is clicked
    @FXML
    private void selectProduct3(MouseEvent event) {
        selectedProductLabel.setText("Ancient Saudi Painting");
    }

    // Apply rounded corners to image
    private void makeRounded(ImageView imageView) {

        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);

        clip.widthProperty().bind(imageView.fitWidthProperty());
        clip.heightProperty().bind(imageView.fitHeightProperty());

        imageView.setClip(clip);
    }

    // Add empty rows so table always looks full
    private void fillEmptyRows() {

        ObservableList<Bid> items = tableView.getItems();
        int currentRows = items.size();

        for (int i = currentRows; i < DESIRED_ROWS; i++) {
              items.add(new Bid("", "", "", ""));
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

