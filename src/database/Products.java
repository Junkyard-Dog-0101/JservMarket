package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Products
{
	private Orm			requester;
	public ResultSet	myResultSet;

	public Products(Orm newOrm)
	{
		requester = newOrm;
	}

	public boolean tryDeleteQuantityFromProduct(String quantity, String idProduct)
	{
		try
		{
			requester.clear();
			requester.select("quantities");
			requester.from("products");
			requester.where("id", "=", idProduct);
			myResultSet = requester.query();
			myResultSet.next();
			if (Integer.parseInt(myResultSet.getString(1)) < Integer.parseInt(quantity))
				return (false);
			requester.clear();
			requester.update("products");
			requester.set("quantities = quantities - " + quantity);
			requester.where("id", "=", idProduct);
			if (requester.exeUpdate() != 0)
				return (true);
		}
		catch (MyOrmException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return (false);
	}
	public ResultSet getProducts()
	{
		try
		{
			requester.clear();
			requester.select("*");
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
