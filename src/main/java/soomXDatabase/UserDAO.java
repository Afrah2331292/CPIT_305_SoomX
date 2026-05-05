package soomXDatabase;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO{

    // Adding the user
    public static void insertUser(String Username,String email,String phone,String password, int role){
        try(Connection con =DBConnection.getConnection()){
            String sql = "INSERT INTO Users (Username, email, phone, password,role) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,Username);
            ps.setString(2,email);
            ps.setString(3,phone);
            ps.setString(4,password);
            ps.setInt(5,role);

            ps.executeUpdate();
            System.out.print("User Add Successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // for log in check
    public static boolean login(String username, String password){
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            // If it find the user identical with the same password and username it will return true
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

 public static int getRole(String userName) {
     String sql = "SELECT role FROM Users WHERE username = ?";

      try(Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)){

          ps.setString(1,userName);

          ResultSet rs = ps.executeQuery();

          if(rs.next()){
              return rs.getInt("role");}


      }catch (SQLException e){
          e.printStackTrace();
      }
     return -1;
 }

}
