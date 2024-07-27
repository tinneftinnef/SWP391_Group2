/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.CustomerDAO;
import DAO.ProductDAO;
import Model.Cart;
import Model.Customers;
import Model.Products;
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
public class AddToCartController extends HttpServlet {

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
            out.println("<title>Servlet AddToCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartController at " + request.getContextPath() + "</h1>");
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
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        if (customerLogin != null) { // check customer login if no chuyển về đăng nhập
            String action = request.getParameter("action");
            action = action != null ? action : "";
            CartDAO cartDao = new CartDAO();
            ProductDAO productDao = new ProductDAO();
            switch (action) {
                case "":
                    try {
                    int customerId = Integer.parseInt(customerLogin.getCustomerID());
                    List<Cart> carts = cartDao.getCartItemsByCustomerId(customerId);
                    request.setAttribute("carts", carts);
                    request.getRequestDispatcher("./Layout/cart/cart.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Can not convert customer id: " + e);
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
        Customers customerLogin = customerDao.checkAccountByaccName(accCustomer);
        if (customerLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            CartDAO cartDao = new CartDAO();
            ProductDAO productDao = new ProductDAO();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            switch (action) {
                case "add-to-cart":
                    try {
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int customerId = Integer.parseInt(customerLogin.getCustomerID());
                    Products product = productDao.getProductByCart(productId);
                    if (product == null) {
                        response.sendRedirect("cart?error=Can not found this product");
                        return;
                    }
                    Cart cartExist = cartDao.getCartItemByProductId(productId, customerId); // check cart đã tồn tại của customer nay hay chưa
                    if (cartExist != null) {
                        if (cartExist.getQuantity() + quantity > product.getStock()) { // check không vượt qua stock trong db
                            response.sendRedirect("cart?error=Quantity out of limit product");
                        } else {
                            cartExist.setQuantity(quantity + cartExist.getQuantity()); // update quantity cart 
                            int result = cartDao.updateCartItem(cartExist); // update cart 
                            if (result > 0) {
                                response.sendRedirect("cart?success=Add to cart successfully");
                            } else {
                                response.sendRedirect("cart?error=Add cart fail");
                            }
                        }
                    } else {
                        if (product.getStock() < quantity) {
                            response.sendRedirect("cart?error=Quantity out of limit product");
                        } else {
                            Cart cart = new Cart(0, productId, customerId, quantity);
                            int result = cartDao.addToCart(cart); // add new cart
                            if (result > 0) {
                                response.sendRedirect("cart?success=Add to cart successfully");
                            } else {
                                response.sendRedirect("cart?error=Add cart fail");
                            }
                        }
                    }
                } catch (Exception e) {
                    response.sendRedirect("cart?error=ID product is not valid");
                }
                break;
                case "update":
                    try {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    Products product = productDao.getProductByCart(productId);
                    if (product.getStock() < quantity) {
                        response.getWriter().write("out");
                    } else {
                        int customerId = Integer.parseInt(customerLogin.getCustomerID());
                        Cart cartExist = cartDao.getCartItemByProductId(productId, customerId);
                        cartExist.setQuantity(quantity);
                        int result = cartDao.updateCartItem(cartExist);
                        if (result > 0) {
                            response.getWriter().write("success");
                        } else {
                            response.getWriter().write("fail");
                        }
                    }
                } catch (Exception e) {
                    response.getWriter().write("success");
                }
                break;
                case "delete":
                    try {
                    int cartId = Integer.parseInt(request.getParameter("cartId"));
                    int result = cartDao.deleteCartItem(cartId);
                    if (result > 0) {
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("fail");
                    }
                } catch (Exception e) {
                    response.getWriter().write("fail");
                }
                break;
                default:
            }

        } else {
            response.sendRedirect("login");
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
