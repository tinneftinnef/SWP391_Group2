<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Products</title>
        <!-- Favicon-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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


        <%@include file="../header.jsp" %>
        <!-- Section-->
        <div class="text-center mt-4"><h3>List Category</h3></div>
        <section class="py-5 container-fluid mt-1">
            <div class="row">
                <div class="col-12">
                    <button type="button" class="btn btn-success"><a href="add-category">Add New</a></button>
                    <table border="1" class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="x" items="${listP}">
                                <tr>
                                    <td style="align-items: center; justify-content: center;">${x.getCateName()}</td>

                                    <td>
                                        <button type="submit" class="btn btn-info"><a href="edit-category?id=${x.getCateId()}">Edit</a></button>


                                        <button type="submit" class="btn btn-danger"><a href="delete-category?id=${x.getCateId()}">Delete</a></button>


                                    </td>

                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>

                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <li class="page-item ${page == i ?"active":""}"><a class="page-link" href="categories?page=${i}">${i}</a></li>
                                </c:forEach>



                        </ul>
                    </nav>


                </div>





                <a href="../home" class="btn btn-info">Back to home</a>
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