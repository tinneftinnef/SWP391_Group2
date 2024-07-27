/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Services {
    int serviceId, userID, orderDetail;
    String startDate;

    public Services() {
    }

    public Services(int serviceId, int userID, int orderDetail, String startDate) {
        this.serviceId = serviceId;
        this.userID = userID;
        this.orderDetail = orderDetail;
        this.startDate = startDate;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(int orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
}
