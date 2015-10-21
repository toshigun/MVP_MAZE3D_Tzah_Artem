package view.Data;

import algorithms.mazeGenerators.Maze3d;

/**
 * @author tzah and Artem
 *	Holds the Data for the cross section command
 */
public class DataCrossSectionMaze implements Data{

	Maze3d maze;
	String CrossSection;
	
	
	//setters & getters
	public Maze3d getMaze() {
		return maze;
	}
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	public String getCrossSection() {
		return CrossSection;
	}
	public void setCrossSection(String crossSection) {
		CrossSection = crossSection;
	}
	
	
	
	
	
}
