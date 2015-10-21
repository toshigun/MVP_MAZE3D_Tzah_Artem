package presenter.commands;

import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;

/**
 * @author tzah and Artem
 *	Used by the CLI to load a maze from file and save it in the data base by a new given name
 */
public class CommandLoadCompMaze extends CommonCommand  {

	Model m;
	View v;
	
	//constructor
	public CommandLoadCompMaze(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void doCommand(ArrayList<String> arr) {
		
		if(arr.size() == 4 && arr.get(1).equalsIgnoreCase("maze")){
			
			
			m.fetchData("load compressed maze from file", arr.get(3), ensureEx(arr.get(2)));
				
			
			
		}
		else{
			v.displayMessage("unrecognized command or parameter - please use the format: load maze 'name' 'file name'");
			
		}

	}
}


