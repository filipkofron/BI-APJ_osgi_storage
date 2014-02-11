package cz.kofron.storage.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import cz.kofron.storage.client.net.Client;
import cz.kofron.storage.client.net.Connector;
import cz.kofron.storage.integration.service.DAOFactoryService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private Connector connector;
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		Client client = new Client();
		connector = new Connector(client);
		connector.start();
		bundleContext.registerService(DAOFactoryService.class, new DAOFactoryServiceFactory(client), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		connector.stop();
	}

}
