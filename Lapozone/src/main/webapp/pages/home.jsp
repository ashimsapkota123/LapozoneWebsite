<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if (request.getAttribute("productList") == null) {
        response.sendRedirect(request.getContextPath() + "/FetchProdutsServlet");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8" />
    <title>Home page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>

    <!-- nav bar start -->
    <jsp:include page="header.jsp" />
    <!-- nav bar ends -->

    <!-- Hero Section -->
    <section class="hero">
        <div class="row container">
            <div class="column">
                <h2>Lapozone: Maximize Your Potential with Our Premium Laptops</h2>
                <p>Welcome to Lapozone: Your Laptop Growth Hub! Explore our curated tools and extensions to boost your business. From productivity to marketing, Lapozone has you covered.</p>
                <div class="buttons">
                    <a href="#redirect-products" class="btn" id="shopbtn">Shop Now</a>
                    <button class="btn">Contact Us</button>
                </div>
            </div>
        </div>
    </section>

    <!-- Product Section -->
    <section class="card-section">
        <div class="latest-title">
            <h2>Our Products</h2>
        </div>

        <c:set var="searchKeyword" value="${requestScope.search_keyword}" />
        
        <form method="get" class="searchbar" action="${pageContext.request.contextPath}/FetchProdutsServlet">
            <div class="input-box">
                <input type="text" name="keyword" placeholder="Search here..." value="${searchKeyword}" />
                <button type="submit" class="button">Search</button>
            </div>
        </form>

        <span id="redirect-products"></span>

        <div class="card-list">
            <c:if test="${empty productList}">
                <p style="color: red; text-align: center;">No products found.</p>
            </c:if>

            <c:forEach var="product" items="${productList}">
                <div class="card-item">
                    <section class="makecenter">
                        <img src="${product.imageUrl}" alt="Card Image" /><br />
                    </section>
                    <span class="discount">${product.discount} % OFF</span>
                    <h3>${product.productName}</h3>
                    <section class="price-wrap">
                        <form action="${pageContext.request.contextPath}/ProductDetailServlet">
                            <input type="hidden" name="product_" value="${product.productID}" />
                            <button type="submit" class="arrow">
                                <i class="fas fa-arrow-right card-icon"></i>
                            </button>
                        </form>
                        <h4 style="margin-top: 46px; color:#e91e63;">RS.${product.price}</h4>
                    </section>
                </div>
            </c:forEach>
        </div>
    </section>

    <!-- Info Section -->
    <section class="info">
        <div class="info-item">
            <section style="display: flex; align-items: center; justify-content: center">
                <img src="${pageContext.request.contextPath}/images/delivary-van.png" alt="del-logo" width="33%" />
                <h3>SHIPPING SERVICES</h3>
            </section>
            <p>Fast and free delivery on all orders. Reliable and around-the-clock shipping.</p>
        </div>

        <div class="info-item">
            <section style="display: flex; align-items: center; justify-content: center">
                <img src="${pageContext.request.contextPath}/images/return.png" alt="return-logo" width="33%" />
                <h3>RETURNS POLICIES</h3>
            </section>
            <p>Easy returns within the time limit. Full refund, exchange, or store credit options available.</p>
        </div>

        <div class="info-item cusSup">
            <section style="display: flex; align-items: center; justify-content: center">
                <img src="${pageContext.request.contextPath}/images/customer.png" alt="customer-support" width="30%" />
                <h3>CUSTOMER SUPPORT</h3>
            </section>
            <p>Reach us via social media or contact form. Our team is ready to assist you!</p>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>