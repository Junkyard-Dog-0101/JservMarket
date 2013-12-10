package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cart
{
	private Orm			requester;
	public ResultSet	myResultSet;

	public Cart(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet getCartContentByIdUser(String id)
	{
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("cart");
			requester.where("userid", "=", id);
			myResultSet = requester.query();
			return (myResultSet);
		}
		catch (MyOrmException e)
		{
			return (null);
		}
	}

	public boolean pay()
	{
		return false;
	}
	
	public boolean addContentToCart(String idUser, String idProduct, String quantity, Products product)
	{
		if (!product.deleteQuantityFromProduct(quantity, idProduct))
		{
			return (false);
		}
		try
		{
			requester.clear();
			requester.select("*");
			requester.from("cart");
			requester.where("userid", "=", idUser);
			myResultSet = requester.query();
			if (myResultSet.next())
			{
				requester.clear();
				requester.update("cart");
				requester.set("quantity = quantity + " + quantity);
				requester.where("userid", "=", idUser);
				if (requester.exeUpdate() != 0)
					return (true);
			}
			else
			{
				requester.clear();
				requester.insert("cart");
				requester.column("userid");
				requester.column("productid");
				requester.column("quantity");
				requester.columnBack();
				requester.valuesInt(idUser);
				requester.valuesInt(idProduct);
				requester.valuesInt(quantity);
				requester.valuesBack();
				if (requester.exeUpdate() != 0)
					return (true);
			}
		}
		catch (MyOrmException e)
		{
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return (false);
	}
}
