package cz.kofron.storage.view.gui;

import javafx.application.Application;
import javafx.application.Platform;

public class GUIThread extends Thread
{
	private boolean running = false;
	
	@Override
	public void run()
	{
		running = true;
		System.out.println("JavaFX Application: pre launch");
		Application.launch(MainWindow.class, "");
		System.out.println("JavaFX Application: past launch");
		running = false;
	}
	
	public void stopGUI()
	{
		if(running)
		{
			Platform.exit();
		}
	}

	public boolean isRunning()
	{
		return running;
	}
}
