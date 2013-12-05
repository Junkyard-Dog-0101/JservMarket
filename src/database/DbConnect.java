package database;

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

	public DbConnect(String newUrl, String newUser, String newPassword)
	{
		myOrm = new Orm(new DbManager(newUrl, newUser, newPassword));
	}
	
	public boolean login(String[] tabCommands)
	{
		if (tabCommands.length >= 3)
		{
			if (user == null)
			{
				user = new Users(myOrm);
			}
			return (user.Login(tabCommands[1], tabCommands[2]));
		}
		return (false);
	}

	public boolean register(String[] tabCommands)
	{
		if (tabCommands.length >= 3)
		{
			if (user == null)
			{
				user = new Users(myOrm);
			}
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
