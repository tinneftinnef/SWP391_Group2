<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Staff</title>
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
            .card {
                border: 1px solid #dc3545;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="mb-0">Update Staff</h4>
                        </div>
                        <div class="card-body">
                            <c:set value="${a}" var="a"/>
                            <form action="managerstaff" method="post">
                                <div class="form-group">
                                    <label for="accountLogin">Account Login</label>
                                    <input type="text" class="form-control" id="accountLogin" name="a" value="${param.a != null ? param.a : a.getAccountLogin()}" required>
                                    <input type="hidden" name="currentAccount" value="${param.a != null ? param.a :a.getAccountLogin()}">
                                </div>
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" class="form-control" id="name" name="b" value="${param.b != null ? param.b :a.getName()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="text" class="form-control" id="password" name="c" value="${param.c != null ? param.c :a.getPassword()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="role">Role</label>
                                    <input type="text" class="form-control" id="role" name="d" value="${param.d != null ? param.d :a.getRoleid()}" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block" name="update">UPDATE</button><br>
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
