<%-- 
    Document   : view_warranty
    Created on : Jul 6, 2024, 9:06:49 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="getProduct" class="DAO.ProductDAO" />
<jsp:useBean id="getCustomer" class="DAO.AccountDAO" />
<!DOCTYPE html>
<html>
    <head>
        <title>View Warranty</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Warranty Details</h1>
            <c:if test="${(checked != null && checked == 'true') || warranty.warrantyStatus != 'Pending'}">
                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#warrantyModal">
                    Process Warranty
                </button>
            </c:if>
            <c:if test="${param.success != null && expired == null}">
                <div class="alert alert-success" role="alert">
                    ${param.success}
                </div>
            </c:if>
            <c:if test="${param.error != null}">
                <div class="alert alert-error" role="alert">
                    ${param.error}
                </div>
            </c:if>

            <c:if test="${isChecked != null && order != null}">
                <c:if test="${expired == null}">
                    <div class="alert alert-success" role="alert">
                        Found this order fit with serial number
                    </div>
                </c:if>
                <c:if test="${expired != null}">
                    <div style="margin-top: 10px" class="alert alert-danger" role="alert">
                        This order is expired warranty. Date order ${order.orderDate}
                    </div>
                </c:if>
                <table border="1" class="table table-bordered">
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
                        </tr>
                    </thead>
                    <tbody>
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
                        </tr>
                    </tbody>
                </table>
                <table border="1" class="table table-bordered">
                    <thead class="thead-inverse">
                        <tr>
                            <th>No</th>
                            <th>Product name</th>
                            <th>Image</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Sub total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="totalQuantity" value="${0}" />
                        <c:set var="totalPrice" value="${0}" />
                        <c:forEach var="orderDetail" items="${orderDetails}" varStatus="status">
                            <c:set var="product" value="${getProduct.getProductByCart(orderDetail.productId)}" />
                            <c:set var="totalPrice" value="${totalPrice + (product.price * orderDetail.quantity)}" />
                            <c:set var="totalQuantity" value="${totalQuantity + orderDetail.quantity}" />
                            <tr>
                                <td style="align-items: center; justify-content: center;">${status.index + 1}</td>
                                <td style="align-items: center; justify-content: center;">
                                    ${product.productName} - 
                                    <c:if test="${warranty.serialNumber == orderDetail.serialNumber}">
                                        <span class="badge badge-success">${orderDetail.serialNumber} --> fit serial</span>
                                    </c:if>
                                    <c:if test="${warranty.serialNumber != orderDetail.serialNumber}">
                                        <span>${orderDetail.serialNumber}</span>
                                    </c:if>
                                </td>
                                <td style="align-items: center; justify-content: center;">
                                    <img src="${product.image}" width="150px" height="100px" alt="alt"/>
                                </td>
                                <td style="align-items: center; justify-content: center;">${orderDetail.quantity}</td>
                                <td style="align-items: center; justify-content: center;">

                                    <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />₫
                                </td>
                                <td style="align-items: center; justify-content: center;">

                                    <fmt:formatNumber value="${product.price * orderDetail.quantity}" type="number" maxFractionDigits="0" />₫
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5">
                                Total quantity
                            </td>
                            <td colspan="4">
                                ${totalQuantity}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                Total price
                            </td>
                            <td colspan="4">
                                <fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="0" />₫
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:if>
            <div class="card">
                <div class="card-header" style="display: flex; justify-content: space-between">
                    Warranty Information
                    <form action="../admin/manager-warranty" method="post">
                        <input type="hidden" name="action" value="check" />
                        <input type="hidden" name="id" value="${warranty.warrantyId}" />
                        <button class="btn btn-warning">Check serial</button>
                    </form>
                </div>
                <c:if test="${isChecked != null && order == null}">
                    <div class="alert alert-danger" role="alert">
                        Not found order fit with serial number
                    </div>
                </c:if>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="mb-3 col-md-6">
                            <strong>Warranty for serial number:</strong>
                            <p>
                                <span class="btn btn-info">${warranty.serialNumber}</span>
                            </p>
                        </div>
                        <div class="mb-3 col-md-6">
                            <strong>Status:</strong>
                            <p>
                                <span class="btn btn-secondary">${warranty.warrantyStatus}</span>
                            </p>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="mb-3 col-md-6">
                            <strong>Warranty ID:</strong>
                            <p>#WARRANTY${warranty.warrantyId}</p>
                        </div>
                        <div class="mb-3 col-md-6">
                            <strong>Product Name:</strong>
                            <p>
                                <c:if test="${product != null}">
                                    <span class="badge badge-success">${product.productName}</span>
                                </c:if>
                                <c:if test="${product == null}">
                                    <span class="badge badge-danger">Can not found product for serial number '${warranty.serialNumber}'</span>
                                </c:if>
                            </p>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="card col-md-12">
                            <div class="card-header">
                                Customer info
                            </div>
                            <div class="card-body">
                                <div class="row mb-3">
                                    <div class="mb-3 col-md-3">
                                        <strong>Customer Name:</strong>
                                        <p>${customer.customerName}</p>
                                    </div>
                                    <div class="mb-3 col-md-3">
                                        <strong>Address: </strong>
                                        <p>${customer.address}</p>
                                    </div>
                                    <div class="mb-3 col-md-3">
                                        <strong>Phone:</strong>
                                        <p>${customer.phone}</p>
                                    </div>
                                    <div class="mb-3 col-md-3">
                                        <strong>Email:</strong>
                                        <p>${customer.email}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="mb-3 col-md-6">
                            <strong>Warranty Start Date:</strong>
                            <p>${warranty.requestDate}</p>
                        </div>
                        <div class="mb-3 col-md-6">
                            <strong>Warranty end date process:</strong>
                            <p>
                                <c:if test="${warranty.doneDate == null}">
                                    <span class="badge badge-secondary">Not yet</span>
                                </c:if>
                                <c:if test="${warranty.doneDate != null}">
                                    <span class="badge badge-success">Done at: ${warranty.doneDate}</span>
                                </c:if>
                            </p>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="mb-3 col-md-6">
                            <strong>Date return:</strong>
                            <p>${warranty.requestDate != null ? warranty.requestDate : "N/A"}</p>
                        </div>
                        <div class="mb-3 col-md-6">
                            <strong>Type warranty:</strong>
                            <p>
                                <c:if test="${warranty.type == 'customer'}">
                                    <span class="badge badge-secondary">Error by customer</span>
                                </c:if>
                                <c:if test="${warranty.type == 'producer'}">
                                    <span class="badge badge-success">Error by producer</span>
                                </c:if>
                            </p>
                        </div>
                    </div>
                    <div class="mb-3">
                        <strong>Status:</strong>
                        <p>${warranty.warrantyStatus}</p>
                    </div>
                    <div class="mb-3">
                        <strong>Note:</strong>
                        <p>${warranty.note}</p>
                    </div>
                    <c:if test="${warranty.noteAdmin != null}">
                        <div class="row mb-3">
                            <div class="col-md-12 form-group">
                                <p class="font-weight-bold">Note from admin:</p>
                                <textarea readonly id="id" name="noteAdmin" class="form-control" rows="5" cols="10">${warranty.noteAdmin}</textarea>
                            </div>
                        </div>
                    </c:if>
                    <div class="row mb-3">
                        <div class="col-md-12">
                            <c:if test="${warranty.img != null}">
                                <p class="font-weight-bold">Image:</p>
                                <img src="../${warranty.img}" class="img-fluid mt-3" alt="Warranty Image">
                            </c:if>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/admin/manager-warranty" class="btn btn-primary">Back to Warranty Management</a>
                </div>
            </div>
        </div>
        <div class="modal fade" id="warrantyModal" tabindex="-1" role="dialog" aria-labelledby="warrantyModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="warrantyModalLabel">Process Warranty</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="warrantyForm" method="post" action="../admin/manager-warranty" enctype="multipart/form-data">
                            <input type="hidden" id="warrantyId" name="warrantyId" value="${warranty.warrantyId}">
                            <input type="hidden" id="action" name="action" value="process">
                             <input type="hidden" id="checked" name="checked" value="${checked}">
                            <div class="form-group">
                                <label for="note">Send to customer:</label>
                                <textarea class="form-control" id="note" name="note" required>${warranty.noteAdmin}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="date-return">Date return:</label>
                                <input value="${warranty.dateReturn}" type="date" class="form-control" id="date-return" name="date-return" required />
                            </div>
                            <div class="form-group">
                                <label for="type">Type warranty</label>
                                <select class="form-control" id="type" name="type" required>
                                    <option ${warranty.type == 'producer' ? "selected" : ""} value="producer">Error by producer</option>
                                    <option ${warranty.type == 'customer' ? "selected" : ""} value="customer">Error by customer</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="img">Upload Image</label>
                                <input type="file" class="form-control-file" id="img" name="image" onchange="previewImage(event)">
                                <input type="hidden" class="form-control-file" id="oldImage" name="oldImage" value="${warranty.img}">
                                <c:choose>
                                    <c:when test="${warranty != null && warranty.img != null}">
                                        <img style="margin-top: 10px" id="img-preview" src="../${warranty.img}" width="200px" height="100px" alt="Image Preview"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img style="margin-top: 10px" id="img-preview" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzmyZfK_wKb_iQbvFuOunUPVo2_I6bEZRBiw&s" width="40px" height="10px" alt="Image Preview"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="form-group">
                                <label for="status">Processing Status</label>
                                <select class="form-control" id="status" name="status" required>
                                    <option ${warranty.warrantyStatus == 'Pending' ? "selected" : ""} value="Pending">Pending</option>
                                    <option ${warranty.warrantyStatus == 'Accepted' ? "selected" : ""} value="Accepted">Accepted</option>
                                    <option ${warranty.warrantyStatus == 'In Progress' ? "selected" : ""} value="In Progress">In Progress</option>
                                    <option ${warranty.warrantyStatus == 'Completed' ? "selected" : ""} value="Completed">Completed</option>
                                    <option ${warranty.warrantyStatus == 'Approved' ? "selected" : ""} value="Approved">Approved</option>
                                    <option ${warranty.warrantyStatus == 'Rejected' ? "selected" : ""} value="Rejected">Rejected</option>
                                </select>
                            </div>
                            <c:choose>
                                <c:when test="${warranty.warrantyStatus != 'Rejected' && warranty.warrantyStatus != 'Approved'}">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </c:when>
                                <c:otherwise>
                                    <p class="badge badge-secondary">This warranty is finished at ${warranty.doneDate}</p>
                                </c:otherwise>
                            </c:choose>

                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                                    $('#warrantyModal').on('show.bs.modal', function (event) {
                                        var button = $(event.relatedTarget);
                                        var modal = $(this);
                                    });
        </script>
        <c:if test="${warranty.warrantyStatus == 'Cancelled' || warranty.warrantyStatus == 'Rejected' || warranty.warrantyStatus == 'Approved'}">
            <script>
                var statusSelect = document.getElementById("status");
                var noteTextarea = document.getElementById("note");

                noteTextarea.disabled = true;
                statusSelect.disabled = true;
            </script>
        </c:if>
        <script>

            var dateInput = document.getElementById("date-return");
            var today = new Date().toISOString().split('T')[0];
            dateInput.setAttribute('min', today);
        </script>
        <script>
            const statusSelectChange = document.getElementById('status');
            const currentStatus = "${warranty.warrantyStatus}";

            const options = {
                "Pending": [
                    {value: "Pending", text: "Pending"},
                    {value: "Accepted", text: "Accepted"},
                    {value: "Rejected", text: "Rejected"}
                ],
                "Accepted": [
                    {value: "Accepted", text: "Accepted"},
                    {value: "In Progress", text: "In Progress"},
                ],
                "In Progress": [
                    {value: "In Progress", text: "In Progress"},
                    {value: "Completed", text: "Completed"}
                ],
                "Completed": [
                    {value: "Approved", text: "Approved"},
                    {value: "Rejected", text: "Rejected"}
                ],
                "Approved": [{value: "Approved", text: "Approved"}, ],
                "Rejected": [{value: "Rejected", text: "Rejected"}]
            };

            function populateOptions(status) {
                statusSelectChange.innerHTML = '';
                const opts = options[status] || [];
                opts.forEach(opt => {
                    const optionElement = document.createElement('option');
                    optionElement.value = opt.value;
                    optionElement.text = opt.text;
                    if (opt.value === currentStatus) {
                        optionElement.selected = true;
                    }
                    statusSelectChange.appendChild(optionElement);
                });
            }

            populateOptions(currentStatus);

            function previewImage(event) {
                const preview = document.getElementById('img-preview');
                const file = event.target.files[0];
                const reader = new FileReader();

                reader.onload = function () {
                    if (reader.readyState === 2) {
                        preview.src = reader.result;
                    }
                }

                if (file) {
                    reader.readAsDataURL(file);
                }
            }
        </script>
    </body>
</html>
