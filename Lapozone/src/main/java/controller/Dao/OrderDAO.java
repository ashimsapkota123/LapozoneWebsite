package controller.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseController;
import model.CustomerTransaction;
import model.Order_products;
import model.OrdersModel;
import util.StringUtils;

public class OrderDAO extends DatabaseController {

	public int addOrder(OrdersModel ordermodel) {
		try (Connection conn = getConnection()) {
			// Prepare the SQL statement
			String query = "INSERT INTO orders (Order_ID, User_ID, Total_Amount, Status, city, address, payment) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);

			// Set parameters for the statement
			statement.setString(1, ordermodel.getOrderId());
			statement.setString(2, ordermodel.getUserId());
			statement.setString(3, ordermodel.getTotalAmount());
			statement.setString(4, ordermodel.getStatus());
			statement.setString(5, ordermodel.getCity());
			statement.setString(6, ordermodel.getAddress());
			statement.setString(7, ordermodel.getPayment());

			// Execute the statement
			return statement.executeUpdate(); // Returns the number of rows affected
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 to indicate failure
		}
	}

	public int addOrderLineItem(Order_products ordersprod) {
		try (Connection conn = getConnection()) {
			// Prepare the SQL statement
			String query = "INSERT INTO order_products (Order_ID, Product_ID, Line_Quantity) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);

			// Set parameters for the statement
			statement.setString(1, ordersprod.getOrderID());
			statement.setString(2, ordersprod.getProductID());
			statement.setString(3, ordersprod.getLineQuantity());

			// Execute the statement
			return statement.executeUpdate(); // Returns the number of rows affected
		} catch (SQLIntegrityConstraintViolationException ex) {
			System.out.println("Duplicate entry detected for Order_ID: " + ordersprod.getOrderID() + ", Product_ID: "
					+ ordersprod.getProductID());
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 to indicate failure
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 to indicate failure
		}
	}

	public int getOrderCount() {
		int count = 0;
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS Order_Count FROM orders");

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt("Order_Count");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return count;

	}

	public int getPendingCount() {
		int count = 0;
		try (Connection con = getConnection()) {
			String sql = "SELECT COUNT(*) AS Order_Count FROM orders WHERE status = 'Pending'";
			try (PreparedStatement st = con.prepareStatement(sql)) {
				try (ResultSet rs = st.executeQuery()) {
					if (rs.next()) {
						count = rs.getInt("Order_Count");
					}
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return count;
	}

	public int getdeliveredCount() {
		int count = 0;
		try (Connection con = getConnection()) {
			String sql = "SELECT COUNT(*) AS Order_Count FROM orders WHERE status = 'Delivered'";
			try (PreparedStatement st = con.prepareStatement(sql)) {
				try (ResultSet rs = st.executeQuery()) {
					if (rs.next()) {
						count = rs.getInt("Order_Count");
					}
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return count;
	}

	public ArrayList<OrdersModel> getAllOrders() {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.FETCH_ORDERS);
			ResultSet result = st.executeQuery();

			ArrayList<OrdersModel> orders = new ArrayList<>();

			while (result.next()) {
				OrdersModel order = new OrdersModel();

				order.setOrderId(result.getString("Order_ID"));
				order.setUserId(result.getString("User_ID"));
				order.setTotalAmount(result.getString("Total_Amount"));
				order.setStatus(result.getString("Status"));
				order.setCity(result.getString("city"));
				order.setAddress(result.getString("address"));
				order.setPayment(result.getString("payment"));

				orders.add(order);
			}

			return orders;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<CustomerTransaction> getAllOrdersDetails() {
		List<CustomerTransaction> ordersCum = new ArrayList<>();
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.LIST_ORDERS);
			ResultSet result = st.executeQuery();

			while (result.next()) {

				CustomerTransaction orderdetails = new CustomerTransaction();

				orderdetails.setId(result.getString("Id"));
				orderdetails.setCustomerName(result.getString("Customer_Name"));
				orderdetails.setProductId(result.getString("Product_Id"));
				orderdetails.setProductName(result.getString("Product_Name"));
				orderdetails.setAmount(result.getString("Amount"));
				orderdetails.setStatus(result.getString("Status"));
				orderdetails.setImageLink(result.getString("Image_Link"));

				ordersCum.add(orderdetails);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return  ordersCum;
	}
}
