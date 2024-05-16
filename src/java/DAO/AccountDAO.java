/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
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

    
    //hau
    
    //tìm một khách hàng dựa trên địa chỉ email được cung cấp.
    public Customers findByEmail(String email) {
        try {
            PreparedStatement ps; //sử dụng để thực thi truy vấn SQL
            ResultSet rs; //chứa tập kết quả chứa dữ liệu được lấy từ cơ sở dữ liệu.
            String sql = "SELECT * FROM [SaleComputer].[dbo].[Customers] WHERE email = ?";
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
    
    
    
    
}
