<%-- 
    Document   : cart
    Created on : Jun 9, 2024, 10:56:24 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="getProduct" class="DAO.ProductDAO" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shoping Cart</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/fonts/linearicons-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="./Layout/cart/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="./Layout/cart/css/util.css">
        <link rel="stylesheet" type="text/css" href="./Layout/cart/css/main.css">
        <link rel="stylesheet" type="text/css" href="./Layout/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./Layout/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            .payment-option {
                display: flex;
                gap: 10px;
                align-items: center;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f9f9f9;
                cursor: pointer;
                transition: background-color 0.3s, box-shadow 0.3s;
            }

            .payment-option:hover {
                background-color: #e9e9e9;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            }

            .payment-option input[type="radio"] {
                display: none;
            }

            .payment-option label {
                margin-bottom: 0;
                font-size: 16px;
                color: #333;
            }

            .payment-option input[type="radio"] + label::before {
                content: '';
                display: inline-block;
                width: 20px;
                height: 20px;
                margin-right: 10px;
                border-radius: 50%;
                border: 2px solid #3498db;
                background-color: white;
                transition: background-color 0.3s, border-color 0.3s;
            }

            .payment-option input[type="radio"]:checked + label::before {
                background-color: #3498db;
                border-color: #3498db;
            }
        </style>
        <!--===============================================================================================-->
    <header id="header" class="site-header header-scrolled position-fixed text-black bg-light">
        <nav id="header-nav" class="navbar navbar-expand-lg px-3 mb-3">
            <div class="container-fluid">
                <a class="navbar-brand" href="home">
                    <img src="Layout/images/main-logo.png" class="logo">
                </a>
                <button class="navbar-toggler d-flex d-lg-none order-3 p-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#bdNavbar" aria-controls="bdNavbar" aria-expanded="false" aria-label="Toggle navigation">
                    <svg class="navbar-icon">
                    <use xlink:href="#navbar-icon"></use>
                    </svg>
                </button>
                <div class="offcanvas offcanvas-end" tabindex="-1" id="bdNavbar" aria-labelledby="bdNavbarOffcanvasLabel">
                    <div class="offcanvas-header px-4 pb-0">
                        <a class="navbar-brand" href="home">
                            <img src="Layout/images/main-logo.png" class="logo">
                        </a>
                        <button type="button" class="btn-close btn-close-black" data-bs-dismiss="offcanvas" aria-label="Close" data-bs-target="#bdNavbar"></button>
                    </div>
                    <div class="offcanvas-body">
                        <ul id="navbar" class="navbar-nav text-uppercase justify-content-end align-items-center flex-grow-1 pe-3">
                            <li class="nav-item">
                                <a class="nav-link me-4 active" href="home">Home</a>
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link me-4" href="product">Product</a>
                            
                            <li class="nav-item">
                                <a class="nav-link me-4" href="order-history">History order</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-4" href="warranty">Warranty</a>
                            </li>
                            <!--login with admin show-->
                            <c:if test="${sessionScope.adminSave != null}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link me-4 dropdown-toggle link-dark" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Manage</a>
                                    <ul class="dropdown-menu">
                                       
                                        <li>
                                            <a href="admin/products" class="dropdown-item">Manage Products</a>
                                        </li>
                                        <li>
                                            <a href="admin/order" class="dropdown-item">Manage Order</a>
                                        </li>
                                        <li>
                                            <a href="admin/manager-warranty" class="dropdown-item">Manage Warranty</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                            <li class="nav-item">
                                <div class="user-items ps-5">
                                    <ul class="d-flex justify-content-end list-unstyled">
                                        <li class="search-item pe-3">
                                            <a href="#" class="search-button">
                                                <svg class="search">
                                                <use xlink:href="#search"></use>
                                                </svg>
                                            </a>
                                        </li>
                                        <li class="nav-item dropdown">
                                            <c:if test="${sessionScope.customerSave != null || sessionScope.adminSave != null}">
                                                <a class="nav-link me-4 dropdown-toggle link-dark" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Wellcome, ${sessionScope.customerFullname}</a>
                                                <ul class="dropdown-menu">
                                                    <li>
                                                        <a href="profile" class="dropdown-item">Profile</a>
                                                    </li>
                                                    <li>
                                                        <a href="warranty" class="dropdown-item">Warranty</a>
                                                    </li>
                                                    <li>
                                                        <a href="LogoutController" class="dropdown-item">
                                                            Logout
                                                        </a>
                                                    </li>
                                                </ul>
                                            </c:if>
                                            <c:if test="${sessionScope.customerSave == null && sessionScope.adminSave == null}">
                                                <a href="login">
                                                    Login
                                                </a>
                                            </c:if>
                                        </li>
                                        <li>
                                            <a href="cart">
                                                <svg class="cart">
                                                <use xlink:href="#cart"></use>
                                                </svg>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <section id="billboard" class="position-relative overflow-hidden bg-light-blue p-t-75">
        <div class="bg0 p-t-75 p-b-85">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                        <div class="m-l-25 m-r--38 m-lr-0-xl">
                            <c:choose>
                                <c:when test="${carts != null}">
                                    <div class="wrap-table-shopping-cart">
                                        <table class="table-shopping-cart">
                                            <tr class="table_head">
                                                <th class="column-1">Product</th>
                                                <th class="column-2"></th>
                                                <th class="column-3">Price</th>
                                                <th class="column-4">Quantity</th>
                                                <th class="column-5">Total</th>
                                            </tr>
                                            <c:set var="totalQuantity" value="${0}" />
                                            <c:set var="totalPrice" value="${0}" />
                                            <c:forEach var="cartItem" items="${carts}">
                                                <c:set value="${getProduct.getProductByCart(cartItem.productId)}" var="product" />
                                                <tr class="table_row">

                                                    <td class="column-1">
                                                        <div class="how-itemcart1">
                                                            <img src="${product.image}" alt="IMG">
                                                        </div>
                                                    </td>
                                                    <td class="column-2">${product.productName}</td>
                                                    <td class="column-3"> 
                                                        <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />₫
                                                    </td>
                                                    <td class="column-2" style="text-align: right">
                                                        <input min="1" max="${product.stock}" id="quantity-${cartItem.productId}" class="mtext-104 cl3 txt-center num-product" type="number" name="newQuantity" value="${cartItem.quantity}" readonly>
                                                    </td>
                                                    <td class="column-5">
                                                        <fmt:formatNumber value="${product.price * cartItem.quantity}" type="number" maxFractionDigits="0" />₫
                                                    </td>
                                                    <c:set var="totalPrice" value="${totalPrice + (product.price * cartItem.quantity)}" />
                                                    <c:set var="totalQuantity" value="${totalQuantity+ cartItem.quantity}" />
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                        <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                            <h4 class="mtext-109 cl2 p-b-30">
                                Totals
                            </h4>
                            <div class="flex-w flex-t bor12 p-b-13">
                                <div class="size-208">
                                    <span class="stext-110 cl2">
                                        Total Quantity:
                                    </span>
                                </div>
                                <div class="size-209 p-t-1">
                                    <span class="mtext-110 cl2">
                                        ${totalQuantity}
                                    </span>
                                </div>
                            </div>

                            <div class="flex-w flex-t p-t-27 p-b-33">
                                <div class="size-208">
                                    <span class="mtext-101 cl2">
                                        Total:
                                    </span>
                                </div>

                                <div class="size-209 p-t-1">
                                    <span class="mtext-110 cl2">                        
                                        <fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="0" />₫
                                    </span>
                                </div>
                            </div>
                            <c:if test="${carts.size() > 0}">
                                <c:if test="${errorMessage != null}">
                                    <div class="alert alert-danger" role="alert">
                                        ${errorMessage}
                                    </div>
                                </c:if>
                                <form action="checkout?action=cash" method="post">
                                    <div class="form-group">
                                        <label>
                                            Name: 
                                        </label>
                                        <input class="form-control" name="name" placeholder="Full name" value="${userLogin.customerName}" required/>
                                    </div>
                                    <div class="form-group">
                                        <label>
                                            Phone:  
                                        </label>
                                        <input class="form-control" name="phone" placeholder="Phone" value="${userLogin.phone}" required/>
                                    </div>
                                    <div class="form-group">
                                        <label>
                                            Address 
                                        </label>
                                        <input class="form-control" name="address" placeholder="Address" value="${userLogin.address}" required/>
                                    </div>
                                    <div class="form-group">
                                        <label>
                                            Note:  
                                        </label>
                                        <input class="form-control" name="note" placeholder="Note" />
                                    </div>
                                    <div class="form-group">
                                        <label>
                                            Payment  
                                        </label>
                                        <div class="payment-option">
                                            <input id="vnpay" name="payment" type="radio" value="1" checked>
                                            <label for="vnpay">VNPAY</label>
                                        </div>
                                        <div class="payment-option">
                                            <input id="cash" name="payment" type="radio" value="0">
                                            <label for="cash">Cash</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-success" type="submit">Order</button>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer id="footer" class="overflow-hidden">
        <div class="container">
            <div class="row">
                <div class="footer-top-area">
                    <div class="row d-flex flex-wrap justify-content-between">
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu">
                                <img src="Layout/images/main-logo.png" alt="logo">
                                <p>Nisi, purus vitae, ultrices nunc. Sit ac sit suscipit hendrerit. Gravida massa volutpat aenean odio erat nullam fringilla.</p>
                                <div class="social-links">
                                    <ul class="d-flex list-unstyled">
                                        <li>
                                            <a href="#">
                                                <svg class="facebook">
                                                <use xlink:href="#facebook" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="instagram">
                                                <use xlink:href="#instagram" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="twitter">
                                                <use xlink:href="#twitter" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="linkedin">
                                                <use xlink:href="#linkedin" />
                                                </svg>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <svg class="youtube">
                                                <use xlink:href="#youtube" />
                                                </svg>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 pb-3">
                            <div class="footer-menu text-uppercase">
                                <h5 class="widget-title pb-2">Quick Links</h5>
                                <ul class="menu-list list-unstyled text-uppercase">
                                    <li class="menu-item pb-2">
                                        <a href="#">Home</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">About</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Shop</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Blogs</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Contact</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu text-uppercase">
                                <h5 class="widget-title pb-2">Help & Info Help</h5>
                                <ul class="menu-list list-unstyled">
                                    <li class="menu-item pb-2">
                                        <a href="#">Track Your Order</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Returns Policies</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Shipping + Delivery</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Contact Us</a>
                                    </li>
                                    <li class="menu-item pb-2">
                                        <a href="#">Faqs</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu contact-item">
                                <h5 class="widget-title text-uppercase pb-2">Contact Us</h5>
                                <p>Do you have any queries or suggestions? <a href="mailto:">yourinfo@gmail.com</a>
                                </p>
                                <p>If you need support? Just give us a call. <a href="">+55 111 222 333 44</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </footer>
    <div id="footer-bottom">
        <div class="container">
            <div class="row d-flex flex-wrap justify-content-between">
                <div class="col-md-4 col-sm-6">
                    <div class="Shipping d-flex">
                        <p>We ship with:</p>
                        <div class="card-wrap ps-2">
                            <img src="Layout/images/dhl.png" alt="visa">
                            <img src="Layout/images/shippingcard.png" alt="mastercard">
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="payment-method d-flex">
                        <p>Payment options:</p>
                        <div class="card-wrap ps-2">
                            <img src="Layout/images/visa.jpg" alt="visa">
                            <img src="Layout/images/mastercard.jpg" alt="mastercard">
                            <img src="Layout/images/paypal.jpg" alt="paypal">
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="copyright">
                        <p>© Copyright 2023 MiniStore. Design by <a href="https://templatesjungle.com/">TemplatesJungle</a> Distribution by <a href="https://themewagon.com">ThemeWagon</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--===============================================================================================-->	
    <script src="./Layout/cart/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="./Layout/cart/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="./Layout/cart/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script>
        $(".js-select2").each(function () {
            $(this).select2({
                minimumResultsForSearch: 20,
                dropdownParent: $(this).next('.dropDownSelect2')
            });
        })
    </script>
    <!--===============================================================================================-->
    <script src="./Layout/cart/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
    <!--===============================================================================================-->
    <script src="./Layout/cart/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script>
        $('.js-pscroll').each(function () {
            $(this).css('position', 'relative');
            $(this).css('overflow', 'hidden');
            var ps = new PerfectScrollbar(this, {
                wheelSpeed: 1,
                scrollingThreshold: 1000,
                wheelPropagation: false,
            });

            $(window).on('resize', function () {
                ps.update();
            })
        });
    </script>
    <!--===============================================================================================-->
    <script src="./Layout/cart/js/main.js"></script>
    <script>var baseURL = window.location.origin + window.location.pathname;
        window.history.replaceState({}, document.title, baseURL);</script>
</body>
</html>

