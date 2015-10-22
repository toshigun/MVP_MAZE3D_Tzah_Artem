package userInterface;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable {

	Display display;
	Shell shell;					
	
	public BasicWindow(int width, int height) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width,height);
		
	}
		
	abstract void initWigets();
	
	@Override
	public void run() {
		initWigets();
		shell.open();
		
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
			
		}
		display.dispose();
	}

}
