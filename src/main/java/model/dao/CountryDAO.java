package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Country;
import model.bean.productDetail;

public class CountryDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public CountryDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<Country> getCountry(){
		ArrayList<Country> listCountry = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM country";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Country objCountry = new Country(rs.getInt("id"), rs.getString("name"));
				listCountry.add(objCountry);
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
		return listCountry;
	}
	public Country country(productDetail proD){
		Country objCn = new Country();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM country WHERE id ="+proD.getCountry_id()+";";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				Country objCn1 = new Country(rs.getInt("id"), rs.getString("name"));
				objCn = objCn1;
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
		return objCn;
	}
}
