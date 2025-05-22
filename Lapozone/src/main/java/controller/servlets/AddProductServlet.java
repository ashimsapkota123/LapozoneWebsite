package controller.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.Dao.ProductDAO;
import model.ProductsModel;
import util.StringUtils;

@MultipartConfig
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ProductDAO productDao = new ProductDAO();

    public AddProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter(StringUtils.PID);
        String productName = request.getParameter(StringUtils.PNAME);
        String priceStr = request.getParameter(StringUtils.PPRICE);
        String screenSize = request.getParameter(StringUtils.PSIZE);
        String ram = request.getParameter(StringUtils.PRAM);
        String processor = request.getParameter(StringUtils.PPROC);
        String battery = request.getParameter(StringUtils.PBAT);
        String storage = request.getParameter(StringUtils.PSTOR);
        String warranty = request.getParameter(StringUtils.PWAR);
        String discountStr = request.getParameter(StringUtils.PDIS);
        String features = request.getParameter(StringUtils.PFEAT);
        Part image = request.getPart(StringUtils.PIMG);

        String imageFilename = image.getSubmittedFileName();
        String uploadPath = "C:\\Users\\acer\\eclipse-workspace\\Lapozone\\src\\main\\webapp\\images\\" + imageFilename;
        String DataBase_img_url = request.getContextPath() + "/images/" + imageFilename;


        try (
        		FileOutputStream fos = new FileOutputStream(uploadPath); 
        		InputStream is = image.getInputStream()) {
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Double price = null;
        Double discount = null;

       

			try {
			    if (priceStr != null && !priceStr.isEmpty()) {
			        price = Double.parseDouble(priceStr);
			    }
			} catch (NumberFormatException e) {
			    request.setAttribute("errorMessage", "Invalid price value. Please enter a numeric value.");
			    request.getRequestDispatcher("/pages/add_product.jsp").forward(request, response);
			    return;
			}
			
			try {
			    if (discountStr != null && !discountStr.isEmpty()) {
			        discount = Double.parseDouble(discountStr);
			    }
			} catch (NumberFormatException e) {
			    request.setAttribute("errorMessage", "Invalid discount value. Please enter a numeric value.");
			    request.getRequestDispatcher("/pages/add_product.jsp").forward(request, response);
			    return;
			}

        Double discountAmount = (price * discount) / 100.0;

        ProductsModel product = new ProductsModel(productId, productName, screenSize, processor, ram, features,
                discount, storage, warranty, price, discountAmount, DataBase_img_url);

        int result = productDao.addProducts(product);

        if (result == 1) {

			request.getRequestDispatcher(StringUtils.ADMIN_HOME).forward(request, response);
			// need to change

		} else if (result == 0) {

			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			// need to change

		} else {

			String errorMessage = StringUtils.SERVER_NOT;
			request.setAttribute(StringUtils.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
        }
    }
}