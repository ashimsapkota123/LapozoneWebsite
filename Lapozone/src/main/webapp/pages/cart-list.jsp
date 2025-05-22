<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/cart-list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />

    <style>
        .cart-error-message {
            width: fit-content;
            max-width: 90%;
            margin: 20px auto;
            padding: 16px 30px;
            background: linear-gradient(135deg, #ff0033, #ffa500); /* Stronger colors */
            color: #ffffff;
            font-size: 17px;
            font-weight: 600;
            text-align: center;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2);

            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;

            animation: fadeInSlideDown 0.5s ease-in-out;
        }

        .cart-error-message i {
            font-size: 20px;
            margin-right: 10px;
            color: #fff;
        }

        @keyframes fadeInSlideDown {
            from {
                opacity: 0;
                transform: translateY(-15px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp" />

<!-- Cart Section -->
<section class="tha-cover-list">

    <!-- Error Message -->
    <c:if test="${not empty errorMessage}">
        <div class="cart-error-message">
            <i class="fas fa-exclamation-circle"></i>
            ${errorMessage}
        </div>
    </c:if>

    <div class="tha-container">

        <header class="tha-headerr">
            <h1 class="tha-text-xl">Shopping Cart</h1>
        </header>

        <div class="tha-main-content">
            <div class="tha-table-container">
                <form action="${pageContext.request.contextPath}/RemoveFromCartServlet" method="post">
                    <table class="tha-tablee">
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Discount</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="grandTotal" value="0" />
                            <c:forEach var="product" items="${productsInCart}">
                                <tr>
                                    <td><img src="${product.imageUrl}" alt="Product Image" style="max-width: 100px;"></td>
                                    <td>${product.productName}</td>
                                    <td>${product.price}</td>
                                    <td>${product.discount}%</td>
                                    <td>${product.quantity}</td>

                                    <c:set var="discountedPrice" value="${product.price - (product.price * product.discount / 100)}" />
                                    <c:set var="total" value="${discountedPrice * product.quantity}" />
                                    <td>RS.${total}</td>
                                    <td>
                                        <button type="submit" name="productId" value="${product.productID}" class="tha-rem-but">X</button>
                                    </td>
                                    <c:set var="grandTotal" value="${grandTotal + total}" />
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>

            <div class="tha-btoon_cart">
                <a href="${pageContext.request.contextPath}/FetchProdutsServlet">
                    <button class="tha-button tha-button-primary">Continue Shopping</button>
                </a>

                <p class="tha-ttll">All Total: Rs.<c:out value="${grandTotal}"/></p>

                <form action="${pageContext.request.contextPath}/CheckOutServlet" method="post">
                    <input type="hidden" name="grandTotal" value="<c:out value='${grandTotal}' />">
                    <button class="tha-proc-but" type="submit">Proceed</button>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" />

<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const quantityControls = document.querySelectorAll('.tha-quantity-control');
        quantityControls.forEach(function (control) {
            const input = control.querySelector('.tha-inputt');
            const plusButton = control.querySelector('.plus');
            const minusButton = control.querySelector('.minus');

            plusButton.addEventListener('click', function () {
                input.value = parseInt(input.value) + 1;
            });

            minusButton.addEventListener('click', function () {
                let value = parseInt(input.value);
                if (value > 1) {
                    input.value = value - 1;
                }
            });
        });
    });
</script>

</body>
</html>