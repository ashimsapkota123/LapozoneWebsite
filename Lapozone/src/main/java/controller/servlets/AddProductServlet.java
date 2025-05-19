package controller.servlets;

import java.io.File;
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

import controller.DatabaseController;
import model.ProductsModel;
import util.StringUtils;

@MultipartConfig
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController = new DatabaseController();

    @Override
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
        Part imagePart = request.getPart(StringUtils.PIMG);

        // Validate required fields
        if (productId == null || productName == null || priceStr == null || discountStr == null
                || screenSize == null || ram == null || processor == null || battery == null
                || storage == null || warranty == null || features == null || imagePart == null) {
            request.setAttribute(StringUtils.ERROR_VAL, "All fields are required.");
            request.getRequestDispatcher("/pages/add_product.jsp").forward(request, response);
            return;
        }

        // Parse price and discount safely
        Double price = null;
        Double discount = null;
        try {
            price = Double.parseDouble(priceStr.trim());
            discount = Double.parseDouble(discountStr.trim());
        } catch (NumberFormatException nfe) {
            request.setAttribute(StringUtils.ERROR_VAL, "Price and Discount must be valid numbers.");
            request.getRequestDispatcher("/pages/add_product.jsp").forward(request, response);
            return;
        }

        double discountAmount = (price * discount) / 100;

        // Handle image upload
        String imageFilename = imagePart.getSubmittedFileName();
        String uploadDirectory = getServletContext().getRealPath("/images");
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String uploadPath = uploadDirectory + File.separator + imageFilename;
        String databaseImageUrl = "images/" + imageFilename;

        try (InputStream inputStream = imagePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(uploadPath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(StringUtils.ERROR_VAL, "Failed to save image file.");
            request.getRequestDispatcher("/pages/add_product.jsp").forward(request, response);
            return;
        }

        // Create product object
        ProductsModel product = new ProductsModel(
                productId, productName, screenSize, processor, ram, features,
                discount, storage, warranty, price, discountAmount, databaseImageUrl
        );

        // Insert into database
        int result = dbController.addProducts(product);

        if (result == 1) {
            request.getRequestDispatcher(StringUtils.ADMIN_HOME).forward(request, response);
        } else {
            String errorMessage = (result == 0)
                    ? "Product already exists or could not be added."
                    : StringUtils.SERVER_NOT;

            request.setAttribute(StringUtils.ERROR_VAL, errorMessage);
            request.getRequestDispatcher("/pages/add_product.jsp").forward(request, response);
        }
    }
}
