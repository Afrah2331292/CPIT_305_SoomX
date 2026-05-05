package soomXDatabase;

import soomXDatabase.DBConnection;
import soomXDatabase.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BidDAO {
    public static void insertBid(double newproductprice, String username, int productID){
        try (Connection con = DBConnection.getConnection()){
            String sql = "INSERT INTO Bids ( newproductprice, username, productID) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, newproductprice);
            ps.setString(2,username);
            ps.setInt(3,productID);

            ps.executeUpdate();
            ProductDAO.updateProductPrice(con, productID,newproductprice );

        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
