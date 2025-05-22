package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DatabaseController;
import model.ProductsModel;
import util.StringUtils;

public class ProductDAO extends DatabaseController{

	public ArrayList<ProductsModel> getAllProductInfo() {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.Fetch_PRODUCTS);
			ResultSet result = st.executeQuery();

			ArrayList<ProductsModel> products_ = new ArrayList<>();

			while (result.next()) {
				ProductsModel product = new ProductsModel();
				product.setProductID(result.getString("Product_ID"));
				product.setProductName(result.getString("Product_Name"));
				product.setScreenSize(result.getString("Screen_Size"));
				product.setProcessor(result.getString("Processor"));
				product.setRam(result.getString("RAM"));
				product.setFeatures(result.getString("Features"));
				product.setDiscount(result.getDouble("Discount"));
				product.setStorage(result.getString("Storage"));
				product.setWarranty(result.getString("Warranty"));
				product.setPrice(result.getDouble("Price"));
				product.setDiscountAmount(result.getDouble("Discount_Amount"));
				product.setImageUrl(result.getString("Image"));
				products_.add(product);
			}

			return products_;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int deleteProductInfo(String productID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_USER);
			st.setString(1, productID);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public int addProducts(ProductsModel productModel) {

		try (Connection conn = getConnection()) {

			PreparedStatement st = conn.prepareStatement(StringUtils.ADD_PRODUCTS);

			st.setString(1, productModel.getProductID());

			st.setString(2, productModel.getProductName());

			st.setString(3, productModel.getScreenSize());

			st.setString(4, productModel.getProcessor());

			st.setString(5, productModel.getRam());

			st.setString(6, productModel.getFeatures());

			st.setDouble(7, productModel.getDiscount());

			st.setString(8, productModel.getStorage());

			st.setString(9, productModel.getWarranty());

			st.setDouble(10, productModel.getDiscountAmount());

			st.setDouble(11, productModel.getPrice());

			st.setString(12, productModel.getImageUrl());

			int result = st.executeUpdate();

			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {

			ex.printStackTrace();

			return -1;

		}

	}

	public ArrayList<ProductsModel> getProductbyID(String ProductID) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.ID_PRODUCTS);
			st.setString(1, ProductID);
			ResultSet result = st.executeQuery();

			ArrayList<ProductsModel> selected_products = new ArrayList<>();

			while (result.next()) {
				ProductsModel product = new ProductsModel();
				product.setProductID(result.getString("Product_ID"));
				product.setProductName(result.getString("Product_Name"));
				product.setScreenSize(result.getString("Screen_Size"));
				product.setProcessor(result.getString("Processor"));
				product.setRam(result.getString("RAM"));
				product.setFeatures(result.getString("Features"));
				product.setDiscount(result.getDouble("Discount"));
				product.setStorage(result.getString("Storage"));
				product.setWarranty(result.getString("Warranty"));
				product.setPrice(result.getDouble("Price"));
				product.setDiscountAmount(result.getDouble("Discount_Amount"));
				product.setImageUrl(result.getString("Image"));
				selected_products.add(product);
			}

			return selected_products;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Return an empty list if an error occurs
		}
	}

	public ArrayList<ProductsModel> getProductbySearch(String SearchItem) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.SEARCH_PRODUCTS);
			st.setString(1, SearchItem);

			ResultSet result = st.executeQuery();

			ArrayList<ProductsModel> searched_products = new ArrayList<>();

			while (result.next()) {
				ProductsModel product = new ProductsModel();
				product.setProductID(result.getString("Product_ID"));
				product.setProductName(result.getString("Product_Name"));
				product.setScreenSize(result.getString("Screen_Size"));
				product.setProcessor(result.getString("Processor"));
				product.setRam(result.getString("RAM"));
				product.setFeatures(result.getString("Features"));
				product.setDiscount(result.getDouble("Discount"));
				product.setStorage(result.getString("Storage"));
				product.setWarranty(result.getString("Warranty"));
				product.setPrice(result.getDouble("Price"));
				product.setDiscountAmount(result.getDouble("Discount_Amount"));
				product.setImageUrl(result.getString("Image"));
				searched_products.add(product);

			}

			return searched_products;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null; // Return an empty list if an error occurs
		}
	}

	public int updateProductById(String ProductID, ProductsModel updatedProduct) {

		try (Connection conn = getConnection()) {
			System.out.println("product id: " + ProductID);

			PreparedStatement statement = conn.prepareStatement(StringUtils.UPDATE_QUERY_ADMIN);

			statement.setString(1, updatedProduct.getProductName());
			statement.setString(2, updatedProduct.getScreenSize());
			statement.setString(3, updatedProduct.getProcessor());
			statement.setString(4, updatedProduct.getRam());
			statement.setString(5, updatedProduct.getFeatures());
			statement.setDouble(6, updatedProduct.getDiscount());
			statement.setString(7, updatedProduct.getStorage());
			statement.setString(8, updatedProduct.getWarranty());
			statement.setDouble(9, updatedProduct.getPrice());
			statement.setDouble(10, updatedProduct.getDiscountAmount());
			statement.setString(11, updatedProduct.getImageUrl());
			statement.setString(12, updatedProduct.getProductID());

			int rowsUpdated = statement.executeUpdate();

			return rowsUpdated > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
			return 0;

		}

	}

}

