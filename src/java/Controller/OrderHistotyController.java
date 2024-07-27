/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import Model.Customers;
import Model.OrderDetails;
import Model.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HP
 */
public class OrderHistotyController extends HttpServlet {

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
            out.println("<title>Servlet OrderHistotyController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderHistotyController at " + request.getContextPath() + "</h1>");
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
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        if (customerLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "status":
                    try {
                    int status = Integer.parseInt(request.getParameter("type"));
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Orders order = orderDao.getOrderById(orderId);
                    List<OrderDetails> orderDetails = orderDetailDao.getAllOrdersById(orderId);
                    request.setAttribute("order", order);
                    request.setAttribute("orderDetails", orderDetails);
                    request.setAttribute("status", status);
                    request.getRequestDispatcher("./Layout/cart/detail-history.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println(e);
                    response.sendRedirect("cart");
                }
                break;
                case "view":
                    try {
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Orders order = orderDao.getOrderById(orderId);
                    if (order != null && order.getUserId() == Integer.parseInt(customerLogin.getCustomerID())) {
                        List<OrderDetails> orderDetails = orderDetailDao.getAllOrdersById(orderId);
                        request.setAttribute("order", order);
                        request.setAttribute("orderDetails", orderDetails);
                        request.getRequestDispatcher("./Layout/cart/detail-history.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("order-history");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    response.sendRedirect("order-history");
                }
                break;
                default:
                    try {
                    int customerId = Integer.parseInt(customerLogin.getCustomerID());
                    List<Orders> orders = orderDao.getAllOrdersByCustomer(customerId);
                    request.setAttribute("orders", orders);
                    request.getRequestDispatcher("./Layout/cart/history-order.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println(e);
                    response.sendRedirect("cart");
                }
                break;
            }
        } else {
            response.sendRedirect("login");
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
        HttpSession session = request.getSession();
        String accCustomer = (String) session.getAttribute("customerSave");
        CustomerDAO customerDao = new CustomerDAO();
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        if (customerLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            switch (action) {
                case "confirm":
                    try {
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Orders order = orderDao.getOrderById(orderId);
                    if (order != null) {
                        if (order.getStatus() == 3) {
                            int result = orderDao.confirmOrder(orderId, 4);
                            if (result >= 1) {
                                response.getWriter().write("success");
                            } else {
                                response.getWriter().write("fail");
                            }
                        } else {
                            response.getWriter().write("date");
                        }
                    } else {
                        response.getWriter().write("fail");
                    }
                } catch (Exception e) {
                    response.getWriter().write("auth");
                }
                break;
            }
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
