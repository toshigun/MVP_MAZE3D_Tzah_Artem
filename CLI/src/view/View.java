package view;

import java.util.concurrent.ConcurrentHashMap;

import presenter.Command;
import view.Data.Data;


/**
 * @author tzah and Artem
 *	Interface for the view classes
 */
public interface View {
	
	void start();
	
	Data getData();
	
	public void displayMessage(String s);
	
	public void setMap(ConcurrentHashMap<String, Command> map);
	
	String getUserCommand();
	
	void updateData(Data d);
	
}
