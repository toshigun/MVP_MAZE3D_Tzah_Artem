package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;

/**
 * @author tzah and Artem
 *	Used as a portal for all the generate type CLI commands
 */
public class CommandGenerate extends CommonCommand  {

	Model m;
	View v;
	
	
	
	public CommandGenerate(Model m, View v) {
		this.m = m;
		this.v = v;
	}



	@Override
	public void doCommand(ArrayList<String> arr) {
		
		switch (arr.get(1)){
		
		case "3d":
				if(arr.get(2).equals("maze")){
					(new CommandGenerate3dMaze(m,v)).doCommand(arr);
				}
				break;
		default:
			v.displayMessage("Error - no matching key found");
				
		
		}
		
	}


}
