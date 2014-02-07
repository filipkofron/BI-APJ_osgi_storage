package cz.kofron.storage.app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cz.kofron.storage.view.service.IViewService;

public class AppRun
{
	private static class AppThread extends Thread
	{
		private boolean running = true;
		private IViewService viewService;
		
		public AppThread(IViewService viewService)
		{
			this.viewService = viewService;
		}
		
		@Override
		public void run()
		{
			while(running)
			{
				System.out.println("AppThread: Starting view ..");
				viewService.initializeView();
				
				try
				{
					synchronized (this)
					{
						wait();
					}
				}
				catch (InterruptedException e)
				{
				}
			}
			System.out.println("AppThread: Destroying view ..");
			viewService.destroyView();
		}
		
		public void stopApp()
		{
			synchronized (this)
			{
				running = false;
				notifyAll();
			}
		}
		
		public IViewService getViewService()
		{
			return viewService;
		}
	}
	
	private static AppThread thread;
	private static Lock lock = new ReentrantLock(); 
	
	public static void onViewAvailable(IViewService viewService)
	{
		System.out.println("AppThread: A view became available.");
		lock.lock();
		if(thread == null)
		{
			thread = new AppThread(viewService);
			thread.start();
		}
		lock.unlock();
	}
	
	public static void onViewLost(IViewService viewService)
	{
		lock.lock();
		if(thread != null)
		{
			thread.stopApp();
			thread = null;
		}
		lock.unlock();
	}
}
