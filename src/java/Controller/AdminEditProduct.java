/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.ProductSerial;
import Model.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author SHD
 */
public class AdminEditProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminEditProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Products product = new ProductDAO().getProductByIdNew(productId);
        if (product != null) {
            request.setAttribute("listC", new ProductDAO().getAllCategory());
            request.setAttribute("product", product);
            double price = product.getPrice();
            String formattedPrice = (product.getPrice() % 1 == 0) ? String.format("%.0f", product.getPrice()) : String.valueOf(product.getPrice());
            request.setAttribute("formattedPrice", formattedPrice);
            request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("products");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int cate_id = Integer.parseInt(request.getParameter("cate_id"));
        String image = request.getParameter("image");
        String detail = request.getParameter("detail");
        float price = Float.parseFloat(request.getParameter("price"));

        String[] serialNumbers = request.getParameterValues("serialNumbers");
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        Products product = productDAO.getProductByIdNew(productId);
        try {

            if (productId > 0) {

                if (product == null) {
                    request.setAttribute("error", "Product not found.");
                    request.setAttribute("listC", new ProductDAO().getAllCategory());
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
                    return;
                }
                product.setProductName(name);
                product.setCateId(cate_id);
                product.setImage(image);
                product.setDescription(detail);
                product.setPrice(price);
                product.setStock(serialNumbers.length);

                boolean updated = productDAO.updateProduct(product);
                if (!updated) {
                    request.setAttribute("error", "Failed to update product.");
                    request.setAttribute("listC", new ProductDAO().getAllCategory());
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
                    return;
                }

                productDAO.deleteProductSerialByProductId(productId);

                if (serialNumbers != null) {
                    for (String serialNumber : serialNumbers) {
                        ProductSerial productSerial = new ProductSerial();
                        productSerial.setProduct_ID(productId);
                        productSerial.setSerial_Number(serialNumber);
                        boolean added = productDAO.addProductSerial(productSerial);
                        if (!added) {
                            request.setAttribute("error", "Failed to add serial number: " + serialNumber);
                            request.setAttribute("listC", new ProductDAO().getAllCategory());
                            request.setAttribute("product", product);
                            request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
                            return;
                        }
                    }
                }
            } else {
                Products productEdit = new Products();
                productEdit.setProductName(name);
                productEdit.setCateId(cate_id);
                productEdit.setImage(image);
                productEdit.setDescription(detail);
                productEdit.setPrice(price);
                productEdit.setStock(serialNumbers.length);

                productId = productDAO.addProduct(product);
                if (productId == -1) {
                    request.setAttribute("error", "Failed to add product.");
                    request.setAttribute("listC", new ProductDAO().getAllCategory());
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
                    return;
                }

                if (serialNumbers != null) {
                    for (String serialNumber : serialNumbers) {
                        ProductSerial productSerial = new ProductSerial();
                        productSerial.setProduct_ID(productId);
                        productSerial.setSerial_Number(serialNumber);
                        boolean added = productDAO.addProductSerial(productSerial);
                        if (!added) {
                            request.setAttribute("listC", new ProductDAO().getAllCategory());
                            request.setAttribute("product", product);
                            request.setAttribute("error", "Failed to add serial number: ");
                            request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
                            return;
                        }
                    }
                }
            }

            response.sendRedirect("products");

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("listC", new ProductDAO().getAllCategory());
            request.setAttribute("product", product);
            request.setAttribute("error", "An unexpected error occurred.");
            request.getRequestDispatcher("../Layout/admin/products/products_edit.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
