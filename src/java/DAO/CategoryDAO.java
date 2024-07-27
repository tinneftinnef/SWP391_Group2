/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CategoryDAO extends DBContext {

    public List<Categories> getAllCategoryWithQuantity() {
        String sql = "SELECT \n"
                + "    c.CateId, \n"
                + "    c.CateName, \n"
                + "    c.Description, \n"
                + "    SUM(p.Stock) AS stock \n"
                + "FROM \n"
                + "    Categories c \n"
                + "LEFT JOIN \n"
                + "    Products p \n"
                + "ON \n"
                + "    c.CateId = p.CateId \n"
                + "GROUP BY \n"
                + "    c.CateId, \n"
                + "    c.CateName, \n"
                + "    c.Description;";
        List<Categories> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categories category = new Categories(
                        rs.getInt("CateId"),
                        rs.getString("CateName"),
                        rs.getInt("Stock"),
                        rs.getString("Description")
                );
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println("Get all category with quantity: " + e);
        }
        return categories;
    }

}
