package soomXDatabase;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDAO {

    public static void insertProduct(String name, String description, double price) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "INSERT IGNORE INTO Product (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);

            ps.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // for updating the product price
    public static void updateProductPrice(Connection con, int productID, double newPrice) {

        String sql = "UPDATE Product SET price = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setInt(2, productID);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}