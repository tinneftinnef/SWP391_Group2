<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Customer</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
            }
            .container {
                margin-top: 50px;
            }
            .form-group label {
                font-weight: bold;
            }
            .form-control[readonly] {
                background-color: #e9ecef;
                opacity: 1;
            }
            .btn-primary {
                background-color: #dc3545;
                border: none;
            }
            .btn-primary:hover {
                background-color: #c82333;
            }
            .card-header {
                background-color: #dc3545;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="mb-0">Update Customer</h4>
                        </div>
                        <div class="card-body">
                            <c:set value="${c}" var="c"/>
                            <form action="manageuser" method="post">
                                <div class="form-group">
                                    <label for="customerID">Customer ID</label>
                                    <input type="text" class="form-control" id="customerID" name="a" value="${param.a != null ? param.a : c.getCustomerID()}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="customerName">Customer Name</label>
                                    <input type="text" class="form-control" id="customerName" name="b" value="${param.b != null ? param.b : c.getCustomerName()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="text" class="form-control" id="phone" name="c" value="${param.c != null ? param.c : c.getPhone()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <input type="text" class="form-control" id="address" name="d" value="${param.d != null ? param.d : c.getAddress()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="e" value="${param.e != null ? param.e : c.getEmail()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="accName">Account Name</label>
                                    <input type="text" class="form-control" id="accName" name="f" value="${param.f != null ? param.f : c.getAccName()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="g" value="${param.g != null ? param.g : c.getPassword()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="userType">Type User</label>
                                    <input type="text" class="form-control" id="userType" name="h" value="${param.h != null ? param.h : c.getUserType()}" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block" name="update">UPDATE</button>
                                <span style="color: red">${error}</span>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
