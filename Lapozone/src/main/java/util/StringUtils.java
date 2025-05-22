package util;

public class StringUtils {

	public static final String INSERT_USER =  "INSERT INTO users (user_id, full_name, email, phone_number, password, role) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String ADD_PRODUCTS = "INSERT INTO products (Product_ID, Product_Name, Screen_Size, Processor, RAM, Features, Discount, Storage, Warranty, Discount_Amount, price, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_USER_INFO = "SELECT user_id, password, role FROM users WHERE user_id = ?";
	public static final String Fetch_PRODUCTS = "SELECT * from products";

	public static final String CHECK_DUP_ID = "SELECT * from users where User_ID = ? ";
	public static final String CHECK_DUP_EMAIL = "SELECT * from users where Email = ? ";
	public static final String CHECK_DUP_NUMBER = "SELECT * from users where Phone_Number = ? ";

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String CONN_LINK = "jdbc:mysql://localhost:3306/lapozone";
	public static final String ID = "root";
	public static final String PASS = "";
	public static final String USER = "User_ID";
	public static final String USER_ROLE = "Role";
	public static final String USER_CREDENTIALS = "Password";

	public static final String Role1 = "admin";
	public static final String Role2 = "user";

	public static final String SESSION_DATA = "userID";

	public static final String ISADMIN = "isAdmin";

	public static final String LOGIN_ENDS = "/login.jsp";

	public static final String LOGIN_SERVLET_ENDS = "/LoginServlet";

	public static final String REGISTER_ENDS = "/register.jsp";

	public static final String REGISTER_SERVLET_ENDS = "/RegisterServlet";

	public static final String LOGOUT_SERVLET_ENDS = "/LogoutServlet";

	

	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/register.jsp";
	public static final String USER_FIELD = "userID";
	public static final String PASS_FIELD = "password";
	public static final String ERROR = "ERROR_MESSAGE";
	public static final String PASS_NOT = " Username or Passwords do not match";
	public static final String ACCOUNT_NOT = "No matching record found. Create a New Account !!!";
	public static final String SERVER_NOT = "Oops! There is a server problem.";

	public static final String ERROR_VAL = "valError";
	public static final String userFIELD = "userID";
	public static final String FULLNAME_FIELD = "fullname";
	public static final String EMAIL_FIELD = "email";
	public static final String PHONE_FIELD = "phoneNumber";
	public static final String PASSW_FIELD = "password";
	public static final String REPASSW_FIELD = "retypePassword";

	public static final String USER_ID_NON = "User Name must be at least 6 characters long.";
	public static final String FULL_NAME_NON = "Invalid full name. Please enter a valid name.";
	public static final String EMAIL_NON = "Invalid email address. Please enter a valid email.";
	public static final String PHONE_NON = "Phone number musts have a total of 10 characters.";
	public static final String PASSW_NON = "Password must be at least 8 characters long and contain Uppercase ,LowerCase, numbers and Sybols";
	public static final String PASSW_NON_NOT = "Password did not match";
	public static final String DUPLICACY_NON = "Check UserId, Email, or PhoneNumber which already exists!";

	// Delete product admin productId
    public static final String PROD_ID = "productId";
    public static final String ADMIN_FETCH_PROD = "/FetchProductForAdmin";
    public static final String PRODUCT_VIEW_PAGE = "/pages/product_view.jsp";
	public static final String CART_VIEW_PAGE = "/pages/cart-list.jsp";
	
	// For addproducts servlet
	public static final String PID = "pID";
	public static final String PNAME = "pname";
	public static final String PPRICE = "pprice";
	public static final String PSIZE = "psize";
	public static final String PRAM = "pram";
	public static final String PPROC = "pproc";
	public static final String PBAT = "pbat";
	public static final String PSTOR = "pstor";
	public static final String PWAR = "pwar";
	public static final String PDIS = "pdis";
	public static final String PFEAT = "pfeat";
	public static final String PIMG = "pimage";

	public static final String ADMIN_HOME = "/pages/order_list.jsp";
	public static final String USER_HOME = "/pages/home.jsp";
	public static final String ID_PRODUCTS = "SELECT * from products where Product_ID = ? ";
	public static final String SEARCH_PRODUCTS = "SELECT * from products where Product_Name LIKE ? ";
	public static final String ADD_CART = "INSERT INTO carts (cart_id, user_id, product_id, quantity) VALUES (?, ?, ?, ?)";
	public static final String INSERT_INQUIRY = "INSERT INTO inquiry (Inquiry_ID ,User_ID, Subject, Created_at, Message) VALUES (?, ?, ?, ?, ?)";
	public static final String LIST_ORDERS = "SELECT o.User_ID AS Id, u.Full_Name AS Customer_Name, oi.Product_ID AS Product_Id, p.Product_Name, o.Total_Amount AS Amount, o.Status, u.IMG_LINK AS Image_Link FROM orders o JOIN order_products oi ON o.Order_ID = oi.Order_ID JOIN products p ON oi.Product_ID = p.Product_ID JOIN users u ON o.User_ID = u.User_ID";
	public static final String UPDATE_USER = "UPDATE users SET Full_Name = ?, Address = ? WHERE User_ID = ?";
	public static final String UPDATE_USER_IMG = "UPDATE users SET IMG_LINK = ? WHERE User_ID = ?";
	public static final String UPDATE_USER_PASS = "UPDATE users SET Password = ? WHERE User_ID = ?";
	public static final String GET_CART = "SELECT * from carts Where user_id = ?";

	public static final String GET_USERS = "SELECT u.Full_Name, u.Email, u.Phone_Number, a.Full_Address, a.City, u.User_ID "
				+ "FROM users u " + "JOIN address a ON u.User_ID = a.User_ID " + "WHERE u.User_ID = ?";

	public static final String UPDATE_QUERY_ADMIN = "UPDATE products SET Product_Name = ?, Screen_Size = ?, Processor = ?, RAM = ?, Features = ?,"
				+ " Discount = ?, Storage = ?, Warranty = ?, Price = ?,"
				+ " Discount_Amount = ?, Image = ? WHERE Product_ID = ?";

	public static final String USER_QUERY = "SELECT User_ID,Full_Name,Email,Phone_Number,ADDRESS,IMG_LINK from users where  User_ID= ?";
	public static final String IMG_RETRIVAL_QUERY = "SELECT IMG_LINK from users where  User_ID= ?";
	public static final String CHECK_DUP_CART = "SELECT * from carts where User_ID = ? and Product_ID= ?";
	public static final String Counter = "SELECT COUNT(*) AS cart_count FROM carts";
	public static final String QUERY_DELETE_USER = "DELETE FROM products WHERE Product_ID = ?";
	public static final String FETCH_ORDERS = "SELECT * FROM orders";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the product!";

	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";


	public static final String AddProductServlet_ = "/AddProductServlet";

	public static final String PROFILE_ENDS = "/profile.jsp";

	public static final String ORDER_LIST_ENDS = "/order_list.jsp";

	public static final String CUSTOMER_MAIL_ENDS = "/customer-mail.jsp";

	public static final String MAIL_LIST_ENDS = "/mail_list.jsp";

	public static final String PRODUCT_VIEW_ENDS = "/product_view.jsp";

	public static final String PRODUCT_DETAIL_ADMIN_ENDS = "/product-detail-page-admin.jsp";

	public static final String ADD_PRODUCT_ENDS = "/add_product.jsp";

	//authentication filter ends

		// cartservlet
	public static final String PROD_ID_CART = "product_id_cart";

	public static final String CARD_ID = "CRD001";

	public static final String QUANT = "quant";
	public static final String CART_ERROR_MSG = "Product already\r\n" + "	exists in cart!\r\n" + "	Go To CartPage";
	public static final String SINGLE_PAGE_PROD = "/pages/singlepage-product.jsp";
	public static final String FETCH_PRODUCT_SERVLET = "/FetchProdutsServlet";
	public static final String REDIRECT_SERVLET = "pages/ProductDetailServlet?product_=\" + \"productId";

		// checkoutservlet
	public static final String TOTAL_VAL = "grandTotal";
	public static final String ORDER_CONFIRM = "/pages/order_confirm.jsp";

	public static final String CART_VIEW_PAGE1 = "/pages/cartView.jsp";
    public static final String ADMIN_MANAGE_USER = "/ManageUserServlet";
    public static final String EDIT_USER = "/EditUserServlet";
    public static final String DELETE_USER = "/DeleteUserServlet";
    public static final String USER_MANAGE_PAGE = "/pages/UserManage.jsp";
    public static final String USER_EDIT_PAGE = "/pages/EditUser.jsp";


}