<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />

</head>
<body>
  <!-- nav start -->
  <jsp:include page="header.jsp" />
  <!-- nav end -->
  <div class="bodydy">
    <div class="sabbb-container">
      <c:forEach var="usr" items="${userDetail}">
        <!-- Profile Information Start -->
        <div class="sabbb-profile-section">
          <div class="sabbb-profile-info">
            <!-- Profile Photo -->
            <img src="${usr.imgLink}" alt="Profile Photo" width="150" height="150">
            <div class="sabbb-profile-details">
              <p class="sabbb-profile-name">Name: ${usr.fullName}</p>
              <p class="sabbb-profile-id">User ID: @${usr.userId}</p>
              <p class="sabbb-profile-email">Email Address: @${usr.email}</p>
              <p class="sabbb-profile-phone">Phone Number: ${usr.phoneNumber}</p>
              <p class="sabbb-profile-address">Address: ${usr.fullAddress}</p>
            </div>
          </div>
        </div>
        <!-- Profile Information End -->
           <!-- Update Button -->
        <form action="<%=request.getContextPath() %>/pages/update_profile.jsp" method="get" enctype="multipart/form-data">
         <input type="hidden" name="userId" value="${usr.userId}" />
            <button type="submit" class="sabbb-btn sabbb-update-profile-btn">Update Details</button>
        </form>
      </c:forEach>
    </div>
  </div>

  <!-- footer start -->
  <jsp:include page="footer.jsp" />
  <!-- footer end -->

</body>
</html>
