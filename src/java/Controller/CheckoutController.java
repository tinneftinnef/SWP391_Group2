/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.ProductDAO;
import Model.Cart;
import Model.Customers;
import Model.OrderDetails;
import Model.Orders;
import Model.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class CheckoutController extends HttpServlet {

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
            out.println("<title>Servlet CheckoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
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
        CartDAO cartDAO = new CartDAO();
        ProductDAO productDAO = new ProductDAO();
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        if (customerLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "cash":
                    try {
                    request.setAttribute("userLogin", customerLogin);
                    int customerId = Integer.parseInt(customerLogin.getCustomerID());
                    List<Cart> carts = cartDAO.getCartItemsByCustomerId(customerId);
                    request.setAttribute("carts", carts);
                    request.getRequestDispatcher("./Layout/cart/checkout.jsp").forward(request, response);
                } catch (Exception e) {
                    response.sendRedirect("cart");
                }
                break;
                default:
                    response.sendRedirect("cart");
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
        CartDAO cartDAO = new CartDAO();
        ProductDAO productDAO = new ProductDAO();
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);

        if (customerLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";

            switch (action) {
                case "cash":
                try {
                    request.setAttribute("userLogin", customerLogin);
                    int customerId = Integer.parseInt(customerLogin.getCustomerID());
                    List<Cart> carts = cartDAO.getCartItemsByCustomerId(customerId);
                    LocalDateTime dateTime = LocalDateTime.now();
                    Date orderDate = Date.valueOf(dateTime.toLocalDate());
                    String name = request.getParameter("name").trim();
                    String address = request.getParameter("address").trim();
                    String phone = request.getParameter("phone").trim();
                    String note = request.getParameter("note").trim();
                    String payment = request.getParameter("payment");
                    request.setAttribute("carts", carts);

                    if (name.equals("") || address.equals("") || phone.equals("")) {
                        request.setAttribute("errorMessage", "Name, phone and email are required");
                        request.getRequestDispatcher("./Layout/cart/checkout.jsp").forward(request, response);
                        return;
                    }

                    if (!isValidPhoneNumber(phone)) {
                        request.setAttribute("errorMessage", "Phone is not valid");
                        request.getRequestDispatcher("./Layout/cart/checkout.jsp").forward(request, response);
                        return;
                    }

                    Orders o = new Orders(0, customerId, 1, orderDate, name, phone, address, note);
                    int orderId = orderDao.addOrder(o);

                    if (orderId > 0) {
                        int isOk = 1;
                        int total = 0;
                        for (Cart cart : carts) {
                            Products pInCart = productDAO.getProductByCart(cart.getProductId());
                            if(pInCart == null) {
                                continue;
                            }
                            total += pInCart.getPrice() * cart.getQuantity();
                            int productId = pInCart.getProductId();
                            int quantity = cart.getQuantity();
                            double price = pInCart.getPrice();
                            double discount = 0;

                            List<String> availableSerials = productDAO.getAvailableSerials(productId, quantity);
                            if (availableSerials.size() == 0) {
                                request.setAttribute("errorMessage", "Not enough serial numbers available for product: " + pInCart.getProductName());
                                request.getRequestDispatcher("./Layout/cart/checkout.jsp").forward(request, response);
                                return;
                            }
                            if (pInCart.getStock() < quantity) {
                                request.setAttribute("errorMessage", "Not enough serial numbers available for product: " + pInCart.getProductName());
                                request.getRequestDispatcher("./Layout/cart/checkout.jsp").forward(request, response);
                                return;
                            }

                            for (int i = 0; i < quantity; i++) {
                                String serialNumber = availableSerials.get(i);
                                OrderDetails orderDetails = new OrderDetails(orderId, productId, 1, price, discount, serialNumber, null);
                                int resultCartDetail = orderDetailDao.addOrderDetail(orderDetails);
                                if (resultCartDetail <= 0) {
                                    isOk = 0;
                                    break;
                                }
                            }
                            if (isOk == 0) {
                                orderDetailDao.deleteOrder(orderId);
                                orderDao.deleteOrder(orderId);
                                response.sendRedirect("order-history?action=status&type=0");
                                return;
                            } else {
                                int newStock = pInCart.getStock() - quantity;
                                newStock = newStock < 0 ? 0 : newStock;
                                productDAO.editStockProduct(newStock, productId);
                            }
                        }
                        if(payment.equals("1")) {
                            session.setAttribute("orderId", orderId);
                            response.sendRedirect("vnpay?amount=" + total);
                            return;
                        } 
                        int result = cartDAO.deleteCartByCustomer(customerId);
                        if (result > 0) {
                            response.sendRedirect("order-history?action=status&type=1&orderId=" + orderId);
                        } else {
                            orderDao.deleteOrder(orderId);
                            response.sendRedirect("order-history?action=status&type=0");
                        }
                    } else {
                        response.sendRedirect("order-history?action=status&type=0");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("cart");
                }
                break;
                default:
                    throw new AssertionError();
            }
        } else {
            response.sendRedirect("login");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("0\\d{9}");
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
