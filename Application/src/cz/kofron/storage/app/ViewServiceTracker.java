package cz.kofron.storage.app;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import cz.kofron.storage.view.service.IViewService;

public class ViewServiceTracker extends ServiceTracker implements Runnable
{
	private final static int WAIT_TIMEOUT_MS = 1000;
	private IViewService currentService;
	
	public ViewServiceTracker(BundleContext context)
	{
		super(context, IViewService.class, null);
		initWait();
	}
	
	public void initWait()
	{
		new Thread(this).start();
	}

	@Override
	public Object addingService(ServiceReference reference)
	{
		return super.addingService(reference);
	}
	
	@Override
	public void removedService(ServiceReference reference, Object service)
	{
		Object [] services = getServices();
		if(services == null)
		{
			if(currentService != null)
			{
				AppRun.onViewLost((IViewService) getService());
			}
		}
		else
		{
			int size = services.length;
			boolean found = false;
			for(int i = 0; i < size; i++)
			{
				if(services[i] == currentService)
				{
					found = true;
					break;
				}
			}
			if(found)
			{
				AppRun.onViewLost((IViewService) getService());
			}
		}
		
		super.removedService(reference, service);
		initWait();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("ViewServiceTracker: Waiting for service.");
				Object service = waitForService(WAIT_TIMEOUT_MS);
				if(service != null)
				{
					currentService = (IViewService) service;
					AppRun.onViewAvailable((IViewService) service);
					break;
				}
			}
			catch (InterruptedException e)
			{
			}
		}
	}
}
