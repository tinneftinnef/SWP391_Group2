/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Account {
    private String acountlogin;
    private String Name;
    private String pass;
    private int roleid;

    public Account() {
    }

    public Account(String acountlogin, String Name, String pass, int roleid) {
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
    

   