package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.Products;
import database.Users;

public class DbManager
{
	private Orm			myOrm;
	private Users		user;
	private Cart		cart;
	private Products	product;
	private Categories	categorie;
	private String		userId;
	private	ResultSet	lastResult;

	public DbManager()
	{
		myOrm = new Orm();
		user = new Users(myOrm);
		cart = new Cart(myOrm);
		categorie = new Categories(myOrm);
		product = new Products(myOrm);
	}

	public boolean login(String[] tabCommands)
	{
		if (tabCommands.length >= 3)
		{
			lastResult = user.login(tabCommands[1], tabCommands[2]);
			try
			{
				if (lastResult.next())
				{
					userId = lastResult.getString(1);
					return (true);
				}
			}
			catch (SQLException e)
			{
				
			}
		}
		return (false);
	}

	public boolean register(String[] tabCommands)
	{
		if (tabCommands.length >= 3)
		{
			lastResult = null;
			return (user.register(tabCommands[1], tabCommands[2]));
		}
		else
		{
			return (false);
		}
	}

	public boolean getProducts(String[] tabCommands)
	{
		lastResult = product.getProducts();
		return (true);
	}

	public boolean getCategories(String[] tabCommands)
	{
		lastResult = categorie.getCategories();
		return (true);
	}

	public boolean addToCart(String[] tabCommands)
	{
		if (userId.isEmpty() || (tabCommands.length < 3))
			return (false);
		else
			return (cart.addContentToCart(userId, tabCommands[1], tabCommands[2], product));
	}

	public boolean pay(String[] tabCommands)
	{
		return (true);
	}

	public boolean getCartContent(String[] tabCommands)
	{
		return (true);
	}
	
	public String getData(int nbrColumn)
	{
		String concatString = new String();
		try
		{
			while (lastResult.next())
			{
				for (int i = 1; i <= nbrColumn; ++i)
				{
					concatString += lastResult.getString(i);
					concatString += ";";
				}
			}
			return (concatString);
		}
		catch (SQLException e)
		{
			return (null);
		}
	}
}
