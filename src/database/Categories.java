package database;

import java.sql.ResultSet;

public class Categories
{
	private Orm			requester;
	public ResultSet	myResultSet;

	public Categories(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet getCategories()
	{
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("categories");
			myResultSet = requester.query();
			return (myResultSet);
		}
		catch (MyOrmException e)
		{
			return (null);
		}
	}
}
