package soomXDatabase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "0000"; // عدليه لو عندك مختلف

    public static void setupDatabase() {
        // Connection link creation && Statement
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stat = connection.createStatement();

            // Creation of the DataBase named SoomX
            stat.executeUpdate("CREATE DATABASE IF NOT EXISTS SoomX");
            stat.execute("USE SoomX");

            // Creation of the Table "Product" in the database
            stat.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Product (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(100) UNIQUE, "+
                            "description TEXT, " +
                            "price DOUBLE," +
                            "date DATE,"+
                            "clock VARCHAR (9)"+
                            ")"
            );

            // Creation of the table "User" in the database
            stat.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Users (" +
                            "username VARCHAR(50) PRIMARY KEY, " +
                            "email VARCHAR(100), " +
                            "phone VARCHAR(20), " +
                            "password VARCHAR(100), " +
                            "role INT " +
                            ")"
            );

            // Creation of the table "Bids" in the database
            stat.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Bids (" +
                            "bidID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "username VARCHAR(50), " +
                            "productID INT, " +
                            "newproductprice DOUBLE, " +
                            "FOREIGN KEY (username) REFERENCES Users(username), " +
                            "FOREIGN KEY (productID) REFERENCES Product(id)" +
                            ")"
            );


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
