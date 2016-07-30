/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Oct. 07. 2015
 * 
 */
package client;

import java.io.*;
import java.net.*;
import java.util.*;

import exception.AutoException;
import model.Automotive;
import util.*;

public class CarModelOptionsIO {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	// Constructs with streams and socket
	public CarModelOptionsIO(ObjectOutputStream out, ObjectInputStream in, Socket socket) {
		this.out = out;
		this.in = in;
		this.socket = socket;
	}
	// Read the properties from file.
	public boolean readProperties(String fileName) throws AutoException{
		FileIO fileIO = new FileIO();
		Properties props = fileIO.readPropertiesFile(fileName);
		if(props != null){
			try{
				sendOutput(props);
			}
			catch(IOException e){
				throw new AutoException();
			}
			return true;
		}
		return false;
	} 
	// Send output with stream.
	public void sendOutput(Object object) throws IOException{
		out.writeObject(object);
		out.flush();
	}
	// Handle input from stream.
	public Object handleInput() throws IOException, ClassNotFoundException{
		Object object = in.readObject();
		return object;
	}
	// Build automobile with a given file.
	public Automotive buildAutomobile(String fileName, String fileType) throws AutoException{
		FileIO fileIO = new FileIO();
		Automotive auto = new Automotive();
		if(fileType.equals("txt")){
			
			fileIO.readFile(fileName, auto);
			return auto;
		}
		else if(fileType.toLowerCase().equals("properties")){
			Properties props = fileIO.readPropertiesFile(fileName);
			fileIO.parseProperties(props);
			return auto;
		}
		return null;
	}
}
