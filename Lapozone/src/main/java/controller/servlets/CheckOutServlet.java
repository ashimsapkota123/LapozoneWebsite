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

import controller.Dao.CartDAO;
import controller.Dao.ProductDAO;
import model.CartsModel;
import model.ProductsModel;
import util.StringUtils;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductDAO productDao = new ProductDAO();
    private final CartDAO cartDao = new CartDAO();
    

    public CheckOutServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        String userId = (String) userSession.getAttribute(StringUtils.SESSION_DATA);

        if (userId != null) {
            userId = userId.trim();
        } else {
            System.out.println("User ID is null. Cannot proceed to checkout.");
            response.sendRedirect("login.jsp");
            return;
        }

        String total = request.getParameter(StringUtils.TOTAL_VAL);
        String cardId = "";

        List<CartsModel> cartItems = cartDao.getCartProduct(userId);

        // Check for empty cart
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println("Cart is empty. Redirecting to cart page.");
            request.setAttribute("errorMessage", "Your cart is empty. Please add items before proceeding to checkout.");
            request.getRequestDispatcher("/pages/cart-list.jsp").forward(request, response); // ensure cart.jsp is the correct path
            return;
        }

        ArrayList<ProductsModel> chooseproductsInCart = new ArrayList<>();

        for (CartsModel cartItem : cartItems) {
            String productId = cartItem.getProductId();
            String quantity = cartItem.getQuantity();
            cardId = cartItem.getCartId();

            ArrayList<ProductsModel> product = productDao.getProductbyID(productId);
            for (ProductsModel p : product) {
                p.setquantity(quantity);
            }
            chooseproductsInCart.addAll(product);
        }

        request.setAttribute("chooseproductsInCart", chooseproductsInCart);
        request.setAttribute("grandTotal", total);
        userSession.setAttribute("cardId", cardId);

        boolean isCleared = false;
        isCleared = cartDao.clearCart(userId);

        System.out.println("Cart cleared? " + isCleared);
        request.getRequestDispatcher(StringUtils.ORDER_CONFIRM).forward(request, response);
    }
}