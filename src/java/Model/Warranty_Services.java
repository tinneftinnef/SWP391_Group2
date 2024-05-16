/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Warranty_Services {
    int warrantyId, Product_ID, CustomerID;
    String warrantys_tart, warranty_end, Serial_Number, Des_note, image, Staff_Name, Done_date;

    public Warranty_Services() {
    }

    public Warranty_Services(int warrantyId, int Product_ID, int CustomerID, String warrantys_tart, String warranty_end, String Serial_Number, String Des_note, String image, String Staff_Name, String Done_date) {
        this.warrantyId = warrantyId;
        
        this.Product_ID = Product_ID;
        this.CustomerID = CustomerID;
        this.warrantys_tart = warrantys_tart;
        this.warranty_end = warranty_end;
        this.Serial_Number = Serial_Number;
        this.Des_note = Des_note;
        this.image = image;
        this.Staff_Name = Staff_Name;
        this.Done_date = Done_date;
    }

    public int getWarrantyId() {
        return warrantyId;
    }

   

    public int getProduct_ID() {
        return Product_ID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public String getWarrantys_tart() {
        return warrantys_tart;
    }

    public String getWarranty_end() {
        return warranty_end;
    }

    public String getSerial_Number() {
        return Serial_Number;
    }

    public String getDes_note() {
        return Des_note;
    }

    public String getImage() {
        return image;
    }

    public String getStaff_Name() {
        return Staff_Name;
    }

    public String getDone_date() {
        return Done_date;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setWarrantys_tart(String warrantys_tart) {
        this.warrantys_tart = warrantys_tart;
    }

    public void setWarranty_end(String warranty_end) {
        this.warranty_end = warranty_end;
    }

    public void setSerial_Number(String Serial_Number) {
        this.Serial_Number = Serial_Number;
    }

    public void setDes_note(String Des_note) {
        this.Des_note = Des_note;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStaff_Name(String Staff_Name) {
        this.Staff_Name = Staff_Name;
    }

    public void setDone_date(String Done_date) {
        this.Done_date = Done_date;
    }

    
    
}
