/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Product_Serial {
    private String Serial_Number;
    private int Product_ID;
    
    public Product_Serial(){
        
    }

    public Product_Serial(String Serial_Number, int Product_ID) {
        this.Serial_Number = Serial_Number;
        this.Product_ID = Product_ID;
    }

    public String getSerial_Number() {
        return Serial_Number;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public void setSerial_Number(String Serial_Number) {
        this.Serial_Number = Serial_Number;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }
    
    
}
