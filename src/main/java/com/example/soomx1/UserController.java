package com.example.soomx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import soomXDatabase.BidDAO;
import soomXDatabase.DBConnection;
import soomXDatabase.ProductDAO;

import java.io.*;

import java.sql.ResultSet;

import static soomXDatabase.BidDAO.TotalBidsAmountByUser;
import static soomXDatabase.BidDAO.TotalBidsByUser;
import static soomXDatabase.ProductDAO.getAllProduct;

public class UserController {

    @FXML
    private Label User_Name;

    @FXML
    private TextField Lab;

   @FXML
   private TextField Active_Auctions_text;


   @FXML
   private Text Total_Bids_txt;

    @FXML
    private FlowPane productsContainer;

   @FXML
   private Text Total_Value_txt;

   @FXML
   private Text Active_Auctions_txt;


    @FXML
    void clic(ActionEvent event) {

    }


    @FXML

    public void initialize() {



            try (

                InputStream input = new FileInputStream("src/main/resources/com/example/soomx1/Products_Info.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(input))
        ) {

                ResultSet rs = getAllProduct();
                String productLine;

            while (rs.next() && (productLine = br.readLine()) != null) {


                // retrieve from the database
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                //retrieve photo URL from file
                String imagePath = productLine.trim() + ".jpg";


                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                Parent card = loader.load();

                ProductCardController controller = loader.getController();

                controller.setRefreshStats(() -> {
                    Active_Auction();
                    Total_Bids();
                    Total_Value();
                });



                controller.setData(
                        name,                    // from DB
                        imagePath,               // from file
                        description,             // from DB
                        "Current Bid",
                        "Total Bids",
                        String.valueOf(price),   //  from DB
                        BidDAO.TotalBids(id),
                        id
                );

                controller.setProductID(id);

                productsContainer.getChildren().add(card);
                Active_Auction();
                Total_Bids();
                Total_Value();


               // set users name
                User_Name.setText(Session.currentUsername);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @FXML
    void Log_Out_Action(ActionEvent event) throws IOException{


        Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    public void Active_Auction(){
        Active_Auctions_txt.setText(ProductDAO.numberOfProduct());
    }

    public void Total_Bids(){
        Total_Bids_txt.setText(TotalBidsByUser(Session.currentUsername));
    }

    public void Total_Value(){
        Total_Value_txt.setText(TotalBidsAmountByUser(Session.currentUsername));}



}