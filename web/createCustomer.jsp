<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Create Customer</title>
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
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header bg-danger text-white">
                            <h4 class="mb-0">Create Customer</h4>
                        </div>
                        <div class="card-body">
                            <form action="manageuser" method="post">
                                <div class="form-group">
                                    <label for="customerID">Customer ID</label>
                                    <input type="text" class="form-control" id="customerID" name="a" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="customerName">Customer Name</label>
                                    <input type="text" class="form-control" id="customerName" name="b" value="${name}" required>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="text" class="form-control" id="phone" name="c" value="${phone}"required>
                                </div>
                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <input type="text" class="form-control" id="address" name="d" value="${address}" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="e" value="${email}" required>
                                </div>
                                <div class="form-group">
                                    <label for="accName">Account Name</label>
                                    <input type="text" class="form-control" id="accName" name="f" value="${accname}" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="g" value="${password}" required>
                                </div>
                                <div class="form-group">
                                    <label for="userType">User Type</label>
                                    <input type="number" class="form-control" id="userType" name="h" value="${type}" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block" name="add">ADD</button><br>
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
