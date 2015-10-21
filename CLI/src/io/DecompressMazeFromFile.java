package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;

/**
 * @author tzah and Artem
 *	Additional assisting class to add to functionality of MyDecompressorInputStream - derived by it
 *	Mainly used to get a clean decompression of compressed mazes
 */
public class DecompressMazeFromFile extends MyDecompressorInputStream {

	
	public DecompressMazeFromFile(FileInputStream fis) {
		super(fis);
		
	}

	public Maze3d decompressFile(FileInputStream in) throws IOException{
		ArrayList<Byte> byteArr = new ArrayList<Byte>();
		byte number=0;
		int numOfRead=0;
		int c;
		int byteArrIndx=0;
		
		while((c=in.read())!=-1){
			
			if(numOfRead%2==0)
				number=(byte)c;
			else{
				for(int i=0;i<c;i++){
					byteArr.add(byteArrIndx,number);
					byteArrIndx++;
				}
			}
			numOfRead++;
		}
		
		//convert to byte array
		byte[] data = new byte[byteArr.size()];
		for (int i = 0; i < data.length; i++) {
		    data[i] = (byte) byteArr.get(i);
		}
		
		
		return new Maze3d(data);
		
	}
		
		
		
		
	
	

}
