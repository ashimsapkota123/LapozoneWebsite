package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;

/**
 * Servlet implementation class UpdateOrderStatusServlet
 */
@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String orderId = request.getParameter("orderId");
	        String status = request.getParameter("status");

	        try {
	            Connection conn = dbController.getConnection(); // Your DB utility
	            String sql = "UPDATE orders SET status=? WHERE id=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, status);
	            stmt.setString(2, orderId);
	            stmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        response.sendRedirect("admin_order_list.jsp"); // Reload the order list
	    }

}
