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
import model.bean.Menu;
import model.bean.Product;
import model.bean.ProductAdmin;
import model.bean.ProductAdminDetail;
import model.bean.RevenueExpense;
import model.bean.Suppliers;
import model.bean.colur;
import model.bean.productDetail;
import model.bean.size;

public class ProductDao {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public ProductDao() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	public ArrayList<productDetail> getItem(){
		ArrayList<productDetail> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				productDetail objPrD = new productDetail(rs.getInt("id"), rs.getString("product_id"), rs.getString("name"), rs.getInt("color_id"), rs.getInt("size_id"), rs.getInt("amount"), rs.getDouble("cost"), rs.getDouble("price"), rs.getString("outstanding_feature"), rs.getString("detail"), rs.getInt("discount"), rs.getString("images"),rs.getString("list_image"), rs.getInt("view"), rs.getInt("menu_c1_id"), rs.getInt("menu_c2_id"), rs.getInt("menu_id"), rs.getInt("suppliers_id"),rs.getInt("country_id"), rs.getInt("created_at"), rs.getInt("update_at"), rs.getString("created_date"), rs.getInt("is_deleted"));
				listProduct.add(objPrD);
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
		return listProduct;
	}
	public productDetail getObjPr(String id){
		productDetail product = new productDetail();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product WHERE product_id=?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				productDetail product1 = new productDetail(rs.getInt("id"), rs.getString("product_id"), rs.getString("name"), rs.getInt("color_id"), rs.getInt("size_id"), rs.getInt("amount"), rs.getDouble("cost"), rs.getDouble("price"), rs.getString("outstanding_feature"), rs.getString("detail"), rs.getInt("discount"), rs.getString("images"),rs.getString("list_image"), rs.getInt("view"), rs.getInt("menu_c1_id"), rs.getInt("menu_c2_id"), rs.getInt("menu_id"), rs.getInt("suppliers_id"),rs.getInt("country_id"), rs.getInt("created_at"), rs.getInt("update_at"), rs.getString("created_date"), rs.getInt("is_deleted"));
				product = product1;
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
		return product;
	}
	public productDetail getObjPro(int id){
		productDetail product = new productDetail();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product WHERE id="+id+";";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				productDetail product1 = new productDetail(rs.getInt("id"), rs.getString("product_id"), rs.getString("name"), rs.getInt("color_id"), rs.getInt("size_id"), rs.getInt("amount"), rs.getDouble("cost"), rs.getDouble("price"), rs.getString("outstanding_feature"), rs.getString("detail"), rs.getInt("discount"), rs.getString("images"), rs.getString("list_image"), rs.getInt("view"), rs.getInt("menu_c1_id"), rs.getInt("menu_c2_id"), rs.getInt("menu_id"), rs.getInt("suppliers_id"),rs.getInt("country_id"), rs.getInt("created_at"), rs.getInt("update_at"), rs.getString("created_date"), rs.getInt("is_deleted"));
				product = product1;
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
		return product;
	}
	public int getNumberPage() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN country ON product.country_id = country.id";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.product_id) AS total";
		//String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
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
	public int getNumberPageSearch(String search) {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id WHERE product.name LIKE '%"+search+"%' OR category_menu.name LIKE '%"+search+"%' OR product.product_id LIKE '%"+search+"%' GROUP BY product.product_id ORDER BY product.product_id) AS total";
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
	public int getNumberPage1() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM product_list";
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
	public int getNumPro() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT COUNT(*) FROM product_list";
		//String sql = "SELECT COUNT(*) FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN country ON product.country_id = country.id";
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id GROUP BY product.product_id ORDER BY product.id) AS total";
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
	public int getNumProSearch(String search) {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id WHERE product.name LIKE '%"+search+"%' OR category_menu.name LIKE '%"+search+"%' OR product.product_id LIKE '%"+search+"%' GROUP BY product.product_id ORDER BY product.product_id) AS total";
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
	public ArrayList<Product> getPaging(int index){
		ArrayList<Product> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN category_menu ON product.menu_id = category_menu.id INNER JOIN country ON product.country_id = country.id ORDER BY product.id DESC LIMIT ?,?";
		//String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN menu_category ON product.menu_id = menu_category.id INNER JOIN country ON product.country_id = country.id ORDER BY product.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("category_menu.id"), rs.getString("category_menu.name"),rs.getString("category_menu.images"), rs.getInt("category_menu.id"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("created"), rs.getString("updated"), rs.getInt("user_init"), rs.getInt("user_upd"));
				Product objP = new Product(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getInt("amount"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("images"), menu,  sup, country);
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
	public ArrayList<Product> getPagingSearch(int index, String search){
		ArrayList<Product> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN category_menu ON product.menu_id = category_menu.id INNER JOIN country ON product.country_id = country.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID WHERE product.name LIKE '%"+search+"%' OR category_menu.name LIKE '%"+search+"%' OR product.product_id LIKE '%"+search+"%' GROUP BY product.product_id  ORDER BY product.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("category_menu.id"), rs.getString("category_menu.name"),rs.getString("category_menu.images"), rs.getInt("category_menu.id"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("created"), rs.getString("updated"), rs.getInt("user_init"), rs.getInt("user_upd"));
				Product objP = new Product(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"),rs.getString("category_menu.name"), rs.getDouble("price"), rs.getDouble("cost"), rs.getInt("amount"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("images"), menu,  sup, country);
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
	public int addProduct(productDetail objP) {
		
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `product`(`product_id`, `name`, `color_id`, `size_id`, `amount`, `cost`, `price`, `outstanding_feature`, `detail`, `images`, `list_image`,  `menu_c1_id`, `menu_c2_id`, `menu_id`, `suppliers_id`, `country_id`, `created_at`, `created_date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objP.getProductSKU());
			pst.setString(2, objP.getName());
			pst.setInt(3, objP.getColor_id());
			pst.setInt(4, objP.getSize_id());
			pst.setInt(5, objP.getAmount());
			pst.setDouble(6, objP.getCost());
			pst.setDouble(7, objP.getPrice());
			pst.setString(8, objP.getOutstanding_feature());
			pst.setString(9, objP.getDetail());
			pst.setString(10, objP.getImages());
			pst.setString(11, objP.getList_images());
			pst.setInt(12, objP.getMenu_c1_id());
			pst.setInt(13, objP.getMenu_c2_id());
			pst.setInt(14, objP.getMenu_id());
			pst.setInt(15, objP.getSuppliers_id());
			pst.setInt(16, objP.getCountry_id());
			pst.setInt(17, objP.getCreated_at());
			pst.setString(18, objP.getCreated_date());
			
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
	public ArrayList<productDetail> getItemBySUK(String id_product) {
		ArrayList<productDetail> listItem = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product WHERE product_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id_product);
			rs = pst.executeQuery();
			while(rs.next()) {
				productDetail objPrD = new productDetail(rs.getInt("id"), rs.getString("product_id"), rs.getString("name"), rs.getInt("color_id"), rs.getInt("size_id"), rs.getInt("amount"), rs.getDouble("cost"), rs.getDouble("price"), rs.getString("outstanding_feature"), rs.getString("detail"), rs.getInt("discount"), rs.getString("images"), rs.getString("list_image"), rs.getInt("view"), rs.getInt("menu_c1_id"), rs.getInt("menu_c2_id"), rs.getInt("menu_id"), rs.getInt("suppliers_id"),rs.getInt("country_id"), rs.getInt("created_at"), rs.getInt("update_at"), rs.getString("created_date"), rs.getInt("is_deleted"));
				listItem.add(objPrD);
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
		return listItem;
	}
	public ArrayList<ProductAdminDetail> getProDetailBySUK(String id_product) {
		ArrayList<ProductAdminDetail> listItem = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN (SELECT a.id AS menu_id, a.name AS categorymenu,a.images AS imagesmenu, a.parent_id AS parent_idc, b.id AS menuc1_id, b.name AS categorymenudetail,b.images AS imagescategorymenudetail, b.parent_id AS parentidc1, c.id AS menuc2_id, c.name AS categorypro,c.images AS imagescategory, c.parent_id AS parentidc2 FROM category_menu a, category_menu b, category_menu c WHERE a.id = b.parent_id AND b.id = c.parent_id) AS categories ON product.menu_c1_id = categories.menuc1_id AND product.menu_c2_id = categories.menuc2_id AND product.menu_id = categories.menu_id WHERE product.product_id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id_product);
			rs = pst.executeQuery();
			while(rs.next()) {
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("categories.menu_id"), rs.getString("categories.categorymenu"), rs.getString("categories.imagescategory"), rs.getInt("categories.parent_idc"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("suppliers.created"), rs.getString("suppliers.updated"), rs.getInt("suppliers.user_init"), rs.getInt("suppliers.user_upd"));
				colur objCl = new colur(rs.getInt("colour.id"), rs.getString("name_color"), rs.getInt("colour.created_by"), rs.getString("colour.creat_date"));
				size objS = new size(rs.getInt("size.id"), rs.getString("size_name"),rs.getInt("size.created_by") , rs.getString("size.creat_date"));
				ProductAdminDetail objP = new ProductAdminDetail(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("colour.name_color"), rs.getString("size.size_name"), rs.getInt("amount"), rs.getInt("quantity_sold"), rs.getDouble("product.cost"), rs.getDouble("product.price"), rs.getString("product.outstanding_feature"), rs.getString("product.detail"),  rs.getInt("product.discount"),rs.getString("product.images"), rs.getString("product.list_image"), rs.getInt("view"), rs.getString("categorymenu"), rs.getString("categorymenudetail"),rs.getString("categorypro"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), objCl, objS, menu,  sup, country);
				listItem.add(objP);
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
		return listItem;
	}
	public int UpdateAmount(int amount, productDetail objPr, int update_at) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `product` SET `amount`=`amount` + ?, `update_at`= ? WHERE `product_id`=? AND `name`=? AND `color_id` = ? AND `size_id`=? AND `menu_c1_id` =? AND `menu_c2_id`=? AND `menu_id` = ? AND `suppliers_id` = ? AND `country_id` = ?  ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, amount);
			pst.setInt(2, update_at);
			pst.setString(3, objPr.getProductSKU());
			pst.setString(4, objPr.getName());
			pst.setInt(5, objPr.getColor_id());
			pst.setInt(6, objPr.getSize_id());
			pst.setInt(7, objPr.getMenu_c1_id());
			pst.setInt(8, objPr.getMenu_c2_id());
			pst.setInt(9, objPr.getMenu_id());
			pst.setInt(10, objPr.getSuppliers_id());
			pst.setInt(11, objPr.getCountry_id());
			
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
	public ArrayList<Product> getPagingAd(int index){
		ArrayList<Product> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT ?,? ";
		//String sql = "SELECT product.id, product_id, product.name,category_menu_detail.name,price,cost,suppliers.supplier_name,country.name,product.images, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT ?,? ";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();

			while(rs.next()) {  
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("category_menu.id"), rs.getString("category_menu.name"), rs.getString("category_menu.images"), rs.getInt("category_menu.id"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("suppliers.tax_code"), rs.getString("suppliers.notes"), rs.getString("suppliers.created"), rs.getString("suppliers.updated"), rs.getInt("suppliers.user_init"), rs.getInt("suppliers.user_upd"));
				Product objP = new Product(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"),rs.getString("category_menu.name"), rs.getDouble("product.price"), rs.getDouble("product.cost"), rs.getInt("sum_amount"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("product.images"), menu,  sup, country);
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
	
	public ArrayList<ProductAdmin> getItemPl(){
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT 10";
		//String sql = "SELECT product.id, product_id, product.name,category_menu_detail.name,price,cost,suppliers.supplier_name,country.name,product.images,GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT 10";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {  
				
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("product.price"), rs.getDouble("product.cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
				listProduct.add(objP);
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
		return listProduct;
		}
		
	public ArrayList<ProductAdmin> getPagingAd1(int index){
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT *, GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color,GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT ?,? ";
		//String sql = "SELECT product.id, product_id, product.name,category_menu_detail.name,price,cost,suppliers.supplier_name,country.name,product.images,GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();

			while(rs.next()) {  
				
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("product.price"), rs.getDouble("product.cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
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
	public int delProductById(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM product WHERE id = ?";
		try {
			pst=conn.prepareStatement(sql);
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
	public int editProduct(productDetail objP, int id) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `product` SET `product_id`=?, `name`=?, `color_id`=?, `size_id`=?, `amount`=?, `cost`=?, `price`=?, `outstanding_feature`=?, `detail`=?, `menu_c1_id`=?, `menu_c2_id`=?, `menu_id`=?, `suppliers_id`=?, `country_id`=?, `update_at`=?, `created_date`=? WHERE id = ?;";
		int result=0;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objP.getProductSKU());
			pst.setString(2, objP.getName());
			pst.setInt(3, objP.getColor_id());
			pst.setInt(4, objP.getSize_id());
			pst.setInt(5, objP.getAmount());
			pst.setDouble(6, objP.getCost());
			pst.setDouble(7, objP.getPrice());
			pst.setString(8, objP.getOutstanding_feature());
			pst.setString(9, objP.getDetail());
			pst.setInt(10, objP.getMenu_c1_id());
			pst.setInt(11, objP.getMenu_c2_id());
			pst.setInt(12, objP.getMenu_id());
			pst.setInt(13, objP.getSuppliers_id());
			pst.setInt(14, objP.getCountry_id());
			pst.setInt(15, objP.getUpdate_at());
			pst.setString(16, objP.getCreated_date());
			pst.setInt(17,id );
			
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
	public int delProductByCat(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM product WHERE menu_id = ? OR menu_c1_id=? OR menu_c2_id=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, id);
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
	public ArrayList<ProductAdmin> getItemBySKU(String productSKU) {
		ArrayList<ProductAdmin> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT *, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu ON product.menu_c1_id = category_menu.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id WHERE product.product_id=?";
		//String sql = "SELECT product.id, product_id, product.name,category_menu_detail.name,price,cost,suppliers.supplier_name,country.name,product.images,GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT 10";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,productSKU);
			rs = pst.executeQuery();
			while(rs.next()) {  
				
				ProductAdmin objP = new ProductAdmin(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("color"), rs.getString("size"),rs.getString("category_menu.name"), rs.getDouble("product.price"), rs.getDouble("product.cost"), rs.getDouble("promotional_price"), rs.getInt("sum_amount"),rs.getInt("sum_quantity_sold"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), rs.getString("list_images"));
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
	public ArrayList<ProductAdminDetail> getItemBySUK(String sku, String category) {
		ArrayList<ProductAdminDetail> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		//String sql = "SELECT * FROM product_list ORDER BY id DESC LIMIT ?,? ";
		String sql = "SELECT * FROM product INNER JOIN colour ON product.color_id = colour.id INNER JOIN size ON product.size_id = size.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN (SELECT a.id AS menu_id, a.name AS categorymenu,a.images AS imagesmenu, a.parent_id AS parent_idc, b.id AS menuc1_id, b.name AS categorymenudetail,b.images AS imagescategorymenudetail, b.parent_id AS parentidc1, c.id AS menuc2_id, c.name AS categorypro,c.images AS imagescategory, c.parent_id AS parentidc2 FROM category_menu a, category_menu b, category_menu c WHERE a.id = b.parent_id AND b.id = c.parent_id) AS categories ON product.menu_c1_id = categories.menuc1_id AND product.menu_c2_id = categories.menuc2_id AND product.menu_id = categories.menu_id WHERE product.product_id!=? AND categorymenudetail =?";
		//String sql = "SELECT product.id, product_id, product.name,category_menu_detail.name,price,cost,suppliers.supplier_name,country.name,product.images,GROUP_CONCAT(size.size_name) AS size,GROUP_CONCAT(colour.name_color) AS color, GROUP_CONCAT(product.images) AS list_images, SUM(amount) AS sum_amount,SUM(quantity_sold) AS sum_quantity_sold,(SUM(amount)-SUM(quantity_sold)) AS remaining, (product.price-(product.price*(discount/100))) as promotional_price FROM product INNER JOIN category_menu_detail ON product.menu_c1_id = category_menu_detail.id INNER JOIN suppliers ON product.suppliers_id = suppliers.ID INNER JOIN country ON product.country_id = country.id INNER JOIN size ON size.id=product.size_id INNER JOIN colour ON colour.id=product.color_id GROUP BY product.product_id ORDER BY product.id DESC LIMIT 10";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,sku);
			pst.setString(2, category);
			rs = pst.executeQuery();
			while(rs.next()) {  
				
				Country country = new Country(rs.getInt("country.id"), rs.getString("country.name"));
				Menu menu = new Menu(rs.getInt("categories.menu_id"), rs.getString("categories.categorymenu"), rs.getString("categories.imagescategory"), rs.getInt("categories.parent_idc"));
				Suppliers sup = new Suppliers(rs.getInt("ID"), rs.getInt("supplier_code"), rs.getString("supplier_name"), rs.getString("supplier_phone"), rs.getString("supplier_email"), rs.getString("supplier_addr"), rs.getString("tax_code"), rs.getString("notes"), rs.getString("suppliers.created"), rs.getString("suppliers.updated"), rs.getInt("suppliers.user_init"), rs.getInt("suppliers.user_upd"));
				colur objCl = new colur(rs.getInt("colour.id"), rs.getString("name_color"), rs.getInt("colour.created_by"), rs.getString("colour.creat_date"));
				size objS = new size(rs.getInt("size.id"), rs.getString("size_name"),rs.getInt("size.created_by") , rs.getString("size.creat_date"));
				ProductAdminDetail objP = new ProductAdminDetail(rs.getInt("product.id"), rs.getString("product.product_id"), rs.getString("product.name"), rs.getString("colour.name_color"), rs.getString("size.size_name"), rs.getInt("amount"), rs.getInt("quantity_sold"), rs.getDouble("product.cost"), rs.getDouble("product.price"), rs.getString("product.outstanding_feature"), rs.getString("product.detail"),  rs.getInt("product.discount"),rs.getString("product.images"), rs.getString("product.list_image"), rs.getInt("view"), rs.getString("categorymenu"), rs.getString("categorymenudetail"),rs.getString("categorypro"), rs.getString("suppliers.supplier_name"),rs.getString("country.name"), objCl, objS, menu,  sup, country);
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
	public int updateView(String sku) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `product` SET `view`= `view`+1 WHERE product_id =?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, sku);
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
	public ArrayList<RevenueExpense> getRevenue() {
		ArrayList<RevenueExpense> listProduct = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT *, (product.price*SUM(quantity_sold)) AS revenue , (product.cost*SUM(amount)) AS expense, SUM(amount) AS sum_amount, SUM(quantity_sold) AS sum_quantity_sold FROM product GROUP BY product_id ORDER BY sum_quantity_sold DESC ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				RevenueExpense objPrD = new RevenueExpense(rs.getString("product_id"), rs.getString("name"),rs.getString("images"), rs.getInt("sum_amount"), rs.getInt("sum_quantity_sold"), rs.getDouble("cost"), rs.getDouble("price"), rs.getDouble("revenue"), rs.getDouble("expense"));
				listProduct.add(objPrD);
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
		return listProduct;
	}
	public int addNewProByColor(productDetail objP, int color_id,String image, String list_image,int amount, int update_at, String created_date) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `product`(`product_id`, `name`, `color_id`, `size_id`, `amount`, `cost`, `price`, `outstanding_feature`, `detail`, `images`, `list_image`,  `menu_c1_id`, `menu_c2_id`, `menu_id`, `suppliers_id`, `country_id`, `created_at`, `created_date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objP.getProductSKU());
			pst.setString(2, objP.getName());
			pst.setInt(3, color_id);
			pst.setInt(4, objP.getSize_id());
			pst.setInt(5, amount);
			pst.setDouble(6, objP.getCost());
			pst.setDouble(7, objP.getPrice());
			pst.setString(8, objP.getOutstanding_feature());
			pst.setString(9, objP.getDetail());
			pst.setString(10, image);
			pst.setString(11, list_image);
			pst.setInt(12, objP.getMenu_c1_id());
			pst.setInt(13, objP.getMenu_c2_id());
			pst.setInt(14, objP.getMenu_id());
			pst.setInt(15, objP.getSuppliers_id());
			pst.setInt(16, objP.getCountry_id());
			pst.setInt(17, update_at);
			pst.setString(18, created_date);
			
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
	public int addNewProBySize(productDetail objP, int size_id,int amount, int update_at, String created_date) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `product`(`product_id`, `name`, `color_id`, `size_id`, `amount`, `cost`, `price`, `outstanding_feature`, `detail`, `images`, `list_image`,  `menu_c1_id`, `menu_c2_id`, `menu_id`, `suppliers_id`, `country_id`, `created_at`, `created_date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objP.getProductSKU());
			pst.setString(2, objP.getName());
			pst.setInt(3, objP.getColor_id());
			pst.setInt(4, size_id);
			pst.setInt(5, amount);
			pst.setDouble(6, objP.getCost());
			pst.setDouble(7, objP.getPrice());
			pst.setString(8, objP.getOutstanding_feature());
			pst.setString(9, objP.getDetail());
			pst.setString(10, objP.getImages());
			pst.setString(11, objP.getList_images());
			pst.setInt(12, objP.getMenu_c1_id());
			pst.setInt(13, objP.getMenu_c2_id());
			pst.setInt(14, objP.getMenu_id());
			pst.setInt(15, objP.getSuppliers_id());
			pst.setInt(16, objP.getCountry_id());
			pst.setInt(17, update_at);
			pst.setString(18, created_date);
			
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
	public int addNewProByColorSize(productDetail objP, int color_id,int size_id,String image, String list_image,int amount, int update_at, String created_date) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `product`(`product_id`, `name`, `color_id`, `size_id`, `amount`, `cost`, `price`, `outstanding_feature`, `detail`, `images`, `list_image`,  `menu_c1_id`, `menu_c2_id`, `menu_id`, `suppliers_id`, `country_id`, `created_at`, `created_date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objP.getProductSKU());
			pst.setString(2, objP.getName());
			pst.setInt(3, color_id);
			pst.setInt(4, size_id);
			pst.setInt(5, amount);
			pst.setDouble(6, objP.getCost());
			pst.setDouble(7, objP.getPrice());
			pst.setString(8, objP.getOutstanding_feature());
			pst.setString(9, objP.getDetail());
			pst.setString(10, image);
			pst.setString(11, list_image);
			pst.setInt(12, objP.getMenu_c1_id());
			pst.setInt(13, objP.getMenu_c2_id());
			pst.setInt(14, objP.getMenu_id());
			pst.setInt(15, objP.getSuppliers_id());
			pst.setInt(16, objP.getCountry_id());
			pst.setInt(17, update_at);
			pst.setString(18, created_date);
			
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
}
