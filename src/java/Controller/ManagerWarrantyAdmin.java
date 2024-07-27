/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.OrderDetailDAO;
import DAO.ProductDAO;
import DAO.WarrantyDAO;
import Model.Accounts;
import Model.Customers;
import Model.OrderDetails;
import Model.Orders;
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
import jakarta.servlet.http.Part;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author HP
 */
@WebServlet(name = "ManagerWarrantyAdmin", urlPatterns = {"/admin/manager-warranty"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class ManagerWarrantyAdmin extends HttpServlet {

    private WarrantyDAO warrantyDao = new WarrantyDAO();

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
            out.println("<title>Servlet ManagerWarrantyAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerWarrantyAdmin at " + request.getContextPath() + "</h1>");
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
            case "process":
                this.proccessWarranty(request, response);
                break;
            case "pending":
                this.listWarrantiesByStatus(request, response, "pending");
                break;
            case "accepted":
                this.listWarrantiesByStatus(request, response, "accepted");
                break;
            default:
                this.listWarranties(request, response);
        }
    }

    private void listWarrantiesByStatus(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException {
        try {
            List<Warranty> warranties = warrantyDao.getAllWarrantiesByStatus(status);
            request.setAttribute("warranties", warranties);
            request.getRequestDispatcher("../Layout/admin/warranty/warranties.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("listWarranties: " + e);
        }
    }

    private void listWarranties(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Warranty> warranties = warrantyDao.getAllWarranties();
            request.setAttribute("warranties", warranties);
            request.getRequestDispatcher("../Layout/admin/warranty/warranties.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("listWarranties: " + e);
        }
    }

    private void viewWarranty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int warrantyId = Integer.parseInt(request.getParameter("id"));
            Warranty warranty = warrantyDao.getWarrantyById(warrantyId);
            if (warranty != null) {
                ProductDAO productDao = new ProductDAO();
                Products product = productDao.getProductByIdSerial(warranty.getSerialNumber());
                AccountDAO accountDao = new AccountDAO();
                Customers accountWarranty = accountDao.getCustomerByID(warranty.getCustomerId() + "");
                request.setAttribute("product", product);
                request.setAttribute("customer", accountWarranty);
                request.setAttribute("warranty", warranty);
                request.getRequestDispatcher("../Layout/admin/warranty/view_warranty.jsp").forward(request, response);
            } else {
                response.sendRedirect("../admin/manager-warranty?error=Can not found this warranty");
            }
        } catch (Exception e) {
            System.out.println("View warranty: " + e);
            response.sendRedirect("../admin/manager-warranty?error=Hava a error with data");
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
            case "check":
                this.checkWarranty(request, response);
                break;
            case "search":
                this.searchWarranties(request, response);
                break;
            case "process":
                this.proccessWarranty(request, response);
                break;
            default:
                this.listWarranties(request, response);
        }
    }

    private void proccessWarranty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wSt = request.getParameter("warrantyId");
        Upload upload = new Upload();
        String pathProduct = "./uploads/warranty/";
        try {
            int warrantyId = Integer.parseInt(wSt);
            String status = request.getParameter("status");
            String note = request.getParameter("note");
            String type = request.getParameter("type");
            String dateReturnStr = request.getParameter("date-return");
            Part mainImgParth = request.getPart("image");
            String uploadPath = getServletContext().getRealPath(pathProduct);
            String nameImgWarranty = upload.uploadImg(mainImgParth, uploadPath);
            String img = pathProduct + nameImgWarranty;
            if (nameImgWarranty == null) {
                img = request.getParameter("oldImage");
            }
            Date dateReturn = null;
            try {
                dateReturn = Date.valueOf(dateReturnStr);

                Date today = new Date(System.currentTimeMillis());
                if (dateReturn.before(today)) {
                    response.sendRedirect("../admin/manager-warranty?action=view&id=" + wSt + "&error=Date must be today or in the future");
                    return;
                }
            } catch (IllegalArgumentException e) {
                response.sendRedirect("../admin/manager-warranty?action=view&id=" + wSt + "&error=Invalid date format");
                return;
            }
            Warranty warranty = warrantyDao.getWarrantyById(warrantyId);
            if (warranty != null) {
                ProductDAO productDao = new ProductDAO();
                Products product = productDao.getProductByIdSerial(warranty.getSerialNumber());
                if (product != null) {
                    boolean result = warrantyDao.updateWarrantyStatus(warrantyId, status, product.getProductId(), note, type, dateReturn, img);
                    if (result) {
                        response.sendRedirect("../admin/manager-warranty?action=view&id=" + warrantyId + "&success=Update status warranty successfully");
                    } else {
                        response.sendRedirect("../admin/manager-warranty?action=view&id=" + warrantyId + "&error=Update status warranty fail");
                    }
                } else {
                    boolean result = warrantyDao.updateWarrantyStatus(warrantyId, status, 0, note, type, dateReturn, img);
                    if (result) {
                        response.sendRedirect("../admin/manager-warranty?action=view&id=" + warrantyId + "&success=Update status warranty successfully");
                    } else {
                        response.sendRedirect("../admin/manager-warranty?action=view&id=" + warrantyId + "&error=Update status warranty fail");
                    }
                }
            } else {
                response.sendRedirect("../admin/manager-warranty?action=view&id=" + wSt + "&error=Can not found this warranty");
            }
        } catch (Exception e) {
            System.out.println("Proccess warranty: " + e);
            response.sendRedirect("../admin/manager-warranty?action=view&id=" + wSt + "&error=Have a error");
        }
    }

    private void checkWarranty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int warrantyId = Integer.parseInt(request.getParameter("id"));
            Warranty warranty = warrantyDao.getWarrantyById(warrantyId);
            if (warranty != null) {
                ProductDAO productDao = new ProductDAO();
                Products product = productDao.getProductByIdSerial(warranty.getSerialNumber());
                AccountDAO accountDao = new AccountDAO();
                Orders order = warrantyDao.checkWarrantiesBy(warranty.getSerialNumber(), warranty.getCustomerId(), warranty.getOrderId());
                Customers accountWarranty = accountDao.getCustomerByID(warranty.getCustomerId() + "");
                if (order != null) {
                    if (this.isMoreThan12MonthsAgo(order.getOrderDate())) {
                        request.setAttribute("expired", true);
                    }
                    OrderDetailDAO orderDetailDao = new OrderDetailDAO();
                    List<OrderDetails> orderDetails = orderDetailDao.getAllOrdersById(order.getOrderId());
                    request.setAttribute("orderDetails", orderDetails);
                }
                request.setAttribute("product", product);
                request.setAttribute("isChecked", "true");
                request.setAttribute("order", order);
                request.setAttribute("customer", accountWarranty);
                request.setAttribute("warranty", warranty);
                request.setAttribute("checked", true);
                request.getRequestDispatcher("../Layout/admin/warranty/view_warranty.jsp").forward(request, response);
            } else {
                response.sendRedirect("../admin/manager-warranty?error=Can not found this warranty");
            }
        } catch (Exception e) {
            System.out.println("View warranty: " + e);
            response.sendRedirect("../admin/manager-warranty?error=Hava a error with data");
        }
    }

    private void searchWarranties(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String query = request.getParameter("query");
            List<Warranty> warranties = warrantyDao.getAllWarranties();
            request.setAttribute("query", query);
            request.setAttribute("warranties", warranties);
            request.getRequestDispatcher("../Layout/admin/warranty/warranties.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("listWarranties: " + e);
        }
    }

    public boolean isMoreThan12MonthsAgo(Date orderDate) {
        LocalDate localOrderDate = orderDate.toLocalDate();
        LocalDate orderDatePlus12Months = localOrderDate.plusMonths(12);
        return orderDatePlus12Months.isBefore(LocalDate.now());
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
