package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Status;

public class StatusDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public StatusDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<Status> getItem (){
		ArrayList<Status> listStatus = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql="SELECT * FROM `status`";
		try {
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Status objStatus = new Status(rs.getInt("id"), rs.getString("status_ad"), rs.getString("status_cus"), rs.getString("note"));
				listStatus.add(objStatus);
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
		
		return listStatus;
	}
}
