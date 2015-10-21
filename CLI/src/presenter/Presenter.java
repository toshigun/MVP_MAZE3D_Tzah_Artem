package presenter;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import model.Model;
import presenter.commands.CommandDir;
import presenter.commands.CommandDisplay;
import presenter.commands.CommandExit;
import presenter.commands.CommandFileSize;
import presenter.commands.CommandGenerate;
import presenter.commands.CommandLoadCompMaze;
import presenter.commands.CommandMazeSize;
import presenter.commands.CommandSaveCompMaze;
import presenter.commands.CommandSolveMaze;
import view.View;
import view.Data.Data;

/**
 * @author tzah & Artem
 * Observing Model and view, managing their activities
 */

public class Presenter implements Observer {

	View view;
	Model model;
	ConcurrentHashMap<String, Command> map;
	Properties p;
	
	public Presenter(View view, Model model){
		this.updatePropertiesdFromFile("properties.xml");		//properties file loads on initiation - set by default , can be changed!!
		this.view = view;
		this.model = model;
		this.map = createCommandMap(model, view);
	}
	
	
	//create command map
	public ConcurrentHashMap<String, Command> createCommandMap(Model m, View v){
		
		ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<String,Command>();
		map.put("dir",new CommandDir(v));
		map.put("generate",new CommandGenerate(m,v));
		map.put("display",new CommandDisplay(m,v));
		map.put("save", new CommandSaveCompMaze(m,v));
		map.put("load", new CommandLoadCompMaze(m,v));
		map.put("maze", new CommandMazeSize(m,v));
		map.put("file", new CommandFileSize(m,v));
		map.put("solve", new CommandSolveMaze(m,v));
		map.put("exit", new CommandExit(m,v));
		
		this.map = map;
		return map;
	}
	
	//push the map to view
	public void mapToView(){
		map = createCommandMap(this.model, this.view);		//later need to be sent to the daughter class
		view.setMap(map);	
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		//update view
		if(o==view){
			
			//split string into array list
			ArrayList<String> tempArr = new ArrayList<String>(Arrays.asList(arg.toString().split(" ")));
			tempArr.removeAll(Arrays.asList("",null));
			
			//check first cell vs hashmap
			if(map.containsKey(tempArr.get(0)))
				map.get(tempArr.get(0)).doCommand(tempArr);
			
			else{
				view.displayMessage("Error - no matching key found");
				
			}
		}
		
		//update model
		if(o==model){
			if(arg instanceof String)
				view.displayMessage((String) arg);
			
			else if(arg instanceof Data)
				view.updateData((Data) arg);
				
			} 
		

	}

	public void notifyChanges(String s) {
		view.displayMessage(s);
		
	}
	

	public void makeChanges(Command c) {
		// TODO Auto-generated method stub
		
	}

	private void updatePropertiesdFromFile(String filePath){
		
		File f = new File(filePath);
		if(f.exists()){
		PropertiesCenter PC = new PropertiesCenter();
		p = PC.fetchPropertiesFromFile(f);
		}
		else
			this.p = new Properties();
		
	}

	//getter & setters
	
	public View getView() {
		return view;
	}

	public void setUi(View ui) {
		this.view = ui;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public ConcurrentHashMap<String, Command> getMap() {
		return map;
	}

	public void setMap(ConcurrentHashMap<String, Command> map) {
		this.map = map;
	}


	public Properties getP() {
		return p;
	}


	public void setP(Properties p) {
		this.p = p;
	}


	
	
}
