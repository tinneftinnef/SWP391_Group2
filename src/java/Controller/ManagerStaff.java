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

public class ManagerStaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerStaff at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO adao = new AccountDAO();
        String account = request.getParameter("a");
        String name = request.getParameter("b");
        String pass = request.getParameter("c");
        String role = request.getParameter("d");
        String currentAccount = request.getParameter("currentAccount");

        if (request.getParameter("add") != null) {
            if (adao.isAccountLoginExist(account)) {
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("pass", pass);
                request.setAttribute("role", role);
                request.setAttribute("error", "Account Login already exists.");
                request.getRequestDispatcher("createStaff.jsp").forward(request, response);
            } else {
                Accounts a = new Accounts(account, name, pass, role);
                adao.addStaff(a);
            }
        }

        if (request.getParameter("update") != null) {
            if (!account.equals(currentAccount) && adao.isAccountLoginExist(account)) {
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("pass", pass);
                request.setAttribute("role", role);
                request.setAttribute("error", "Account Login already exists.");
                request.getRequestDispatcher("updateStaff.jsp").forward(request, response);
            } else {
                Accounts a = new Accounts(account, name, pass, role);
                adao.updateStaff(a);
            }
        }

        ArrayList<Customers> list = adao.getAllUser();
        ArrayList<Accounts> data = adao.getAllAccount();
        request.setAttribute("accounts", data);
        request.setAttribute("customersList", list);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
