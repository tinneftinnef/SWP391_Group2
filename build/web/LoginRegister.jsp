<%-- 
    Document   : LoginRegister
    Created on : May 13, 2024, 7:04:11 PM
    Author     : Bùi Vũ Tiến
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Layout/css/login_style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper">
            <h2>Registration</h2>
            <form action="register" method="post">
                <div class="input-box">
                    <input type="text" placeholder="Enter your account" name="account" required value="${account}">
                </div>
                <div class="input-box">
                    <input type="text" placeholder="Enter your name" name="name" required value="${name}">
                </div>
                <div class="input-box">
                    <input type="text" placeholder="Enter your email" name="email" required value="${email}">
                </div>
                <div class="input-box">
                    <input type="text" placeholder="Enter your phone number" name="phone" required value="${phone}">
                </div>
                <div class="input-box">
                    <input type="text" placeholder="Enter your address" name="address" required value="${address}">
                </div>
                <div class="input-box">
                    <input type="password" placeholder="Create password" name="password" required value="${pass}">
                </div>
                <div class="input-box">
                    <input type="password" placeholder="Confirm password" name="password-confirm" required value="${pass2}">
                </div>
                    <span style="color: red">${err}</span>
                <div class="policy">
                    <input type="checkbox">
                    <h3>I accept all terms & condition</h3>
                </div>
                <div class="input-box button">
                    <input type="Submit" value="Register Now">
                </div>
                <div class="text">
                    <h3>Already have an account? <a href="#">Login now</a></h3>
                </div>
            </form>
        </div>
    </body>
</html>
