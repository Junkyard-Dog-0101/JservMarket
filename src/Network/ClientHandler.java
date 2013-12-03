package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler
{
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public ClientHandler(Socket newClientSocket) throws IOException
	{
		clientSocket = newClientSocket;
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public String readOnClient() throws IOException
	{
		return (in.readLine());
	}

	public void writeOnClient(String outputLine)
	{
		out.println(outputLine);
	}
}
