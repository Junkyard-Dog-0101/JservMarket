package swing;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class ServerMainView extends JFrame
{
	public ServerControler controler;

	public ServerMainView(ServerControler newControler)
	{
		super();
		controler = newControler;
		stateOnCurrentObject();
		addComponents();
	}

	public void stateOnCurrentObject()
	{
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(960, 540));
		setResizable(false);
		setLocation((screenDimension.width - 960) / 2, (screenDimension.height - 540) / 2);
		setLayout(new GridLayout(1, 2));
	}

	public void addComponents()
	{
		add(new ListUserView());
	}
}
