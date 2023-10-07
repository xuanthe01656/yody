package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Admin;

public class AdminDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public AdminDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	public ArrayList<Admin> getListAd () {
		Admin objU = new Admin();
		ArrayList<Admin> listUs = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM admin";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				objU = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("position_detail"), rs.getString("images"), rs.getInt("position_id"), rs.getString("created_date"));
				listUs.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listUs;
	}
	public ArrayList<Admin> exisUser(String username, String password) {
		Admin objU = new Admin();
		ArrayList<Admin> listUs = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM admin WHERE username=? OR password=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs=pst.executeQuery();
			while(rs.next()) {
				objU = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("position_detail"), rs.getString("images"), rs.getInt("position_id"), rs.getString("created_date"));
				listUs.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listUs;
	}
	
	public int getNumAd() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM admin";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	public int addUser(String fullname, String phone, String email, String username, String password, int position_id,
			String position, String created_date) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `admin`(`username`, `password`, `name`, `email`, `phone`, `position_detail`,`position_id`, `created_date`) VALUES (?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, fullname);
			pst.setString(4, email);
			pst.setString(5, phone);
			pst.setString(6, position);
			pst.setInt(7, position_id);		
			pst.setString(8, created_date);
			result= pst.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public Admin getUsById(int id) {
		Admin objUs = new Admin();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM admin WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Admin objAdUs = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("position_detail"), rs.getString("images"), rs.getInt("position_id"), rs.getString("created_date"));
				objUs = objAdUs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return objUs;
	}
	public int editUser(int id, String fullname, String phone, String email, String username, String password, int position_id,
			String position) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `admin` SET `username`=?,`password`=?,`name`=?,`email`=?,`phone`=?,`position_detail`=?,`position_id`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, fullname);
			pst.setString(4, email);
			pst.setString(5, phone);
			pst.setString(6, position);
			pst.setInt(7, position_id);
			pst.setInt(8, id);
			result= pst.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public ArrayList<Admin> getListAd(int id) {
		Admin objU = new Admin();
		ArrayList<Admin> listUs = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM admin WHERE id!=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				objU = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("position_detail"), rs.getString("images"), rs.getInt("position_id"), rs.getString("created_date"));
				listUs.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listUs;
	}
	public int deleteUser(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `admin` WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result= pst.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public int updatePassword(int id, String npass) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `admin` SET `password`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, npass);
			pst.setInt(2, id);
			result= pst.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public int updateInfoNormal(Admin objAdInfor, int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `admin` SET `name`=?,`email`=?,`phone`=?,`address`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAdInfor.getFullname());
			pst.setString(2, objAdInfor.getEmail());
			pst.setString(3, objAdInfor.getPhone());
			pst.setString(4, objAdInfor.getAddress());
			pst.setInt(5, id);
			result= pst.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public int updateInfoWhitImage(Admin objAdInfor, int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `admin` SET `name`=?,`email`=?,`phone`=?,`address`=?, `images`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAdInfor.getFullname());
			pst.setString(2, objAdInfor.getEmail());
			pst.setString(3, objAdInfor.getPhone());
			pst.setString(4, objAdInfor.getAddress());
			pst.setString(5, objAdInfor.getImages());
			pst.setInt(6, id);
			result= pst.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
}
