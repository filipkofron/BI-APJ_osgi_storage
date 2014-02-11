package cz.kofron.storage.integration;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import cz.kofron.storage.integration.service.DAOFactoryService;

public class DAOFactoryServiceTracker extends ServiceTracker
{

	public DAOFactoryServiceTracker(BundleContext context)
	{
		super(context, DAOFactoryService.class, null);
	}

	@Override
	public Object addingService(ServiceReference reference)
	{
		// TODO Auto-generated method stub
		return super.addingService(reference);
	}
	
	@Override
	public void removedService(ServiceReference reference, Object service)
	{
		// TODO Auto-generated method stub
		super.removedService(reference, service);
	}
}
