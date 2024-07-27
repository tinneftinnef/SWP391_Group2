/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class OrderDetailDAO extends DBContext {

    public int addOrderDetail(OrderDetails o) {
        String query = "INSERT INTO OrderDetails (orderID, productId, quantity, price, discount, serial_Number) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, o.getOrderID());
            preparedStatement.setInt(2, o.getProductId());
            preparedStatement.setInt(3, o.getQuantity());
            preparedStatement.setDouble(4, o.getPrice());
            preparedStatement.setDouble(5, o.getDiscount());
            preparedStatement.setString(6, o.getSerialNumber());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add order detail: " + e);
        }
        return generatedId;
    }

    public List<OrderDetails> getAllOrdersById(int orderId) {
        List<OrderDetails> orders = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails where orderID =?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, orderId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                double discount = resultSet.getDouble("discount");
                String serialNumber = resultSet.getString("serial_Number");
                String warrantyId = resultSet.getString("warranty_ID");
                OrderDetails order = new OrderDetails(orderId, productId, quantity, price, discount, serialNumber, warrantyId);
                orders.add(order);
            }
        } catch (Exception e) {
            System.out.println("Get all order: " + e);
        }
        return orders;
    }

    // DELETE
    public void deleteOrder(int orderId) {
        String query = "DELETE FROM OrderDetails WHERE orderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
