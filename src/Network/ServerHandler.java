package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler
{
	private ServerSocket serverSocket;

	public ServerHandler(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
	}

	public Socket getNewClient()
	{
		Socket clientSocket = null;
		try
		{
			clientSocket = serverSocket.accept();
		}
		catch (IOException e)
		{
			System.err.println("Accept failed.");
		}
		return (clientSocket);
	}
}
