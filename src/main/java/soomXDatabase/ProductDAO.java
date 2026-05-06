package soomXDatabase;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class ProductDAO {

    public static void insertProduct(String name, String description, double price,  LocalDate date, String time) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "INSERT IGNORE INTO Product (name, description, price,date,clock) VALUES (?, ?, ?, ? ,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setDate(4, java.sql.Date.valueOf(date));
            ps.setString(5, time);
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