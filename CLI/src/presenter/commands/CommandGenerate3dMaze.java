package presenter.commands;

import java.io.Serializable;
import java.util.ArrayList;

import model.Model;
import model.MyModel;
import presenter.CommonCommand;
import view.View;




/**
 * @author tzah and Artem
 *	A sub department of Generate for the generation of a 3d Maze with or without parameters
 */
public class CommandGenerate3dMaze extends CommonCommand implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Model m;
	View v;
	
	public CommandGenerate3dMaze(Model m, View v) {
		this.m = m;
		this.v = v;
	}

//seters & getters

	public Model getM() {
		return m;
	}



	public void setM(Model m) {
		this.m = m;
	}



	public View getV() {
		return v;
	}



	public void setV(View v) {
		this.v = v;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public void doCommand(ArrayList<String> arr) {
		
		if(arr.size() == 4){		//no parameters
		
			m.fetchData("generate 3d maze default", arr.get(3), null);
			
		}
		else if(arr.size() == 7){		//include parameters - ensure smallest maze 10x10x10
			
			//ensureing minimal maze 5x5x5
			for(int i = 4;i < arr.size(); ++i){
				if(arr.get(i).isEmpty() || ((MyModel) m).StringToInt(arr.get(i)) < 10)
					arr.set(i, ""+10);
			}
			
			m.fetchData("generate 3d maze with parameters", arr.get(3), arr.get(4)+" "+arr.get(5)+" "+arr.get(6));
			
		}
		else{
			v.displayMessage("unrecognized command or parameter - please use the format: name x y z or just: name after the command");
			
		}
				
	}

}
