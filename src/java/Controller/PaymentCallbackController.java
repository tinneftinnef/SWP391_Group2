/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import Model.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "PaymentCallbackController", urlPatterns = {"/payment-callback"})
public class PaymentCallbackController extends HttpServlet {

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
            out.println("<title>Servlet PaymentCallbackController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentCallbackController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String accCustomer = (String) session.getAttribute("customerSave");
        CustomerDAO customerDao = new CustomerDAO();
        CartDAO cartDAO = new CartDAO();
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        int orderID = 0;
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        try {
            orderID = Integer.parseInt(session.getAttribute("orderId") + "");
            String transactionStatus = request.getParameter("vnp_TransactionStatus");
            if ("00".equals(transactionStatus)) {
                int result = cartDAO.deleteCartByCustomer(Integer.parseInt(customerLogin.getCustomerID()));
                if (result > 0) {
                    response.sendRedirect("order-history?action=status&type=1&orderId=" + orderID);
                } else {
                    orderDao.deleteOrder(orderID);
                    response.sendRedirect("order-history?action=status&type=0");
                }
            } else {
                orderDetailDAO.deleteOrder(orderID);
                orderDao.deleteOrder(orderID);
                response.sendRedirect("order-history?action=status&type=0");
            }
        } catch (Exception e) {
            response.sendRedirect("cart");
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
        processRequest(request, response);
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
