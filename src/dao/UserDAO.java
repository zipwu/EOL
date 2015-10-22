package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DBHelper;

public class UserDAO {
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	DBHelper dbHelper = new DBHelper();

	public boolean isAllowed(String username,String password)
	{
		try {
			connection = dbHelper.getConnection();
			String sql = "SELECT * FROM user WHERE Username=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			User user = new User();
			if(resultSet.next()) {
				user.setId(Integer.parseInt(resultSet.getString("ID")));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
				if (user.getUsername().equals(username)&&user.getPassword().hashCode()==password.hashCode()) {
					return true;
				}
				else {
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if (resultSet!=null) {
				try {
					resultSet.close();
					resultSet=null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement!=null) {
				try {
					statement.close();
					statement=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection!=null) {
				dbHelper.close();
				connection=null;
			}
		}
	}
	
	public User getUserbyUsername(String username){
		try {
			connection = dbHelper.getConnection();
			String sql = "SELECT * FROM user WHERE Username=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			User user = new User();
			if(resultSet.next()) {
				user.setId(Integer.parseInt(resultSet.getString("ID")));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
				return user;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if (resultSet!=null) {
				try {
					resultSet.close();
					resultSet=null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement!=null) {
				try {
					statement.close();
					statement=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection!=null) {
				dbHelper.close();
				connection=null;
			}
		}
	}
	
	public void closeConnection() throws SQLException
	{
		dbHelper.close();
		connection=null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
		if (userDAO.isAllowed("wujiaye", "wjy901226")) {
			System.out.println("12");
		}else {
			System.out.println("89");
		}
		String userinfo = userDAO.getUserbyUsername("wujiaye").toString();
		int pwdhash = "wjy901226".hashCode();
		System.out.println(userinfo);
		System.out.println(pwdhash);
		try {
			userDAO.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
