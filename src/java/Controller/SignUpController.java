/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CustomerDAO;
import Model.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/signup"})
public class SignUpController extends HttpServlet {

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
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("./Signup.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String accName = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("password-confirm");
        String userType = "0";

        if (customerName.isEmpty() || phone.isEmpty() || email.isEmpty() || accName.isEmpty() || password.isEmpty()) {
            request.setAttribute("signupMessage", "All fields are required.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }

        Customers customer = new Customers("", customerName, phone, address, email, accName, password, userType);

        CustomerDAO userDAO = new CustomerDAO();
        if (accName.trim().length() <= 5 || accName.trim().contains(" ")) {
            request.setAttribute("signupMessage", "Username must be greater than 5 character and no space.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }
        
        if (address.trim().length() == 0) {
            request.setAttribute("signupMessage", "Address can not empty.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }

        if (password.trim().length() <= 7) {
            request.setAttribute("signupMessage", "Password must be from than 8 character.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }

        if (!password.trim().equals(passwordConfirm)) {
            request.setAttribute("signupMessage", "Confirm  password is not correct.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }

        if (userDAO.isUsernameExist(accName)) {
            request.setAttribute("signupMessage", "Username already exists.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }
        
        if (userDAO.isPhoneExist(phone)) {
            request.setAttribute("signupMessage", "Phone already exists.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }

        if (userDAO.isEmailExist(email)) {
            request.setAttribute("signupMessage", "Email already exists.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
            return;
        }

        boolean isUserCreated = userDAO.createUser(customer);
        if (isUserCreated) {
            request.setAttribute("SuccessMessage", "Register successfully.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
        } else {
            request.setAttribute("signupMessage", "Signup failed, please try again.");
            request.getRequestDispatcher("./Signup.jsp").forward(request, response);
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
