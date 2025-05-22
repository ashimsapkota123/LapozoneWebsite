<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lapozone | Admin</title>

</head>

<body>

<div class="sidebar">
      <div class="side-header">
        <h3>L<span>apozone</span></h3>
      </div>

      <div class="side-content">
        <div class="profile">
         <h4>${fullName}</h4>


        </div>

        <div class="side-menu">
          <ul>

          <li>
              <a href="${pageContext.request.contextPath}/FetchProductForAdmin">
                <span class="las la-mobile"></span>
                <small> Products</small>
              </a>
            </li>

            <li>
              <a href="${pageContext.request.contextPath}/ShowingAdminDetails">
                <span class="las la-shopping-cart"></span>
                <small>Orders</small>
              </a>
            </li>

                   <li>
              <a href="${pageContext.request.contextPath}/ManageUserServlet">
                 <span class="las la-mobile"></span>
                <small> Users Manage </small>
               </a>
            </li>
            
              <li>
              <a href="${pageContext.request.contextPath}/InquiryServlet">
                <span class="las la-comment"></span>
                <small>Customer Inquiry</small>
              </a>
            </li> 


          </ul>
        </div>
      </div>
    </div>
</body>
</html>