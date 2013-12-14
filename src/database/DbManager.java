package database;

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
	private String		failLog;

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
			lastResult = user.login(tabCommands[1], tabCommands[2]);
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
				return (false);
			}
			catch (java.lang.NullPointerException e)
			{
				return (false);
			}
		}
		failLog = "wrong combination login/password";
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
		if ((userId == null))
		{
			setFailLog("addtocart : not login");
			return (false);
		}
		else if (tabCommands.length < 3)
		{
			setFailLog("addtocart : invalid command line");
			return (false);
		}
		else
		{
			try
			{
				Integer.parseInt(tabCommands[1]);
				Integer.parseInt(tabCommands[2]);
			}
			catch (NumberFormatException e)
			{
				setFailLog("addtocart : invalid Id");
				return (false);
			}
			if (cart.addContentToCart(userId, tabCommands[1], tabCommands[2], product))
			{
				return (true);
			}
			else
			{
				failLog = "addtocart : sql error";
				return (false);
			}
		}
	}

	public boolean pay(String[] tabCommands)
	{
		if (userId == null)
		{
			setFailLog("pay : Not Login");
			return (false);
		}
		else
		{
			return (cart.pay(userId));
		}
	}

	public boolean getCartContent(String[] tabCommands)
	{
		if (userId == null)
		{
			setFailLog("getcartcontent : Not Login");
			return (false);
		}
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

	public String getFailLog()
	{
		return (failLog);
	}

	public void setFailLog(String failLog)
	{
		this.failLog = failLog;
	}

	public void setUserId(String s)
	{
		userId = s;
	}

	public void setUserLogin(String s)
	{
		userLogin = null;
	}
}
