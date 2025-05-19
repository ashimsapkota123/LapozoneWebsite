<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% HttpSession userSession = request.getSession(); %>
<% String username = (String) userSession.getAttribute("username"); %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="UTF-8" />
  <title>Lapozone | Laptop Store - Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/login.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>

<!-- signin form start -->
<div class="ras-bodym">
  <div class="ras-container">
  
   <c:if test="${not empty requestScope.ERROR_MESSAGE}">
	    <div style="color: white; background-color: #d81c5c; padding: 15px; border-radius: 10px; margin-bottom: 15px;">
	        <c:out value="${requestScope.ERROR_MESSAGE}" />
	    </div>
   </c:if>
	   
    <div class="ras-title">Login to Lapozone</div>
    <div class="ras-content">
      <form action="<%=request.getContextPath() %>/LoginServlet" method="post">
        <div class="ras-user-details">
          <div class="ras-input-box">
            <span class="details">User ID</span>
            <input type="text" placeholder="Enter your userID" name="userID" required />
          </div>
          <div class="ras-input-box">
            <span class="details">Password</span>
            <input type="password" placeholder="Enter your password" name="password" required />
          </div>
          
        </div>
        <div class="ras-button">
          <input type="submit" value="Login" />
        </div>
        <p style="text-align: center; margin-bottom:10px;">Don't have a Lapozone account? <a href="${pageContext.request.contextPath}/pages/register.jsp">Sign up</a></p>
        
      </form>
    </div>
  </div>
</div>
<!-- signin form ends -->

<script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>
