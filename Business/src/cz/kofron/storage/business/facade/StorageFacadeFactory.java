package cz.kofron.storage.business.facade;

import cz.kofron.storage.business.facade.impl.DefaultStorageFacade;
import sun.misc.Lock;

public class StorageFacadeFactory
{
	private static Lock lock = new Lock();
	private static StorageFacade instance;
	
	public static StorageFacade getInstance()
	{
		try
		{
			lock.lock();
			
			if(instance == null)
			{
				instance = new DefaultStorageFacade();
			}
			
			lock.unlock();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return instance;
	}
}
