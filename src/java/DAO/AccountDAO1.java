/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Accounts;
import Model.Customers;
import Model.Roles;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author admin
 */
public class AccountDAO1 extends DBContext {

    public ArrayList<Customers> getAllUser() {
        ArrayList<Customers> data = new ArrayList<Customers>();
        try {
            String strSQL = "select * from Customers";
            PreparedStatement st = connection.prepareStatement(strSQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String customerID = rs.getString(1);
                String customerName = rs.getString(2);
                String phone = rs.getString(3);
                String address = rs.getString(4);
                String email = rs.getString(5);
                String accName = rs.getString(6);
                String password = rs.getString(7);
                String userType = rs.getString(8);
                String idFb = rs.getString(9);
                String idGoogle = rs.getString(10);
                data.add(new Customers(customerID, customerName, phone, address, email, accName, password, userType, idFb, idGoogle));
            }
        } catch (Exception e) {
            System.out.println("getAllUser" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Accounts> getAllAccount() {
        ArrayList<Accounts> list = new ArrayList<Accounts>();
        try {
            String strSQL = "select * from Accounts";
            PreparedStatement st = connection.prepareStatement(strSQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String accountLogin = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String roleid = rs.getString(4);
                list.add(new Accounts(accountLogin, name, password, roleid));
            }
        } catch (Exception e) {
            System.out.println("getAllUser" + e.getMessage());
        }
        return list;
    }

    public void deleteCustomer(String id) {
        try {
            String strSQL = "DELETE FROM [dbo].[Customers]\n"
                    + "      WHERE accName = ?";
            PreparedStatement st = connection.prepareStatement(strSQL);
            st.setString(1, id);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println("delete:" + e.getMessage());
        }

    }

    public void deleteAccount(String id) {
        try {
            String strSQL = "DELETE FROM [dbo].[Accounts]\n"
                    + "      WHERE Account_Login = ?";
            PreparedStatement st = connection.prepareStatement(strSQL);
            st.setString(1, id);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println("delete:" + e.getMessage());
        }

    }

    public void createCustomer(Customers c) {
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([Customer_Name]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[email]\n"
                + "           ,[accName]\n"
                + "           ,[password]\n"
                + "           ,[User_type])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCustomerName());
            st.setString(2, c.getPhone());
            st.setString(3, c.getAddress());
            st.setString(4, c.getEmail());
            st.setString(5, c.getAccName());
            st.setString(6, c.getPassword());
            st.setInt(7, Integer.parseInt(c.getUserType()));
            st.execute();
        } catch (Exception e) {
        }
    }

    public void updateCustomer(Customers c) {
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [Customer_Name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[accName] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[User_type] = ?\n"
                + " WHERE Customer_ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCustomerName());
            st.setString(2, c.getPhone());
            st.setString(3, c.getAddress());
            st.setString(4, c.getEmail());
            st.setString(5, c.getAccName());
            st.setString(6, c.getPassword());
            st.setString(7, c.getUserType());
            st.setInt(8, Integer.parseInt(c.getCustomerID()));
            st.execute();
        } catch (Exception e) {
        }
    }

    public Customers getCustomerByAccName(String accName) {
        String sql = "SELECT [Customer_ID]\n"
                + "      ,[Customer_Name]\n"
                + "      ,[phone]\n"
                + "      ,[address]\n"
                + "      ,[email]\n"
                + "      ,[accName]\n"
                + "      ,[password]\n"
                + "      ,[User_type]\n"
                + "  FROM [dbo].[Customers]\n"
                + "  where accName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, accName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String address = rs.getString(4);
                String email = rs.getString(5);
                //String accName = rs.getString(6);
                String password = rs.getString(7);
                String usertype = rs.getString(8);
                String idFb = rs.getString(9);
                String idGoogle = rs.getString(10);
                return new Customers(id, name, phone, address, email, accName, password, usertype, idFb, idGoogle);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean isAccountLoginExist(String account) {
        boolean exists = false;
        try {
            String strSQL = "SELECT COUNT(*) FROM Accounts WHERE Account_Login = ?";
            PreparedStatement st = connection.prepareStatement(strSQL);
            st.setString(1, account);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("isAccountLoginExist: " + e.getMessage());
        }
        return exists;
    }

    public void addStaff(Accounts a) {
        String sql = "INSERT INTO [dbo].[Accounts]\n"
                + "           ([Account_Login]\n"
                + "           ,[Name]\n"
                + "           ,[Password]\n"
                + "           ,[RoleId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getAccountLogin());
            st.setString(2, a.getName());
            st.setString(3, a.getPassword());
            st.setString(4, a.getRoleid());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean isAccnameExist(String accname) {
        boolean exists = false;
        try {
            String strSQL = "SELECT COUNT(*) FROM Customers WHERE accName = ?";
            PreparedStatement st = connection.prepareStatement(strSQL);
            st.setString(1, accname);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("isAccountLoginExist: " + e.getMessage());
        }
        return exists;
    }

    public void updateStaff(Accounts a) {
        String sql = "UPDATE [dbo].[Accounts]\n"
                + "   SET [Account_Login] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[RoleId] = ?\n"
                + " WHERE Account_Login = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getAccountLogin());
            st.setString(2, a.getName());
            st.setString(3, a.getPassword());
            st.setString(4, a.getRoleid());
            st.setString(5, a.getAccountLogin());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Accounts getAccountByAccount(String account) {
        String sql = "SELECT [Account_Login]\n"
                + "      ,[Name]\n"
                + "      ,[Password]\n"
                + "      ,[RoleId]\n"
                + "  FROM [dbo].[Accounts] where Account_Login = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new Accounts(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Accounts Login(String account, String password) {
        String sql = "SELECT * FROM [Accounts] WHERE Account_Login = ? And Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Accounts(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("Login account: " + e);
        }
        return null;
    }
    
    
    public Roles getRole(int id) {
        String sql = "select * from Roles where RoleId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return new Roles(rs.getInt(1), rs.getString(2));
            }
        }catch(Exception  e) {
            System.out.println("Get roles: " + e);
        }
        return null;
    }
    
//hau
    
    //tìm một khách hàng dựa trên địa chỉ email được cung cấp.
    public Customers findByEmail(String email) {
        try {
            PreparedStatement ps; //sử dụng để thực thi truy vấn SQL
            ResultSet rs; //chứa tập kết quả chứa dữ liệu được lấy từ cơ sở dữ liệu.
            String sql = "SELECT * FROM [swp391_Happy2].[dbo].[Customers] WHERE email = ?";
            ps = connection.prepareStatement(sql); // tạo một PreparedStatement bằng cách sử dụng truy vấn SQL được cung cấp ( sql).
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) { //di chuyển con trỏ đến hàng tiếp theo và vòng lặp sẽ dừng khi k còn hàng dữ liệu
                Customers a = new Customers();
                a.setCustomerID(rs.getString(1));   //liên kết tham số 
                a.setEmail(rs.getString(2));
                a.setPassword(rs.getString(3));

                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Customers findByIdFb(String id) {
        try {
            PreparedStatement ps; //sử dụng để thực thi truy vấn SQL
            ResultSet rs; //chứa tập kết quả chứa dữ liệu được lấy từ cơ sở dữ liệu.
            String sql = "SELECT * FROM [swp391_Happy2].[dbo].[Customers] WHERE idFacebook = ?";
            ps = connection.prepareStatement(sql); // tạo một PreparedStatement bằng cách sử dụng truy vấn SQL được cung cấp ( sql).
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) { //di chuyển con trỏ đến hàng tiếp theo và vòng lặp sẽ dừng khi k còn hàng dữ liệu
                Customers a = new Customers();
                a.setCustomerID(rs.getString(1));   //liên kết tham số 
                a.setEmail(rs.getString(2));
                a.setPassword(rs.getString(3));

                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updateCustomerPassword(String email, String password) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "update [Customers] set password = ? where email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(2, email);
            ps.setString(1, password);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getCustomerIDByAccName(String accName) {
        int customer_id = -1;
        PreparedStatement ps;
        String sql = "select Customer_ID from Customers where accName = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, accName);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    customer_id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer_id;
    }
    
    public Customers getCustomerID(String email) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT [Customer_ID] FROM [swp391_Happy2].[dbo].[Customers] where Customers.email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Customers a = new Customers();
                a.setCustomerID(rs.getString(1));
                a.setAccName(rs.getString(2));
                a.setPassword(rs.getString(4));
                a.setCustomerID(rs.getString(4));
                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Customers getCustomerIDByIDGG(String id) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT [Customer_ID] FROM [swp391_Happy2].[dbo].[Customers] where Customers.idGoogle = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Customers a = new Customers();
                a.setCustomerID(rs.getString(1));
                a.setAccName(rs.getString(2));
                a.setPassword(rs.getString(4));
                a.setCustomerID(rs.getString(4));
                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void RegisterFacebook(String id, String name, String email) {
    try{
        
    PreparedStatement ps;
            
            String strAdd=
                    "insert into Customers (Customer_Name,email,idFacebook ) values(?,?,?)";
           
            ps=connection.prepareStatement(strAdd);
            ps.setString(1, name);
            ps.setString(2,email);
            ps.setString(3, id);
        
            ps.execute();
        } catch(Exception e){
            System.out.println("register fail:"+e.getMessage());

       }
    }
    
    public void RegisterGoogle(String email, String id) {
    try{
        
    PreparedStatement ps;
            
            String strAdd=
                    "insert into Customers (email, idGoogle) values(?, ?)";
           
            ps=connection.prepareStatement(strAdd);
            
            ps.setString(1,email);
            ps.setString(2, id);
           
        
            ps.execute();
        } catch(Exception e){
            System.out.println("register fail:"+e.getMessage());

       }
    }
}
