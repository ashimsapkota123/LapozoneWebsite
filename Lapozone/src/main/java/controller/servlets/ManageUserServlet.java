package controller.servlets;

import controller.Dao.UserDAO;
import model.UsersModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageUserServlet")
public class ManageUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            UserDAO userDao = new UserDAO();
            List<UsersModel> userList = userDao.getAllUsers();

            request.setAttribute("userList", userList);

            // Optionally show message (e.g., from delete/update)
            String message = (String) request.getSession().getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                request.getSession().removeAttribute("message");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/UserManage.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to a generic error page
            response.sendRedirect("error.jsp");
        }
    }
}