package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseController;
import model.CartsModel;
import util.StringUtils;

public class CartDAO extends DatabaseController {
	private Connection conn;

	// Constructor: Establishes database connection once
	public CartDAO() {
		try {
			this.conn = DatabaseController.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public int addProductsCart(CartsModel cart) {
		try {
			PreparedStatement st = conn.prepareStatement(StringUtils.ADD_CART);
			st.setString(1, cart.getCartId());
			st.setString(2, cart.getUserId());
			st.setString(3, cart.getProductId());
			st.setString(4, cart.getQuantity());

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public ArrayList<CartsModel> getCartProduct(String userId) {
		try {
			PreparedStatement st = conn.prepareStatement(StringUtils.GET_CART);
			st.setString(1, userId);
			ResultSet result = st.executeQuery();

			ArrayList<CartsModel> cartProducts_ = new ArrayList<>();

			while (result.next()) {
				CartsModel cart = new CartsModel();
				cart.setCartId(result.getString("cart_id"));
				cart.setProductId(result.getString("Product_ID"));
				cart.setQuantity(result.getString("quantity"));
				cart.setUserId(result.getString("User_ID"));

				cartProducts_.add(cart);
			}
			return cartProducts_;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int deletecart(String productID) {
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM carts WHERE product_id = ?");
			st.setString(1, productID);
			return st.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int getCartItemCount(String userId) {
		int count = 0;
		try {
			PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) AS cartItemCount FROM carts WHERE user_id = ?");
			st.setString(1, userId);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cartItemCount");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}

	public List<CartsModel> getCartDetail(String cart_id) {
		List<CartsModel> cartDetails = new ArrayList<>();
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM carts WHERE cart_id = ?");
			st.setString(1, cart_id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				CartsModel cartItem = new CartsModel();
				cartItem.setProductId(rs.getString("product_id"));
				cartItem.setQuantity(rs.getString("quantity"));
				cartDetails.add(cartItem);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return cartDetails;
	}

	public boolean clearCart(String userId) {
		String query = "DELETE FROM carts WHERE user_id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userId);
			int rowsAffected = stmt.executeUpdate();

			System.out.println("Deleting cart for user: " + userId);
			System.out.println("Rows deleted: " + rowsAffected);

			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error while clearing cart: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkDuplicacyy(String columnValue, String columnValue2, String dbStatement) {
		try {
			PreparedStatement st = conn.prepareStatement(dbStatement);
			st.setString(1, columnValue);
			st.setString(2, columnValue2);
			ResultSet rs = st.executeQuery();
			return rs.next(); // If rs.next() returns true, a duplicate exists
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Optional: Call this when you're done with DAO
	public void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
