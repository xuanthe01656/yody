package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.size;

public class SizeDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public SizeDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<size> getSize(){
		ArrayList<size> listSize = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM size";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				size objS = new size(rs.getInt("id"), rs.getString("size_name"),rs.getInt("created_by") , rs.getString("creat_date"));
				listSize.add(objS);
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
		return listSize;
	}

	public int addSize(String size, int id, String created_date) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `size`(`size_name`, `created_by`, `creat_date`) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, size);
			pst.setInt(2, id);
			pst.setString(3, created_date);
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

	public size getSizeByName(String size) {
		size objSize = new size();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `size` WHERE size_name=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				size objS = new size(rs.getInt("id"), rs.getString("size_name"),rs.getInt("created_by") , rs.getString("creat_date"));
				objSize = objS;
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
		return objSize;
	}
}
