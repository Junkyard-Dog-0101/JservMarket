package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import database.DbManager;
import swing.ServerControler;

public class ClientHandler implements Runnable
{
	private Socket			clientSocket;
	private PrintWriter		out;
	private BufferedReader	in;
	private ServerControler controler;
	private DbManager		db;

	public ClientHandler(Socket newClientSocket, ServerControler newControler) throws IOException
	{
		db = new DbManager();
		clientSocket = newClientSocket;
		controler = newControler;
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

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				String buf = readOnClient();
				if (buf == null)
				{
					clientSocket.close();
					break;
				}
				controler.getView().getCommandView().updateContent(clientSocket.getInetAddress().getHostAddress() + " : " + buf);
				String str[] = buf.split(";");
				writeOnClient(controler.getCommand(str, db));
			}
		}
		catch (IOException e)
		{
			
		}
	}
}
