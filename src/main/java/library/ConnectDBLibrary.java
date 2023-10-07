package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDBLibrary {	
	private String url;
	private String user;
	private String pass; 
	private Connection conn=null;
	//private String db;
	
//	public ConnectDBLibrary() {
//		this.db = "bsong";//db bsong trên MySQL;
//		this.url="jdbc:mysql://localhost:3306/"+db+"?useUnicode=true&characterEncoding=UTF-8";
//		this.user="root";
//		this.pass="";
//	}	
	public Connection getConnectMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = PropertiesUtil.readProperties();
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			pass = properties.getProperty("password");
			conn = DriverManager.getConnection(url, user, pass); 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		ConnectDBLibrary connectDBLibrary = new ConnectDBLibrary();
		System.out.println(connectDBLibrary.getConnectMySQL());
	}
}
