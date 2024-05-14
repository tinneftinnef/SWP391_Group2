/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Payments {
    int payId, userId, OderId, totalAmount ;
    String payDate, currencyCode;

    public Payments() {
    }

    public Payments(int payId, int userId, int OderId, int totalAmount, String payDate, String currencyCode) {
        this.payId = payId;
        this.userId = userId;
        this.OderId = OderId;
        this.totalAmount = totalAmount;
        this.payDate = payDate;
        this.currencyCode = currencyCode;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOderId() {
        return OderId;
    }

    public void setOderId(int OderId) {
        this.OderId = OderId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    
}
