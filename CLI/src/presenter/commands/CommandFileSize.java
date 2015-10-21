package presenter.commands;

import java.util.ArrayList;

import model.Model;
import presenter.CommonCommand;
import view.View;

/**
 * @author tzah and Artem
 *	Used by the CLI to show a requested file size
 */
public class CommandFileSize extends CommonCommand {
	
	Model m;
	View v;

	public CommandFileSize(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}

	@Override
	public void doCommand(ArrayList<String> arr) {
		
		m.fetchData("file size",arr.get(2), null);

	}

}
