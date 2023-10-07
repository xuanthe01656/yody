package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import library.DefineLb;
import model.bean.CommentRate;
import model.bean.Country;
import model.bean.DisplayPublic;
import model.bean.Menu;
import model.bean.ProductAdmin;
import model.bean.ProductAdminDetail;
import model.bean.Suppliers;
import model.bean.colur;
import model.bean.size;

public class PublicDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public PublicDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<DisplayPublic> getItemPl (){
		ArrayList<DisplayPublic> listDP = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `display_pl`;";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DisplayPublic objDP = new DisplayPublic(rs.getInt("id"), rs.getString("name"),rs.getString("images"), rs.getString("img_detail"), rs.getString("detail"), rs.getString("created_date"), rs.getString("update_date"), rs.getInt("created_at"), rs.getInt("update_at"));
				listDP.add(objDP);
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
		return listDP;
	}
	public DisplayPublic getObjPl (int id) {
		DisplayPublic objPl = new DisplayPublic();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `display_pl` WHERE id=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				DisplayPublic getObj = new DisplayPublic(rs.getInt("id"), rs.getString("name"), rs.getString("images"), rs.getString("img_detail"), rs.getString("detail"), rs.getString("created_date"), rs.getString("update_date"), rs.getInt("created_at"), rs.getInt("update_at"));
				objPl = getObj;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objPl;
	}
	public int getNumberPageFS() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE discount>0 AND amount>0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getItemFS(int index){
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE discount>0 AND amount>0 GROUP BY product.product_id LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumberPageProNew() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE YEARWEEK(product.created_date)=YEARWEEK(NOW()) AND amount>0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getProductNew(int index){
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE YEARWEEK(product.created_date)=YEARWEEK(NOW()) AND amount>0 GROUP BY product.product_id LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumberPageProPS() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Áo Polo%' AND amount>0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getProPS(int index) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Áo Polo%' AND amount >0 GROUP BY product.product_id LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumberPageProJean() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Jean%' AND amount>0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getProJean(int index) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Jean%' AND amount >0 GROUP BY product.product_id LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumberPageProOffice() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE ((product.name LIKE '%Quần Âu%') OR (product.name LIKE '%Áo Sơ Mi%') OR (product.name LIKE '%Chân Váy Nữ%')) AND amount >0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getProOffice(int index) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE ((product.name LIKE '%Quần Âu%') OR (product.name LIKE '%Áo Sơ Mi%') OR (product.name LIKE '%Chân Váy Nữ%')) AND amount>0 GROUP BY product.product_id LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumberPageProSport() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Đồ Thể Thao%' AND amount >0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getProSport(int index) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Đồ Thể Thao%' AND amount>0 GROUP BY product.product_id LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumberPageProAccessories() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Phụ Kiện%' AND amount >0 GROUP BY product.product_id) AS total;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<ProductAdmin> getProAccessories(int index) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Phụ Kiện%' AND amount >0 GROUP BY product.product_id LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}
	public int getNumProPS() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Áo Polo%' AND amount >0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public int getNumProFS() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE discount>0 AND amount >0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public int getNumProNew() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE YEARWEEK(product.created_date)=YEARWEEK(NOW()) AND amount>0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public int getNumProJean() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Jean%' AND amount >0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public int getNumProOffice() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c2_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE ((product.name LIKE '%Quần Âu%') OR (product.name LIKE '%Áo Sơ Mi%') OR (product.name LIKE '%Chân Váy Nữ%')) AND amount>0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public int getNumProSport() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Đồ Thể Thao%' AND amount>0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public int getNumProAccessories() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE category_menu.name LIKE '%Phụ Kiện%' AND amount>0 GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}
	public ArrayList<ProductAdmin> getProDetailByCat(int id, int index) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=? GROUP BY product.product_id LIMIT ?,?";
		//String sql = "SELECT product.id, product_id, product.name,category_menu_detail.name,price,cost,suppliers.supplier_name,country.name,product.images,GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT 10";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setInt(2,id);
			pst.setInt(3,id);
			pst.setInt(4,((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(5, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
			
	}
	public int getNumberPageByCat(int id) {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=? GROUP BY product.product_id) as total;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setInt(2,id);
			pst.setInt(3,id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public int getNumProByCat(int id) {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=? GROUP BY product.product_id) AS total;";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setInt(2,id);
			pst.setInt(3,id);
			rs = pst.executeQuery();
			while(rs.next()) {
				num = rs.getInt(1);				
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
		return num;
	}

	public ProductAdmin getProByColorSku(String color, String sku) {
		ProductAdmin objPro = new ProductAdmin();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE product_id =? AND colour.name_color=? ORDER BY product.product_id;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setString(2, color);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("product.price"), rs.getDouble("product.cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				objPro = objP;
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
		return objPro;
	}

	public ProductAdminDetail getProBySKUColorSize(String sku, String size, String color, int amount) {
		ProductAdminDetail objPro = new ProductAdminDetail();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN (SELECT a.id AS menu_id, a.name AS categorymenu,a.images AS imagesmenu, a.parent_id AS parent_idc, b.id AS menuc1_id, b.name AS categorymenudetail,b.images AS imagescategorymenudetail, b.parent_id AS parentidc1, c.id AS menuc2_id, c.name AS categorypro,c.images AS imagescategory, c.parent_id AS parentidc2 FROM category_menu a, category_menu b, category_menu c WHERE a.id = b.parent_id AND b.id = c.parent_id) AS categories ON product.menu_c1_id = categories.menuc1_id AND product.menu_c2_id = categories.menuc2_id AND product.menu_id = categories.menu_id WHERE product.product_id =? AND colour.name_color=? AND size.size_name=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setString(2, color);
			pst.setString(3, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("categories.menu_id"), rs.getString("categories.categorymenu"), rs.getString("categories.imagescategory"), rs.getInt("categories.parent_idc"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("suppliers.created"), rs.getString("suppliers.updated"), rs.getInt("suppliers.user_init"), rs.getInt("suppliers.user_upd"));
				colur objCl = new colur(rs.getInt("colour.id"), rs.getString("name_color"), rs.getInt("colour.created_by"), rs.getString("colour.creat_date"));
				size objS = new size(rs.getInt("size.id"), rs.getString("size_name"),rs.getInt("size.created_by") , rs.getString("size.creat_date"));
				
				ProductAdminDetail objP = new ProductAdminDetail(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("colour.name_color"), rs.getString("size.size_name"), amount,  rs.getInt("quantity_sold"), rs.getDouble("product.cost"), rs.getDouble("product.price"), rs.getString("product.outstanding_feature"), rs.getString("product.detail"),  rs.getInt("product.discount"),rs.getString("product.images"), rs.getString("product.list_image"), rs.getInt("view"), rs.getString("categorymenu"), rs.getString("categorymenudetail"),rs.getString("categorypro"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), objCl, objS, menu,  sup, country);
				objPro = objP;
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
		return objPro;
	}
	public ProductAdminDetail getProBySKUColor(String sku, String color) {
		ProductAdminDetail objPro = new ProductAdminDetail();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN (SELECT a.id AS menu_id, a.name AS categorymenu,a.images AS imagesmenu, a.parent_id AS parent_idc, b.id AS menuc1_id, b.name AS categorymenudetail,b.images AS imagescategorymenudetail, b.parent_id AS parentidc1, c.id AS menuc2_id, c.name AS categorypro,c.images AS imagescategory, c.parent_id AS parentidc2 FROM category_menu a, category_menu b, category_menu c WHERE a.id = b.parent_id AND b.id = c.parent_id) AS categories ON product.menu_c1_id = categories.menuc1_id AND product.menu_c2_id = categories.menuc2_id AND product.menu_id = categories.menu_id WHERE product.product_id =? AND colour.name_color=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			pst.setString(2, color);
			rs = pst.executeQuery();
			while(rs.next()) {
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("categories.menu_id"), rs.getString("categories.categorymenu"), rs.getString("categories.imagescategory"), rs.getInt("categories.parent_idc"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("suppliers.created"), rs.getString("suppliers.updated"), rs.getInt("suppliers.user_init"), rs.getInt("suppliers.user_upd"));
				colur objCl = new colur(rs.getInt("colour.id"), rs.getString("name_color"), rs.getInt("colour.created_by"), rs.getString("colour.creat_date"));
				size objS = new size(rs.getInt("size.id"), rs.getString("size_name"),rs.getInt("size.created_by") , rs.getString("size.creat_date"));
				
				ProductAdminDetail objP = new ProductAdminDetail(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("colour.name_color"), rs.getString("size.size_name"), rs.getInt("product.amount"), rs.getInt("quantity_sold"), rs.getDouble("product.cost"), rs.getDouble("product.price"), rs.getString("product.outstanding_feature"), rs.getString("product.detail"),  rs.getInt("product.discount"),rs.getString("product.images"), rs.getString("product.list_image"), rs.getInt("view"), rs.getString("categorymenu"), rs.getString("categorymenudetail"),rs.getString("categorypro"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), objCl, objS, menu,  sup, country);
				objPro = objP;
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
		return objPro;
	}

	public ArrayList<ProductAdmin> getItemBySizeMenu(int menu_id, int size_id) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE (product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=?) AND product.size_id=? GROUP BY product.product_id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, menu_id);
			pst.setInt(2, menu_id);
			pst.setInt(3, menu_id);
			pst.setInt(4, size_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}

	public ArrayList<ProductAdmin> getItemByColorMenu(int menu_id, int color_id) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE (product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=?) AND product.color_id=? GROUP BY product.product_id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, menu_id);
			pst.setInt(2, menu_id);
			pst.setInt(3, menu_id);
			pst.setInt(4, color_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}

	public ArrayList<ProductAdmin> getItemByMenu(int menu_id) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=? GROUP BY product.product_id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, menu_id);
			pst.setInt(2, menu_id);
			pst.setInt(3, menu_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}

	public ArrayList<ProductAdmin> getItemByColorSize(int menu_id, Integer integer, Integer integer2) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE (product.menu_id=? OR product.menu_c1_id=? OR product.menu_c2_id=?) AND product.size_id=? AND product.color_id=? GROUP BY product.product_id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, menu_id);
			pst.setInt(2, menu_id);
			pst.setInt(3, menu_id);
			pst.setInt(4, integer);
			pst.setInt(5, integer2);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
	}

	public ArrayList<Menu> getItemByNameMenu(String menu) {
		ArrayList<Menu> listM = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `category_menu` WHERE name = ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, menu);
			rs = pst.executeQuery();
			while(rs.next()) {
				Menu objM = new Menu(rs.getInt("id"), rs.getString("name"), rs.getString("images"), rs.getInt("parent_id"));
				listM.add(objM);
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
		return listM;
	}
}
