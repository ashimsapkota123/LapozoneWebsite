package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controller.DatabaseController;
import model.UsersModel;
import util.StringUtils;

@WebServlet("/UserManageServlet")
public class ManageUser extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController = new DatabaseController();

    public ManageUser() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String userId = request.getParameter("id");

        if ("delete".equalsIgnoreCase(action) && userId != null) {
            boolean deleted = dbController.deleteUserById(userId);
            if (deleted) {
                request.setAttribute("success", "User deleted successfully.");
            } else {
                request.setAttribute("error", "Failed to delete user.");
            }
        }

        List<UsersModel> userList = dbController.getAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/pages/manage_users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String role = request.getParameter("role");
        String fullAddress = request.getParameter("fullAddress");
        String imgLink = request.getParameter("imgLink");

        UserModel user = new UserModel(userId, fullName, email, password, phoneNumber, role, fullAddress, imgLink);

        boolean updated = dbController.updateUser(user);

        if (updated) {
            request.setAttribute("success", "User updated successfully.");
        } else {
            request.setAttribute("error", "Failed to update user.");
        }

        List<UserModel> userList = dbController.getAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/pages/manage_users.jsp").forward(request, response);
    }
}
