package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Suppliers;
import model.bean.productDetail;

public class SuppliersDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public SuppliersDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<Suppliers> getSup(){
		ArrayList<Suppliers> listSup = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM suppliers";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Suppliers objSup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("created"), rs.getString("updated"), rs.getInt("user_init"), rs.getInt("user_upd"));
				listSup.add(objSup);
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
		return listSup;
	}
	public Suppliers suppliers(productDetail objPrD){
		Suppliers objSp = new Suppliers();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM suppliers WHERE id="+objPrD.getSuppliers_id()+";";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				Suppliers objSp1 = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("created"), rs.getString("updated"), rs.getInt("user_init"), rs.getInt("user_upd"));
				objSp = objSp1;
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
		return objSp;
	}
}
