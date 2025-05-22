package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import controller.Dao.UserDAO;
import util.StringUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDao = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter(StringUtils.USER_FIELD);
        String password = request.getParameter(StringUtils.PASS_FIELD);

        // Fetch login result: 1 = Admin, 2 = User, 3/4/0 = failure codes
        int loginResult = userDao.getUserLoginInfo(userId, password);
        

        HttpSession session = request.getSession();
        session.setAttribute("usr", loginResult);

        if (loginResult == 1) {
            session.setAttribute(StringUtils.ISADMIN, true);
            session.setAttribute(StringUtils.SESSION_DATA, userId);
            session.setAttribute("role", "admin");
            session.setMaxInactiveInterval(30 * 60);
            response.sendRedirect(request.getContextPath() + "/pages/admin_sidebar.jsp");

        } else if (loginResult == 2) {
            session.setAttribute(StringUtils.ISADMIN, false);
            session.setAttribute(StringUtils.SESSION_DATA, userId);
            session.setAttribute("role", "user");
            session.setMaxInactiveInterval(30 * 60);
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");

        } else if (loginResult == 4) {
            request.setAttribute(StringUtils.ERROR, StringUtils.PASS_NOT);
            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);

        } else if (loginResult == 0) {
            request.setAttribute(StringUtils.ERROR, StringUtils.ACCOUNT_NOT);
            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);

        } else {
            request.setAttribute(StringUtils.ERROR, StringUtils.SERVER_NOT);
            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
        }
    }
}

