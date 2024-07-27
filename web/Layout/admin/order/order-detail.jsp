<%-- 
    Document   : order
    Created on : Jun 10, 2024, 1:02:52 PM
    Author     : HP
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="getProduct" class="DAO.ProductDAO" />
<jsp:useBean id="getCustomer" class="DAO.AccountDAO" />
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Order</title>
        <!-- Favicon-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Bootstrap icons-->

        <!-- Core theme CSS (includes Bootstrap)-->

        <style>
            .navi{
                justify-content: center;
            }
            th{
                text-align: center;
                vertical-align: middle;
            }
            td{
                text-align: center;
                vertical-align: middle;
            }
            button{
                width: 100px;
            }
            a{
                text-decoration: none;
                color: white;
            }
            a:hover{
                color: white;
            }
        </style>
    </head>
    <body>
        <!-- Navigation-->
        <!-- Section-->
        <div class="text-center mt-4"><h3>Order detail</h3></div>
        <section class="py-5 container-fluid mt-1">
            <div class="row">
                <div class="col-12">
                    <form action="../admin/order?action=change-status" method="post">
                        <input type="hidden" value="${order.orderId}" name="orderId" />
                        <h2>Order status</h2>
                        <div class="form-group">
                            <label>Name: </label>
                            <input class="form-control" value="${order.name}" readonly/>
                        </div>
                        <div class="form-group">
                            <label>Phone: </label>
                            <input class="form-control" value="${order.phone}" readonly/>
                        </div>
                        <div class="form-group">
                            <label>Address: </label>
                            <input class="form-control" value="${order.address}" readonly/>
                        </div>
                        <div class="form-group mb-2">
                            <label>Note: </label>
                            <input class="form-control" value="${order.note}" readonly/>
                        </div>
                        <c:if test="${order.status != 4}">
                            <select name="status" style="padding: 10px 30px; margin-bottom: 10px">
                                <option value="1" ${order.status == 1 ? "selected" : ""}>New</option>
                                <option value="2" ${order.status == 2 ? "selected" : ""}>Prepare</option>
                                <option value="3" ${order.status == 3 ? "selected" : ""}>Finish</option>
                            </select>
                        </c:if>
                        <c:if test="${order.status == 4}">
                            <input style="padding: 10px 30px; margin-bottom: 10px; background: green; color: #fff" value="Confirmed from customer" readonly/>
                            </select>
                        </c:if>

                        <c:if test="${order.status != 4}">
                            <button name="change" class="btn btn-success">Change</button>
                        </c:if>
                    </form>
                    <c:if test="${param.message != null}">
                        <span class="btn btn-danger" style="margin-bottom: 10px;">${param.message}</span>
                    </c:if>
                    <table border="1" class="table table-bordered">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Product name</th>
                                <th>Image</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Sub total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalQuantity" value="${0}" />
                            <c:set var="totalPrice" value="${0}" />
                            <c:forEach var="orderDetail" items="${orderDetails}" varStatus="status">
                                <c:set var="product" value="${getProduct.getProductByCart(orderDetail.productId)}" />
                                <c:set var="totalPrice" value="${totalPrice + (product.price * orderDetail.quantity)}" />
                                <c:set var="totalQuantity" value="${totalQuantity + orderDetail.quantity}" />
                                <tr>
                                    <td style="align-items: center; justify-content: center;">${status.index + 1}</td>
                                    <td style="align-items: center; justify-content: center;">${product.productName} - (${orderDetail.serialNumber})</td>
                                    <td style="align-items: center; justify-content: center;">
                                        <img src="${product.image}" width="150px" height="100px" alt="alt"/>
                                    </td>
                                    <td style="align-items: center; justify-content: center;">${orderDetail.quantity}</td>
                                    <td style="align-items: center; justify-content: center;">
                                        
                                        <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />₫
                                    </td>
                                    <td style="align-items: center; justify-content: center;">
                                        
                                           <fmt:formatNumber value="${product.price * orderDetail.quantity}" type="number" maxFractionDigits="0" />₫
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5">
                                    Total quantity
                                </td>
                                <td colspan="4">
                                    ${totalQuantity}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    Total price
                                </td>
                                <td colspan="4">
                                   
                                    <fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="0" />₫
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <a class="btn btn-primary" href="../admin/order">Back to list</a>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script>var baseURL = window.location.origin + window.location.pathname;
            window.history.replaceState({}, document.title, baseURL);</script>
    </body>
</html>
