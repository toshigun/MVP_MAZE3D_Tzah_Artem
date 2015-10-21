package presenter.commands;


import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;


/**
 * @author tzah and Artem
 * sub department of display used by the CLI to show the maze solution
 */
public class CommandDisplaySolution extends CommonCommand {

	Model m;
	View v;
	
	public CommandDisplaySolution(Model m, View v) {
		this.m = m;
		this.v = v;
	}

	@Override
	public void doCommand(ArrayList<String> arr) {
	
		m.fetchData("display solution", arr.get(2), null);
	}

	
}
