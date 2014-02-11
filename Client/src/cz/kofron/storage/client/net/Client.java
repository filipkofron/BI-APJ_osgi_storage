package cz.kofron.storage.client.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import cz.kofron.storage.protocol.request.Request;
import cz.kofron.storage.protocol.response.Response;

public class Client
{
	private Socket socket;
	private final static Logger log = Logger.getLogger(Client.class.getName());
	private Lock lock = new ReentrantLock();
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	public void connect() throws UnknownHostException, IOException
	{
		socket = new Socket("127.0.0.1", 1234);
		socket.setSoTimeout(60 * 60);
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		objectInputStream = new ObjectInputStream(socket.getInputStream());
		log.info("Client connected to server");
	}
	
	public void disconnect() throws IOException
	{
		try
		{
			
			objectOutputStream.flush();
			objectOutputStream.close();
			objectInputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		socket.close();
	}
	
	public boolean isConnected()
	{
		if(socket != null)
		{
			if(socket.isConnected())
			{
				return true;
			}
		}
		return false;
	}
	
	public Response communicate(Request request)
	{
		synchronized (this)
		{
			try
			{
				objectOutputStream.writeObject(request);
				Response response = (Response) objectInputStream.readObject();
				return response;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
}
