package soomXDatabase;
import java.lang.Thread;
import com.example.soomx1.Bid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BidDAO {
   private static Connection con = DBConnection.getConnection();

    // method for inserting bids and making sure no race condition happed
    public static synchronized boolean insertBid(double newProductPrice, String username, int productID) {

        try (Connection con = DBConnection.getConnection()) {
            Thread.sleep(3000);

            double currentMaxBid = getHighestBid(con, productID);

            if (newProductPrice <= currentMaxBid) {
                return false;
            }

            String sql = "INSERT INTO Bids (newproductprice, username, productID) VALUES (?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setDouble(1, newProductPrice);
                ps.setString(2, username);
                ps.setInt(3, productID);

                ps.executeUpdate();
            }

            ProductDAO.updateProductPrice(con, productID, newProductPrice);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // get the Highest Bid
    //______________________________________________________________________________________
    public static double getHighestBid(int productID) throws Exception{
        Connection con = DBConnection.getConnection();
        double BIDHihestValue = getHighestBid(con, productID);
        con.close();
        return BIDHihestValue;
    }
    private static double getHighestBid(Connection con, int productID) throws Exception {

        String sql = "SELECT MAX(newproductprice) AS maxBid FROM Bids WHERE productID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("maxBid");
                }
            }
        }

        return 0;
    }
    //________________________________________________________________________________



    // To get the number of Bids for each product
    public static String TotalBids(int productID){

        return  TotalBids( productID,  con)+"";
    }




    private static int TotalBids(int productID, Connection con){
        String sql = "SELECT COUNT(*) FROM Bids WHERE productID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productID);

            ResultSet rs = ps.executeQuery();


            if(rs.next()){
                int count = rs.getInt(1);
                return count;
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return 0;
    }
    //__________________________________________________________________________________




    // Get the number of Bid the user get involved on
    public static String TotalBidsByUser(String username) {
        String sql = "SELECT COUNT(*) FROM Bids WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1) + "";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    //________________________________________________________________________________



    // The amount of money the user put on Bids
    public static String TotalBidsAmountByUser(String username) {
        String sql = "SELECT SUM(newproductprice) FROM Bids WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1) + "$";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0$";
    }
    //________________________________________________________________________


    // Retrieve all bids for the selected product from the database
    public static ObservableList<Bid> getBidsFor_A_SelectedProduct(int productID){

        // List used to store bid objects
        ObservableList<Bid> bidsList = FXCollections.observableArrayList();

        // SQL query to get bid and user information
        String sql =
                "SELECT b.bidID, b.username, b.newproductprice, u.phone, u.email " +
                        "FROM Bids b " +
                        "JOIN Users u ON b.username = u.username " +
                        "WHERE b.productID = ?";

        try(
                // Connect to the database
                Connection con = DBConnection.getConnection();

                // Prepare the SQL query
                PreparedStatement ps = con.prepareStatement(sql)
        ){

            // Set the selected product ID
            ps.setInt(1, productID);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Loop through all returned bids
            while(rs.next()){

                // Create Bid object and add it to the list
                bidsList.add(new Bid(
                        rs.getInt("bidID")+"",
                        rs.getString("username"),
                        rs.getDouble("newproductprice")+"",
                        "Phone: " + rs.getString("phone") +
                                " | Email: " + rs.getString("email")
                ));
            }

        }catch(Exception e){

            // Print error if something goes wrong
            e.printStackTrace();
        }

        // Return the list of bids
        return bidsList;
    }





}