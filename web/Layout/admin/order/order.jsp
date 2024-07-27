<%-- 
    Document   : order
    Created on : Jun 10, 2024, 1:02:52 PM
    Author     : HP
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap4.css"/>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.js"></script>
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

        <%@include file="../header.jsp" %>

        <!-- Section-->
        <div class="text-center mt-4"><h3>List Order</h3></div>
        <section class="py-5 container-fluid mt-1">
            <div class="row">
                <div class="col-12">
                    <table border="1" class="table table-bordered" id="data-table">
                        <thead class="thead-dark">
                            <tr>
                                <th>No</th>
                                <th>Order code</th>
                                <th>Customer</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Note</th>
                                <th>Date</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orders}" varStatus="status">
                                <c:set var="customer" value="${getCustomer.getCustomerByID(order.userId)}" />
                                <tr>
                                    <td style="align-items: center; justify-content: center;">${status.index + 1}</td>
                                    <td style="align-items: center; justify-content: center;">${order.orderId}</td>
                                    <td style="align-items: center; justify-content: center;">${order.name}</td>
                                    <td style="align-items: center; justify-content: center;">${order.address}</td>
                                    <td style="align-items: center; justify-content: center;">${order.phone}</td>
                                    <td style="align-items: center; justify-content: center;">${order.note}</td>
                                    <td style="align-items: center; justify-content: center;">${order.orderDate}</td>
                                    <td>
                                        <c:if test="${order.status == 1}">
                                            New order
                                        </c:if>
                                        <c:if test="${order.status == 2}">
                                            Prepare
                                        </c:if>
                                        <c:if test="${order.status == 3}">
                                            Finish
                                        </c:if>
                                        <c:if test="${order.status == 4}">
                                            Confirmed
                                        </c:if>
                                    </td>
                                    <td>
                                        <a class="btn btn-success" href="../admin/order?action=view&orderId=${order.orderId}">View detail</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                </div>
                 <a href="../home" class="btn btn-info">Back to home</a>
            </div>


        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white"></p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#data-table").DataTable();
            });
        </script>
    </body>
</html>
