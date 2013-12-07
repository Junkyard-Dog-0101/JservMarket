package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Products {
	
	private Orm			requester;
	public DbConnect	dbManager;
	public ResultSet	myResultSet;

	public Products(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet getDesignationCategories()
	{
		try
		{
			requester.clear();
			requester.select("designation");
			requester.from("products");
			myResultSet = requester.query();
			return (myResultSet);
		}
		catch (MyOrmException e)
		{
			return (null);
		}
	}
}
