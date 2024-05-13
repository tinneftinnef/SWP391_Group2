/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class PaymentDetails {
    String PaymentQr, paymentStatus, paymentPhone, paymentEmail, paymentDetails;
    int payId, paymentTransaction;

    public PaymentDetails() {
    }

    public PaymentDetails(String PaymentQr, String paymentStatus, String paymentPhone, String paymentEmail, String paymentDetails, int payId, int paymentTransaction) {
        this.PaymentQr = PaymentQr;
        this.paymentStatus = paymentStatus;
        this.paymentPhone = paymentPhone;
        this.paymentEmail = paymentEmail;
        this.paymentDetails = paymentDetails;
        this.payId = payId;
        this.paymentTransaction = paymentTransaction;
    }

    public String getPaymentQr() {
        return PaymentQr;
    }

    public void setPaymentQr(String PaymentQr) {
        this.PaymentQr = PaymentQr;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentPhone() {
        return paymentPhone;
    }

    public void setPaymentPhone(String paymentPhone) {
        this.paymentPhone = paymentPhone;
    }

    public String getPaymentEmail() {
        return paymentEmail;
    }

    public void setPaymentEmail(String paymentEmail) {
        this.paymentEmail = paymentEmail;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(int paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }
    
}
