package entryPoint;

import java.io.IOException;
import java.net.Socket;
import network.ClientHandler;
import network.ServerHandler;
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
		swingFrame = new ServerMainView();
		swingFrame.setVisible(true);
		ServerControler mainController = swingFrame.getControler();
		try
		{
			ServerHandler Server = new ServerHandler(4242);
			while (true)
			{
				Socket clientSocket = Server.getNewClient();
				new Thread(new ClientHandler(clientSocket, mainController)).start();
			}
		}
		catch (IOException e)
		{
			
		}
	}

	public void handleException(Exception e)
	{
		System.out.println("exception " + e.toString());
		e.printStackTrace();
	}
}
