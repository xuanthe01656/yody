package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Menu;
import model.bean.productDetail;

public class MenuDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public MenuDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	public ArrayList<Menu> getItemMByParent_id(int parent_id){
		ArrayList<Menu> listMenu = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM category_menu ";
		String sql = "SELECT * FROM category_menu WHERE parent_id=?";
		//String sql = "SELECT * FROM menu_category INNER JOIN category_menu_detail ON menu_category.id=menu_category_detail.parent_id INNER JOIN category ON menu_category_detail.id=category.parent_id WHERE menu_category.parent_id=? OR category_menu_detail.parent_id=? OR category.parent_id=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, parent_id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Menu objM = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("images"), rs.getInt("parent_id"));
				listMenu.add(objM);
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
		return listMenu;
	}
	public ArrayList<Menu> getItemMenu(){
		ArrayList<Menu> listMenu = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM category_menu WHERE parent_id=0";
		//String sql = "SELECT * FROM menu_category WHERE parent_id=0";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Menu objM = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				listMenu.add(objM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listMenu;
	}
	public ArrayList<Menu> getItemChilMenu(int parentID){
		ArrayList<Menu> listChilMenu = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM category_menu WHERE parent_id="+parentID+";";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Menu objM = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				listChilMenu.add(objM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listChilMenu;
	}
	
	public ArrayList<Menu> getItemMenuM(){
		ArrayList<Menu> listMenu = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM menu_menu WHERE parent_id=0";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Menu objM = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("images"), rs.getInt("parent_id"));
				listMenu.add(objM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listMenu;
	}
	
	public ArrayList<Menu> getItemChilMenuM(int parentID){
		ArrayList<Menu> listChilMenu = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM menu_menu WHERE parent_id="+parentID;
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Menu objM = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("images"), rs.getInt("parent_id"));
				listChilMenu.add(objM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listChilMenu;
	}
	public Menu menu(productDetail ProD){
		Menu objM = new Menu();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM menu_category WHERE id="+ProD.getMenu_id()+";";
		String sql = "SELECT * FROM category_menu WHERE id="+ProD.getMenu_id()+";";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				objM = objM1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return objM;
	}
	public Menu menu(){
		Menu objM = new Menu();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM menu_category WHERE id=1";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {			
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				objM = objM1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return objM;
	}
	public Menu ChilMenuLV1(productDetail ProD){
		Menu objM = new Menu();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM category_menu_detail WHERE id="+ProD.getMenu_c1_id()+";";
		String sql = "SELECT * FROM category_menu WHERE id="+ProD.getMenu_c1_id()+";";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				objM = objM1;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return objM;
	}
	public Menu ChilMenuLV2(productDetail ProD){
		Menu objM = new Menu();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM category WHERE id="+ProD.getMenu_c2_id()+";";
		String sql = "SELECT * FROM category_menu WHERE id="+ProD.getMenu_c2_id()+";";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				objM = objM1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return objM;
	}

	public ArrayList<Menu> getItemChilMenuLV1(int menu) {
		ArrayList<Menu> listChilLv1 = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM category_menu WHERE parent_id="+menu+";";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				listChilLv1.add(objM1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listChilLv1;
	}

	public ArrayList<Menu> getItemChilMenuLV2(int menu) {
		ArrayList<Menu> listChilLv2 = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM category_menu WHERE parent_id="+menu+";";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				listChilLv2.add(objM1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listChilLv2;
	}

	public int editNameCategory(int id, String name) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `category_menu` SET `name`=? WHERE id = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
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

	public int editCategory(int id, String name, String images) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `category_menu` SET `name`=?, `images`=? WHERE id = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, images);
			pst.setInt(3, id);
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

	public int changeImage(int id, String images) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `category_menu` SET `images`=? WHERE id = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, images);
			pst.setInt(2, id);
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

	public Menu getItemById(int id) {
		Menu objM = new Menu();
		conn= connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM category_menu WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				objM = new Menu(rs.getInt("id"), rs.getString("name"), rs.getString("images"), rs.getInt("parent_id"));
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
		return objM;
	}
	public int delMenuById(int id, int parentID, int parentID2) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `category_menu`  WHERE id = ?  ";
		String sql1 = "DELETE FROM `category_menu`  WHERE parent_id=? ";
		String sql2 = "DELETE FROM `category_menu`  WHERE parent_id=? ";
		try {
			pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql1);
			pst = conn.prepareStatement(sql2);
			pst.setInt(1, id);
			pst.setInt(2, parentID);
			pst.setInt(3, parentID2);
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
	public int delMenuById(int id, int parentID) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `category_menu`  WHERE id = ? AND parent_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, parentID);
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
	public int delMenuById(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `category_menu`  WHERE id = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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
	public int addCatByPId(String menuCategory, int parent_id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `category_menu`(`name`, `parent_id`) VALUES (?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, menuCategory);
			pst.setInt(2, parent_id);
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
	public int addCatByPId(String menuCategory,String image, int parent_id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `category_menu`(`name`,`images`, `parent_id`) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, menuCategory);
			pst.setString(2, image);
			pst.setInt(3, parent_id);
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
	public Menu getItemByCat(String cat) {
		Menu objM = new Menu();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM menu_category WHERE id="+ProD.getMenu_id()+";";
		String sql = "SELECT * FROM category_menu WHERE name=?;";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, cat);
			rs=pst.executeQuery();
			if(rs.next()) {
				Menu objM1 = new Menu(rs.getInt("id"), rs.getString("name"),rs.getString("category_menu.images"), rs.getInt("parent_id"));
				objM = objM1;
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
		return objM;
	}
}
