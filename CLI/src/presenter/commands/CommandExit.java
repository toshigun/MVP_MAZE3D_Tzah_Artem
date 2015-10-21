package presenter.commands;

import java.util.ArrayList;

import model.Model;
import presenter.Command;
import view.View;

public class CommandExit implements Command{

	Model m;
	View v;
	
	public CommandExit(Model m, View v) {
	
		this.m = m;
		this.v = v;
	}
	
	public void doCommand(ArrayList<String> arr){
		if(arr.size() > 1){
		v.displayMessage("Error - no matching key found - if you are trying to exit please type Exit");
		}
		else
		m.fetchData("exit", null, null);
		
		
		
	}
	
	
	
	
	
}
