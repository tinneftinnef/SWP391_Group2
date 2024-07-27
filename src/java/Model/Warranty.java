/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Warranty {
    private int warrantyId;
    private int orderId;
    private int customerId;
    private int productId;
    private String serialNumber;
    private String warrantyStatus;
    private Date requestDate;
    private Date doneDate;
    private Date dateReturn;
    private String type;
    private String note;
    private String img;
    private String noteAdmin;

    public Warranty() {}

    public Warranty(int warrantyId, int orderId, int customerId, int productId, String serialNumber, String warrantyStatus, Date requestDate, Date doneDate, String note, String img) {
        this.warrantyId = warrantyId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.serialNumber = serialNumber;
        this.warrantyStatus = warrantyStatus;
        this.requestDate = requestDate;
        this.doneDate = doneDate;
        this.note = note;
        this.img = img;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getNoteAdmin() {
        return noteAdmin;
    }

    public void setNoteAdmin(String noteAdmin) {
        this.noteAdmin = noteAdmin;
    }
    
    public int getWarrantyId() {
        return warrantyId;
    }
    
    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(String warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
