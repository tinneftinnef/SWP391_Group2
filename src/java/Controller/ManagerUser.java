/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import Model.Accounts;
import Model.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ManagerUser extends HttpServlet {

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
            out.println("<title>Servlet ManagerUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerUser at " + request.getContextPath() + "</h1>");
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
        String mode = request.getParameter("mode");

        AccountDAO adao = new AccountDAO();
        if (mode != null) {
            if (mode.equals("1")) {
                request.getRequestDispatcher("createCustomer.jsp").forward(request, response);
            } else if (mode.equals("2")) {
                String accName = request.getParameter("accName");
                Customers c = adao.getCustomerByAccName(accName);
                request.setAttribute("c", c);
                request.getRequestDispatcher("updateCustomer.jsp").forward(request, response);
            } else if (mode.equals("3")) {
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) {
                    adao.deleteCustomer(id);
                }
            } else if (mode.equals("4")) {
                request.getRequestDispatcher("createStaff.jsp").forward(request, response);
            } else if (mode.equals("5")) {
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) {
                    adao.deleteAccount(id);
                }
            } else if (mode.equals("6")) {
                String account = request.getParameter("account");
                Accounts a = adao.getAccountByAccount(account);
                request.setAttribute("a", a);
                request.getRequestDispatcher("updateStaff.jsp").forward(request, response);
            }
        }

        ArrayList<Customers> list = adao.getAllUser();
        ArrayList<Accounts> data = adao.getAllAccount();
        request.setAttribute("accounts", data);
        request.setAttribute("customersList", list);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
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
        String id = request.getParameter("a");
        String name = request.getParameter("b");
        String phone = request.getParameter("c");
        String address = request.getParameter("d");
        String email = request.getParameter("e");
        String accname = request.getParameter("f");
        String password = request.getParameter("g");
        String type = request.getParameter("h");

        AccountDAO adao = new AccountDAO();

        if (request.getParameter("add") != null) {
            if (adao.isAccnameExist(accname)) {
                request.setAttribute("name", name);
                request.setAttribute("phone", address);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("accname", accname);
                request.setAttribute("password", password);
                request.setAttribute("type", type);
                request.setAttribute("error", "accName already exist");
                request.getRequestDispatcher("createCustomer.jsp").forward(request, response);
            } else {
                Customers c = new Customers(" ", name, phone, address, email, accname, password, type);
                adao.createCustomer(c);
            }

        }
        if (request.getParameter("update") != null) {
            if(adao.isAccnameExist(accname)){
                request.setAttribute("name", name);
                request.setAttribute("phone", address);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("accname", accname);
                request.setAttribute("password", password);
                request.setAttribute("type", type);
                Customers c = new Customers(id, name, phone, address, email, accname, password, type);
                request.setAttribute("error", "accName already exist");
                request.getRequestDispatcher("updateCustomer.jsp").forward(request, response);
            }else{
                Customers c = new Customers(id, name, phone, address, email, accname, password, type);
                adao.updateCustomer(c);
            }
        }

        doGet(request, response);
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
