/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import Model.OrderDetails;
import Model.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author HP
 */
public class AdminOrderController extends HttpServlet {

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
            out.println("<title>Servlet AdminOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminOrderController at " + request.getContextPath() + "</h1>");
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
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        switch (action) {
            case "view":
                try {
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Orders order = orderDao.getOrderById(orderId);
                    List<OrderDetails> orderDetails = orderDetailDao.getAllOrdersById(orderId);
                    request.setAttribute("orderDetails", orderDetails);
                    request.setAttribute("order", order);
                    request.getRequestDispatcher("../Layout/admin/order/order-detail.jsp").forward(request, response);
                }catch(Exception e) {
                    response.sendRedirect("../admin/order");
                }
                break;
            default:
                List<Orders> orders = orderDao.getAllOrders();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("../Layout/admin/order/order.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "change-status":
                try {
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    int status = Integer.parseInt(request.getParameter("status")); // status of order sau khi admin click button change
                    OrderDAO orderDao = new OrderDAO();
                    int result = orderDao.confirmOrder(orderId, status);
                    if(result > 0) {
                        response.sendRedirect("../admin/order?action=view&orderId=" + orderId + "&message=Change status order successfully");
                    } else {
                        response.sendRedirect("../admin/order?action=view&orderId=" + orderId + "&message=Change status order fail");
                    }
                }catch(Exception e) {
                     response.sendRedirect("../admin/order");
                }
                break;
            default:
                throw new AssertionError();
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
