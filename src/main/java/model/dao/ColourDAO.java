package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.colur;

public class ColourDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public ColourDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<colur> getColour(){
		ArrayList<colur> listColour = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM colour";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				colur objCl = new colur(rs.getInt("id"), rs.getString("name_color"), rs.getInt("created_by"), rs.getString("creat_date"));
				listColour.add(objCl);
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
		return listColour;
	}

	public int addColor(String color, int user_id, String created_date) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `colour`(`name_color`, `created_by`, `creat_date`) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, color);
			pst.setInt(2, user_id);
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

	public colur getColorByName(String color) {
		colur objColor = new colur();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `colour` WHERE `name_color`=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, color);
			rs = pst.executeQuery();
			while(rs.next()) {
				colur objCl = new colur(rs.getInt("id"), rs.getString("name_color"), rs.getInt("created_by"), rs.getString("creat_date"));
				objColor = objCl;
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
		return objColor;
	}
}
