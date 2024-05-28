<%-- 
    Document   : categories_add
    Created on : May 20, 2024, 11:49:05 PM
    Author     : SHD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Add Product </title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
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
                width: 120px;
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
        <div class="text-center mt-4"><h3>Add Category</h3></div>
        <section class="py-5 container-fluid mt-1">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                    <form method="post" action="add-category">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Name</label>
                            <input type="text" class="form-control" required="" id="exampleInputEmail1"  aria-describedby="emailHelp" name="name">
                           
                        </div>
                        
                        <div class="form-group">
                            <label for="exampleInputPassword1">Detail</label>
                            <textarea required name="detail" class="form-control"></textarea>
                        </div>
                        
                        <button type="submit" class="btn btn-primary mt-2">Submit</button>
                        <button type="button" class="btn btn-primary mt-2"><a href="categories">Back</a></button>
                    </form>

                </div>







            </div>
            <div class="col-2"></div>


        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
