/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Accounts;
/**
 *
 * @author dell
 */
public class AccountDAO {
//    public Accounts LoginAccount(String email, String password) {
//        try {
//            PreparedStatement ps;
//            ResultSet rs;
//            String sql = "SELECT * FROM [GreenRoom].[dbo].[Account] where userMail = ? and userPassword = ?";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, email);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Account a = new Account();
//                a.setUserID(rs.getInt(1));
//                a.setUserMail(rs.getString(2));
//                a.setUserPassword(rs.getString(4));
//                a.setUserRole(rs.getInt(4));
//                return a;
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
