package soomXDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BidDAO {

    public static boolean insertBid(double newProductPrice, String username, int productID) {

        try (Connection con = DBConnection.getConnection()) {

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
}