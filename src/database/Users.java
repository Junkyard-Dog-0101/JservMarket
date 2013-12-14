package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users
{
	private Orm			requester;
	public ResultSet	myResultSet;

	public Users(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet getAllUser()
	{
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("users");
			myResultSet = requester.query();
			return (myResultSet);
		}
		catch (MyOrmException e)
		{
			return (null);
		}
	}
	
	public ResultSet login(String user, String password)
	{
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("users");
			requester.where("login", "=", user);
			requester.andMD5("password", "=", password);
			myResultSet = requester.query();
			return (myResultSet);
		}
		catch (MyOrmException e)
		{
			return (null);
		}
	}

	public boolean register(String user, String password)
	{
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("users");
			requester.where("login", "=", user);
			myResultSet = requester.query();
			if (!myResultSet.next())
			{
				requester.insert("users");
				requester.column("login");
				requester.column("password");
				requester.columnBack();
				requester.valuesString(user);
				requester.valuesStringMD5(password);
				requester.valuesBack();
				if (requester.exeUpdate() != 0)
					return (true);
			}
			else
			{
				return (false);
			}
		}
		catch (SQLException e)
		{
			return (false);
		}
		catch (MyOrmException e)
		{
			e.printStackTrace();
			return (false);
		}
		return (false);
	}
}
