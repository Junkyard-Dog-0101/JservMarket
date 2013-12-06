package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.Cart;
import database.DbManager;
import database.Products;
import database.Users;

public class DbConnect
{
	private Orm			myOrm;
	private Users		user;
	public DbManager	dbManager;
	public Products		products;
	public Users		users;
	public Cart			cart;
	public ResultSet	lastResult;

	public DbConnect(String newUrl, String newUser, String newPassword)
	{
		myOrm = new Orm(new DbManager(newUrl, newUser, newPassword));
		user = new Users(myOrm);
	}
	
	public boolean login(String[] tabCommands)
	{
		if (tabCommands.length >= 3)
		{
			lastResult = user.Login(tabCommands[1], tabCommands[2]);
				try
				{
					if (lastResult.next())
						return (true);
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
			return (user.Register(tabCommands[1], tabCommands[2]));
		}
		return (false);
	}

	public boolean getProducts(String[] tabCommands)
	{
		return (true);	
	}

	public boolean getCategories(String[] tabCommands)
	{
		return (true);
	}

	public boolean addToCart(String[] tabCommands)
	{
		return (true);
	}

	public boolean pay(String[] tabCommands)
	{
		return (true);
	}

	public boolean getCartContent(String[] tabCommands)
	{
		return (true);
	}
}
