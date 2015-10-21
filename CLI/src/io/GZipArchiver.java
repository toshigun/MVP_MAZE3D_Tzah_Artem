package io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import view.Data.Data;
import view.Data.DataDefault3dMaze;
import view.Data.DataMapContainer;


public class GZipArchiver {



		/**
	 * when we get back to the project - need to fix the pack and unpack and to check when to close the stream and who should be serializable
	 */
	public GZipArchiver(){}

		//save compressed Gzip file
	    public void compressToGZipFile(ConcurrentHashMap<String, Data> map) throws IOException{
	
	    	//creating a serializable Data object
	    	ConcurrentHashMap<String, DataMapContainer> serMap = new ConcurrentHashMap<>();
	    	
	    	//migrating data 
	    	for(String key: map.keySet()){
	    		// creating a serialized data object (solving the maze3d not being serialized issue)
	    		DataMapContainer tempData = new DataMapContainer(((DataDefault3dMaze)(map.get(key))).getMaze().toByteArray()
	    				, ((DataDefault3dMaze)(map.get(key))).getMazeSolution(),
	    				((DataDefault3dMaze)(map.get(key))).getX(), ((DataDefault3dMaze)(map.get(key))).getY(),
	    				((DataDefault3dMaze)(map.get(key))).getZ());
	    				serMap.put(key, tempData);
	    	}
	    	
	    	FileOutputStream fos = new FileOutputStream("map.gz");
	    	GZIPOutputStream gz = new GZIPOutputStream(fos);
	    	ObjectOutputStream oos = new ObjectOutputStream(gz);
	    	oos.writeObject(serMap);
	    	oos.close();
	           
	    }
	    	
	    	
	    //decompress Gzip
	 
	public ConcurrentHashMap<String, Data> deCompressFromGZip(File f) throws IOException, ClassNotFoundException  {				
	    	
		  	FileInputStream fin = new FileInputStream(f);
	    	GZIPInputStream gzis = new GZIPInputStream(fin);
	    	ObjectInputStream ois = new ObjectInputStream(gzis);
	    	@SuppressWarnings("unchecked")
			ConcurrentHashMap<String, Data> newMap = ((ConcurrentHashMap<String, Data>)ois.readObject());
	    	//convert map to DataDefault3dMaze type
			for(String key: newMap.keySet()){
				DataDefault3dMaze tempData = new DataDefault3dMaze();
				tempData.setMaze(new Maze3d(((DataMapContainer)(newMap.get(key))).getMaze()));
				tempData.setMazeSolution(((DataMapContainer)(newMap.get(key))).getMazeSolution());
				tempData.setName(key);
				tempData.setX(((DataMapContainer)(newMap.get(key))).getX());
				tempData.setY(((DataMapContainer)(newMap.get(key))).getY());
				tempData.setZ(((DataMapContainer)(newMap.get(key))).getZ());
				newMap.put(key, tempData);
			}
			 
	    	
	    	
	    	
	    	ois.close();
	    	return newMap;
		   /*
	    	 FileInputStream fin = new FileInputStream(f);
	    	 GZIPInputStream gzis = new GZIPInputStream(fin);
	    	 ObjectInputStream in = new ObjectInputStream(gzis);
	    	 String[][] strArr = (String[][]) in.readObject();
	    	 DataDefault3dMaze d = new DataDefault3dMaze();
	    	HashMap<String, Data> map = new HashMap<>();
	    
	    
	    	
	    	
	    	//reconstructing map
	    	for(int i = 0; i < strArr.length; ++i){
	    	// //	SimpleDecompressor dec = new SimpleDecompressor(new ByteArrayInputStream(strArr[i][1].getBytes()));
	    		
	    		d.setName(strArr[i][0].toString());
	    		// //d.setMaze(dec.read());
	    		d.setMaze(new Maze3d(strArr[i][1].getBytes()));
	    		d.setMazeSolution(strArr[i][2].toString());
	    		d.setX(Integer.parseInt(strArr[i][3].toString()));
	    		d.setY(Integer.parseInt(strArr[i][4].toString()));
	    		d.setZ(Integer.parseInt(strArr[i][5].toString()));
	    		
	    		map.put(strArr[i][0].toString(), d);
	    		
	    	}
	    	  
	    	  in.close();
	    	  return map;
	    */
	    	
	    	
	    	
	    }
	    
}
