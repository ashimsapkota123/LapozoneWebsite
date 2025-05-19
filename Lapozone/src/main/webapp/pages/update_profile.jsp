<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Update Profile</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/profile.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />
</head>
<body>

  <!-- Header Include -->
  <jsp:include page="header.jsp" />

  <div class="bodydy">
    <div class="sabbb-container">

      <c:set var="usr" value="${sessionScope.loggedInUser}" />
      <c:set var="img" value="${requestScope.imgUsers}" />

      <c:if test="${not empty usr}">

        <!-- Error Message -->
        <c:if test="${not empty sessionScope.errorVal}">
          <div class="alert-message error-message">
            <span><c:out value="${sessionScope.errorVal}" /></span>
            <button class="close-btn" onclick="this.parentElement.style.display='none';">X</button>
          </div>
          <c:remove var="errorVal" scope="session" />
        </c:if>

        <!-- Success Message -->
        <c:if test="${not empty sessionScope.messageSuccess}">
          <div class="alert-message success-message">
            <span><c:out value="${sessionScope.messageSuccess}" /></span>
            <button class="close-btn" onclick="this.parentElement.style.display='none';">X</button>
          </div>
          <c:remove var="messageSuccess" scope="session" />
        </c:if>

        <!-- Update Photo Form -->
        <form action="${pageContext.request.contextPath}/UpdatePhoto" method="post" enctype="multipart/form-data">
          <div class="sabbb-profile-section">
            <div class="sabbb-profile-info">
              <img class="sabbb-profile-image" src="${img}" alt="Profile Image" />
              <div class="sabbb-profile-details">
                <h2 class="sabbb-profile-name">${usr.fullName}</h2>
                <p class="sabbb-profile-email">@${usr.userId}</p>

                <label for="uploadImage" class="sabbb-btn sabbb-change-profile-btn">Change Profile</label>
                <input type="file" id="uploadImage" name="profileImage" accept="image/*" style="display:none;" />
                <button type="submit" class="sabbb-btn sabbb-change-profile-btn" style="background-color:#2b7b76;">Save Profile</button>
              </div>
            </div>
          </div>
        </form>

        <!-- Update Profile Form -->
        <form action="${pageContext.request.contextPath}/updateProfileServlet" method="post" onsubmit="return validateForm()">
          <div class="sabbb-settings-section">
            <h2 class="sabbb-section-title">Profile Settings</h2>
            <div class="sabbb-form">
              <div class="sabbb-form-grid">
                <input type="hidden" name="userId" value="${usr.userId}" />

                <div class="sabbb-form-group">
                  <label for="fullName">Full Name</label>
                  <input type="text" id="fullName" class="sabbb-form-control" name="fullName" value="${usr.fullName}" />
                </div>

                <div class="sabbb-form-group">
                  <label for="email">Email Address</label>
                  <input type="email" id="email" class="sabbb-form-control" name="email" value="${usr.email}" disabled />
                </div>
              </div>

              <div class="sabbb-form-grid">
                <div class="sabbb-form-group">
                  <label for="phone">Phone</label>
                  <input type="tel" id="phone" class="sabbb-form-control" name="phone" value="${usr.phoneNumber}" disabled />
                </div>
              </div>

              <div class="sabbb-form-group">
                <label for="address">Address</label>
                <input type="text" id="address" class="sabbb-form-control" name="address" value="${usr.fullAddress}" />
              </div>

              <button class="sabbb-btn sabbb-save-profile-btn" type="submit">Save Profile</button>
            </div>
          </div>
        </form>

        <!-- Change Password Form -->
        <form action="${pageContext.request.contextPath}/UpdatePasswordServlet" method="post" onsubmit="return validatePasswordForm()">
          <input type="hidden" name="userId" value="${usr.userId}" />
          <div class="sabbb-experience-section">
            <h2 class="sabbb-section-title">Change Password</h2>
            <div class="sabbb-form">
              <div class="sabbb-form-group">
                <label for="currentPassword">Current Password</label>
                <input type="password" id="currentPassword" class="sabbb-form-control" name="currpass" placeholder="Enter current password" />
              </div>
              <div class="sabbb-form-group">
                <label for="newPassword">New Password</label>
                <input type="password" id="newPassword" class="sabbb-form-control" name="newpass" placeholder="Enter new password" />
              </div>
              <div class="sabbb-form-group">
                <label for="confirmNewPassword">Retype New Password</label>
                <input type="password" id="confirmNewPassword" class="sabbb-form-control" name="confpass" placeholder="Confirm new password" />
              </div>
              <button type="submit" class="sabbb-btn sabbb-save-profile-btn">Confirm Change</button>
            </div>
          </div>
        </form>

      </c:if>
    </div>
  </div>

  <!-- Footer Include -->
  <jsp:include page="footer.jsp" />

  <script src="${pageContext.request.contextPath}/js/home.js"></script>

  <script>
    function validateForm() {
      const fullName = document.getElementById("fullName").value.trim();
      const address = document.getElementById("address").value.trim();

      if (!fullName) {
        alert("Full Name must not be empty.");
        return false;
      }
      if (!/^[a-zA-Z\s]+$/.test(fullName)) {
        alert("Full Name can only contain letters and spaces.");
        return false;
      }

      if (!address) {
        alert("Address must not be empty.");
        return false;
      }
      if (!/^[a-zA-Z0-9\s,.-]+$/.test(address)) {
        alert("Address contains invalid characters.");
        return false;
      }
      return true;
    }

    function validatePasswordForm() {
      const curr = document.getElementById("currentPassword").value.trim();
      const newP = document.getElementById("newPassword").value.trim();
      const conf = document.getElementById("confirmNewPassword").value.trim();

      if (!curr) {
        alert("Please enter your current password.");
        return false;
      }
      if (newP.length < 8 || !/(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])/.test(newP)) {
        alert("New password must be at least 8 characters, include a number, a special character, and an uppercase letter.");
        return false;
      }
      if (newP !== conf) {
        alert("Passwords do not match.");
        return false;
      }
      return true;
    }
  </script>
</body>
</html>
