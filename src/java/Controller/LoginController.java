/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import Model.Accounts;
import Model.Customers;
import Model.Roles;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        String username = (String) session.getAttribute("username");
        if (username == null) {
            request.getRequestDispatcher("/LoginView.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
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
        String btnLogin = request.getParameter("btn-submit");
        if (btnLogin != null) {
            AccountDAO accountDao = new AccountDAO();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Accounts accountLogin = accountDao.Login(username, password);
            HttpSession session = request.getSession();
            if (accountLogin != null) {
                Roles role = accountDao.getRole(Integer.parseInt(accountLogin.getRoleid()));
                if (role.getRoleName().equals("admin")) {
                    String usernameSave = accountLogin.getAccountLogin();
                    session.setAttribute("adminSave", usernameSave);
                    session.setAttribute("adminFullname", accountLogin.getName());
                    response.sendRedirect("home");
                } else {
                    request.setAttribute("message", "Account can not login here");
                    request.getRequestDispatcher("/LoginView.jsp").forward(request, response);
                }
            } else {
                CustomerDAO customerDao = new CustomerDAO();
                Customers customer = customerDao.Login(username, password);
                if (customer != null) {
                    String adminSave = customer.getAccName();
                    session.setAttribute("customerSave", adminSave);
                    session.setAttribute("customerFullname", customer.getCustomerName());
                    response.sendRedirect("home");
                } else {
                    request.setAttribute("message", "Username or password incorrect");
                    request.getRequestDispatcher("/LoginView.jsp").forward(request, response);
                }
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
