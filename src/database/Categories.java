package database;

import java.sql.ResultSet;

public class Categories
{
	private Orm			requester;
	public DbConnect	dbManager;
	public ResultSet	myResultSet;

	public Categories(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet getLabelCategories()
	{
		try
		{
			requester.clear();
			requester.select("label");
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
