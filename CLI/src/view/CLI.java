package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;



/**
 * @author tzah and Artem
 *	The engine of the command line interface
 */
public class CLI extends Observable{
	protected BufferedReader in;
	protected PrintWriter out;
	protected MyCLIView mcv;
	
	
	//Constructor
	public CLI(Object inO, Object outO, MyCLIView mcv){
		in = (BufferedReader)inO;
		out = (PrintWriter)outO;
		this.mcv = mcv;
	}
	
	
	public void start(){
		new Thread(new Runnable(){
				
			@Override
			public void run(){
				try {
					
					
					while(!(mcv.userCommand=in.readLine()).equals("exit")){
					
						if(!mcv.userCommand.equals("")){
							
						mcv.userCommandHasChanged();
						mcv.notifyObservers(mcv.getUserCommand());
						
						}
					}
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				finally {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					out.close();
					
				}
				//exit command
				
				mcv.userCommandHasChanged();
				mcv.notifyObservers(mcv.getUserCommand());
			}	
		}).start();
	}
	
}
