package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Discount;
import model.bean.Menu;

public class DiscountDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public DiscountDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<Discount> getListItem(){
		ArrayList<Discount> listDiscount = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `discount_code`;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {			
				Discount objDiscount = new Discount(rs.getInt("discount_code.id"), rs.getInt("category_id"), rs.getString("discount_code"), rs.getInt("reduced_price"), rs.getString("created_date"), rs.getString("date_end"), null);
				listDiscount.add(objDiscount);
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
		return listDiscount;
	}
	public ArrayList<Discount> getListItemIdMenu(){
		ArrayList<Discount> listDiscount = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `discount_code` INNER JOIN `category_menu` ON category_id = category_menu.id;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Menu objMenu = new Menu(rs.getInt("category_menu.id"), rs.getString("name"), rs.getString("images"), rs.getInt("parent_id"));				
				Discount objDiscount = new Discount(rs.getInt("discount_code.id"), rs.getInt("category_id"), rs.getString("discount_code"), rs.getInt("reduced_price"), rs.getString("created_date"), rs.getString("date_end"), objMenu);
				listDiscount.add(objDiscount);
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
		return listDiscount;
	}
	public Discount getItemByCodeNotMeuId(String code) {
		Discount objDiscount = new Discount();
		conn = connectDBLibrary.getConnectMySQL();
		String sql ="SELECT * FROM `discount_code` WHERE `discount_code`=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, code);
			rs = pst.executeQuery();
			while(rs.next()) {
								
				Discount objDiscount1 = new Discount(rs.getInt("discount_code.id"), rs.getInt("category_id"), rs.getString("discount_code"), rs.getInt("reduced_price"), rs.getString("created_date"), rs.getString("date_end"), null);
				objDiscount = objDiscount1;
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
		return objDiscount;
	}
	public Discount getItemByCode(String code) {
		Discount objDiscount = new Discount();
		conn = connectDBLibrary.getConnectMySQL();
		String sql ="SELECT * FROM `discount_code` INNER JOIN `category_menu` ON category_id = category_menu.id WHERE `discount_code`=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, code);
			rs = pst.executeQuery();
			while(rs.next()) {
				Menu objMenu = new Menu(rs.getInt("category_menu.id"), rs.getString("name"), rs.getString("images"), rs.getInt("parent_id"));				
				Discount objDiscount1 = new Discount(rs.getInt("discount_code.id"), rs.getInt("category_id"), rs.getString("discount_code"), rs.getInt("reduced_price"), rs.getString("created_date"), rs.getString("date_end"), objMenu);
				objDiscount = objDiscount1;
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
		return objDiscount;
	}
}
