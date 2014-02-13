package cz.kofron.storage.derby;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import cz.kofron.storage.derby.service.impl.DerbyDAOFactoryServiceImpl;
import cz.kofron.storage.integration.service.DAOFactoryService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private final static Logger log = Logger.getLogger(Activator.class.getName());

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		log.info("Registering derbyDB service.");
		bundleContext.registerService(DAOFactoryService.class, new DerbyDAOFactoryServiceImpl(bundleContext), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
