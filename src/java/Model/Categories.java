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
    
    private String Description;
    
    public Categories(){
        
    }

    public Categories(int CateId, String CateName, String Description) {
        this.CateId = CateId;
        this.CateName = CateName;
        
        this.Description = Description;
    }

    public int getCateId() {
        return CateId;
    }

    public String getCateName() {
        return CateName;
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

    

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
