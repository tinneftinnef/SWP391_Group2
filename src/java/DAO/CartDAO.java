/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CartDAO extends DBContext {

    public int addToCart(Cart cartItem) {
        String query = "INSERT INTO cart (product_id, customer_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartItem.getProductId());
            stmt.setInt(2, cartItem.getCustomerId());
            stmt.setInt(3, cartItem.getQuantity());
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add to cart: " + e);
        }
        return 0;
    }

    public int updateCartItem(Cart cartItem) {
        String query = "UPDATE cart SET quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartItem.getQuantity());
            stmt.setInt(2, cartItem.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update cart: " + e);
        }
        return 0;
    }

    public int deleteCartItem(int cartItemId) {
        String query = "DELETE FROM cart WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartItemId);
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete cart: " + e);
        }
        return 0;
    }
    
    public int deleteCartByCustomer(int customerId) {
        String query = "DELETE FROM cart WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete cart by customer: " + e);
        }
        return 0;
    }

    public Cart getCartItemById(int cartItemId) {
        String query = "SELECT * FROM cart WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartItemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cart cartItem = new Cart();
                cartItem.setId(rs.getInt("id"));
                cartItem.setProductId(rs.getInt("product_id"));
                cartItem.setCustomerId(rs.getInt("customer_id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                return cartItem;
            }
        } catch (Exception e) {
            System.out.println("Get cart: " + e);
        }
        return null;
    }

    public Cart getCartItemByProductId(int productId, int customerId) {
        String query = "SELECT * FROM cart WHERE product_id = ? and customer_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cart cartItem = new Cart();
                cartItem.setId(rs.getInt("id"));
                cartItem.setProductId(rs.getInt("product_id"));
                cartItem.setCustomerId(rs.getInt("customer_id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                return cartItem;
            }
        } catch (Exception e) {
            System.out.println("Get cart: " + e);
        }
        return null;
    }

    public List<Cart> getCartItemsByCustomerId(int customerId) {
        String query = "SELECT * FROM cart WHERE customer_id = ?";
        List<Cart> cartItems = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cart cartItem = new Cart();
                cartItem.setId(rs.getInt("id"));
                cartItem.setProductId(rs.getInt("product_id"));
                cartItem.setCustomerId(rs.getInt("customer_id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            System.out.println("Get cart by id: " + e);
        }
        return cartItems;
    }
}
