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
    int warrantyId,userId,StatusID;
    String warrantystart, warrantyend;

    public Warranty_Services() {
    }

    public Warranty_Services(int warrantyId, int userId, int StatusID, String warrantystart, String warrantyend) {
        this.warrantyId = warrantyId;
        this.userId = userId;
        this.StatusID = StatusID;
        this.warrantystart = warrantystart;
        this.warrantyend = warrantyend;
    }

    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public String getWarrantystart() {
        return warrantystart;
    }

    public void setWarrantystart(String warrantystart) {
        this.warrantystart = warrantystart;
    }

    public String getWarrantyend() {
        return warrantyend;
    }

    public void setWarrantyend(String warrantyend) {
        this.warrantyend = warrantyend;
    }
    
}
