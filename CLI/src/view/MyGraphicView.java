package view;

import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import presenter.Command;
import view.Data.Data;

public class MyGraphicView extends Observable implements View {

	String MenuCommand;
	
	public void menuCommandHasChanged(){
		
		this.setChanged();
	}
	
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public Data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayMessage(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMap(ConcurrentHashMap<String, Command> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUserCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateData(Data d) {
		// TODO Auto-generated method stub

	}

}
