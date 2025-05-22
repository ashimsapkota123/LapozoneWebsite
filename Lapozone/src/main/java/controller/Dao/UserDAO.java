 package controller.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseController;
import model.InquiryModel;
import model.PasswordEncryptionWithAes;
import model.UsersModel;
import util.StringUtils;

public class UserDAO extends DatabaseController {

	public int addUser(UsersModel usermodel) {

		try (Connection conn = getConnection()) {

			PreparedStatement st = conn.prepareStatement(StringUtils.INSERT_USER);

			// insert_student ko order sanga milna parxa check stringUtils class
			st.setString(1, usermodel.getUserId());
			st.setString(2, usermodel.getFullName());
			st.setString(3, usermodel.getEmail());
			st.setString(4, usermodel.getPhoneNumber());
			st.setString(5, PasswordEncryptionWithAes.encrypt(usermodel.getUserId(), usermodel.getPassword()));
			st.setString(6, usermodel.getRole());

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}

	}


	public int getUserLoginInfo(String userId, String password) {
	    try (Connection conn = getConnection();
	         PreparedStatement st = conn.prepareStatement(StringUtils.GET_USER_INFO)) {

	        st.setString(1, userId);

	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                String userDb = rs.getString(StringUtils.USER);
	                String passwordDb = rs.getString(StringUtils.USER_CREDENTIALS);
	                String roleDb = rs.getString("role"); // FIXED: fetch role column
	                String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, userDb);

	                if (decryptedPwd != null && decryptedPwd.equals(password) && userDb.equals(userId)) {
	                    if (roleDb.equalsIgnoreCase(StringUtils.Role1)) return 1;
	                    else if (roleDb.equalsIgnoreCase(StringUtils.Role2)) return 2;
	                    else return 3; // unknown role
	                } else {
	                    return 4; // password mismatch
	                }
	            } else {
	                return 0; // user not found
	            }
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        return -1;
	    }
	}

	public boolean checkDuplicacy(String columnValue, String dbStatement) {
		try (Connection conn = getConnection()) {
			PreparedStatement st = conn.prepareStatement(dbStatement);
			st.setString(1, columnValue);
			ResultSet rs = st.executeQuery();
			return rs.next(); // If rs.next() returns true, a duplicate exists
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}
	}

	public ArrayList<UsersModel> getProfileInfo(String userId) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.USER_QUERY);

			st.setString(1, userId);

			ResultSet result = st.executeQuery();

			ArrayList<UsersModel> usersDetails = new ArrayList<>();

			while (result.next()) {
				UsersModel user = new UsersModel();
				user.setFullName(result.getString("Full_Name"));
				user.setEmail(result.getString("Email"));
				user.setPhoneNumber(result.getString("Phone_Number"));
				user.setFullAddress(result.getString("ADDRESS"));
				user.setUserId(result.getString("User_ID"));
				user.setImgLink(result.getString("IMG_LINK"));
				usersDetails.add(user);
			}
			for (UsersModel user : usersDetails) {
				System.out.println("Full Name: " + user.getFullName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Phone Number: " + user.getPhoneNumber());
				System.out.println("Full Address: " + user.getFullAddress());
				System.out.println();
			}
			return usersDetails;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int userProfileUpdate(UsersModel user) {

		try (Connection conn = getConnection()) {

			PreparedStatement st = conn.prepareStatement(StringUtils.UPDATE_USER);

			st.setString(1, user.getFullName());

			st.setString(2, user.getFullAddress());

			st.setString(3, user.getUserId());

			int result = st.executeUpdate();

			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {

			ex.printStackTrace();

			return -1;

		}

	}

	public int changeLoginInfo(String userId, String curpassword, String newpassword) {
		try (Connection conn = getConnection();
				PreparedStatement st = conn.prepareStatement("SELECT User_ID, Password FROM users WHERE User_ID = ?")) {

			// Set parameters for the prepared statement
			System.out.println("check 1 " + userId);
			System.out.println("check 2 " + curpassword);
			System.out.println("check 3 " + newpassword);
			st.setString(1, userId);

			// Execute the query
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String userDb = rs.getString("User_ID");
					System.out.println("check 1 " + userDb);
					String passwordDb = rs.getString("Password");
					System.out.println("check 2 " + passwordDb);

					String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, userDb);
					System.out.println("check 3 " + decryptedPwd);

					// Check if the provided credentials match the database records
					if (decryptedPwd != null && decryptedPwd.equals(curpassword) && userDb.equals(userId)) {

						// Prepare the update statement
						PreparedStatement st1 = conn
								.prepareStatement("UPDATE users SET Password = ? WHERE User_ID = ?");
						st1.setString(1, PasswordEncryptionWithAes.encrypt(userId, newpassword)); // Set new encrypted
																									// password
						st1.setString(2, userId); // Set user ID for WHERE clause

						// Execute the update statement
						int rowsUpdated = st1.executeUpdate();

						if (rowsUpdated > 0) {
							return 1; // Successful password update
						} else {
							return -1; // Update failed
						}

					} else {
						return 4; // Incorrect current password
					}

				} else {
					return 0; // No matching record found for the provided userId
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 to indicate an error occurred
		}
	}

	public int addusersimg(UsersModel userModel) {

		try (Connection conn = getConnection()) {

//			System.out.println("yeti ta print hunxa ta");

			System.out.println("test1" + userModel.getImgLink());

			System.out.println("test2" + userModel.getUserId());

			PreparedStatement st = conn.prepareStatement(StringUtils.UPDATE_USER_IMG);

			st.setString(1, userModel.getImgLink());

			st.setString(2, userModel.getUserId());

			int result = st.executeUpdate();

			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {

			ex.printStackTrace();

			return -1;

		}

	}
    public int updatePassword(String newPassword, String userID) {
        String query = "UPDATE users SET password = ? WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, userID);
            return stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    public String getPhotoLink(String userId) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.IMG_RETRIVAL_QUERY);

			st.setString(1, userId);

			ResultSet result = st.executeQuery();

			String link = null;
			while (result.next()) {
				link = result.getString("IMG_LINK");
			}

			return link;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// Handle the exception appropriately, e.g., logging or throwing a custom
			// exception
			return null; // Return null or handle the error differently based on your requirement
		}
	}
	public int addUserInquiry(InquiryModel inqury) {

		try (Connection conn = getConnection()) {

			PreparedStatement st = conn.prepareStatement(StringUtils.INSERT_INQUIRY);

			// insert_student ko order sanga milna parxa check stringUtils class
			st.setString(1, inqury.getInquiryID());
			st.setString(2, inqury.getUserID());
			st.setString(3, inqury.getSubject());
			st.setString(4, inqury.getCreatedAt());
			st.setString(5, inqury.getMessage());

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}

	}
	public String getFirstNameByUsername(String userId) {
	    String fullName = null;
	    String query = "SELECT Full_Name FROM users WHERE User_ID = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, userId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                fullName = rs.getString("Full_Name");
	            }
	        }

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return fullName;
	}
	public List<UsersModel> getAllUsers() throws ClassNotFoundException {
	    List<UsersModel> userList = new ArrayList<>();
	    String query = "SELECT * FROM users";

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            UsersModel user = new UsersModel();
	            user.setUserId(rs.getString("User_ID"));
	            user.setFullName(rs.getString("Full_Name"));
	            user.setEmail(rs.getString("Email"));
	            user.setPhoneNumber(rs.getString("Phone_Number"));
	            user.setRole(rs.getString("Role"));
	            user.setFullAddress(rs.getString("ADDRESS"));
	            user.setImgLink(rs.getString("IMG_LINK"));
	            userList.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}
	public UsersModel getUserById(String userId) throws SQLException, ClassNotFoundException {
	    String sql = "SELECT * FROM users WHERE User_ID = ?";
	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, userId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            UsersModel user = new UsersModel();
	            user.setUserId(rs.getString("User_ID"));
	            user.setFullName(rs.getString("Full_Name"));
	            user.setEmail(rs.getString("Email"));
	            user.setPhoneNumber(rs.getString("Phone_Number"));
	            user.setRole(rs.getString("Role"));
	            user.setFullAddress(rs.getString("ADDRESS"));
	            user.setImgLink(rs.getString("IMG_LINK"));
	            return user;
	        }
	    }
	    return null;
	}
	public boolean updateUser(
	        String userId,
	        String fullName,
	        String email,
	        String phone,
	        String role,
	        String address,
	        String imgLink
	) throws SQLException, ClassNotFoundException {

	    String sql = "UPDATE users SET Full_Name=?, Email=?, Phone_Number=?, Role=?, ADDRESS=?, IMG_LINK=? WHERE User_ID=?";

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, fullName);
	        ps.setString(2, email);
	        ps.setString(3, phone);
	        ps.setString(4, role);
	        ps.setString(5, address);

	        ps.setString(6, imgLink);
	        ps.setString(7, userId);

	        return ps.executeUpdate() > 0;
	    }
	}
	public int deleteUser(String userId) {
        String sql = "DELETE FROM users WHERE User_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return 1;  // success
            } else {
                return 0;  // no rows affected (userId not found)
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // error occurred
        }
    }
}
