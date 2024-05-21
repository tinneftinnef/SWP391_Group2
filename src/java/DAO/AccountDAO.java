/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Accounts;
import java.util.ArrayList;
import java.util.List;
import Model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dell
 */
public class AccountDAO extends MyDAO {

    public Accounts getAccountBy(String username, String password) {
        try {
            String sql = """
                         SELECT * FROM Accounts
                         WHERE Account_Login = ?
                         AND Password = ?""";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Accounts account = new Accounts();
                account.setAcountlogin(rs.getString("Account_Login"));
                account.setName(rs.getString("Name"));
                account.setPass(rs.getString("Password"));
                account.setRoleid(rs.getInt("RoleId"));
                return account;
            }
        } catch (SQLException ex) {
          
        }
        return null;
    }
        public static void main(String[] args) {
            System.out.println( new AccountDAO().getAccountBy("Huy", "123"));
    }
    
    //hau
    /*
        Table: Customer
        int CustomerID, phone;
        String CustomerName, address, email, accName, password, User_type;
        
    */
        
    //tìm một khách hàng dựa trên địa chỉ email được cung cấp.
    public Customers findByEmail(String email) {
        try {
            PreparedStatement ps; //sử dụng để thực thi truy vấn SQL
            ResultSet rs; //chứa tập kết quả chứa dữ liệu được lấy từ cơ sở dữ liệu.
            String sql = "SELECT * FROM [swp391_Happy].[dbo].[Customers] WHERE email = ?";
            ps = connection.prepareStatement(sql); // tạo một PreparedStatement bằng cách sử dụng truy vấn SQL được cung cấp ( sql).
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) { //di chuyển con trỏ đến hàng tiếp theo và vòng lặp sẽ dừng khi k còn hàng dữ liệu
                Customers a = new Customers();
                a.setCustomerID(rs.getInt(1));   //liên kết tham số 
                a.setEmail(rs.getString(2));
                a.setPassword(rs.getString(3));

                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updateCustomerPassword(String accName, String password) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "update [Customers] set password = ? where accName = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(2, accName);
            ps.setString(1, password);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Customers getCustomerID(String email) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT [CustomerID] FROM [swp391_Happy].[dbo].[Customers] where Customers.email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Customers a = new Customers();
                a.setCustomerID(rs.getInt(1));
                a.setAccName(rs.getString(2));
                a.setPassword(rs.getString(4));
                a.setCustomerID(rs.getInt(4));
                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
