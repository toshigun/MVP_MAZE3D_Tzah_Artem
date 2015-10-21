package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;

/**
 * @author tzah and Artem
 *	Used by the CLI to sole a maze and add the solution to the Maze Data instance in the Data base
 */
public class CommandSolveMaze extends CommonCommand{

	Model m;
	View v;
	
	//constructor
	public CommandSolveMaze(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	
	@Override
	public void doCommand(ArrayList<String> arr) {
		
		if(arr.size() == 3){
					m.fetchData("solve maze", arr.get(1), arr.get(2));
				}
			else{
				v.displayMessage("unrecognized command or parameter - please use the format: solve 'name' 'algorithm'");
			}
		}
	
	

}
