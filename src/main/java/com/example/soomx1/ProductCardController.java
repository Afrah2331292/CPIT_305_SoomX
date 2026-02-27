package com.example.soomx1;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle; // 👈 استورد Rectangle

public class ProductCardController {
    @FXML
    private Label Product_Description;
    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    public void setData(String name, String imagePath, String ProductDescription) {
        productName.setText(name);
        productImage.setImage(new Image(imagePath));
        Product_Description.setText(ProductDescription);

        // ✅ عمل Clip لتدوير الزوايا العليا فقط
        Rectangle clip = new Rectangle(productImage.getFitWidth(), productImage.getFitHeight());
        clip.setArcWidth(30);   // عرض الزوايا العليا
        clip.setArcHeight(30);


        productImage.setPreserveRatio(false);
        javafx.scene.shape.SVGPath svgClip = new javafx.scene.shape.SVGPath();
         svgClip.setContent( "M0,25 " + "Q0,0 18,0 " +"L228,0 " +"Q240,0 240,18 " +"L250,175 " +"L0,175 " +"Z");;

        productImage.setClip(svgClip);
    }
}