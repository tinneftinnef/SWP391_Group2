/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author laptop
 */
public class AccountDao extends DBContext {
        public Account getAccountBy(String username, String password) {
        try {
            String sql = """
                         SELECT * FROM Accounts
                         WHERE Name = ?
                         AND Password = ?""";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAcountlogin(rs.getString("Account_Login"));
                account.setName(rs.getString("Name"));
                account.setPass(rs.getString("Password"));
                account.setRoleid(rs.getInt("RoleId"));
                return account;
            }
        } catch (SQLException ex) {
          
        }
        return null;
    }
        public static void main(String[] args) {
            System.out.println( new AccountDao().getAccountBy("Huy", "123"));
    }
}
