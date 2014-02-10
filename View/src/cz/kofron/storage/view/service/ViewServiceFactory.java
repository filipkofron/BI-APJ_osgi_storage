package cz.kofron.storage.view.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import cz.kofron.storage.view.service.impl.ViewServiceImpl;

public class ViewServiceFactory implements ServiceFactory
{
	@Override
	public Object getService(Bundle bundle, ServiceRegistration registration)
	{
		System.out
				.println("ViewServiceFactory: Creating service implementation!");
		return new ViewServiceImpl();
	}

	@Override
	public void ungetService(Bundle bundle, ServiceRegistration registration,
			Object service)
	{
		System.out.println("ViewServiceFactory: Service ceased usage!");
		((ViewServiceImpl) service).destroyView();
	}
}
