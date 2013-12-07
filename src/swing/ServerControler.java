package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import database.DbConnect;

public class ServerControler implements ActionListener, FocusListener, WindowListener
{
	private ServerMainView	mainFrame;
	private DbConnect		db;
	
	public ServerControler(ServerMainView conversionFrame, DbConnect newDb)
	{
		db = newDb;
		setConversionFrame(conversionFrame);
	}

	public synchronized String getCommand(String[] tabCommands, String login)
	{
		switch (tabCommands[0])
		{
			case "login":
				if (db.login(tabCommands) == true)
					return ("you are connected");
				else
					return ("wrong combination login/password");
			case "register":
				if (db.register(tabCommands) == true)
					return ("you are registered");
				else
					return ("something goes wrong");
			case "getproducts":
				if (db.getProducts(tabCommands))
					return (db.getData(1));
			case "getcategories":
				if (db.getCategories(tabCommands))
					return (db.getData(1));
			case "addtocart":
				db.addToCart(tabCommands);
				break;
			case "pay":
				db.pay(tabCommands);
				break;
			case "getcartcontent":
				db.getCartContent(tabCommands);
				break;
		}
		return (new String("invalid command"));
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

	public void setConversionFrame(ServerMainView conversionFrame)
	{
		this.mainFrame = conversionFrame;
	}

	public synchronized ServerMainView getView()
	{
		return (mainFrame);
	}
}
