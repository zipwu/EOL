package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
	
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://222.73.209.52:8899/test";
	private static final String DBUSER = "wujiaye";
	private static final String DBPASSWORD = "123456";
	
	private Connection connection = null;
	
	
	public Connection getConnection() throws Exception
	{
		try {
			Class.forName(DBDRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (connection==null) {
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		}
		return connection;
	}
	public void close()
	{
		if (connection!=null) {
			try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DBHelper dbHelper = new DBHelper();
		Connection connection = null;
		
		try {
		    connection = dbHelper.getConnection();
			if (connection!=null) {
				System.out.println("连接成功");
			}else {
				System.out.println("连接失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbHelper.close();
		if (connection!=null) {
			System.out.println("连接成功");
		}else {
			System.out.println("连接失败");
		}
		try {
		    connection = dbHelper.getConnection();
			if (connection!=null) {
				System.out.println("连接成功");
			}else {
				System.out.println("连接失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
