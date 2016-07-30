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

public class DefaultSocketClient extends Thread {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	private String strHost;
	private int iPort;
	private CarModelOptionsIO carModelOptionsIO;
	public DefaultSocketClient(String strHost, int iPort) {
		setPort (iPort);
		setHost (strHost);
	}
	public void run(){
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}
	private void closeSession() {
		try {
			in = null;
			out = null;
			socket.close();
		}
		catch (IOException e){
			System.err.println("Error closing socket to " + strHost);
		}
	}
	private void handleSession() {
		carModelOptionsIO = new CarModelOptionsIO(out, in, socket);
		if(carModelOptionsIO == null){
			System.out.println("Failed in creating CarModelOptionsIO.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		StringBuffer sb;
		SelectCarOption selectCarOption = new SelectCarOption();
		try{
			while(true){
				sb = new StringBuffer();
				sb.append("MAIN MENU\n");
				sb.append("1. Add automobiles to server\n");
				sb.append("2. Get automobiles list\n");
				sb.append("3. Configure a model\n");
				sb.append("0. Quit\n");
				sb.append("Please enter command code:");
				System.out.print(sb.toString());
				int mainCode = Integer.valueOf(sc.nextLine());
				switch(mainCode){
				case(1): // Interact with server and add an automobile to the server from given file.
					carModelOptionsIO.sendOutput(1);
					System.out.print("Please enter file name:");
					String fileName = sc.nextLine();
					carModelOptionsIO.readProperties(fileName);
					int commandCode = 0;
					commandCode = (int) carModelOptionsIO.handleInput();
					if(commandCode == 5){
						System.out.println("Automobile added message received.");
					}
					break;
				case(2):// Interact with server and get the list of models.
					carModelOptionsIO.sendOutput(mainCode);
					String modelsList = new String();
					modelsList = (String)carModelOptionsIO.handleInput();
					System.out.println(modelsList);
					break;
				case(3):// Interact with server and set the choices with a helper method.
					carModelOptionsIO.sendOutput(mainCode);
					System.out.print("Please enter the model name to modity:");
					String modelName = sc.nextLine();
					carModelOptionsIO.sendOutput(modelName);
					Automotive auto = new Automotive();
					auto = (Automotive)carModelOptionsIO.handleInput();
					selectCarOption.makeChoice(auto);
					break;
				case(0):// Interact with server and exit.
					carModelOptionsIO.sendOutput(0);
					System.out.println("Quit the server.");
					return;
				}
			}
		} 
		catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}
	}
	private boolean openConnection() {
		try{
			socket = new Socket(strHost, iPort);
		}
		catch (IOException e) {
			System.err.println("Accept failed.");
			return false;
		}
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return socket != null ? true : false;
	}
	private void setHost(String strHost) {
		this.strHost = strHost;
	}
	private void setPort(int iPort) {
		this.iPort = iPort;
	}
	private Object handleInput(){
		Object object = null;
       	try {
			object = in.readObject();
		} 
       	catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
       	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return object;
	}
	private void sendOutput(Object object){
		if(object != null){
			try {
				out.writeObject(object);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
