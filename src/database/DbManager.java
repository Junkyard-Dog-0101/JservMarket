package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import swing.UserListView;
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
	private String		userLogin;
	private	ResultSet	lastResult;
//	private String		failLog;

	public DbManager()
	{
		myOrm = new Orm();
		user = new Users(myOrm);
		cart = new Cart(myOrm);
		categorie = new Categories(myOrm);
		product = new Products(myOrm);
	}

	public boolean getAllUser()
	{
		lastResult = user.getAllUser();
		return (true);
	}
	public boolean login(String[] tabCommands)
	{
		if (tabCommands.length >= 3)
		{
			try
			{
				lastResult = user.login(tabCommands[1], new String(MessageDigest.getInstance("MD5" ).digest(tabCommands[2].getBytes())));
			}
			catch (NoSuchAlgorithmException e1)
			{
				e1.printStackTrace();
				return (false);
			}
			try
			{
				if (lastResult.next())
				{
					userLogin = lastResult.getString(2);
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
			try
			{
				return (user.register(tabCommands[1], new String(MessageDigest.getInstance("MD5" ).digest(tabCommands[2].getBytes()))));
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
				return (false);
			}
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
		if ((userId == null) || (tabCommands.length < 3))
			return (false);
		else
			return (cart.addContentToCart(userId, tabCommands[1], tabCommands[2], product));
	}

	public boolean pay(String[] tabCommands)
	{
		if (userId == null)
			return (false);		
		return (cart.pay(userId));
	}

	public boolean getCartContent(String[] tabCommands)
	{
		if (userId == null)
			return (false);
		else
		{
			lastResult = cart.getCartContentByIdUser(userId);
			return (true);
		}
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

	public void fillUsers(HashMap<String, Boolean> loginList, UserListView userView)
	{
		try
		{
			while (lastResult.next())
			{
				loginList.put(lastResult.getString(2), false);
				userView.updateContent(lastResult.getString(2), false);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getUserLogin()
	{
		return (userLogin);
	}
}
