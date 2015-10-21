package view.Data;

import java.io.Serializable;

import algorithms.mazeGenerators.Maze3d;


/**
 * @author tzah and Artem
 *	Holds the Data for the Maze3d commands
 */
public class DataDefault3dMaze implements Data, Serializable {
	

	private static final long serialVersionUID = 1L;
	
	String name = null;
	Maze3d maze ;
	String mazeSolution;
	int x=10;
	int y=10;
	int z=10;
	
	public DataDefault3dMaze(){}
	
	//setters & getters
	
	public Maze3d getMaze() {
		return maze;
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	
	
	public String getMazeSolution() {
		return mazeSolution;
	}

	public void setMazeSolution(String mazeSolution) {
		this.mazeSolution = mazeSolution;
	}

	@Override
	public String toString(){
		
		return "Here is a display for the maze "+name+":"+"\n"+ maze.toString();
	}

	
	
	
	
}
