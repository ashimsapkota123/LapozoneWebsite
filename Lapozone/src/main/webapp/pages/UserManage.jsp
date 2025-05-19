<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UsersModel" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Manage Users</title>
</head>
<body>
    <h2>User Management</h2>

    <% if (request.getAttribute("success") != null) { %>
        <p style="color:green;"><%= request.getAttribute("success") %></p>
    <% } else if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>

        <%
            List<UsersModel> userList = (List<UsersModel>) request.getAttribute("userList");
            for (UsersModel user : userList) {
        %>
        <form action="ManageUser" method="post">
            <tr>
                <td><input type="hidden" name="uid" value="<%= user.getId() %>" /> <%= user.getId() %></td>
                <td><input type="text" name="name" value="<%= user.getFullName() %>"/></td>
                <td><input type="text" name="uname" value="<%= user.getUsername() %>"/></td>
                <td><input type="text" name="email" value="<%= user.getEmail() %>"/></td>
                <td><input type="text" name="role" value="<%= user.getRole() %>"/></td>
                <td>
                    <button type="submit" name="action" value="update">Update</button>
                    <button type="submit" name="action" value="delete" onclick="return confirm('Are you sure?');">Delete</button>
                </td>
            </tr>
        </form>
        <% } %>
    </table>
</body>
</html>
