package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;


/**
 * @author tzah and Artem
 *	used by the CLI to display a maze from the data base and as a portal which executes other display commands
 */
public class CommandDisplay extends CommonCommand {

	Model m;
	View v;
	
	public CommandDisplay(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void doCommand(ArrayList<String> arr) {
		
		if(arr.size() == 2){
			//directly executes
				m.fetchData(arr.get(0),arr.get(1),null);
			
			
		}
		else if(arr.size() == 3){
			if(arr.get(1).equalsIgnoreCase("solution")){
				//call daughter command 
				(new CommandDisplaySolution(m, v)).doCommand(arr);
			}
			
			
		}
		else if(arr.size() == 8){
			//call daughter command 
			(new CommandDisplayCross(m,v)).doCommand(arr);
			
		}
		else
			v.displayMessage("Error - no matching key found");
		
	}

	
	
	
	
	
}
