package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import database.DbManager;

public class ServerControler implements ActionListener, FocusListener, WindowListener
{
	private ServerMainView	mainFrame;
	private List<String>	loginList;

	public ServerControler(ServerMainView conversionFrame)
	{
		loginList = new ArrayList<String>();
		setConversionFrame(conversionFrame);
	}

	public String getCommand(String[] tabCommands, DbManager db)
	{
		switch (tabCommands[0])
		{
			case "login":
				if (db.login(tabCommands) == true)
				{
					if (!loginList.contains(tabCommands[1]))
					{
						/* risque de caca si plusieur user se log */
						loginList.add(tabCommands[1]);
						return ("you are connected");
					}
					return ("you are already connected");
				}
				else
				{
					return ("wrong combination login/password");
				}
			case "register":
				if (db.register(tabCommands) == true)
					return ("you are registered");
				else
					return ("something goes wrong");
			case "getproducts":
				if (db.getProducts(tabCommands))
					return (db.getData(6));
			case "getcategories":
				if (db.getCategories(tabCommands))
					return (db.getData(2));
			case "addtocart":
				if (db.addToCart(tabCommands))
					return ("OK");
				else
					return ("PAS OK");
			case "pay":
				db.pay(tabCommands);
				break;
			case "getcartcontent":
				db.getCartContent(tabCommands);
				break;
		}
		return ("invalid command");
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
