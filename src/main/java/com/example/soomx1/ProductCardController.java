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

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable {

    @FXML
    private Label Current_Bid_Number;

    @FXML
    private Button Place_Bid;

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

    @FXML
    private Spinner<Integer> Spinner_Price_Min_Teller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // قيمة مؤقتة إلى أن يتم استدعاء setData
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);

        Spinner_Price_Min_Teller.setValueFactory(valueFactory);

        // منع النزول أقل من الحد الأدنى (حماية إضافية)
        Spinner_Price_Min_Teller.valueProperty().addListener((obs, oldValue, newValue) -> {
            SpinnerValueFactory.IntegerSpinnerValueFactory vf =
                    (SpinnerValueFactory.IntegerSpinnerValueFactory) Spinner_Price_Min_Teller.getValueFactory();

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
                        String totalBidsNumber) {
        productName.setWrapText(true); // مهم جداً عشان النص ينزل سطر
        productName.setMinWidth(Region.USE_COMPUTED_SIZE);
        productName.setMaxWidth(Double.MAX_VALUE);

        productName.setText(name);
        productImage.setImage(new Image(imagePath));
        Product_Description.setText(productDescription);
        Current_Bid_Title.setText(currentBidTitle);
        Total_Bids_Title.setText(totalBidsTitle);
        Current_Bid_Price.setText(currentBidPrice);
        Total_Bids_Number.setText(totalBidsNumber);

        // تنظيف السعر من أي رموز
        String cleanPrice = currentBidPrice.replaceAll("[^0-9]", "");
        int currentPrice = 0;

        if (!cleanPrice.isEmpty()) {
            currentPrice = Integer.parseInt(cleanPrice);
        }

        // 🔥 إنشاء Factory جديدة بالقيم الصحيحة
        SpinnerValueFactory<Integer> newFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        currentPrice + 200,   // أقل قيمة
                        currentPrice + 10000, // أعلى قيمة
                        currentPrice + 200,   // القيمة الابتدائية
                        200                   // مقدار الزيادة
                );

        Spinner_Price_Min_Teller.setValueFactory(newFactory);

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







    @FXML
    private void handlePlaceBid() {

        int bidValue = Spinner_Price_Min_Teller.getValue();

        // تحديث السعر الحالي
        Current_Bid_Price.setText(String.valueOf(bidValue));
         //dkkfkf
        // زيادة عدد المزايدات
        int total = Integer.parseInt(Total_Bids_Number.getText());
        total++;
        Total_Bids_Number.setText(String.valueOf(total));

        // 🔥 إعادة إنشاء Spinner ليبدأ من السعر الجديد +200
        SpinnerValueFactory<Integer> newFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        bidValue + 200,
                        bidValue + 10000,
                        bidValue + 200,
                        200
                );

        Spinner_Price_Min_Teller.setValueFactory(newFactory);
    }


}