<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="UTF-8" />
  <title>Lapozone | Laptop Store - Register</title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/register.css" />
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

  <body>
    <!-- register form start -->
    <div class="sab-bodyrrr">
      <div class="sab-container">
      
	   <c:if test="${not empty requestScope.valError}">
	    <div style="color: white; background-color: #d81c5c; padding: 15px; border-radius: 10px; margin-bottom: 15px;">
	        <c:out value="${requestScope.valError}" />
	    </div>
	   </c:if>
               
      
        <div class="sab-title">Registration to Lapozone</div>
        <div class="sab-content">
        
                   
          <form action="<%=request.getContextPath() %>/RegisterServlet"  method="post">
            <div class="sab-user-details">
              <div class="sab-input-box">
                <span class="details">Full Name</span>
                <input type="text" placeholder="Enter your name"  name="fullname" required />
              </div>
              <div class="sab-input-box">
                <span class="details">User Name</span>
                <input type="text" placeholder="Enter your user name" name="userID" required />
              </div> 
              <div class="sab-input-box">
                <span class="details">Email</span>
                <input type="text" placeholder="Enter your email" name="email" required />
              </div>
              <div class="sab-input-box">
                <span class="details">Phone Number</span>
                <input type="tel" placeholder="Enter your number"  name="phoneNumber" value="+977" required />
              </div>
              <div class="sab-input-box">
                <span class="details">Password</span>
                <input
                  type="password"
                  placeholder="Enter your password"
                  name="password"
                  required
                />
              </div>
              <div class="sab-input-box">
                <span class="details">Confirm Password</span>
                <input
                  type="password"
                  placeholder="Confirm your password"
                  name="retypePassword"
                  required
                />
              </div>
            </div>
		    <div class="sab-role-details">
		  <span class="sab-role-title">Select Role</span>
		  <div class="sab-input-box">
		    <select name="role" required>
		      <option value="">-- Select Role --</option>
		      <option value="user">User</option>
		      <option value="admin">Admin</option>
		    </select>
		  </div>
		</div>

            <div class="sab-button">
              <input type="submit" value="Register" />
            </div>
            <p style="text-align: center">
              Already have an Lapozone account? <a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a>
            </p>
          </form>
        </div>
      </div>
    </div>
    <!-- register form close -->

    <script src="${pageContext.request.contextPath}/js/home.js"></script>
  </body>
</html>
   