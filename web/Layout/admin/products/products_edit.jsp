<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getIsExistSerial" class="DAO.WarrantyDAO" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .form-group {
                margin-bottom: 1rem;
            }
            .btn-group {
                margin-top: 1rem;
            }
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="text-center mb-4">Edit Product</h2>
            <%-- Display error message if any --%>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form method="post" action="edit-product">
                <input type="hidden" name="id" value="${product.productId}">

                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${product.productName}" required>
                </div>

                <div class="form-group">
                    <label for="cate_id">Category</label>
                    <select class="form-control" id="cate_id" name="cate_id">
                        <c:forEach var="category" items="${listC}">
                            <option value="${category.cateId}" ${product.cateId == category.cateId ? 'selected' : ''}>${category.cateName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="image">Image Link</label>
                    <input type="text" class="form-control" id="image" name="image" value="${product.image}" required>
                </div>

                <div class="form-group">
                    <label for="detail">Detail</label>
                    <textarea class="form-control" id="detail" name="detail" rows="3" required>${product.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" class="form-control" id="price" name="price" value="${formattedPrice}" step="0.01" required>
                </div>
                <div class="form-group">
                    <label>Serial Numbers:</label>
                    <ul id="serialList">
                        <c:forEach var="serial" items="${product.serialNumbers}">
                            <c:set var="seri" value="${getIsExistSerial.checkWarrantiesByInOrder(serial)}" />
                            <c:if test="${seri == null}">
                                <li>
                                    <input class="form-control" type="text" name="serialNumbers" value="${serial}" readonly>
                                    <button type="button" class="btn btn-sm btn-danger removeSerial">Remove</button>
                                </li>
                            </c:if>
                            <c:if test="${seri != null}">
                                <li>
                                    <input class="form-control" type="text" name="serialNumbersBought" value="${serial}" readonly>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
                <button type="button" class="btn btn-sm btn-primary addSerial">Add Serial</button>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="products" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>

        <<script>
            document.querySelector('.addSerial').addEventListener('click', function () {
                var serialList = document.getElementById('serialList');
                var li = document.createElement('li');
                li.innerHTML = '<input class="form-control" type="text" name="serialNumbers" value="" required>' +
                        '<button type="button" class="btn btn-sm btn-danger removeSerial">Remove</button>';
                serialList.appendChild(li);
            });

            document.getElementById('serialList').addEventListener('click', function (e) {
                if (e.target.classList.contains('removeSerial')) {
                    e.target.parentNode.remove();
                }
            });
        </script>

    </body>
</html>
