/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author dell
 */

public class MyDAO extends DBContext{
    public Connection cn = null; //Gán connection from DBContext cho 'cn' 
    public PreparedStatement ps = null; // sử dụng để chuẩn bị và thực thi các câu lệnh SQL động
    public ResultSet rs = null; //là một đối tượng chứa kết quả của một câu lệnh SQL đã được thực thi
    //public String xSql = null;

    public MyDAO() {
        cn = connection;
    }

    @Override
    public void finalize() {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}