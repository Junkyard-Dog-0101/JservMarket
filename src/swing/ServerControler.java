package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import database.DbManager;

public class ServerControler implements ActionListener, FocusListener, WindowListener
{
	private ServerMainView				mainFrame;
	private HashMap<String, Boolean>	loginList;
	private DbManager					db;
	private UserListView				userView;
	
	public ServerControler(ServerMainView Frame)
	{
		db = new DbManager();
		db.getAllUser();
		loginList = new HashMap<String, Boolean>();
		mainFrame = Frame;
		userView = mainFrame.getUserView();
		setUserView(userView);
	}

	private void setUserView(UserListView userView)
	{
		db.fillUsers(loginList, userView);
	}

	public synchronized String getCommand(String[] tabCommands, DbManager db)
	{
		switch (tabCommands[0])
		{
			case "login":
				if (db.login(tabCommands) == true)
				{
					if (loginList.get(tabCommands[1]) == false)
					{
						contentManager(tabCommands[1], true);
						return ("login:ok");
					}
					else
					{
						return ("login:error");
					}
				}
				else
				{
					//return (db.getFailLog());
					return ("login:error");
				}
			case "register":
				if (db.register(tabCommands) == true)
				{
					contentManager(tabCommands[1], false);
					return ("register:ok");
				}
				else
					return ("register:error");
			case "getproducts":
				if (db.getProducts(tabCommands))
					return ("getproducts:" + db.getData(6));
			case "getcategories":
				if (db.getCategories(tabCommands))
					return ("getcategories" + db.getData(2));
			case "addtocart":
				if (db.addToCart(tabCommands))
					return ("addtocart:ok");
				else
					//return (db.getFailLog());
					return ("addtocart:error");
			case "pay":
				if (db.pay(tabCommands))
					return ("pay:ok");
				else
					//return (db.getFailLog());
					return ("pay:error");
			case "getcartcontent":
				if (db.getCartContent(tabCommands))
					return ("getcartcontent:" + db.getData(4));
				else
					return ("getcartconten:error");
			case "logout":
				if (db.getUserLogin() != null)
				{
					if (loginList.get(db.getUserLogin()) == true)
					{
						contentManager(db.getUserLogin(), false);
						db.setUserId(null);
						db.setUserLogin(null);
						return ("you are disconnect");
					}
				}
		}
		return ("invalid command");
	}

	private synchronized void contentManager(String tabCommands, boolean b)
	{
		loginList.put(tabCommands, b);
		userView.updateContent(tabCommands, b);
	}

	@Override
	public void windowActivated(WindowEvent arg0){}
	
	@Override
	public void windowClosed(WindowEvent arg0){}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		mainFrame.setVisible(false);
		mainFrame.dispose();
		System.exit(1);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0){}

	@Override
	public void windowDeiconified(WindowEvent arg0){}

	@Override
	public void windowIconified(WindowEvent arg0){}

	@Override
	public void windowOpened(WindowEvent arg0){}

	@Override
	public void focusGained(FocusEvent arg0){}

	@Override
	public void focusLost(FocusEvent arg0){}

	@Override
	public void actionPerformed(ActionEvent arg0){}


	public ServerMainView getView()
	{
		return (mainFrame);
	}
}
