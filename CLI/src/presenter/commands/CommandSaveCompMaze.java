package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;


/**
 * @author tzah and Artem
 *	Used by the CLI to save a compressed version on the maze in a given name new or existing file
 */
public class CommandSaveCompMaze extends CommonCommand {

	Model m;
	View v;
	
	//constructor
	public CommandSaveCompMaze(Model m, View v) {
		this.m = m;
		this.v = v;
	}


	@Override
	public void doCommand(ArrayList<String> arr) {
		
		
		if(arr.size() == 4 && arr.get(1).equalsIgnoreCase("maze")){
			
			m.fetchData("save compressed maze to file", arr.get(2), ensureEx(arr.get(3)));

		}
	}
}
