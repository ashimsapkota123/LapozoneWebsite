package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import controller.Dao.UserDAO;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            int result = userDao.deleteUser(userId);
            if (result == 1) {
                request.getSession().setAttribute("message", "User deleted successfully.");
            } else {
                request.getSession().setAttribute("message", "Failed to delete user.");
            }
        }

        response.sendRedirect("ManageUserServlet");
    }
}