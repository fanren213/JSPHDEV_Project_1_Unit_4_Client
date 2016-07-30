/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Oct. 07. 2015
 * 
 */
package client;

import java.net.*;

import constants.ClientConstants;

public class ClientDriver {

	public static void main(String[] args) {
		String strLocalHost = "";
		try{
			strLocalHost = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e){
			System.err.println ("Unable to find local host");
		}
		DefaultSocketClient d = new DefaultSocketClient(strLocalHost, ClientConstants.iDAYTIME_PORT);
		d.start();

	}

}
