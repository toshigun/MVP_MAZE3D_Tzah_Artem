package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;

/**
 * @author tzah and Artem
 *	Used by the CLI to give out the maze size in the Memory
 */
public class CommandMazeSize extends CommonCommand {

	Model m;
	View v;
	
	public CommandMazeSize(Model m, View v) {
		
		this.m = m;
		this.v = v;
	}

	@Override
	public void doCommand(ArrayList<String> arr) {
		
		if(arr.size() == 3 && arr.get(1).equalsIgnoreCase("size")){
		m.fetchData("maze size in RAM", arr.get(2), null);
		
		
		}
		else{
			v.displayMessage("unrecognized command or parameter - please use the format: maze size 'name' ");
			
			
		}
	}

}
