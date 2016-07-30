/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Oct. 07. 2015
 * 
 */
package client;

import java.util.Scanner;

import exception.AutoException;
import model.Automotive;

public class SelectCarOption {
	// The helper method for setting choices of options.
	public void makeChoice(Automotive auto) throws AutoException{
		Scanner sc = new Scanner(System.in);
		auto.printAutomotive();
		System.out.print("Please input the option set to modify:");
		String optionSetName = sc.nextLine();
		auto.printOptionSet(optionSetName);
		System.out.print("Please input the option you want:");
		String optionName = sc.nextLine();
		auto.setOptionChoice(optionSetName, optionName);
		auto.printChoices();
	}
}
