package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import presenter.Command;
import view.Data.Data;


/**
 * @author tzah and Artem
 *	Instance of view
 */
public class MyCLIView extends Observable implements View {

	
	CLI cli;
	Object in,out;		// I/O
	String userCommand;
	
	//default constructor
	public MyCLIView() {		
		
		//default - console view
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		
		
	}
	
	public void userCommandHasChanged(){
		
		this.setChanged();
	}
	
	
	//setters & getters


	public CLI getCli() {
		return cli;
	}

	public void setCli(CLI cli) {
		this.cli = cli;
	}



	//overrides
	
	@Override
	public void start() {
										
		cli = new CLI(in, out, this);
		cli.start();

	}
	
	@Override
	public Data getData() {
		
		return null;
	}
	

	@Override
	public void displayMessage(String s) {
		((PrintWriter)out).println(s);
		((PrintWriter)out).flush();
	}
	
	@Override
	public String getUserCommand() {
	
		return userCommand;
	}
	
	

	@Override
	public void updateData(Data d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMap(ConcurrentHashMap<String, Command> map) {
		// TODO Auto-generated method stub
		
	}


	








}
