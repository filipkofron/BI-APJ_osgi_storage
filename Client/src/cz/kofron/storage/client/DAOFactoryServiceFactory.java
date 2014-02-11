package cz.kofron.storage.client;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import cz.kofron.storage.client.impl.NetworkDAOFactoryService;
import cz.kofron.storage.client.net.Client;

public class DAOFactoryServiceFactory implements ServiceFactory
{
	private Client client;
	
	public DAOFactoryServiceFactory(Client client)
	{
		super();
		this.client = client;
	}

	@Override
	public Object getService(Bundle bundle, ServiceRegistration registration)
	{
		return new NetworkDAOFactoryService(client);
	}

	@Override
	public void ungetService(Bundle bundle, ServiceRegistration registration, Object service)
	{
	}
	
}
