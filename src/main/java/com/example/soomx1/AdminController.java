package com.example.soomx1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import soomXDatabase.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.cell.PropertyValueFactory;

import static soomXDatabase.BidDAO.getBidsFor_A_SelectedProduct;
import static soomXDatabase.ProductDAO.getAllProduct;

public class AdminController {

    private int selectedProductID = -1;
    private Parent selectedCard = null;

    @FXML private HBox productsBar;
    @FXML private Label selectedProductLabel;
    @FXML private TableView<Bid> tableView;

    @FXML private TableColumn<Bid, String> bidIdColumn;
    @FXML private TableColumn<Bid, String> nameColumn;
    @FXML private TableColumn<Bid, String> bidPriceColumn;
    @FXML private TableColumn<Bid, String> contactInfoColumn;

    public void initialize() {

        bidIdColumn.setCellValueFactory(new PropertyValueFactory<>("bidId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bidPriceColumn.setCellValueFactory(new PropertyValueFactory<>("bidPrice"));
        contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

        try (

                InputStream input = getClass().getResourceAsStream("/com/example/soomx1/Products_Info.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(input))
        ) {

            ResultSet rs = getAllProduct();
            String productLine;

            while (rs.next() && (productLine = br.readLine()) != null) {

                // From the database
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String clock = rs.getString("clock");

                // From the file just the photos
                String imagePath = productLine.trim() + ".jpg";

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductAdmainCard.fxml"));
                Parent card = loader.load();

                ProductAdmainCardController controller = loader.getController();

                controller.setData(
                        id,
                        imagePath
                );

                controller.setProductID(id);

                card.setOnMouseClicked(event -> {

                    selectedProductID = id;

                    selectedProductLabel.setText("Selected Product ID: " + id);

                    if (selectedCard != null) {
                        selectedCard.setScaleX(1);
                        selectedCard.setScaleY(1);
                    }

                    selectedCard = card;

                    ScaleTransition st = new ScaleTransition(Duration.millis(180), card);
                    st.setToX(1.08);
                    st.setToY(1.08);
                    st.play();

                    System.out.println("Clicked card id = " + id);

                    loadBidsForProduct(id);
                });

                productsBar.getChildren().add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBidsForProduct(int productID) {

        tableView.setItems(getBidsFor_A_SelectedProduct(productID));
    }

    @FXML
    private void generateAuctionReport(ActionEvent event) {

        if (selectedProductID == -1) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Product Selected",
                    "Please select a product first."
            );

            return;
        }

        try {

            Path reportPath = AuctionReport.generateReport(
                    "Product " + selectedProductID,
                    selectedProductID,
                    tableView.getItems()
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Report Generated",
                    "Auction report saved at:\n" + reportPath.toAbsolutePath()
            );

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Failed to generate report."
            );
        }
    }



    @FXML
    private void goToSignIn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }





    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


}