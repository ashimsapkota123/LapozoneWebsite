package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.UsersModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/updateProfileServlet" })
public class updateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final DatabaseController dbController = new DatabaseController();

    public updateProfileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Disallow GET method for update profile
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processUpdate(request, response);
    }

    private void processUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userID = request.getParameter("useride");
        String fullName = request.getParameter("fulName");
        String address = request.getParameter("addre");

        // Validate input
        if (userID == null || fullName == null || address == null ||
            userID.trim().isEmpty() || fullName.trim().isEmpty() || address.trim().isEmpty()) {

            session.setAttribute("errorVal", "Please fill all required fields.");
            response.sendRedirect(request.getContextPath() + "/ProfileServlet");
            return;
        }

        UsersModel usermodel = new UsersModel(userID, fullName, address);

        int result = dbController.userProfileUpdate(usermodel);

        if (result == 1) {
            session.setAttribute("messageSuccess", "Profile Successfully Updated");
            response.sendRedirect(request.getContextPath() + "/ProfileServlet");
        } else {
            session.setAttribute("errorVal", "Failed to update profile. Please try again.");
            response.sendRedirect(request.getContextPath() + "/ProfileServlet");
        }
    }
}
