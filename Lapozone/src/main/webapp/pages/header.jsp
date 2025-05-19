<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav>
  <div class="logo">Lapozone &#128722;</div>
  <input type="checkbox" id="click" />
  <label for="click" class="menu-btn">&#9776;
  <img src="${pageContext.request.contextPath}/images/bars.png" alt="bar" id="bars-img" />
  </label>

  <ul>
    <li><a class="active" href="${pageContext.request.contextPath}/pages/home.jsp">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/pages/about.jsp">About</a></li>

    <c:choose>
      <c:when test="${not empty sessionScope.userID}">
        <!-- User is logged in -->
        <li>
          <form action="${pageContext.request.contextPath}/LogoutServlet" method="post" style="display:inline;">
            <button type="submit" class="logout-btn">Logout</button>
          </form>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/ProfileServlet">
            <img src="${pageContext.request.contextPath}/images/login.png" alt="User" width="33px" />
          </a>
        </li>
      </c:when>
      <c:otherwise>
        <!-- User is not logged in -->
        <li><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
      </c:otherwise>
    </c:choose>

    <li>
      <a href="${pageContext.request.contextPath}/DisplayToCart">
        <img src="${pageContext.request.contextPath}/images/cartt.png" alt="Cart" width="33px" />
        <span class="cart-count">
          <c:out value="${itemCount}" default="0"/>
        </span>
      </a>
    </li>
  </ul>
</nav>
