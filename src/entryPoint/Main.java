package entryPoint;

import swing.ServerControler;
import swing.ServerMainView;

public class Main
{
	public ServerMainView swingFrame;
	
	public static void main(String argv[])
	{
		new Main();
	}

	public Main()
	{
		try
		{
			createSwingConversionFrame();
		}
		catch (Exception e)
		{
			handleException(e);
		}
	}

	public void createSwingConversionFrame()
	{
		swingFrame = new ServerMainView(new ServerControler());
		swingFrame.setVisible(true);
	}
	
	public void handleException(Exception e)
	{
		System.out.println("exception " + e.toString());
		e.printStackTrace();
	}
}
