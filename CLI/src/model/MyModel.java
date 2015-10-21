package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFSCommonSearcher;
import algorithms.search.Maze3dAirDistance;
import algorithms.search.Maze3dManhattanDistance;
import algorithms.search.Maze3dSolution;
import algorithms.search.Solution;
import io.DecompressMazeFromFile;
import io.GZipArchiver;
import io.MyCompressorOutputStream;
import presenter.Properties;
import presenter.PropertiesCenter;
import view.Data.Data;
import view.Data.DataDefault3dMaze;


/**
 * @author tzah and Artem
 *	Instance of Model
 */
public class MyModel extends Observable implements Model {

	 ConcurrentHashMap<String,Data> DataBase;
	Data data;
	
	public MyModel() {
		//loading from file
		File f = new File("map.gz");
		
		if(f.exists()){
				GZipArchiver gz = new GZipArchiver();
				try {
					DataBase = gz.deCompressFromGZip(f);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		else
			DataBase = new ConcurrentHashMap<>();
		
		
				}
	
	//overrides
	@Override
	public void makeChanges(Data d) {
		
		//need to fill in 
		
		
	}

	@Override
	public void fetchData(String command, String name, String parameters) {
		if(command == null){
			setChanged();
			notifyObservers("Command input Error");
			return;
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
			
		if(command.equalsIgnoreCase("display")){					//display
			//call method
			display(name);
		}
		else if(command.equalsIgnoreCase("display solution")){		//display solution
			//call method
			displaySolution(name);
		}
		else if(command.equalsIgnoreCase("display cross section by")){	//display Cross section by x/y/z
			if(DataBase.containsKey(name) && DataBase.get(name) instanceof DataDefault3dMaze){	
				ArrayList<String> tempArr = splitStringToArrayList(parameters);
				
			//call method
			displayCrossSectionBy(name, tempArr.get(0), StringToInt(tempArr.get(1)), ((DataDefault3dMaze) DataBase.get(name)).getMaze());
		
			}
			else
				setChanged();
				notifyObservers("Error - maze do not exist in the data base!");
		}
		else if(command.equalsIgnoreCase("generate 3d maze default")){			//generate 3d maze - default 15x15x15
			
			if(DataBase.containsKey(name)){
				setChanged();
				notifyObservers("Maze already exist in the DataBase");
			}
			else{
				//Data for later wrapping 
				 DataDefault3dMaze a = new DataDefault3dMaze();
				//prepare Data
				a.setName(name);
				a.setMaze(generate3dMaze(15, 15, 15));
				//Save to DataBase
				DataBase.put(name, a);
				//notify presenter
				setChanged();
				notifyObservers("maze "+name+" is ready");
			
			}
		}
		else if(command.equalsIgnoreCase("generate 3d maze with parameters")){	//generate 3d maze - minimum imposed 10x10x10
			//creating array list of parameters
			ArrayList<String> arrList = splitStringToArrayList(parameters);
			//Data for later wrapping
			DataDefault3dMaze a = new DataDefault3dMaze();
			a.setName(name);
			a.setMaze(generate3dMaze(StringToInt(arrList.get(0)), StringToInt(arrList.get(1)), StringToInt(arrList.get(2))));
			//setting the name and data into the DB
			DataBase.put(name, a);
			setChanged();
			notifyObservers("maze "+name+" is ready");
			
		}
		else if(command.equalsIgnoreCase("save compressed maze to file") && DataBase.containsKey(name)){	//compress and save maze to a given name file
				
				
				//ask model to compress and save the maze in a given file name
				try {
					saveCompMaze(name, parameters);
					setChanged();
					notifyObservers(name+" have been compressed and saved to file: "+parameters);
				} catch (Exception e) {
					setChanged();
					notifyObservers("Compressed maze writing failed");
					e.printStackTrace();
				}
			}
		else if(command.equalsIgnoreCase("load compressed maze from file")){		//load compressed maze from file, decompressing and saving to DataBase
			
			File f = new File(parameters);
			//validating file
			if(f.exists() && f.length() != 0){
				
				try {
					loadCompMaze(name, parameters);
					setChanged();
					notifyObservers("Maze have been loaded from file: "+parameters+" and save to the database uder the name: "+name);
				} 
				catch (Exception e) {
					setChanged();
					notifyObservers("Error retriving from file!");
					e.printStackTrace();
				}
			}
			else{
				setChanged();
				notifyObservers("File can't be found");
				
			}
		}
		else if(command.equalsIgnoreCase("maze size in RAM")){							//mazeSizeRAM returns either a message with the size of the maze or an error
			
			setChanged();
			notifyObservers(mazeSizeRAM(name));
			
		}
		else if(command.equalsIgnoreCase("file size")){								//shows the maze size while compressed in a file
			
			if(DataBase.containsKey(name)){
			
				//saving the compressed maze to file
			try {
				saveCompMaze(name, "temp.bin");
			} catch (Exception e) {
				e.printStackTrace();
			}
			File file = new File("temp.bin");
			
			setChanged();
			notifyObservers("If saved, the file size for "+name+" would be : " +getFileSize(file)+" bytes");
			//deleting temp file
			file.delete();
			}
			
			else{
				setChanged();
				notifyObservers("Maze can't be found on the Data Base");
			}
		
			
		}
		else if(command.equalsIgnoreCase("solve maze")){				//solve maze and save it to the maze's Data on the Data Base
			
			if(DataBase.containsKey(name)){
				
			if(((DataDefault3dMaze) DataBase.get(name)).getMazeSolution() != null && !(((DataDefault3dMaze) DataBase.get(name)).getMazeSolution()).equals("0")){
				
				 	setChanged();
					notifyObservers("solution for "+name+" is ready");
				
			}
				
			//BFS
			else if(parameters.equalsIgnoreCase("BFS")){
				
				((DataDefault3dMaze) DataBase.get(name)).setMazeSolution((generateMazeSolution(name, "BFS")).toString());
				setChanged();
				notifyObservers("solution for "+name+" is ready");
				
			}
			//Manhattan
			else if (parameters.equalsIgnoreCase("ASTAR-Manhattan")){
				
				((DataDefault3dMaze) DataBase.get(name)).setMazeSolution((generateMazeSolution(name, "ASTAR-Manhattan")).toString());
				setChanged();
				notifyObservers("solution for "+name+" is ready");
				
			}
			//Air
			else if (parameters.equalsIgnoreCase("ASTAR-Air")){
				
				((DataDefault3dMaze) DataBase.get(name)).setMazeSolution((generateMazeSolution(name, "ASTAR-Air")).toString());
				setChanged();
				notifyObservers("solution for "+name+" is ready");
			}
			//error
			else{
				setChanged();
				notifyObservers("The requested algorithm was not found, the currently supported algorithms are BFS, ASTAR-Manhattan and ASTAR-Air");
				
			}
			}
			
		
		else{
			setChanged();
			notifyObservers("Maze does not exist in the Data Base");
		}
		
			
	}
		else if(command.equalsIgnoreCase("exit")){								//Exit
			//save properties to xml file
			PropertiesCenter PC = new PropertiesCenter();
			PC.setToXMLFile(new Properties(System.currentTimeMillis()+"", 3));
			
			//save hashMap to GZip
			GZipArchiver zip = new GZipArchiver();
			try {
				zip.compressToGZipFile(DataBase);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadPool.executor.shutdown();
			System.exit(0);
		}
	

		
	}
		}).start();
	}

	//Assistance
	private ArrayList<String> splitStringToArrayList(String s){
		
		//split string into array list
		if(!s.equals(null)){
		ArrayList<String> tempArr = new ArrayList<String>(Arrays.asList(s.toString().split(" ")));
		tempArr.removeAll(Arrays.asList("",null));
		return tempArr;
		}
		else
		return (new ArrayList<String>());
	}
	
	//turns 2d array into string - assisting getCrossSection (Static - not object dependent)
	private static String arrayToString(int[][] a) {

		  String aString;     
		  aString = "";
		  int column;
		  int row;

		  for (row = 0; row < a.length; row++) {
		      for (column = 0; column < a[0].length; column++ ) {
		      aString = aString + " " + a[row][column];
		        }
		      aString = aString + "\n";
		    }

		    return aString;
		}
		
	//byte size counter
	private long countByte(Object o){
			
		return ((Maze3d)o).toByteArray().length;	
	}
		
	//parse String to int and ensuring the receiving of an int parameter (default is 10)
	public int StringToInt(String s){
		if(Pattern.compile("-?[0-9]+").matcher(s) != null && s.length() < 6){
			try{
				return Integer.parseInt(s);
			}
			catch(Exception e){
				return 10;
			}
			
		}
		else
			return 10;
	}
	
	
	//fetchData methods
	
	//display maze
	private void display(String name){
		
		if(!DataBase.isEmpty() && DataBase.containsKey(name)){
			
			setChanged();
			notifyObservers(((DataDefault3dMaze)DataBase.get(name)).getMaze().toString());
		
		}
		else{
		setChanged();
		notifyObservers("Error - Maze not found!");
			}
		

	
	}
	
	//display solution
	private void displaySolution(String name){
		
		if(!DataBase.isEmpty() && DataBase.containsKey(name) && DataBase.get(name) instanceof DataDefault3dMaze && 
				((DataDefault3dMaze) DataBase.get(name)).getMazeSolution() != null && !(((DataDefault3dMaze) DataBase.get(name)).getMazeSolution()).equals("0")){
			
			setChanged();
			notifyObservers(((DataDefault3dMaze)DataBase.get(name)).getMazeSolution().toString());
		
		}
		else{
		setChanged();
		notifyObservers("Error - Solution not found!");
			}
	}
	
	//display maze cross section
	private void displayCrossSectionBy(String name, String xyz, int index, Maze3d maze){
		
		if(xyz.equalsIgnoreCase("x")){
			
			if(maze.getXLength() > index && index >= 0){					//by x
				
				setChanged();
				notifyObservers(arrayToString(maze.getCrossSectionByX(index)));
				
				}
			}
			else if(xyz.equalsIgnoreCase("y")){								//by y
				
				if(maze.getYLength() > index && index >= 0){
					
					setChanged();
					notifyObservers(arrayToString(maze.getCrossSectionByY(index)));
					
				}
			}
			else if(xyz.equalsIgnoreCase("z")){								//by z
					
				if(maze.getZLength() > index && index >= 0){
						
					setChanged();
					notifyObservers(arrayToString(maze.getCrossSectionByZ(index)));
						
				}
					
			}
			else{															//bad input
						setChanged();
						notifyObservers("Error - check your input");
						
					}
	}
	
	//Generate 3d maze
	private Maze3d generate3dMaze(int x,int y, int z){
		
		
		
		Future<Maze3d> futureMaze = ThreadPool.executor.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
			
				//create maze
				MyMaze3dGenerator my3dGen = new MyMaze3dGenerator();
				Maze3d maze = my3dGen.generate(x,y,z);
				
				
				return maze;
			}
			});
			
			
			
			try {
				return futureMaze.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return null;
			} catch (ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		
			
		
	}
	
	//saves compressed maze into file by a given file name
	private void saveCompMaze(String mazeName, String fileName) throws Exception{
			
		//open write channel
		
		FileOutputStream fos = new FileOutputStream(fileName);
			
		//initiate MyCompressorOutputStream
		MyCompressorOutputStream out = new MyCompressorOutputStream(fos);
			
		//get maze
		Maze3d maze = ((DataDefault3dMaze) DataBase.get(mazeName)).getMaze();
			
		//to byte array
		byte[] byteMaze = maze.toByteArray();
		//write to file
		out.write(byteMaze);
			
		//close stream
		out.close();
		}
	
	//loads compressed maze from a file name and save it to map by a new name
	private void loadCompMaze(String mazeName, String fileName) throws Exception{
			
		//open read channel
		FileInputStream fis = new FileInputStream(fileName);
			
		DecompressMazeFromFile deMzae = new DecompressMazeFromFile(fis);
		//create maze
		Maze3d maze = deMzae.decompressFile(fis);
		//create data
		DataDefault3dMaze mazeData = new DataDefault3dMaze();
		mazeData.setMaze(maze);
		mazeData.setName(mazeName);
			
		//save to DataBase
		DataBase.put(mazeName, mazeData);
			
		//closing streams
		fis.close();
		deMzae.close();
		}
	
	//checks maze size in memory
	private String mazeSizeRAM(String mazeName){
			
			if(DataBase.containsKey(mazeName)){
				
				return ""+this.countByte(((DataDefault3dMaze)DataBase.get(mazeName)).getMaze())+" Bytes";
						
			}
					
			else
						return "Maze can't be found in the Data Base!";
				
			}
	
	//check file size
	private long getFileSize(File file){
		return file.length();
		}
	
	//find solution to maze
	private Solution generateMazeSolution(String mazeName, String algorithm){
		
		Future<Solution> futureMaze = ThreadPool.executor.submit(new Callable<Solution>() {
			

			@Override
			public Solution call() throws Exception {
				
			Maze3d maze = (Maze3d) ((DataDefault3dMaze) DataBase.get(mazeName)).getMaze();
			
			if(algorithm.equalsIgnoreCase("BFS")){								//BFS
			BFSCommonSearcher BFS = new BFSCommonSearcher(new Maze3dSolution());
			
			return BFS.search(new SearchableMaze3d(maze));
			}
			
			else if(algorithm.equalsIgnoreCase("ASTAR-Manhattan")){				//A* Manhattan
			//Heuristic
			Maze3dManhattanDistance man = new Maze3dManhattanDistance();
			Astar astarMan = new Astar(new Maze3dSolution(), man);
			//solve
			return astarMan.search(new SearchableMaze3d(maze));
			
			}
			else if(algorithm.equalsIgnoreCase("ASTAR-Air")){
			//Heuristic
			Maze3dAirDistance Air = new Maze3dAirDistance();
			Astar astarAir = new Astar(new Maze3dSolution(), Air);
			//solve
			return astarAir.search(new SearchableMaze3d(maze));
			
			}
			else{
				setChanged();
				notifyObservers("solution for "+mazeName+" have been canceled due to a syntax error");
				return null;
			}
			}
			
			
			});
			try {
						return futureMaze.get();
				} catch (InterruptedException e) {
						e.printStackTrace();
							return null;
				} catch (ExecutionException e) {
						e.printStackTrace();
							return null;
				}
			
	}
}
