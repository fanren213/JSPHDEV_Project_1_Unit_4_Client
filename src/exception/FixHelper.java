/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 22. 2015
 */
package exception;
import java.util.Scanner;
import model.Automotive;
import util.FileIO;
public class FixHelper {
	public void fix1(int errorNo, Automotive auto){
	}
	public void fix2(int errorNo, Automotive auto){
	}
	public void fix3(int errorNo, Automotive auto){
	}
	public void fix4(int errorNo, Automotive auto) throws AutoException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input correct file name: ");
		String fileName = sc.nextLine();
		FileIO fileIO = new FileIO();
		fileIO.readFile(fileName, auto);
	}
	public void fix5(int errorNo, Automotive auto){
		System.exit(errorNo);
	}
	public void fix6(int errorNo, Automotive auto){
		System.exit(errorNo);
	}
	public void fix7(int errorNo, Automotive auto){
		System.exit(errorNo);
	}
	public void fix8(int errorNo, Automotive auto){
		System.exit(errorNo);
	}
	public void fix9(int errorNo, Automotive auto){
		System.exit(errorNo);
	}
	public void fix10(int errorNo, Automotive auto){
		System.exit(errorNo);
	}
}
