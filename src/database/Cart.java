package database;

import java.sql.ResultSet;

public class Cart
{
	private Orm			requester;
	public ResultSet	myResultSet;

	public Cart(Orm newOrm)
	{
		requester = newOrm;
	}

	public ResultSet getCartContentByIdUser(int id)
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

	public boolean addContentToCart(String idUser, String idProduct, String quantity, Products product)
	{
		if (!product.tryDeleteQuantityFromProduct(quantity, idProduct))
		{
			return (false);
		}
		requester.clear();
		/* bug en base de donnée : créé des doublons */
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
		return (false);
	}
}
