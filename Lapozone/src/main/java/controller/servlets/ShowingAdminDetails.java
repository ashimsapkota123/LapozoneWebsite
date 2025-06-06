package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Dao.OrderDAO;
import controller.Dao.UserDAO;
import model.CustomerTransaction;
import model.OrdersModel;
import util.StringUtils;

@WebServlet("/ShowingAdminDetails")
public class ShowingAdminDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserDAO userDao = new UserDAO();
	private final OrderDAO orderDao = new OrderDAO();

	public ShowingAdminDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession userSession = request.getSession();

		String userId = (String) userSession.getAttribute(StringUtils.SESSION_DATA);
		
		String full_name = userDao.getFirstNameByUsername(userId);
		
		request.setAttribute("fullName", full_name);

		ArrayList<OrdersModel> orders = getAllOrders();

		List<CustomerTransaction> listings = orderDao.getAllOrdersDetails();

		request.setAttribute("orders", orders);

		int orderItemCount = orderDao.getOrderCount();

		int pendingCount = orderDao.getPendingCount();

		int deliveredCount = orderDao.getdeliveredCount();

		request.setAttribute("listings", listings);

		request.setAttribute("orderItemCount", orderItemCount);

		request.setAttribute("pendingCount", pendingCount);

		request.setAttribute("deliveredCount", deliveredCount);

		request.getRequestDispatcher("/pages/order_list.jsp").forward(request, response);

	}

	private ArrayList<OrdersModel> getAllOrders() {

		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}