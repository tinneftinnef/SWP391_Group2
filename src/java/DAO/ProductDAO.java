/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import Model.Categories;
import Model.ProductSerial;
import Model.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SHD
 */
public class ProductDAO extends DBContext {

    public List<Products> getAllProductAdmin() {
        List<Products> requests = new ArrayList<>();

        try {
            String sql = "select * from Products order by ProductId desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Products request = new Products(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(4));
                requests.add(request);
            }

        } catch (Exception e) {
            System.out.println("Eerror:" + e);
        }
        return requests;
    }

    public Products getProductByCart(int id) {

        try {
            String sql = "select * from Products where ProductId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Products p = new Products(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(4));
                p.setSerial_Number("");
                return p;
            }

        } catch (Exception e) {
            System.out.println("Errpr: " + e);
        }
        return null;
    }

    public Products getProductAdmin(int id) {

        try {
            String sql = "select * from Products where ProductId = ? order by ProductId desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Products(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(4));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Products> getAllProductByCategoryIDAdmin(int cate_id) {
        List<Products> requests = new ArrayList<>();

        try {
            String sql = "select * from Products where CateId = ? order by ProductId desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cate_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Products request = new Products(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(4));
                requests.add(request);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return requests;
    }

    public static void main(String[] args) {
        System.out.println(new ProductDAO().getAllCategory().size());
    }

    public List<Categories> getAllCategory() {
        List<Categories> requests = new ArrayList<>();

        try {
            String sql = "select * from Categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categories request = new Categories(rs.getInt(1), rs.getString(2));
                requests.add(request);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return requests;
    }

    public boolean checkSerial(String serial) {

        try {
            String sql = "select * from Products where Serial_Number like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, serial);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int addProduct(String name, int cate_id, String image, String detail, float price, int stock, String serial) {
        int productId = -1;
        try {
            String sql1 = "INSERT INTO [dbo].[Products]\n"
                    + "           ([ProductName]\n"
                    + "           ,[Price]\n"
                    + "           ,[CateId]\n"
                    + "           ,[Image]\n"
                    + "           ,[Stock]\n"
                    + "           ,[Description]\n"
                    + "     OUTPUT INSERTED.ProductId\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, name);
            stm.setFloat(2, price);
            stm.setInt(3, cate_id);
            stm.setString(4, image);
            stm.setInt(5, stock);
            stm.setString(6, detail);

            int rowsInserted = stm.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    productId = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productId;
    }

    public int addProduct(Products product) {
        int productId = -1;

        try {
            String sql = "INSERT INTO [dbo].[Products]\n"
                    + "           ([ProductName]\n"
                    + "           ,[Price]\n"
                    + "           ,[CateId]\n"
                    + "           ,[Image]\n"
                    + "           ,[Stock]\n"
                    + "           ,[Description])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, product.getProductName());
            stm.setDouble(2, product.getPrice());
            stm.setInt(3, product.getCateId());
            stm.setString(4, product.getImage());
            stm.setInt(5, product.getStock());
            stm.setString(6, product.getDescription());

            int rowsInserted = stm.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    productId = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productId;
    }

    public void editProduct(String name, int cate_id, String image, String detail, float price, int stock, int id) {
        try {
            String sql1 = "UPDATE [dbo].[Products]\n"
                    + "   SET [ProductName] = ?\n"
                    + "      ,[Price] = ?\n"
                    + "      ,[CateId] = ?\n"
                    + "      ,[Image] = ?\n"
                    + "      ,[Stock] = ?\n"
                    + "      ,[Description] = ?\n"
                    + " WHERE ProductId = ?";

            PreparedStatement stm = connection.prepareStatement(sql1);
            stm.setString(1, name);
            stm.setFloat(2, price);
            stm.setInt(3, cate_id);
            stm.setString(4, image);
            stm.setInt(5, stock);
            stm.setString(6, detail);
            stm.setInt(7, id);
            stm.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    public void editStockProduct(int stock, int id) {
        try {
            String sql1 = "UPDATE [dbo].[Products]\n"
                    + "   SET [Stock] = ?\n"
                    + " WHERE ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql1);
            stm.setInt(1, stock);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Edit stock product: " + ex);
        }
    }

    public void deleteProduct(int id) {
        try {
            String sql1 = "DELETE FROM [dbo].[Products]\n"
                    + "      WHERE ProductId = ?";

            PreparedStatement stm = connection.prepareStatement(sql1);
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (SQLException ex) {

        }
    }
//  home page

    public Map<Categories, List<Products>> getProductsByCategory() {
        String sql = "SELECT c.*, p.* FROM Categories c JOIN Products p ON c.CateId = p.CateId ORDER BY c.CateId, p.ProductId desc";
        Map<Categories, List<Products>> productsByCategory = new LinkedHashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Categories currentCategory = null;
            List<Products> productList = null;
            while (rs.next()) {
                int cateId = rs.getInt("CateId");
                String cateName = rs.getString("CateName");
                if (currentCategory == null || currentCategory.getCateId() != cateId) {
                    currentCategory = new Categories(cateId, cateName);
                    productList = new ArrayList<>();
                    productsByCategory.put(currentCategory, productList);
                }
                Products product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setImage(rs.getString("Image"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                productList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error fetching products by category: " + e);
        }
        return productsByCategory;
    }

    public List<Products> getProductsBy() {
        String sql = "SELECT p.* FROM Categories c JOIN Products p ON c.CateId = p.CateId ORDER BY c.CateId, p.ProductId desc";
        List<Products> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Categories currentCategory = null;
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setImage(rs.getString("Image"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error fetching products: " + e);
        }
        return products;
    }

    public List<Products> getProductsByCategory(int categoryId) {
        String sql = "SELECT p.* FROM Categories c JOIN Products p ON c.CateId = p.CateId ";
        if (categoryId > 0) {
            sql += "WHERE p.CateId=? ";
        }
        sql += "ORDER BY c.CateId, p.ProductId desc";
        List<Products> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (categoryId > 0) {
                ps.setInt(1, categoryId);
            }
            ResultSet rs = ps.executeQuery();
            Categories currentCategory = null;
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setImage(rs.getString("Image"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error fetching products category: " + e);
        }
        return products;
    }

    public Map<Categories, List<Products>> searchProductsByCategoryName(String categoryName) {
        String sql = "SELECT c.*, p.* FROM Categories c JOIN Products p ON c.CateId = p.CateId WHERE c.CateName LIKE ? or p.ProductName LIKE ? ORDER BY c.CateId, p.ProductId desc";
        Map<Categories, List<Products>> productsByCategory = new LinkedHashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + categoryName + "%");
            ps.setString(2, "%" + categoryName + "%");
            ResultSet rs = ps.executeQuery();
            Categories currentCategory = null;
            List<Products> productList = null;
            while (rs.next()) {
                int cateId = rs.getInt("CateId");
                String cateName = rs.getString("CateName");
                if (currentCategory == null || currentCategory.getCateId() != cateId) {
                    currentCategory = new Categories(cateId, cateName);
                    productList = new ArrayList<>();
                    productsByCategory.put(currentCategory, productList);
                }
                Products product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setImage(rs.getString("Image"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                productList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error searching products by category name: " + e);
        }
        return productsByCategory;
    }

    public Map<Categories, List<Products>> getProductsByCategoryId(int cateId, int productId) {
        String sql = "SELECT c.*, p.* FROM Categories c JOIN Products p ON c.CateId = p.CateId WHERE c.CateId = ? and p.ProductId != ? ORDER BY c.CateId, p.ProductId desc";
        Map<Categories, List<Products>> productsByCategory = new LinkedHashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cateId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            Categories currentCategory = null;
            List<Products> productList = null;
            while (rs.next()) {
                String cateName = rs.getString("CateName");
                if (currentCategory == null || currentCategory.getCateId() != cateId) {
                    currentCategory = new Categories(cateId, cateName);
                    productList = new ArrayList<>();
                    productsByCategory.put(currentCategory, productList);
                }
                Products product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setImage(rs.getString("Image"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                productList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error searching products by category name: " + e);
        }
        return productsByCategory;
    }

    public Map<Categories, Products> getProductById(int productId) {
        String sql = "SELECT c.CateId, c.CateName, p.* FROM Categories c JOIN Products p ON c.CateId = p.CateId WHERE ProductId = ? ORDER BY c.CateId";
        Map<Categories, Products> productsByCategory = new LinkedHashMap<>();
        Products product = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Products();
                int cateId = rs.getInt("CateId");
                String cateName = rs.getString("CateName");
                Categories currentCategory = new Categories(cateId, cateName);
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setImage(rs.getString("Image"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                productsByCategory.put(currentCategory, product);
            }
        } catch (Exception e) {
            System.out.println("Error fetching product by ID: " + e);
        }
        return productsByCategory;
    }

    public boolean isExistSerialNumber(ProductSerial productSerial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connection;
            String checkQuery = "SELECT COUNT(*) AS count FROM Product_Serial WHERE Serial_Number = ?";
            stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, productSerial.getSerial_Number());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addProductSerial(ProductSerial productSerial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connection;
            String checkQuery = "SELECT COUNT(*) AS count FROM Product_Serial WHERE Serial_Number = ?";
            stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, productSerial.getSerial_Number());
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    return false;
                }
            }
            String insertQuery = "INSERT INTO Product_Serial (Product_ID, Serial_Number) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            stmt.setInt(1, productSerial.getProduct_ID());
            stmt.setString(2, productSerial.getSerial_Number());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Products getProductByIdNew(int productId) {
        Products product = null;
        String sql = "SELECT * FROM [dbo].[Products] WHERE [ProductId] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setCateId(rs.getInt("CateId"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setSerialNumbers(getSerialNumbersByProductId(productId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public Products getProductByIdSerial(String Serial_Number) {
        Products product = null;
        String sql = "select P.* from Products as P "
                + "join Product_Serial as Ps on Ps.Product_ID = P.ProductId where Ps.Serial_Number = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, Serial_Number);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                product = new Products();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setCateId(rs.getInt("CateId"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getFloat("Price"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    private List<String> getSerialNumbersByProductId(int productId) {
        List<String> serialNumbers = new ArrayList<>();
        String sql = "SELECT [Serial_Number] FROM [dbo].[Product_Serial] WHERE [Product_ID] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                serialNumbers.add(rs.getString("Serial_Number"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return serialNumbers;
    }

    public boolean deleteProductSerialByProductId(int productId) throws SQLException {
        boolean deleted = false;
        String sql = "DELETE FROM Product_Serial WHERE Product_ID = ? and Serial_Number not in (select Ps.Serial_Number from Product_Serial as Ps \n"
                + "               join OrderDetails as OD \n"
                + "                on OD.Serial_Number = Ps.Serial_Number \n"
                + "                join Orders as O ON OD.OrderID = O.Order_Id \n"
                + "                join Customers as C on C.Customer_ID = O.Customer_ID \n"
                + "                join Warranty as W on W.order_id = O.Order_Id)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, productId);

            int rowsAffected = stm.executeUpdate();
            deleted = (rowsAffected > 0);
        }
        return deleted;
    }

    public List<String> getProductSerialsByProductId(int productId) throws SQLException {
        List<String> serialNumbers = new ArrayList<>();
        String sql = "SELECT Serial_Number FROM Product_Serial WHERE Product_ID = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, productId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                serialNumbers.add(rs.getString("Serial_Number"));
            }
        }
        return serialNumbers;
    }

    public boolean updateProduct(Products product) throws SQLException {
        boolean updated = false;
        String sql = "UPDATE Products SET ProductName = ?, CateId = ?, Image = ?, "
                + "Description = ?, Price = ?, Stock = ? WHERE ProductId = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, product.getProductName());
            stm.setInt(2, product.getCateId());
            stm.setString(3, product.getImage());
            stm.setString(4, product.getDescription());
            stm.setDouble(5, product.getPrice());
            stm.setInt(6, product.getStock());
            stm.setInt(7, product.getProductId());

            int rowsAffected = stm.executeUpdate();
            updated = (rowsAffected == 1);
        }
        return updated;
    }

    public List<String> getAvailableSerials(int productId, int quantity) {
        List<String> serials = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connection;
            String sql = "SELECT * FROM Product_Serial as  PS1 where PS1.Product_ID = ? AND  PS1.Serial_Number not in "
                    + "(select PS.Serial_Number from Product_Serial as Ps\n"
                    + "join OrderDetails as OD "
                    + "on OD.Serial_Number = Ps.Serial_Number WHERE PS.Product_ID = ? "
                    + "GROUP BY PS.Serial_Number)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.setInt(2, productId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String serialNumber = rs.getString("Serial_Number");
                serials.add(serialNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serials;
    }

    public List<Products> getBestAndLeastProducts() {
        List<Products> productsList = new ArrayList<>();

        String sql = "WITH BestSellingProducts AS (\n"
                + "    SELECT TOP 5 \n"
                + "        p.productId, \n"
                + "        p.productName,\n"
                + "        SUM(od.quantity) AS totalSold\n"
                + "    FROM \n"
                + "        Products p\n"
                + "    JOIN \n"
                + "        OrderDetails od ON p.productId = od.productId\n"
                + "    GROUP BY \n"
                + "        p.productId, p.productName\n"
                + "    ORDER BY \n"
                + "        totalSold DESC\n"
                + "),\n"
                + "LeastWarrantyProducts AS (\n"
                + "    SELECT TOP 5\n"
                + "        p.productId AS productId_lwp,\n"
                + "        p.productName AS productName_lwp,\n"
                + "        COUNT(w.warranty_id) AS totalWarranties\n"
                + "    FROM\n"
                + "        Products p\n"
                + "	JOIN Product_Serial as PS on PS.Product_ID = P.ProductId\n"
                + "    JOIN\n"
                + "        Warranty w ON PS.Serial_Number = w.serial_number\n"
                + "    GROUP BY\n"
                + "        p.productId, p.productName\n"
                + "    ORDER BY\n"
                + "        totalWarranties ASC\n"
                + "),\n"
                + "BestAndLeastProducts AS (\n"
                + "    SELECT \n"
                + "        bsp.productId,\n"
                + "        bsp.productName\n"
                + "    FROM \n"
                + "        BestSellingProducts bsp\n"
                + "    JOIN \n"
                + "        LeastWarrantyProducts lwp ON bsp.productId = lwp.productId_lwp\n"
                + "),\n"
                + "RankedProducts AS (\n"
                + "    SELECT \n"
                + "        p.productId,\n"
                + "        p.productName,\n"
                + "        ROW_NUMBER() OVER (ORDER BY bsp.totalSold DESC) AS SoldRank,\n"
                + "        ROW_NUMBER() OVER (ORDER BY lwp.totalWarranties ASC) AS WarrantyRank\n"
                + "    FROM \n"
                + "        Products p\n"
                + "    LEFT JOIN \n"
                + "        BestSellingProducts bsp ON p.productId = bsp.productId\n"
                + "    LEFT JOIN \n"
                + "        LeastWarrantyProducts lwp ON p.productId = lwp.productId_lwp\n"
                + ")\n"
                + "SELECT TOP 5\n"
                + "   PRO.*\n"
                + "FROM \n"
                + "    RankedProducts as RP\n"
                + "join Products as PRO on RP.ProductId = PRO.ProductId\n"
                + "WHERE \n"
                + "    (SoldRank <= 5 OR WarrantyRank <= 5);";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setCateId(rs.getInt("CateId"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                productsList.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productsList;
    }

}
