package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import library.DefineLb;
import model.bean.Country;
import model.bean.Menu;
import model.bean.OrderDetail;
import model.bean.Orders;
import model.bean.Product;
import model.bean.ProductAdminDetail;
import model.bean.Suppliers;

public class OrdersDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public OrdersDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<OrderDetail> getListOrder(){
		ArrayList<OrderDetail> listOrder = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `orders`;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrderDetail objOrder = new OrderDetail(rs.getInt("id"), rs.getString("order_code"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("shipping_address"), rs.getString("product_detail"),rs.getString("color"),rs.getString("size"),  rs.getInt("qty"), rs.getFloat("price"), rs.getString("images"),rs.getFloat("subtotal"), rs.getFloat("discount"),  rs.getFloat("transports"), rs.getFloat("tax"), rs.getFloat("total"), rs.getInt("order_user"),rs.getString("order_date"), rs.getInt("status"));
				listOrder.add(objOrder);
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
		return listOrder;
	}
	public int addOrder(OrderDetail order) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `orders`(`order_code`, `fullname`, `email`, `phone`, `shipping_address`, `product_detail`,`color`,`size`,`qty`,`images`, `subtotal`, `discount`, `transports`, `tax`, `total`, `order_user`, `price`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, order.getOder_code());
			pst.setString(2, order.getFullname());
			pst.setString(3, order.getEmail());
			pst.setString(4, order.getPhone());
			pst.setString(5, order.getAddress());
			pst.setString(6, order.getProductName());
			pst.setString(7, order.getColor());
			pst.setString(8, order.getSize());
			pst.setInt(9, order.getQty());
			pst.setString(10, order.getImages());
			pst.setFloat(11, order.getSubtotal());
			pst.setFloat(12, order.getDiscount());
			pst.setFloat(13, order.getShipping());
			pst.setFloat(14, order.getTax());
			pst.setFloat(15, order.getTotal());
			pst.setInt(16, order.getOrder_user());
			pst.setFloat(17, order.getPrice());
			result=pst.executeUpdate();
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

	public int addOrders(Orders orders) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `order`(`order_code`, `product_id`, `product_name`, `qty`, `amount`, `user_id`) VALUES (?,?,?,?,?,?);";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, orders.getOrder_code());
			pst.setString(2, orders.getProduct_id());
			pst.setString(3, orders.getProduct_name());
			pst.setInt(4, orders.getQty());
			pst.setFloat(5, orders.getTotal());
			pst.setInt(6, orders.getOrder_user());
			
			result=pst.executeUpdate();
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

	public int addOrderss(ProductAdminDetail order, String order_code, String fullname, String email, String phone,
			String address, float subtotal, float discount, float transport, float tax, float total, int user_id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `orders`(`order_code`, `fullname`, `email`, `phone`, `shipping_address`, `product_detail`,`color`,`size`,`qty`, `subtotal`, `discount`, `transports`, `tax`, `total`, `order_user`,`images`,`price`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, order_code);
			pst.setString(2, fullname);
			pst.setString(3, email);
			pst.setString(4, phone);
			pst.setString(5, address);
			pst.setString(6, order.getName());
			pst.setString(7, order.getColor());
			pst.setString(8, order.getSize());
			pst.setInt(9, order.getAmount());
			pst.setFloat(10, subtotal);
			pst.setFloat(11, discount);
			pst.setFloat(12, transport);
			pst.setFloat(13, tax);
			pst.setFloat(14, total);
			pst.setInt(15, user_id);
			pst.setString(16, order.getImages());
			pst.setFloat(17, (float)order.getPrice());
			result=pst.executeUpdate();
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
	public int getNumberPage() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM `order`";
		
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
		String sql = "SELECT COUNT(*) FROM `order`;";
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
	public ArrayList<Orders> getPaging(int index){
		ArrayList<Orders> listOr = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `order` LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {  
				Orders objOr = new Orders(rs.getInt("id"), rs.getString("order_code"), rs.getString("product_id"), rs.getString("product_name"), rs.getInt("qty"), rs.getInt("amount"), rs.getInt("user_id"),rs.getString("order_date"), rs.getInt("status"));
				listOr.add(objOr);
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
		return listOr;
	}
	
	public ArrayList<OrderDetail> getListOrderByOrderCode(String code_order) {
		ArrayList<OrderDetail> listOrder = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `orders` WHERE `order_code`=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, code_order);
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderDetail objOrder = new OrderDetail(rs.getInt("id"), rs.getString("order_code"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("shipping_address"), rs.getString("product_detail"),rs.getString("color"),rs.getString("size"),  rs.getInt("qty"), rs.getFloat("price"), rs.getString("images"),rs.getFloat("subtotal"), rs.getFloat("discount"),  rs.getFloat("transports"), rs.getFloat("tax"), rs.getFloat("total"), rs.getInt("order_user"),rs.getString("order_date"), rs.getInt("status"));
				listOrder.add(objOrder);
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
		return listOrder;
	}
	public ArrayList<OrderDetail> getListOrderByStatus(int status) {
		ArrayList<OrderDetail> listOrder = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `orders` WHERE `status`=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderDetail objOrder = new OrderDetail(rs.getInt("id"), rs.getString("order_code"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("shipping_address"), rs.getString("product_detail"),rs.getString("color"),rs.getString("size"),  rs.getInt("qty"), rs.getFloat("price"), rs.getString("images"),rs.getFloat("subtotal"), rs.getFloat("discount"),  rs.getFloat("transports"), rs.getFloat("tax"), rs.getFloat("total"), rs.getInt("order_user"),rs.getString("order_date"), rs.getInt("status"));
				listOrder.add(objOrder);
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
		return listOrder;
	}
	public ArrayList<OrderDetail> getListOrderByUser(int oid) {
		ArrayList<OrderDetail> listOrder = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM `orders` WHERE `order_user`=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderDetail objOrder = new OrderDetail(rs.getInt("id"), rs.getString("order_code"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("shipping_address"), rs.getString("product_detail"),rs.getString("color"),rs.getString("size"),  rs.getInt("qty"), rs.getFloat("price"), rs.getString("images"),rs.getFloat("subtotal"), rs.getFloat("discount"),  rs.getFloat("transports"), rs.getFloat("tax"), rs.getFloat("total"), rs.getInt("order_user"),rs.getString("order_date"), rs.getInt("status"));
				listOrder.add(objOrder);
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
		return listOrder;
	}

	public ArrayList<Orders> getListOrder1() {
		// TODO Auto-generated method stub
		return null;
	}

	public int UpdateStatus(String order_code, int status) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql="UPDATE `orders` SET `status`= ? WHERE order_code =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setString(2, order_code);
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
