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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminController {

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

        // Set initial bid values
        highestBidLabel.setText("Highest Bid: 7000 SAR");
        totalBidsLabel.setText("Total Bids: 25");

        highestBidLabel2.setText("Highest Bid: 8000 SAR");
        totalBidsLabel2.setText("Total Bids: 30");

        highestBidLabel3.setText("Highest Bid: 9000 SAR");
        totalBidsLabel3.setText("Total Bids: 20");

        // Set time placeholders
        product1TimeField.setPromptText("HH:MM");
        product2TimeField.setPromptText("HH:MM");
        product3TimeField.setPromptText("HH:MM");

        // Make images rounded
        makeRounded(productImage1);
        makeRounded(productImage2);
        makeRounded(productImage3);

        // Make sure table has a list
        if (tableView.getItems() == null) {
            tableView.setItems(FXCollections.observableArrayList());
        }

        // Fill table with empty rows
        fillEmptyRows();
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

