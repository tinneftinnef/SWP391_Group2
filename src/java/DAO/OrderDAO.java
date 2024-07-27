/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Orders;
import java.sql.Connection;
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
public class OrderDAO extends DBContext {
// CREATE

    public int addOrder(Orders order) {
        String query = "INSERT INTO Orders (status, customer_ID, orderDate, name, phone, address, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getStatus());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            preparedStatement.setString(4,  order.getName());
            preparedStatement.setString(5,  order.getPhone());
            preparedStatement.setString(6,  order.getAddress());
            preparedStatement.setString(7,  order.getNote());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Add order: " + e);
        }

        return generatedId;
    }

    // READ
    public Orders getOrderById(int orderId) {
        Orders order = null;
        String query = "SELECT * FROM Orders WHERE order_Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("customer_ID");
                Date orderDate = resultSet.getDate("orderDate");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String note = resultSet.getString("note");
                order = new Orders(orderId, userId, status, orderDate, name, phone, address, note);
            }
        } catch (Exception e) {
            System.out.println("get order by id: " + e);
        }
        return order;
    }

    public List<Orders> getAllOrdersByCustomer(int customerId) {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders where customer_ID=? order by order_Id desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_Id");
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("customer_ID");
                Date orderDate = resultSet.getDate("orderDate");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String note = resultSet.getString("note");
                Orders order = new Orders(orderId, userId, status, orderDate, name, phone, address, note);
                orders.add(order);
            }
        } catch (Exception e) {
            System.out.println("get orders: " + e);
        }
        return orders;
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders order by order_Id desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_Id");
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("customer_ID");
                Date orderDate = resultSet.getDate("orderDate");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String note = resultSet.getString("note");
                Orders order = new Orders(orderId, userId, status, orderDate, name, phone, address, note);
                orders.add(order);
            }
        } catch (Exception e) {
            System.out.println("get orders: " + e);
        }
        return orders;
    }

    // UPDATE
    public void updateOrder(Orders updatedOrder) {
        String query = "UPDATE Orders SET status = ?, customer_ID = ?, orderDate = ? WHERE order_Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, updatedOrder.getStatus());
            preparedStatement.setInt(2, updatedOrder.getUserId());
            preparedStatement.setDate(3, new java.sql.Date(updatedOrder.getOrderDate().getTime()));
            preparedStatement.setInt(4, updatedOrder.getOrderId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update order: " + e);
        }
    }

    public int confirmOrder(int orderId, int status) {
        String query = "UPDATE Orders SET status = ? WHERE order_Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, orderId);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update order: " + e);
        }
        return 0;
    }

    // DELETE
    public void deleteOrder(int orderId) {
        String query = "DELETE FROM Orders WHERE order_Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete order: " + e);
        }
    }
}
