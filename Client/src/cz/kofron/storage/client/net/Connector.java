package cz.kofron.storage.client.net;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Connector
{
	private Client client;
	private final static int WAIT_TIMEOUT = 3000;
	private final static Logger log = Logger.getLogger(Connector.class.getName());
	
	private class ConnThread extends Thread
	{
		public boolean stopped = false;
		
		@Override
		public void run()
		{
			while(!stopped)
			{
				if(!client.isConnected())
				{
					try
					{
						client.connect();
					}
					catch (UnknownHostException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				synchronized (this)
				{
					try
					{
						wait(WAIT_TIMEOUT);
					}
					catch (InterruptedException e)
					{
					}
				}
			}
			try
			{
				client.disconnect();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private ConnThread connThread;
	
	public Connector(Client client)
	{
		this.client = client;
	}
	
	public void start()
	{
		if(connThread == null)
		{
			connThread = new ConnThread();
			connThread.start();
		}
	}
	
	public void stop()
	{
		if(connThread != null)
		{
			connThread.stopped = true;
			synchronized (connThread)
			{
				connThread.notifyAll();
			}
			connThread = null;
		}
	}
}
