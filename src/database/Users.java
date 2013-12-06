package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users
{
	private Orm			requester;
	public DbManager	dbManager;
	public ResultSet	myResultSet;

	public Users(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet Login(String user, String password)
	{
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("users");
			requester.where("login", "=", user);
			requester.and("password", "=", password);
			myResultSet = requester.query();
			//if (myResultSet.next())
				//return (true);
			return (myResultSet);
		}
		catch (MyOrmException e)
		{
			return (null);
		}
	/*	catch (SQLException e)
		{
			return (null);
		} */
	}

	public boolean Register(String user, String password)
	{
		requester.insert("users");
		requester.column("login");
		requester.column("password");
		requester.columnBack();
		requester.valuesString(user);
		requester.valuesString(password);
		requester.valuesBack();
		if (requester.update() != 0)
			return (true);
		return (false);
	}
}
