package com.example.soomx1;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.sql.Date;
import soomXDatabase.BidDAO;
import soomXDatabase.ProductDAO;

public class ProductAdmainCardController {
    private int productID;


   @FXML
   private Text Highest_Bid_Number;


   @FXML
   private Text Total_Bids_number;

    @FXML
    private ImageView productImage;

    @FXML
    private Label Acution_EndTime;

    @FXML
    private Label Acution_Endate;


    public void setData(int productID,
                        String imagePath)


    {
        //_________________________________________________
        // manage photo placement
        productImage.setImage(new Image(imagePath));


        // Clipping the photo corners in a rounded shape
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


        //______________________________________________________
        // Retrieve the highest bid and display it on the card
        double highestBid = 0;
        try {
             highestBid = BidDAO.getHighestBid(productID);

        }catch(Exception e){
            e.printStackTrace();
        }
        Highest_Bid_Number.setText(highestBid+ " RS");
     //________________________________________________________________


        Total_Bids_number.setText(BidDAO.TotalBids(productID));

        Acution_EndTime.setText(ProductDAO.getClockforProduct(productID));

        Date date = ProductDAO.getDateforProduct(productID);
        Acution_Endate.setText(date != null ? date.toString() : "No date");


    }













    public void setProductID(int productID) {
        this.productID = productID;
    }




}