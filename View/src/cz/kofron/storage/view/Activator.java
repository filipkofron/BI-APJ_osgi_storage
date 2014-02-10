package cz.kofron.storage.view;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import cz.kofron.storage.view.service.IViewService;
import cz.kofron.storage.view.service.ViewServiceFactory;

public class Activator implements BundleActivator
{

	private static BundleContext context;

	static BundleContext getContext()
	{
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception
	{
		Activator.context = bundleContext;

		Hashtable<String, String> properties = new Hashtable<>();
		properties.put("type", "javafx");

		System.out.println("ViewActivator: Registering View service!");
		bundleContext.registerService(IViewService.class.getName(),
				new ViewServiceFactory(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception
	{
		Activator.context = null;
	}

}
