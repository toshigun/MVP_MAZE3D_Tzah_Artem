package presenter.commands;


import java.io.File;
import java.util.ArrayList;

import presenter.CommonCommand;
import view.View;


/**
 * @author tzah and Artem
 *	used by the CLI to show the list of files in the current path
 */
public class CommandDir extends CommonCommand {

	View v;
	
	public CommandDir(View v) {
		
		this.v = v;
	}



	@Override
	public void doCommand(ArrayList<String> arr) {
		
		//fix for a stand alone dir [root]
		if(arr.size() < 2)
			arr.add("/");
		
		//file object for path finding
		File myFile = new File(arr.get(1));
		
		
		//validate directory exist
		if(myFile.isDirectory()){
		//file list for printing
		String[] listOfFiles = myFile.list();
		
		for(int i = 0; i < listOfFiles.length; ++i){
		if(listOfFiles[i] != null){
			
		try {
			//print out
			v.displayMessage(listOfFiles[i]);
			
			} 
		catch (Exception e) {
			System.out.println("file not found");
			e.printStackTrace();
			}
		}
		}
		}
		else{
			//bad directory input
			v.displayMessage("no such directory");
			
		}
		
	}

}
