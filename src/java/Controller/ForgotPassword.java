/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;



/**
 *
 * @author dell
 */

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassword at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
       // processRequest(request, response);
       //Gửi mail đơn giản sử dụng SMTP của Gmail với kết nối SSL (Secure Sockets Layer)
       String email = request.getParameter("email");
        
        RequestDispatcher dispatcher = null;
        
        int otpvalueLength = 6; // ma otp co do dai la 6
        
        Random rand = new Random();
        
        String string = "0123456789A-Za-z"; 
        
        String randomOtp = ""; //luu tru opt duoc tao ra ngau nhien
        
        HttpSession mySession = request.getSession();
        
        if (email != null || !email.equals("")) {
            for (int i = 0; i < otpvalueLength; i++) {
                char c = string.charAt(rand.nextInt(string.length())); // moi vong lap se co 1 ki tu otp duoc sinh ra ngau nhien
                randomOtp = randomOtp + c;    // sau do duoc noi voi ki tu sinh ra ngau nhien truoc do cho den khi chuoi kí tu co do dai = 6
            }
            Cookie cookie = new Cookie("otpR", randomOtp); // tao 1 cookie voi gia tri duoc tao cung la randomOtp
            
            cookie.setMaxAge(1*60); // max = 1m
            
            response.addCookie(cookie);
            
            String to = email; // to luu tru dia chi nguoi nhan, co the co nhieu nguoi nhan 
            
            
            // Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("bnvqm1721@gmail.com", "tgqwyawkaytmqvka");
                }
            });
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Request to reset password ");
                message.setText("Hi, for security, please verify your account with the OPT below. " 
                        + "Your OTP is: " + randomOtp + ". " + "Click the link to enter otp: " + "http://localhost:9999/SWP391_Draft1/enterotp.jsp");
                Transport.send(message);
                System.out.println("message sent successfully");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            
            //=======================================================
            
            
            
            dispatcher = request.getRequestDispatcher("ForgotPAssword.jsp");
            request.setAttribute("message", "OTP is sent to your email");
            mySession.setAttribute("otp", randomOtp);
            mySession.setAttribute("email", email);
            dispatcher.forward(request, response);
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
