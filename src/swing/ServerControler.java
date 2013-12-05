package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ServerControler implements ActionListener, FocusListener, WindowListener
{
	private ServerMainView	mainFrame;

	public ServerControler(ServerMainView conversionFrame)
	{
		setConversionFrame(conversionFrame);
	}

	public void getCommand(String[] tabCommands)
	{
		switch (tabCommands[0])
		{
			case "login":
				login(tabCommands);
				break;
			case "register":
				register(tabCommands);
				break;
			case "getproducts":
				getProducts(tabCommands);
				break;
			case "getcategories":
				getCategories(tabCommands);
				break;
			case "addtocart":
				addToCart(tabCommands);
				break;
			case "pay":
				pay(tabCommands);
				break;
			case "getcartcontent":
				getCartContent(tabCommands);
				break;
		}
	}

	private void login(String[] tabCommands)
	{
		System.out.println("login");
	}

	private void register(String[] tabCommands)
	{
		
	}

	private void getProducts(String[] tabCommands)
	{
		
	}

	private void getCategories(String[] tabCommands)
	{
		
	}

	private void addToCart(String[] tabCommands)
	{
	
	}

	private void pay(String[] tabCommands)
	{
		
	}

	private void getCartContent(String[] tabCommands)
	{
		
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

	public ServerMainView getView()
	{
		return (mainFrame);
	}
}
