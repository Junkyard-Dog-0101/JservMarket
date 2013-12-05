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

	public boolean Login(String user, String password)
	{
		try
		{
			requester.select("*");
			requester.from("users");
			requester.where("login", "=", user);
			requester.and("password", "=", password);
			myResultSet = requester.query();
			if (myResultSet.next())
				return (true);
			return (false);
		}
		catch (MyOrmException e)
		{
			return (false);
		}
		catch (SQLException e)
		{
			return (false);
		}
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
