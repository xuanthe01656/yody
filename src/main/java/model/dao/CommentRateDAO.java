package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.CommentRate;

public class CommentRateDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public CommentRateDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}

	public int addProductCode(String product_code) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `comment_rate`(`product_sku`, `parent_id`) VALUES (?,0)";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, product_code);
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
	public int addCommentBySKU(String sku) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `comment_rate`(`product_sku`, `parent_id`) VALUES (?,0)";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, sku);
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
	public ArrayList<CommentRate> getCmtBySKU(String sku){
		ArrayList<CommentRate> listCmtBySKU = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `comment_rate` WHERE product_sku = ? AND parent_id=0";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			rs = pst.executeQuery();
			while(rs.next()) {
				CommentRate objCmt = new CommentRate(rs.getInt("id"), rs.getString("product_sku"), rs.getString("comment"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getInt("like_comment"), rs.getString("ip_like"),rs.getString("id_user_like"), rs.getString("images"), rs.getString("rate"), rs.getString("comment_at"), rs.getInt("parent_id"));
				listCmtBySKU.add(objCmt);
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
		
		return listCmtBySKU;
	}
	public ArrayList<CommentRate> getCmtChil(String sku, int id){
		ArrayList<CommentRate> listCmtBySKU = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `comment_rate` WHERE product_sku = ? AND parent_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				CommentRate objCmt = new CommentRate(rs.getInt("id"), rs.getString("product_sku"), rs.getString("comment"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getInt("like_comment"), rs.getString("ip_like"),rs.getString("id_user_like"), rs.getString("images"), rs.getString("rate"), rs.getString("comment_at"), rs.getInt("parent_id"));
				listCmtBySKU.add(objCmt);
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
		
		return listCmtBySKU;
	}
	public ArrayList<CommentRate> getCmtRate(String sku, String rate){
		ArrayList<CommentRate> listCmtBySKU = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `comment_rate` WHERE product_sku = ? AND rate =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setString(2, rate);
			rs = pst.executeQuery();
			while(rs.next()) {
				CommentRate objCmt = new CommentRate(rs.getInt("id"), rs.getString("product_sku"), rs.getString("comment"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getInt("like_comment"), rs.getString("ip_like"),rs.getString("id_user_like"), rs.getString("images"), rs.getString("rate"), rs.getString("comment_at"), rs.getInt("parent_id"));
				listCmtBySKU.add(objCmt);
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
		
		return listCmtBySKU;
	}

	public int addCmtParent(String sku, String fullname, String comment, String rate, String email, String phone, String images,
			int user_id) {
		int result=0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `comment_rate`( `product_sku`, `comment`, `name`, `email`, `phone`, `images`, `rate`, `comment_at`, `parent_id`) VALUES (?,?,?,?,?,?,?,?,0) ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setString(2, comment);
			pst.setString(3, fullname);
			pst.setString(4, email);
			pst.setString(5, phone);
			pst.setString(6, images);
			pst.setString(7, rate);
			pst.setInt(8, user_id);
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

	public int addCmtChil(int id, String sku, String fullname, String email, String comment, String phone,
			int user_id) {
		int result=0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `comment_rate`( `product_sku`, `comment`, `name`, `email`, `phone`, `comment_at`, `parent_id`) VALUES (?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setString(2, comment);
			pst.setString(3, fullname);
			pst.setString(4, email);
			pst.setString(5, phone);
			pst.setInt(6, user_id);
			pst.setInt(7, id);
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

	public CommentRate getCmtById(int id_comment) {
		CommentRate objCmtR = new CommentRate();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM comment_rate WHERE id = ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_comment);
			rs = pst.executeQuery();
			while(rs.next()) {
				CommentRate objCmt = new CommentRate(rs.getInt("id"), rs.getString("product_sku"), rs.getString("comment"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getInt("like_comment"), rs.getString("ip_like"),rs.getString("id_user_like"), rs.getString("images"), rs.getString("rate"), rs.getString("comment_at"), rs.getInt("parent_id"));
				objCmtR = objCmt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objCmtR;
	}

	public int addLikeByIpLike(int id_comment, String ip_like) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `comment_rate` SET `like_comment`=`like_comment`+1,`ip_like`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, ip_like);
			pst.setInt(2, id_comment);
			result=pst.executeUpdate();
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
	public int addLikeByIdUser(int id_comment, String id_user) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `comment_rate` SET `like_comment`=`like_comment`+1,`id_user_like`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id_user);
			pst.setInt(2, id_comment);
			result=pst.executeUpdate();
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

	public int addLikeByIdUserIp(int id_comment, String user_like, String ip_like) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `comment_rate` SET `like_comment`=`like_comment`+1,`id_user_like`=?, `ip_like`=? WHERE id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_like);
			pst.setString(2, ip_like);
			pst.setInt(3, id_comment);
			result=pst.executeUpdate();
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
