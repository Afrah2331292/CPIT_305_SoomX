package soomXDatabase;


import java.sql.*;
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


    // get the product table from the database
    public static ResultSet getAllProduct() {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Product");

            ResultSet rs = ps.executeQuery();

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

    public static Date getDateforProduct(int productID) {

        String sql = "SELECT date FROM Product WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDate("date");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getClockforProduct(int productID) {

        String sql = "SELECT clock FROM Product WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("clock");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String numberOfProduct() {
        String sql = "SELECT COUNT(*) FROM Product";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1)+"";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0+"";
    }

    public static int getProductPrice(int productID) {
        String sql = "SELECT price FROM Product WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("price");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}