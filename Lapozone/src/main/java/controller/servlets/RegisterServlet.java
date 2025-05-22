package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import controller.Dao.UserDAO;
import model.UsersModel;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDAO userDao = new UserDAO();

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userID = request.getParameter("userID");
		String fullName = request.getParameter("fullname");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		String role = request.getParameter("role");


		// Validations
		if (userID == null || userID.trim().isEmpty() || userID.length() < 6) {
			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.USER_ID_NON);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		if (fullName == null || fullName.trim().isEmpty() || !fullName.matches("[a-zA-Z\\s]+")) {
			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.FULL_NAME_NON);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.EMAIL_NON);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		if (phoneNumber.length() != "+977".length() + 10) {
			String errorMessage = StringUtils.PHONE_NON;
			request.setAttribute(StringUtils.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		if (password.length() <= 8 || !password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).*$")) {
			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.PASSW_NON);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		if (!password.equals(retypePassword)) {
			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.PASSW_NON_NOT);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		if (role == null || (!role.equals("admin") && !role.equals("user"))) {
			request.setAttribute(StringUtils.ERROR_VAL, "Invalid role selected!");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		// Duplicacy check
		if (userDao.checkDuplicacy(userID, StringUtils.CHECK_DUP_ID)
				|| userDao.checkDuplicacy(email, StringUtils.CHECK_DUP_EMAIL)
				|| userDao.checkDuplicacy(phoneNumber, StringUtils.CHECK_DUP_NUMBER)) {

			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.DUPLICACY_NON);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}

		// Add user
		UsersModel userModel = new UsersModel(userID, fullName.toUpperCase(), email, password, phoneNumber, role);
		int result = userDao.addUser(userModel);

		if (result == 1) {
			response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
		} else {
			request.setAttribute(StringUtils.ERROR_VAL, StringUtils.SERVER_NOT);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		}
	}
}
