<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inquiry Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/admin.css">
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
</head>
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
                <h2>Submitted Inquiries</h2>

                <c:if test="${empty inquiries}">
                    <p>No inquiries found.</p>
                </c:if>

                <c:if test="${not empty inquiries}">
                    <div class="records table-responsive">
                        <div class="record-header">
                            <h3>Inquiry List</h3>
                        </div>

                        <table width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Inquiry ID</th>
                                    <th>User ID</th>
                                    <th>Subject</th>
                                    <th>Message</th>
                                    <th>Created At</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="inq" items="${inquiries}" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${inq.inquiryID}</td>
                                        <td>${inq.userID}</td>
                                        <td>${inq.subject}</td>
                                        <td>${inq.message}</td>
                                        <td>${inq.createdAt}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </section>
    </main>
</div>

</body>
</html>