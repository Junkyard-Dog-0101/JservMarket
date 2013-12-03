package network;

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

	public Socket getNewClient() throws IOException
	{
		Socket clientSocket = null;
		clientSocket = serverSocket.accept();
		return (clientSocket);
	}
}
