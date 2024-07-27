<%-- 
    Document   : warranties
    Created on : Jul 6, 2024, 2:34:50 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>View Warranties</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap4.css"/>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.js"></script>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="container-fluid mt-5">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h1 class="card-title">Warranties</h1>
                    <a href="../admin/manager-warranty?action=accepted" class="btn btn-success">Warranty accepted</a>
                    <a href="../admin/manager-warranty?action=pending"  class="btn btn-secondary">Warranty pending</a>
                    <a href="../admin/manager-warranty"  class="btn btn-warning">All</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" id="data-table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Order ID</th>
                                    <th scope="col">Product</th>
                                    <th scope="col">Serial Number</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Request Date</th>
                                    <th scope="col">Done Date</th>
                                    <th scope="col">Note</th>
                                    <th scope="col">Image</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="warranty" items="${warranties}">
                                    <tr>
                                        <td>#WARRANTY${warranty.warrantyId}</td>
                                        <td>${warranty.orderId}</td>
                                        <td>${warranty.productId == 0 ? "N/A" : warranty.productId}</td>
                                        <td>${warranty.serialNumber}</td>
                                        <td>
                                            <span class="badge badge-secondary">${warranty.warrantyStatus}</span>
                                        </td>
                                        <td>${warranty.requestDate}</td>
                                        <td>${warranty.doneDate}</td>
                                        <td>${warranty.note}</td>
                                        <td>
                                            <c:if test="${warranty.img != null}">
                                                <img src="../${warranty.img}" alt="Warranty Image" width="100">
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="manager-warranty?action=view&id=${warranty.warrantyId}" class="btn btn-info btn-sm">View</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                      <a href="../home" class="btn btn-info">Back to home</a>
                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#data-table").DataTable();
            });
        </script>
    </body>
</html>
