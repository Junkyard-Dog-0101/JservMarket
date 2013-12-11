package swing;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class ServerMainView extends JFrame
{
	private ServerControler	controler;
	private CommandView		panelCommand;
	private UserListView	panelUser;

	public ServerMainView()
	{
		panelCommand = new CommandView();
		panelUser = new UserListView();
		controler = new ServerControler(this);
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
//		panelCommand.setSize(50,500);
		add(panelCommand);
		add(panelUser);
	}

	public ServerControler getControler()
	{
		return (controler);
	}

	public CommandView getCommandView()
	{
		return (panelCommand);
	}

	public UserListView getUserView()
	{
		return (panelUser);
	}
}
