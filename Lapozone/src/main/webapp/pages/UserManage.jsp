<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <title>User Manage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/admin.css" />
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
  </head>
  <style>
  .records.table-responsive {
    width: 100%;
    overflow-x: auto;
    padding: 0 20px;
    box-sizing: border-box;
}
  
  .table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0 10px; /* only vertical spacing between rows */
    table-layout: fixed;
}
.table th,
.table td {
    padding: 12px 16px;
    text-align: center;
    background-color: #fff;
    border-radius: 6px;
    vertical-align: middle;
    word-wrap: break-word;
}

  </style>
<body>
	<input type="checkbox" id="menu-toggle">
	 <jsp:include page="admin_sidebar.jsp" />
	
	<div class="main-content">
	 <jsp:include page="admin_header.jsp" />

    <main>
        <div class="page-header">
            <h1>Lapozone Admin Panel</h1>
        </div>

        <section class="product-listing-section">
            <div class="page-content">
                <h2>User Management</h2>

                <c:if test="${not empty successMessage}">
                    <div class="success-message">${successMessage}</div>
                </c:if>

                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>

                <div class="records table-responsive">
                    <div class="record-header">
                        <h3>User List</h3>
                    </div>

                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><span class="las la-sort"></span>Image</th>
                            <th><span class="las la-sort"></span>User ID</th>
                            <th><span class="las la-sort"></span>Full Name</th>
                            <th><span class="las la-sort"></span>Email</th>
                            <th><span class="las la-sort"></span>Phone Number</th>
                            <th><span class="las la-sort"></span>Role</th>
                            <th><span class="las la-sort"></span>Address</th>
                            <th><span class="las la-sort"></span>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${userList}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>
                                    <!-- Static image for all users -->
                                    <img src="${pageContext.request.contextPath}/images/Ashim.jpg" alt="User Image" width="50" height="50"/>
                                </td>
                                <td>${user.userId}</td>
                                <td>${user.fullName}</td>
                                <td>${user.email}</td>
                                <td>${user.phoneNumber}</td>
                                <td>${user.role}</td>
                                <td>${user.fullAddress}</td>
                                <td>
                                    <a href="EditUserServlet?userId=${user.userId}">Edit</a> |
                                    <a href="DeleteUserServlet?userId=${user.userId}" onclick="return confirm('Are you sure to delete users?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </section>
    </main>
</div>

<script>
    function confirmDelete(userId) {
        if (confirm("Are you sure you want to delete user: " + userId + "?")) {
            document.getElementById("deleteForm-" + userId).submit();
        }
    }
</script>

</body>
</html>
