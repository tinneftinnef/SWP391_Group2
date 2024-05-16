/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Products {
    private int ProductId;
    private String ProductName;
    private double Price;
    private String Image;
    private int Stock;
    private String Description;
    private int CateId;
    private String Serial_Number;

    public Products(){
        
    }
    
    public Products(int ProductId, String ProductName, double Price, String Image, int Stock, String Description, int CateId, String Serial_Number) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Image = Image;
        this.Stock = Stock;
        this.Description = Description;
        this.CateId = CateId;
        this.Serial_Number = Serial_Number;
    }

    public void setSerial_Number(String Serial_Number) {
        this.Serial_Number = Serial_Number;
    }

    public String getSerial_Number() {
        return Serial_Number;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getPrice() {
        return Price;
    }

    public String getImage() {
        return Image;
    }

    public int getStock() {
        return Stock;
    }

    public String getDescription() {
        return Description;
    }

    public int getCateId() {
        return CateId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setCateId(int CateId) {
        this.CateId = CateId;
    }
    
    
    
            
}
