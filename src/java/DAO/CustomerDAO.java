/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Customers;
import java.util.*;
import java.sql.*;

/**
 *
 * @author admin
 */
public class CustomerDAO extends DBContext {

    public Customers checkAccountByaccName(String account) {
        String sql = "select * from Customers where accName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Customers(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Customers Login(String username, String password) {
        String sql = "select * from Customers where accName = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customers(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
            System.out.println("Login: " + e);
        }
        return null;
    }

    public void register(String name, String phone, String address, String email, String account, String pass) {
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([Customer_Name]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[email]\n"
                + "           ,[accName]\n"
                + "           ,[password]\n"
                + "           ,[User_type])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, phone);
            st.setString(3, address);
            st.setString(4, email);
            st.setString(5, account);
            st.setString(6, pass);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean updateCustomer(String email, String name, String phone, String address, int userId) {
        String sql = "UPDATE Customers SET Customer_Name = ?, phone = ?, email = ?, address=? WHERE Customer_ID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);
            st.setString(2, phone);
            st.setString(3, email);
            st.setString(4, address);
            st.setInt(5, userId);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String cusId, String newPassword) {
        String sql = "UPDATE Customers SET password = ? WHERE Customer_ID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, newPassword);
            st.setString(2, cusId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkEmailExists(String email, int id) {
        String sql = "SELECT COUNT(*) FROM Customers WHERE email = ? and Customer_ID !=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createUser(Customers customer) {
        String sql = "INSERT INTO Customers (customer_Name, phone, address, email, accName, password, User_type) VALUES (?, ?, ?, ?, ?, ?, 0)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getAccName());
            stmt.setString(6, customer.getPassword());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUsernameExist(String accName) {
        String sql = "SELECT COUNT(*) FROM Customers WHERE accName = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPhoneExist(String phone) {
        String sql = "SELECT COUNT(*) FROM Customers WHERE phone = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM Customers WHERE email = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
