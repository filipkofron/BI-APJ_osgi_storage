package cz.kofron.storage.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import cz.kofron.storage.business.facade.StorageFacadeFactory;
import cz.kofron.storage.protocol.request.Request;
import cz.kofron.storage.protocol.request.RequestLogin;
import cz.kofron.storage.protocol.response.ResponseBoolean;
import cz.kofron.storage.protocol.service.IResponseService;

public class ClientWorker implements Runnable, IResponseService
{
	private Socket socket;
	private boolean responseLogin = false;
	private boolean logged = false;
	private final static Logger log = Logger.getLogger(ClientWorker.class.getName());
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public ClientWorker(Socket socket)
	{
		this.socket = socket;
	}

	public void run()
	{
		try
		{
			log.info("New client: " + socket);
			socket.setSoTimeout(1000 * 60 * 60);
			
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			serve();
			
			socket.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void serve()
	{
		try
		{
			
			while(socket.isConnected())
			{
				Request request = (Request) ois.readObject();
				log.info("Received request: " + request);
				if(!logged)
				{
					if(request instanceof RequestLogin)
					{
						responseLogin = true;
					}
					else
					{
						log.warning("Error tried command without login!");
						break;
					}
				}
				request.execute(StorageFacadeFactory.getInstance(), this);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendResponse(Object response)
	{
		log.info("Sending response: " + response);
		try
		{
			if(responseLogin)
			{
				responseLogin = false;
				logged = ((ResponseBoolean)response).getValue();
			}
			oos.writeObject(response);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
