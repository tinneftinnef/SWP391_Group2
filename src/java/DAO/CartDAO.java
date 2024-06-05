/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class CartDAO {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    public Cart checkCartExist(int customer_id, int product_id, String product_size) {

        String sql = "select * from Cart\r\n"
                + "where [Customer_ID] = ? and [ProductId] = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ps.setInt(2, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void editQuantityCart(int product_id, int product_quantity, int customer_id) {
        String query = "update Cart set [quantity]= ? "
                + "where [Customer_ID] = ? and [ProductId] = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product_quantity);
            ps.setInt(2, customer_id);
            ps.setInt(3, product_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertCart(int product_id, int product_quantity, int customer_id, String product_size) {
        String query = "insert Cart(ProductId, quantity, Customer_ID)\r\n"
                + "values(?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product_id);
            ps.setInt(2, product_quantity);
            ps.setInt(3, customer_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cart> getCartByCustomerID(int customer_id) {
        List<Cart> list = new ArrayList<>();
        String query = "select * from Cart where Customer_ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, customer_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteCart(int product_id, int customer_id, String product_size) {
        String query = "delete from Cart where Customer_ID=? and ProductId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product_id);
            ps.setInt(2, customer_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCartByCustomerID(int customer_id) {
        String query = "delete from Shopping_Cart where Customer_ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, customer_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
