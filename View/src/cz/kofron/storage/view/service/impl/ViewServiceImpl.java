package cz.kofron.storage.view.service.impl;

import cz.kofron.storage.view.gui.GUIThread;
import cz.kofron.storage.view.service.IViewService;

public class ViewServiceImpl implements IViewService
{
	private boolean viewInitialized = false;
	private GUIThread guiThread = new GUIThread();
	
	public void initializeView()
	{		
		if(!viewInitialized)
		{
			System.out.println("ViewServiceImpl: Initializing view service...");
			guiThread = new GUIThread();
			guiThread.start();
			viewInitialized = true;
		}
		else
		{
			System.out.println("ViewServiceImpl: View already initialized!");
		}
	}
	
	public void destroyView()
	{
		System.out.println("ViewServiceImpl: destroyView(): Should destroy view stuff.");

		if(viewInitialized)
		{
			System.out.println("ViewServiceImpl: Stopping view service...");
			guiThread.stopGUI();
			viewInitialized = false;
		}
	}
}
