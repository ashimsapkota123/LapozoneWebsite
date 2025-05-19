<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Lapozone Contact Us</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/about.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<section class="mid-mir">
  <div class="conmir-container">
    <div class="conmir-logo-container">
      <div class="conmir-company-name">Contact Form &#9742;</div>
    </div>
    <div class="conmir-form-container">

      <c:if test="${not empty requestScope.valError}">
        <div id="m" style="color: white; background-color: #37a980; padding: 10px; border-radius: 10px; margin-bottom: 15px; display: flex; justify-content: space-between; align-items: center">
          <c:out value="${requestScope.valError}" />
          <button id="h" style="padding: 5px 13px;">X</button>
        </div>
      </c:if>

      <form action="${pageContext.request.contextPath}/InquiryServlet" method="post" onsubmit="return validateForm()">
        <div class="conmir-form-grid">
          <div>
            <label for="fullname" class="conmir-label">Full Name</label>
            <input type="text" id="fullname" class="conmir-input" name="fullname" required>
          </div>
          <div>
            <label for="add" class="conmir-label">Address</label>
            <input type="text" id="add" class="conmir-input" name="addres" required>
          </div>
          <div>
            <label for="phone" class="conmir-label">Phone</label>
            <input type="tel" id="phone" class="conmir-input" name="phon" required>
          </div>
          <div>
            <label for="email" class="conmir-label">Email</label>
            <input type="email" id="email" class="conmir-input" name="emaill" required>
          </div>
          <div>
            <label for="subject" class="conmir-label">Subject</label>
            <input type="text" id="subject" class="conmir-input" name="subj" required>
          </div>
          <div>
            <label for="message" class="conmir-label">Message</label>
            <textarea id="message" class="conmir-textarea" rows="4" name="mesg" required></textarea>
          </div>
        </div>
        <button type="submit" class="conmir-button">Submit</button>
      </form>
    </div>
  </div>
</section>

<jsp:include page="footer.jsp" />
<script src="${pageContext.request.contextPath}/js/home.js"></script>

<script>
  function validateForm() {
    var fullname = document.getElementById("fullname").value;
    var address = document.getElementById("add").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var subject = document.getElementById("subject").value;
    var message = document.getElementById("message").value;

    var fullnameRegex = /^[a-zA-Z\s]*$/;
    var phoneRegex = /^\d+$/;

    if (!fullnameRegex.test(fullname)) {
      alert("Full name should contain only letters.");
      return false;
    }
    if (!phoneRegex.test(phone)) {
      alert("Phone number should contain only numbers.");
      return false;
    }
    return true;
  }

  document.getElementById("h")?.addEventListener("click", function() {
    document.getElementById("m").style.display = "none";
  });
</script>

</body>
</html>
