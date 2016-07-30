/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 29. 2015
 * Class EditOptions supports multi-threading with synchronized methods. In this unit, the synchronized methods are
 * designed here.
 */
package scale;
import java.util.LinkedHashMap;
import adapter.BuildAuto;
import model.Automotive;
public class EditOptions extends Thread {
	private String model;
	private String optionSetName;
	private String newOptionSetName;
	private String optionName;
	private String newOptionName;
	private Double price;
	private BuildAuto ba; // the instance of BuildAuto
	private LinkedHashMap<String,Automotive> autos;
	private int operation = 0; // pass the operation to be operated
	/* Constructs the object for set name editing. */
	public EditOptions(String model, String setName, String newSetName, BuildAuto ba) {
		this.model = model;
		this.optionSetName = setName;
		this.newOptionSetName = newSetName;
		operation = 1;
		this.ba = ba;
		autos = ba.getAutos();
	}
	/* Constructs the object for option name editing. */
	public EditOptions(String model, String setName, String optionName,String newOptionName, BuildAuto ba) {
		this.model = model;
		this.optionSetName = setName;
		this.optionName = optionName;
		this.newOptionName = newOptionName;
		operation = 2;
		this.ba = ba;
		autos = ba.getAutos();
	}
	/* Constructs the object for option price editing. */
	public EditOptions(String model, String setName, String optionName, double price, BuildAuto ba) {
		this.model = model;
		this.optionSetName = setName;
		this.optionName = optionName;
		this.price = price;
		operation = 3;
		this.ba = ba;
		autos = ba.getAutos();
	}
	@Override
	public void run() {
		synchronized(ba){
			// edit set name
			if(operation == 1) {
				ba.updateOptionSetName(model, optionSetName, newOptionSetName);
				System.out.println("Option set " + optionSetName + " updated to " + newOptionSetName);
			}
			// edit option name
			else if(operation == 2) {
				ba.updateOptionName(model, optionSetName, optionName, newOptionName);
				System.out.println("Option " + optionName + " of " + optionSetName + " updated to " + newOptionName);
			}
			// edit option price
			else if(operation == 3) {
				ba.updateOptionPrice(model, optionSetName, optionName, price);
				System.out.println("The price of option " + optionName + " of " + optionSetName + " updated to " 
								   + Double.toString(price));
			}
		}
	}
}