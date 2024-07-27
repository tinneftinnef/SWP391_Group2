/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class OrderDetails {

    int orderID, productId, quantity;
    double price, discount;
    String serialNumber, warranty_ID;

    public OrderDetails(int orderID, int productId, int quantity, double price, double discount, String serialNumber, String warranty_ID) {
        this.orderID = orderID;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.serialNumber = serialNumber;
        this.warranty_ID = warranty_ID;
    }

    public OrderDetails() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWarranty_ID() {
        return warranty_ID;
    }

    public void setWarranty_ID(String warranty_ID) {
        this.warranty_ID = warranty_ID;
    }
}
