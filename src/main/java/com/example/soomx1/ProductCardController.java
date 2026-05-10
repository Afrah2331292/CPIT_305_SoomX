package com.example.soomx1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import soomXDatabase.BidDAO;

import java.net.URL;
import java.util.ResourceBundle;

import static soomXDatabase.ProductDAO.getProductPrice;

public class ProductCardController implements Initializable {

    private Runnable refreshStats;
    private int productID;

    @FXML private Label Current_Bid_Price;
    @FXML private Label Current_Bid_Title;
    @FXML private Label Total_Bids_Title;
    @FXML private Label Product_Description;
    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label Total_Bids_Number;
    @FXML private Spinner<Integer> Spinner_Price_Min_Teller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Spinner_Price_Min_Teller.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1)
        );

        Spinner_Price_Min_Teller.valueProperty().addListener((obs, oldValue, newValue) -> {

            SpinnerValueFactory.IntegerSpinnerValueFactory vf =
                    (SpinnerValueFactory.IntegerSpinnerValueFactory)
                            Spinner_Price_Min_Teller.getValueFactory();

            if (newValue < vf.getMin()) {
                vf.setValue(vf.getMin());
            }
        });
    }

    public void setData(String name,
                        String imagePath,
                        String productDescription,
                        String currentBidTitle,
                        String totalBidsTitle,
                        String currentBidPrice,
                        String totalBidsNumber,
                        int productID) {

        this.productID = productID;

        productName.setWrapText(true);
        productName.setMinWidth(Region.USE_COMPUTED_SIZE);
        productName.setMaxWidth(Double.MAX_VALUE);

        productName.setText(name);
        Product_Description.setText(productDescription);
        Current_Bid_Title.setText(currentBidTitle);
        Total_Bids_Title.setText(totalBidsTitle);
        Current_Bid_Price.setText(currentBidPrice + "$");
        Total_Bids_Number.setText(totalBidsNumber);

        productImage.setImage(new Image(imagePath));
        productImage.setPreserveRatio(false);

        SVGPath clip = new SVGPath();

        clip.setContent(
                "M0,25 Q0,0 18,0 " +
                        "L228,0 Q240,0 240,18 " +
                        "L240,175 L0,175 Z"
        );

        productImage.setClip(clip);

        try {

            int highestBid = (int) BidDAO.getHighestBid(this.productID);
            int productPrice = (int) getProductPrice(this.productID);

            if (highestBid > 0) {
                updateSpinner(highestBid + 200);
            } else {
                updateSpinner(productPrice + 200);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePlaceBid() {

        Spinner_Price_Min_Teller.commitValue();

        try {

            int highestBid = (int) BidDAO.getHighestBid(productID);
            int bidValue = Spinner_Price_Min_Teller.getValue();

            if (bidValue <= highestBid) {
                return;
            }

            String result = AuctionClient.sendBid(
                    Session.currentUsername,
                    productID,
                    bidValue
            );

            if (result.equals("ACCEPT")) {

                Current_Bid_Price.setText(bidValue + "$");

                Total_Bids_Number.setText(BidDAO.TotalBids(productID));

                updateSpinner(bidValue + 200);

                if (refreshStats != null) {
                    refreshStats.run();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSpinner(int min) {

        Spinner_Price_Min_Teller.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        min,
                        min + 10000,
                        min,
                        200
                )
        );
    }

    public void setRefreshStats(Runnable refreshStats) {
        this.refreshStats = refreshStats;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }
}