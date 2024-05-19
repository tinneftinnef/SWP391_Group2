/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class RegisterAccount extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet RegisterAccount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterAccount at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password-confirm");
        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() || pass2.isEmpty()){
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("pass", pass);
            request.setAttribute("pass2", pass2);
            request.setAttribute("erol", "vui long nhap day du thong tin");
            request.getRequestDispatcher("LoginRegister.jsp").forward(request, response);
        }else{
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            request.setAttribute("account", account);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("pass", pass);
            request.setAttribute("pass2", pass2);
            request.setAttribute("erro", "vui long nhap day du thong tin");
            request.getRequestDispatcher("LoginRegister.jsp").forward(request, response);
        } else {
            if (!pass.equals(pass2)) {
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("err", "Passwords do not match.");
                request.getRequestDispatcher("LoginRegister.jsp").forward(request, response);
            } else if (!isValidPassword(pass)) {
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("err", "Password must be over 8 characters, contain at least one uppercase letter and one special character");
                request.getRequestDispatcher("LoginRegister.jsp").forward(request, response);
            } else if (!isValidEmail(email)) {
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("pass", pass);
                request.setAttribute("pass2", pass2);
                request.setAttribute("err", "Email must end with @gmail.com or @fpt.edu.vn");
                request.getRequestDispatcher("LoginRegister.jsp").forward(request, response);
            } else if (!isValidPhoneNumber(phone)) {
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("pass", pass);
                request.setAttribute("pass2", pass2);
                request.setAttribute("error", "Phone number must be 10 digits and start with 0.");
                request.getRequestDispatcher("LoginRegister.jsp").forward(request, response);
            }
        }
                
    }
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private boolean isValidPassword(String pass) {
        if (pass.length() <= 8) {
            return false;
        }

        boolean hasUpperCase = !pass.equals(pass.toLowerCase());
        boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(pass).find();

        return hasUpperCase && hasSpecialChar;
    }

    private boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com") || email.endsWith("@fpt.edu.vn");
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("0\\d{9}");
    }
}
