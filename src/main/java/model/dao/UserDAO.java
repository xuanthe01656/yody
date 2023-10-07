package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import library.DefineLb;
import model.bean.Country;
import model.bean.Menu;
import model.bean.Product;
import model.bean.Suppliers;
import model.bean.User;

public class UserDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public UserDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<User> getUserAc(){
		ArrayList<User> listUser = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				User objUs = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),rs.getString("city"), rs.getString("created_date"),rs.getString("update_date"),rs.getString("update_at"),rs.getInt("status"));
				listUser.add(objUs);
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
		return listUser;
	}

	public int addUser(User objU) {
		conn= connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `user`(`name`, `password`, `email`, `phone`,`created_date`) VALUES (?,?,?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objU.getFullname());
			pst.setString(2, objU.getPassword());
			pst.setString(3, objU.getEmail());
			pst.setString(4, objU.getPhone());
			pst.setString(5, objU.getCreated_date());
			result = pst.executeUpdate();
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

	public ArrayList<User> exisUser(String email, String password) {
		User objU = new User();
		ArrayList<User> listUs = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM user WHERE email=? OR password=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			while(rs.next()) {
				objU = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),rs.getString("city"), rs.getString("created_date"),rs.getString("update_date"),rs.getString("update_at"),rs.getInt("status"));
				listUs.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUs;
	}

	public User getUserByID(int id) {
		User objU = new User();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				User objU1 = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),rs.getString("city"), rs.getString("created_date"),rs.getString("update_date"),rs.getString("update_at"),rs.getInt("status"));
				objU = objU1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objU;
	}
	public int getNumUs() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM user";
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
	public int getNumberPage() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN country ON product.country_id = country.id";
		String sql = "SELECT COUNT(*) FROM user";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<User> getPaging(int index){
		ArrayList<User> listUser = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM user LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				User objU = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),rs.getString("city"), rs.getString("created_date"),rs.getString("update_date"),rs.getString("update_at"),rs.getInt("status"));
				listUser.add(objU);
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
		return listUser;
	}

	public ArrayList<User> getListUs(int id) {
		ArrayList<User> listUs = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM user WHERE id != ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				User objU = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),rs.getString("city"), rs.getString("created_date"),rs.getString("update_date"),rs.getString("update_at"),rs.getInt("status"));
				listUs.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listUs;
	}

	public int updateInfo(int id, User objUs) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `user` SET `name`=?, `email`=?, `phone`=?, `update_date`=?, `update_at`= ? WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUs.getFullname());
			pst.setString(2, objUs.getEmail());
			pst.setString(3, objUs.getPhone());
			pst.setString(4, objUs.getUpdate_date());
			pst.setString(5, objUs.getUpdate_at());
			pst.setInt(6, id);
			result = pst.executeUpdate();
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

	public int updatePassword(int id, User objUs) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `user` SET `password`=?, `update_date`=?, `update_at`= ? WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUs.getPassword());
			pst.setString(2, objUs.getUpdate_date());
			pst.setString(3, objUs.getUpdate_at());
			pst.setInt(4, id);
			result = pst.executeUpdate();
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
