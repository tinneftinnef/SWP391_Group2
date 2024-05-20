/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Accounts {
    private String acountlogin;
    private String Name;
    private String pass;
    private int roleid;

    public Accounts() {
    }

    public Accounts(String acountlogin, String Name, String pass, int roleid) {
        this.acountlogin = acountlogin;
        this.Name = Name;
        this.pass = pass;
        this.roleid = roleid;
    }

    public String getAcountlogin() {
        return acountlogin;
    }

    public void setAcountlogin(String acountlogin) {
        this.acountlogin = acountlogin;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Account{" + "acountlogin=" + acountlogin + ", Name=" + Name + ", pass=" + pass + ", roleid=" + roleid + '}';
    }


    
    
}
