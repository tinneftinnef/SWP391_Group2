<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customers List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            margin-top: 30px;
        }
        .table th {
            background-color: #dc3545; /* Màu đỏ */
            color: white;
            text-align: center;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle; /* Canh giữa dọc */
        }
        .table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .header h2 {
            margin: 0;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        /* Tùy chỉnh cho bảng thứ hai */
        .second-table {
            margin-top: 20px;
            border-collapse: collapse;
            width: 100%;
        }
        .second-table th, .second-table td {
            border: 1px solid #dee2e6;
            padding: 8px;
            text-align: left;
        }
        .second-table th {
            background-color: #dc3545; /* Màu đỏ */
            color: white;
        }
        .second-table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Customers List</h2>
        </div>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Account Name</th>
                    <th>Password</th>
                    <th>User Type</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customersList}">
                    <tr>
                        <td>${customer.getCustomerID()}</td>
                        <td>${customer.getCustomerName()}</td>
                        <td>${customer.getPhone()}</td>
                        <td>${customer.getAddress()}</td>
                        <td>${customer.getEmail()}</td>
                        <td><a href="manageuser?mode=2&accName=${customer.getAccName()}">${customer.getAccName()}</a></td>
                        <td>${customer.getPassword()}</td>
                        <td>${customer.getUserType()}</td>
                        <td><a href="manageuser?mode=3&id=${customer.getAccName()}">delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="manageuser?mode=1">create new user</a><br><br>
        <!-- Bảng thứ hai -->
        <h2>Admin and staff List</h2>
        <table class="second-table">
            <thead>
                <tr>
                    <th>accountLogin</th>
                    <th>name</th>
                    <th>password</th>
                    <th>roleid</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${accounts}" var="acc">
                    <tr>
                        <td><a href="manageuser?mode=6&account=${acc.getAccountLogin()}">${acc.getAccountLogin()}</a></td>
                        <td>${acc.getName()}</td>
                        <td>${acc.getPassword()}</td>
                        <td>${acc.getRoleid()}</td>
                        <td><a href="manageuser?mode=5&id=${acc.getAccountLogin()}">delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="manageuser?mode=4">create new staff</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // JavaScript for additional interactivity can be added here
    </script>
</body>
</html>
