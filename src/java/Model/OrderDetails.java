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
    String Serial_Number;
    int Warranty_ID;
            

    public OrderDetails() {
    }

    public OrderDetails(int orderID, int productId, int quantity, double price, double discount, String Serial_Number, int Warranty_ID) {
        this.orderID = orderID;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.Serial_Number = Serial_Number;
        this.Warranty_ID = Warranty_ID;
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

    public String getSerial_Number() {
        return Serial_Number;
    }

    public int getWarranty_ID() {
        return Warranty_ID;
    }

    public void setSerial_Number(String Serial_Number) {
        this.Serial_Number = Serial_Number;
    }

    public void setWarranty_ID(int Warranty_ID) {
        this.Warranty_ID = Warranty_ID;
    }
    
}
