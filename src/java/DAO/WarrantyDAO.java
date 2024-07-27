/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Orders;
import Model.Warranty;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarrantyDAO extends DBContext {

    public boolean requestWarranty(Warranty warranty) {
        String sql = "INSERT INTO Warranty (order_id, customer_id, product_id, serial_number, warranty_status, request_date, done_date, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = connection; 
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, warranty.getOrderId());
            stmt.setInt(2, warranty.getCustomerId());
            stmt.setInt(3, warranty.getProductId());
            stmt.setString(4, warranty.getSerialNumber());
            stmt.setString(5, warranty.getWarrantyStatus());
            stmt.setDate(6, new java.sql.Date(warranty.getRequestDate().getTime()));
            stmt.setDate(7, null);
            stmt.setString(8, warranty.getNote());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Warranty> getAllWarranties() {
        List<Warranty> warranties = new ArrayList<>();
        String sql = "SELECT * FROM Warranty";
        try {
            Connection conn = connection;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Warranty warranty = new Warranty();
                warranty.setWarrantyId(rs.getInt("warranty_id"));
                warranty.setOrderId(rs.getInt("order_id"));
                warranty.setCustomerId(rs.getInt("customer_id"));
                warranty.setProductId(rs.getInt("product_id"));
                warranty.setSerialNumber(rs.getString("serial_number"));
                warranty.setWarrantyStatus(rs.getString("warranty_status"));
                warranty.setRequestDate(rs.getDate("request_date"));
                warranty.setDoneDate(rs.getDate("done_date"));
                warranty.setDateReturn(rs.getDate("date_return"));
                warranty.setType(rs.getString("type"));
                warranty.setNote(rs.getString("note"));
                warranty.setImg(rs.getString("img"));
                warranty.setNoteAdmin(rs.getString("noteAdmin"));
                warranties.add(warranty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warranties;
    }

    public List<Warranty> getAllWarrantiesByStatus(String status) {
        List<Warranty> warranties = new ArrayList<>();
        String sql = "SELECT * FROM Warranty where warranty_status=?";
        try {
            Connection conn = connection;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Warranty warranty = new Warranty();
                warranty.setWarrantyId(rs.getInt("warranty_id"));
                warranty.setOrderId(rs.getInt("order_id"));
                warranty.setCustomerId(rs.getInt("customer_id"));
                warranty.setProductId(rs.getInt("product_id"));
                warranty.setSerialNumber(rs.getString("serial_number"));
                warranty.setWarrantyStatus(rs.getString("warranty_status"));
                warranty.setRequestDate(rs.getDate("request_date"));
                warranty.setDoneDate(rs.getDate("done_date"));
                warranty.setDateReturn(rs.getDate("date_return"));
                warranty.setType(rs.getString("type"));
                warranty.setNote(rs.getString("note"));
                warranty.setImg(rs.getString("img"));
                warranty.setNoteAdmin(rs.getString("noteAdmin"));
                warranties.add(warranty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warranties;
    }

    public List<Warranty> getAllWarrantiesByKey(String query) {
        List<Warranty> warranties = new ArrayList<>();
        String sql = "SELECT * FROM Warranty where query";
        try {
            Connection conn = connection;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Warranty warranty = new Warranty();
                warranty.setWarrantyId(rs.getInt("warranty_id"));
                warranty.setOrderId(rs.getInt("order_id"));
                warranty.setCustomerId(rs.getInt("customer_id"));
                warranty.setProductId(rs.getInt("product_id"));
                warranty.setSerialNumber(rs.getString("serial_number"));
                warranty.setWarrantyStatus(rs.getString("warranty_status"));
                warranty.setRequestDate(rs.getDate("request_date"));
                warranty.setDoneDate(rs.getDate("done_date"));
                warranty.setDateReturn(rs.getDate("date_return"));
                warranty.setType(rs.getString("type"));
                warranty.setNote(rs.getString("note"));
                warranty.setImg(rs.getString("img"));
                warranty.setNoteAdmin(rs.getString("noteAdmin"));
                warranties.add(warranty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warranties;
    }

    public List<Warranty> getWarrantiesByCustomerId(int customerId) {
        List<Warranty> warranties = new ArrayList<>();
        String sql = "SELECT * FROM Warranty WHERE customer_id = ?";

        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int warrantyId = rs.getInt("warranty_id");
                int orderId = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                String serialNumber = rs.getString("serial_number");
                String warrantyStatus = rs.getString("warranty_status");
                Date requestDate = rs.getDate("request_date");
                Date doneDate = rs.getDate("done_date");
                String note = rs.getString("note");
                String img = rs.getString("img");
                Warranty warranty = new Warranty(warrantyId, orderId, customerId, productId, serialNumber, warrantyStatus, requestDate, doneDate, note, img);
                warranty.setDateReturn(rs.getDate("date_return"));
                warranty.setType(rs.getString("type"));
                warranty.setNoteAdmin(rs.getString("noteAdmin"));
                warranties.add(warranty);
            }
        } catch (Exception e) {
            System.out.println("getWarrantiesByCustomerId: " + e);
        }
        return warranties;
    }

    public Warranty getWarrantyById(int warrantyId) {
        Warranty warranty = null;
        String sql = "SELECT * FROM Warranty WHERE warranty_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, warrantyId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                warranty = new Warranty();
                warranty.setWarrantyId(rs.getInt("warranty_id"));
                warranty.setOrderId(rs.getInt("order_id"));
                warranty.setCustomerId(rs.getInt("customer_id"));
                warranty.setProductId(rs.getInt("product_id"));
                warranty.setSerialNumber(rs.getString("serial_number"));
                warranty.setWarrantyStatus(rs.getString("warranty_status"));
                warranty.setRequestDate(rs.getDate("request_date"));
                warranty.setDoneDate(rs.getDate("done_date"));
                warranty.setDateReturn(rs.getDate("date_return"));
                warranty.setType(rs.getString("type"));
                warranty.setNote(rs.getString("note"));
                warranty.setImg(rs.getString("img"));
                warranty.setNoteAdmin(rs.getString("noteAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warranty;
    }

    public Orders checkWarrantiesBy(String serial, int customerId, int orderId) {
        Orders order = null;
        String sql = "select O.* from Product_Serial as Ps "
                + "join OrderDetails as OD "
                + "on OD.Serial_Number = Ps.Serial_Number "
                + "join Orders as O ON OD.OrderID = O.Order_Id "
                + "join Customers as C on C.Customer_ID = O.Customer_ID "
                + "join Warranty as W on W.order_id = O.Order_Id "
                + "where Ps.Serial_Number = ? and C.Customer_ID = ? and O.Order_Id=?";
        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, serial);
            ps.setInt(2, customerId);
            ps.setInt(3, orderId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int orderIdG = resultSet.getInt("Order_Id");
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("customer_ID");
                Date orderDate = resultSet.getDate("orderDate");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String note = resultSet.getString("note");
                order = new Orders(orderIdG, userId, status, orderDate, name, phone, address, note);
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Orders checkWarrantiesByInOrder(String serial) {
        Orders order = null;
        String sql = "select O.* from Product_Serial as Ps "
                + "join OrderDetails as OD "
                + "on OD.Serial_Number = Ps.Serial_Number "
                + "join Orders as O ON OD.OrderID = O.Order_Id "
                + "join Customers as C on C.Customer_ID = O.Customer_ID "
                + "join Warranty as W on W.order_id = O.Order_Id "
                + "where Ps.Serial_Number = ?";
        try {
            Connection conn = connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, serial);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int orderIdG = resultSet.getInt("Order_Id");
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("customer_ID");
                Date orderDate = resultSet.getDate("orderDate");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String note = resultSet.getString("note");
                order = new Orders(orderIdG, userId, status, orderDate, name, phone, address, note);
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateWarrantyStatus(int warrantyId, String newStatus, int productId, String note, String type, Date dateReturn, String image) {
        String sql = "UPDATE Warranty SET warranty_status = ?, product_id = ?, done_date = GETDATE(), noteAdmin=?, type=?, date_return =?, img=? WHERE warranty_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newStatus);
            statement.setInt(2, productId);
            statement.setString(3, note);
            statement.setString(4, type);
            statement.setDate(5, dateReturn);
            statement.setString(6, image);
            statement.setInt(7, warrantyId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Warranty: " + e);
            return false;
        }
    }

}
