/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Customers {
    private int CustomerID, phone;
    private String CustomerName, address, email, accName, password, User_type;
    
    public Customers(){
        
    }

    public Customers(int CustomerID, int phone, String CustomerName, String address, String email, String accName, String password, String User_type) {
        this.CustomerID = CustomerID;
        this.phone = phone;
        this.CustomerName = CustomerName;
        this.address = address;
        this.email = email;
        this.accName = accName;
        this.password = password;
        this.User_type = User_type;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getPhone() {
        return phone;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getAccName() {
        return accName;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_type() {
        return User_type;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_type(String User_type) {
        this.User_type = User_type;
    }
    
}
