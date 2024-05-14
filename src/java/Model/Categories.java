/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Categories {
    private int CateId;
    private String CateName;
    private int Stock;
    private String Description;

    public Categories(int CateId, String CateName, int Stock, String Description) {
        this.CateId = CateId;
        this.CateName = CateName;
        this.Stock = Stock;
        this.Description = Description;
    }

    public int getCateId() {
        return CateId;
    }

    public String getCateName() {
        return CateName;
    }

    public int getStock() {
        return Stock;
    }

    public String getDescription() {
        return Description;
    }

    public void setCateId(int CateId) {
        this.CateId = CateId;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
