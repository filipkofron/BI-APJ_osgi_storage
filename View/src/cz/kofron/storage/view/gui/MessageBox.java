package cz.kofron.storage.view.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageBox extends Stage
{
	public final static int ERROR = 0x1;
	public final static int INFO = 0x2;
	public final static int WARN = 0x4;
	
	private Runnable callBack;
	
	public MessageBox(String message, int type, Runnable callBack)
	{
		this.callBack = callBack;
		switch(type)
		{
			case ERROR:
				setTitle("Error!");
				break;
			case WARN:
				setTitle("Warning!");
				break;
			case INFO:
				setTitle("Info");
				break;
			default:
				setTitle("Message");
				break;
		}
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(5));
		initStyle(StageStyle.UTILITY);
		Text text = new Text(message);
		vbox.getChildren().add(text);
		Button butt = new Button("Ok");
		butt.setAlignment(Pos.CENTER);
		butt.setPadding(new Insets(5));
		butt.setOnAction(new EventHandler<ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent arg0)
			{
				MessageBox.this.close();
				if(MessageBox.this.callBack != null)
				{
					MessageBox.this.callBack.run();
				}
			}
		});
		vbox.getChildren().add(butt);
		
		Scene scene = new Scene(vbox, 250, 120);
		setScene(scene);
		
	}
	
	public static void show(String message, int type, Runnable callBack)
	{
		MessageBox box = new MessageBox(message, type, callBack);
		box.showAndWait();
	}
}
