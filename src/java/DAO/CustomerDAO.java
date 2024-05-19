/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class CustomerDAO extends DBContext{
        public Customers checkAccountByaccName(String account) {
        String sql = "select * from Customers where accName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Customers(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
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
}
