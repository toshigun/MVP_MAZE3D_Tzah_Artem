package view.Data;

import java.io.Serializable;


/**
 * @author tzah & Artem
 * used to create a serialized data object, main difference from DataDefault3dMaze is that Maze3d is presented here as a byte array
 */
public class DataMapContainer implements Data, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	byte[] maze;
	String mazeSolution;
	int x,y,z;
	
	//constructor
	public DataMapContainer(byte[] maze, String mazeSolution, int x, int y, int z) {
		
		this.maze = maze;
		this.mazeSolution = mazeSolution;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//getters & setters
	
	public byte[] getMaze() {
		return maze;
	}


	public void setMaze(byte[] maze) {
		this.maze = maze;
	}


	public String getMazeSolution() {
		return mazeSolution;
	}


	public void setMazeSolution(String mazeSolution) {
		this.mazeSolution = mazeSolution;
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
	
	

	
	
	
	
	
}
