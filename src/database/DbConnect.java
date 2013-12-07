package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect
{
	private String				url = "jdbc:mysql://localhost/jservmarket";
	private String				user = "root";
	private String				password = "";
	public Statement			myState;
	private static DbConnect	currentDb;

	private DbConnect()
	{
		try
		{
			connectDb();
		}
		catch (Exception e)
		{
			System.err.println("unable to connect db");
		}
	}

	private void connectDb() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection myConnect = DriverManager.getConnection(url, user, password);
		myState = myConnect.createStatement();
	}

	public ResultSet executeQuery(String query) throws SQLException
	{
		return (currentDb.myState.executeQuery(query));
	}

	public int executeUpdate(String query) throws SQLException
	{
		return (currentDb.myState.executeUpdate(query));
	}

	public static DbConnect getInstance()
	{
		if (currentDb == null)
			return (currentDb = new DbConnect());
		else
			return (currentDb);
	}
}
