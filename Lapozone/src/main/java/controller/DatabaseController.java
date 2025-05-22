package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.CartsModel;
import model.CustomerTransaction;
import model.InquiryModel;
import model.Order_products;
import model.OrdersModel;
import model.PasswordEncryptionWithAes;
import model.ProductsModel;
import model.UsersModel;
import util.StringUtils;

//   for database connectivity
public class DatabaseController {

	private static final String system = null;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(StringUtils.DRIVER);
		String url = StringUtils.CONN_LINK;
		String user = StringUtils.ID;
		String pass = StringUtils.PASS;
		return DriverManager.getConnection(url, user, pass);// returns connection object
	}

}