/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Accounts;
import Model.Customers;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;

/**
 *
 * @author admin
 */
public class AccountDAO extends DBContext {

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

                data.add(new Customers(customerID, customerName, phone, address, email, accName, password, userType));
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
                return new Customers(id, name, phone, address, email, accName, password, usertype);
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

}
