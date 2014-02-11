package cz.kofron.storage.server.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server
{
	private final static ExecutorService threadPool = Executors.newCachedThreadPool(); 
	private final static Logger log = Logger.getLogger(Server.class.getName());
	private ServerSocket serverSocket;
	
	private class AcceptThread implements Runnable
	{
		public boolean stopped = false;
		
		@Override
		public void run()
		{
			log.info("Storage server started.");
			while(!stopped && !serverSocket.isClosed())
			{
				acceptClient();
			}
		}
	}
	
	private AcceptThread acceptThread = new AcceptThread();
	
	public void acceptClient()
	{
		try
		{
			Socket socket = serverSocket.accept();
			if(socket != null)
			{
				threadPool.execute(new ClientWorker(socket));
			}
		}
		catch (IOException e)
		{
		}
	}
	
	public Server() throws IOException
	{
		serverSocket = new ServerSocket(1234);
		threadPool.execute(acceptThread);
	}
	
	public void stop()
	{
		acceptThread.stopped = true;
		
		synchronized (serverSocket)
		{
			serverSocket.notifyAll();
		}
	}
}
