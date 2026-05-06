package com.example.soomx1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import soomXDatabase.BidDAO;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductAdmainCardController {
    private int productID;


    @FXML
    private Label Current_Bid_Price;

    @FXML
    private Label Current_Bid_Title;

    @FXML
    private Label Total_Bids_Title;

    @FXML
    private Label Product_Description;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label Total_Bids_Number;



    public void setData(String name,
                        String imagePath,
                        String productDescription)


    {
        productName.setWrapText(true); // مهم جداً عشان النص ينزل سطر
        productName.setMinWidth(Region.USE_COMPUTED_SIZE);
        productName.setMaxWidth(Double.MAX_VALUE);

        productName.setText(name);
        productImage.setImage(new Image(imagePath));
        Product_Description.setText(productDescription);





        // قص الصورة بزوايا علوية مدورة
        SVGPath svgClip = new SVGPath();
        svgClip.setContent(
                "M0,25 " +
                        "Q0,0 18,0 " +
                        "L228,0 " +
                        "Q240,0 240,18 " +
                        "L240,175 " +
                        "L0,175 " +
                        "Z"
        );

        productImage.setPreserveRatio(false);
        productImage.setClip(svgClip);
    }













    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }



}