<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/admin.css" />
</head>
<style>
 .edit-user-label {
            font-weight: 600;
            margin-bottom: 5px;
            display: block;
            color: #333;
        }
        .edit-user-input,
        .edit-user-upload {
            width: 100%;
            padding: 10px 14px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 15px;
        }
</style>
<body>
    
<section class="arb-mid-mir">
  <div class="arb-conmir-container">
    <a href="${pageContext.request.contextPath}/AdminDashboard.jsp">
        <button class="arb-back-p">Back</button>
    </a>
    <div class="arb-conmir-logo-container">
        <div class="arb-conmir-company-name">Edit User ✏️</div>
    </div>
    <div class="arb-conmir-form-container">
        <form action="${pageContext.request.contextPath}/EditUserServlet" method="post">
            <div class="arb-conmir-form-grid">

                <input type="hidden" name="userId" value="${user.userId}" />

                <div>
                    <label class="arb-conmir-label">Full Name:</label>
                    <input type="text" name="fullName" class="arb-conmir-input" value="${user.fullName}" required>
                </div>

                <div>
                    <label class="arb-conmir-label">Email:</label>
                    <input type="email" name="email" class="arb-conmir-input" value="${user.email}" required>
                </div>

                <div>
                    <label class="arb-conmir-label">Phone:</label>
                    <input type="text" name="phone" class="arb-conmir-input" value="${user.phoneNumber}" required>
                </div>

                <div>
                    <label class="arb-conmir-label">Role:</label>
                    <input type="text" name="role" class="arb-conmir-input" value="${user.role}" readonly>
                </div>

                <div>
                    <label class="arb-conmir-label">Address:</label>
                    <input type="text" name="address" class="arb-conmir-input" value="${user.fullAddress}" required>
                </div>

               <div>
                <label class="edit-user-label">Profile Image:</label>
                <input type="file" name="imgFile" accept="image/*" class="edit-user-upload" />
            </div>
               

            </div>
            <button type="submit" class="arb-conmir-button">Update</button>
        </form>
    </div>
  </div>
</section>

</body>
</html>
