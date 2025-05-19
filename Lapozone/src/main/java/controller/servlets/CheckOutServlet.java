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

import controller.DatabaseController;
import model.CartsModel;
import model.ProductsModel;
import util.StringUtils;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatabaseController dbController = new DatabaseController();

	public CheckOutServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieving user session and user ID
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtils.SESSION_DATA);

		// Ensure no null or extra whitespace
		if (userId != null) {
			userId = userId.trim();
		} else {
			System.out.println("User ID is null. Cannot proceed to checkout.");
			response.sendRedirect("login.jsp");  // Or any fallback page
			return;
		}

		String total = request.getParameter(StringUtils.TOTAL_VAL);
		String cardId = "";

		// Fetch user's cart
		List<CartsModel> cartItems = dbController.getCartProduct(userId);
		ArrayList<ProductsModel> chooseproductsInCart = new ArrayList<>();

		for (CartsModel cartItem : cartItems) {
			String productId = cartItem.getProductId();
			String quantity = cartItem.getQuantity();
			cardId = cartItem.getCartId();

			ArrayList<ProductsModel> product = dbController.getProductbyID(productId);
			for (ProductsModel p : product) {
				p.setquantity(quantity);
			}
			chooseproductsInCart.addAll(product);
		}

		// Log cart contents
		if (chooseproductsInCart.isEmpty()) {
			System.out.println("The cart is empty.");
		} else {
			System.out.println("Checkout includes " + chooseproductsInCart.size() + " products.");
		}

		// Set data for confirmation page
		request.setAttribute("chooseproductsInCart", chooseproductsInCart);
		request.setAttribute("grandTotal", total);
		userSession.setAttribute("cardId", cardId);

		// ✅ Clear the cart after checkout
		System.out.println("User ID in CheckOutServlet: " + userId);
		boolean isCleared = false;
		try {
			isCleared = dbController.clearCart(userId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cart cleared? " + isCleared);

		// Forward to order confirmation page
		request.getRequestDispatcher(StringUtils.ORDER_CONFIRM).forward(request, response);
	}
}