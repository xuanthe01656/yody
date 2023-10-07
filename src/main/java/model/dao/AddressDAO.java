package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Address;

public class AddressDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public AddressDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	public ArrayList<Address> getProvinces (){
		ArrayList<Address> listProvinces = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `provinces`";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Address objAdd = new Address(rs.getString("code"), rs.getString("name"));
				listProvinces.add(objAdd);
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
		
		return listProvinces;
	}
	public ArrayList<Address> getDistrictsByCode (String code){
		ArrayList<Address> listDistricts = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `districts` WHERE province_code=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, code);
			rs = pst.executeQuery();
			while(rs.next()) {
				Address objAdd = new Address(rs.getString("code"), rs.getString("name"));
				listDistricts.add(objAdd);
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
		
		return listDistricts;
	}
	public ArrayList<Address> getWardsByCode (String code){
		ArrayList<Address> listWards = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `wards` WHERE district_code=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, code);
			rs = pst.executeQuery();
			while(rs.next()) {
				Address objAdd = new Address(rs.getString("code"), rs.getString("name"));
				listWards.add(objAdd);
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
		return listWards;
	}
}
