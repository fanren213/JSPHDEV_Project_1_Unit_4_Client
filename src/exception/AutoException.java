/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 22. 2015
 */
package exception;
import java.io.*;
import java.util.*;
import java.text.*;
public class AutoException extends Exception{
	private int errorNo;
	public AutoException(){
		super();
	}
	public AutoException(int errorNo){
		super(ExceptionEnum.getErrorMsg(errorNo));
		this.errorNo = errorNo;
	}
	public int getErrorNo(){
		return errorNo;
	}
	public void log(){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("exception.log", true));
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			Date date = new Date();
			String timeStamp = dateFormat.format(date);
			bw.write(timeStamp + " | Error No.: " + Integer.toString(errorNo) + " | Error Msg: "
					+ ExceptionEnum.getErrorMsg(errorNo) + "\n");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
