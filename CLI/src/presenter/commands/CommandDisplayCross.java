package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;


/**
 * @author tzah and Artem
 *	sub department of display used by the CLI to display cross section cuts
 */
public class CommandDisplayCross extends CommonCommand {

	Model m;
	View v;
	
	
	
	public CommandDisplayCross(Model m, View v) {
		this.m = m;
		this.v = v;
	}



	@Override
	public void doCommand(ArrayList<String> arr) {
		
		
		
		
		if(arr.get(1).equalsIgnoreCase("cross") && arr.get(2).equalsIgnoreCase("section") && arr.get(3).equalsIgnoreCase("by") && 
arr.get(6).equalsIgnoreCase("for") && (arr.get(4).equalsIgnoreCase("x") || arr.get(4).equalsIgnoreCase("y") || arr.get(4).equalsIgnoreCase("z"))){
			
				m.fetchData("display cross section by", arr.get(7), ""+arr.get(4)+" "+arr.get(5));
		}
		else{
			v.displayMessage("Error - check your input");
			
		}
		
	}

	
}
