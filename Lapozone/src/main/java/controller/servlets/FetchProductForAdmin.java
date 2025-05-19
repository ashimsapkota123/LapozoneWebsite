package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.ProductsModel;
import util.StringUtils;

@WebServlet("/FetchProductForAdmin")
public class FetchProductForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final DatabaseController dbController = new DatabaseController();

	public FetchProductForAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession userSession = request.getSession(); 
		
		String userId = (String) userSession.getAttribute(StringUtils.SESSION_DATA);
		
		String full_name = dbController.getFirstNameByUsername(userId);
		
		request.setAttribute("fullName", full_name);


		List<ProductsModel> allProducts = dbController.getAllProductInfo();

		System.out.println("product size is" + allProducts.size());

		// Set product list as a request attribute
		request.setAttribute("allProducts", allProducts);

		// Forward the request to the admin page
		request.getRequestDispatcher("/pages/product_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}