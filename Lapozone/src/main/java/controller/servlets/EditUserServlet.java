package controller.servlets;

import controller.DatabaseController;
import controller.Dao.UserDAO;
import model.UsersModel;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        try {
            UserDAO userDao = new UserDAO();
            UsersModel user = userDao.getUserById(userId);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditUser.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // read all fields, including password + imgLink
        String userId   = request.getParameter("userId");
        String fullName = request.getParameter("fullName");
        String email    = request.getParameter("email");
        String phone    = request.getParameter("phone");
        String role     = request.getParameter("role");
        String address  = request.getParameter("address");
        String imgLink  = request.getParameter("imgLink");

        try {
        	UserDAO userDao = new UserDAO();
            boolean success = userDao.updateUser(
                userId, fullName, email, phone, role, address, imgLink
            );

            HttpSession sess = request.getSession();
            if (success) {
                sess.setAttribute("message", "User updated successfully.");
            } else {
                sess.setAttribute("message", "Failed to update user.");
            }
            response.sendRedirect(request.getContextPath() + "/ManageUserServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}