package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Position;

public class PositionDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
public PositionDAO() {
	connectDBLibrary = new ConnectDBLibrary();
}
public ArrayList<Position> GetPosition (){
	ArrayList<Position> listPosition = new ArrayList<>();
	conn = connectDBLibrary.getConnectMySQL();
	String sql = "SELECT * FROM position";
	try {
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			Position objPosition = new Position(rs.getInt("id"), rs.getString("position"));
			listPosition.add(objPosition);
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
	return listPosition;
}
}
