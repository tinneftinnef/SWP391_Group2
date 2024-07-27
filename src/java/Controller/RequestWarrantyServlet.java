/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CustomerDAO;
import DAO.ProductDAO;
import DAO.WarrantyDAO;
import Model.Customers;
import Model.Products;
import Model.Upload;
import Model.Warranty;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "RequestWarrantyServlet", urlPatterns = {"/warranty"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class RequestWarrantyServlet extends HttpServlet {

    private WarrantyDAO warrantyDAO;

    @Override
    public void init() {
        warrantyDAO = new WarrantyDAO();
    }

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
            out.println("<title>Servlet RequestWarrantyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestWarrantyServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "view":
                this.viewWarranty(request, response);
                break;
            default:
                this.listWarranty(request, response);
                break;
        }
    }

    private void viewWarranty(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String accCustomer = (String) session.getAttribute("customerSave");
            CustomerDAO customerDao = new CustomerDAO();
            Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);

            if (customerLogin != null) {
                int warrantyId = Integer.parseInt(request.getParameter("id"));
                Warranty warranty = warrantyDAO.getWarrantyById(warrantyId);
                if (warranty != null) {
                    ProductDAO productDao = new ProductDAO();
                    Products product = productDao.getProductByIdSerial(warranty.getSerialNumber());
                    request.setAttribute("account", customerLogin);
                    request.setAttribute("product", product);
                    request.setAttribute("warranty", warranty);
                    request.getRequestDispatcher("./Layout/cart/view-warranty.jsp").forward(request, response);
                } else {
                    response.sendRedirect("warranty?error=Can not found this warranty");
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (Exception e) {
            System.out.println("View warranty: " + e);
        }
    }

    private void listWarranty(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String accCustomer = (String) session.getAttribute("customerSave");
            CustomerDAO customerDao = new CustomerDAO();
            Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);

            if (customerLogin != null) {
                int customerId = Integer.parseInt(customerLogin.getCustomerID());
                List<Warranty> warranties = warrantyDAO.getWarrantiesByCustomerId(customerId);
                request.setAttribute("warranties", warranties);
                request.getRequestDispatcher("./Layout/cart/warranty.jsp").forward(request, response);
            } else {
                response.sendRedirect("login");
            }
        } catch (Exception e) {
            System.out.println("List warranty: " + e);
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
        Upload upload = new Upload();
        HttpSession session = request.getSession();
        String accCustomer = (String) session.getAttribute("customerSave");
        CustomerDAO customerDao = new CustomerDAO();
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        if (customerLogin != null) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int productId = 0;
            String serialNumber = request.getParameter("serialNumber");
            int customerId = Integer.parseInt(customerLogin.getCustomerID());
            String note = request.getParameter("note");
            Warranty warranty = new Warranty();
            warranty.setOrderId(orderId);
            warranty.setCustomerId(customerId);
            warranty.setProductId(productId);
            warranty.setSerialNumber(serialNumber);
            warranty.setWarrantyStatus("Pending");
            warranty.setRequestDate(new Date(System.currentTimeMillis()));
            warranty.setDoneDate(null);
            warranty.setNote(note);

            boolean isRequested = warrantyDAO.requestWarranty(warranty);
            if (isRequested) {
                response.sendRedirect("order-history?action=warranty&type=1");
            } else {
                response.sendRedirect("order-history?action=warranty&type=0");
            }
        } else {
            response.sendRedirect("login");
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
