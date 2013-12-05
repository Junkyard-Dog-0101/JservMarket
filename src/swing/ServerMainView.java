package swing;

import javax.swing.JFrame;

import database.DbConnect;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class ServerMainView extends JFrame
{
	private ServerControler	controler;
	private CommandView		panelCommand;

	public ServerMainView()
	{
		controler = new ServerControler(this, new DbConnect(new String("jdbc:mysql://localhost/jservmarket"), new String("root"), new String("")));
		panelCommand = new CommandView(controler);
		stateOnCurrentObject();
		addComponents();
	}

	public void stateOnCurrentObject()
	{
		addWindowListener(controler);
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(960, 540));
		setResizable(false);
		setLocation((screenDimension.width - 960) / 2, (screenDimension.height - 540) / 2);
		setLayout(new GridLayout(2, 1));
	}

	public void addComponents()
	{
		add(panelCommand);
	}
	
	public ServerControler getControler()
	{
		return (controler);
	}
	
	public CommandView getCommandView()
	{
		return (panelCommand);
	}
}
