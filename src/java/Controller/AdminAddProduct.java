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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author SHD
 */
public class AdminAddProduct extends HttpServlet {

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
            out.println("<title>Servlet AdminAddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAddProduct at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("listC", new ProductDAO().getAllCategory());
        request.getRequestDispatcher("../Layout/admin/products/products_add.jsp").forward(request, response);
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
        request.setAttribute("listC", new ProductDAO().getAllCategory());
        if (serialNumbers != null && serialNumbers.length > 0) {
            Set<String> uniqueSerials = new HashSet<>(Arrays.asList(serialNumbers));
            if (uniqueSerials.size() != serialNumbers.length) {
                request.setAttribute("error", "Duplicate serial numbers are not allowed.");
                request.getRequestDispatcher("../Layout/admin/products/products_add.jsp").forward(request, response);
                return;
            }

            try {
                ProductDAO productDAO = new ProductDAO();
                Products product = new Products();
                product.setProductName(name);
                product.setCateId(cate_id);
                product.setImage(image);
                product.setDescription(detail);
                product.setPrice(price);
                product.setStock(serialNumbers.length);
                for (String serialNumber : serialNumbers) {
                    ProductSerial productSerial = new ProductSerial();
                    productSerial.setSerial_Number(serialNumber);
                    boolean added = productDAO.isExistSerialNumber(productSerial);
                    if (!added) {
                        request.setAttribute("error", "Duplicate serial number: " + serialNumber);
                        request.getRequestDispatcher("../Layout/admin/products/products_add.jsp").forward(request, response);
                        return;
                    }
                }

                int productId = productDAO.addProduct(product);

                if (productId != -1) {
                    for (String serialNumber : serialNumbers) {
                        ProductSerial productSerial = new ProductSerial();
                        productSerial.setProduct_ID(productId);
                        productSerial.setSerial_Number(serialNumber);
                        boolean added = productDAO.addProductSerial(productSerial);
                        if (!added) {
                            request.setAttribute("error", "Duplicate serial number: " + serialNumber);
                            request.getRequestDispatcher("../Layout/admin/products/products_add.jsp").forward(request, response);
                            return;
                        }
                    }
                }
                response.sendRedirect("products");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            request.setAttribute("error", "Serial numbers are required.");
            request.getRequestDispatcher("../Layout/admin/products/products_add.jsp").forward(request, response);
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
