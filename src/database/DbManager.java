package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbManager
{
	private String	url;
	private String	user;
	private String	password;

	public Connection 			myConnect;
	public Statement			myState;
	public ResultSet			myResultSet;
	
	public ResultSetMetaData	myResultSetMetaData;
	
	public String 				arrayContent[][];
	public String 				arrayHeader[];
	
	public List<String[]>		productList;
	public List<String[]>		cartList;
	public List<String[]>		usersList;

	public DbManager(String newUrl, String newUser, String newPassword)
	{
		url = newUrl;
		user = newUser;
		password = newPassword;
	}

	public void connectDb() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection myConnect = DriverManager.getConnection(url, user, password);
		myState = myConnect.createStatement();
	}

	public ResultSet executeQuery(String query) throws SQLException
	{
		return (myState.executeQuery(query));
	}
	
	public int executeUpdate(String query) throws SQLException
	{
		return (myState.executeUpdate(query));
	}
}
