/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 22. 2015
 * Implements several methods allowing building, printing and updating automobiles.
 */
package adapter;
import java.util.*;
import exception.*;
import model.*;
import util.*;
public abstract class ProxyAutomobile {
	private static Automotive a1 = new Automotive();
	private static LinkedHashMap<String,Automotive> autos = new LinkedHashMap<String, Automotive>();
	public void buildAuto(String fileName, String fileType){
		try{
			FileIO fileIO = new FileIO();
			a1 = new Automotive();
			if(fileType.toLowerCase().equals("properties")){
				Properties props = fileIO.readPropertiesFile(fileName);
				a1 = fileIO.parseProperties(props);
			}
			else{
				a1 = fileIO.readFile(fileName, a1);
				if(a1.getBasePrice() == 0)
					throw new AutoException(1);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
			
		}
		finally{
			if(!a1.getMake().equals(null))
				autos.put(a1.getModel(), a1);
		}
	}
	/* this method returns the autos in a linked hash map. */
	public LinkedHashMap<String, Automotive> getAutos(){
		return autos;
	}
	/* this method prints the auto with given name in format. */
	public void printAuto(String modelName){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).printAutomotive();
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	/* this method updates the name of option set. */
	public void updateOptionSetName(String modelName, String optionSetName, String newName){
		try{
			if(!autos.containsKey(modelName)){ // if auto with given name doesn't exist
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).updateOptionSet(newName, optionSetName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	/* this method updates the name of option. */
	public void updateOptionName(String modelName, String optionSetName, String optionName, String newName){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).updateOptionName(newName, optionName, optionSetName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	/* this method updates the price of option. */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).updateOptionPrice(optionName, newPrice, optionSetName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	public void updateOptionSetName(String modelName, String optionSetName, String newName,
									LinkedHashMap<String, Automotive> autos){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).updateOptionSet(newName, optionSetName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	public void updateOptionName(String modelName, String optionSetName, String optionName, String newName,
								 LinkedHashMap<String, Automotive> autos){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).updateOptionSet(newName, optionSetName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice,
								  LinkedHashMap<String, Automotive> autos){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				autos.get(modelName).updateOptionPrice(optionName, newPrice, optionSetName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	public void addAutoFromClient(Object props){
		FileIO fileIO = new FileIO();
		try {
			Automotive auto = fileIO.parseProperties((Properties)props);
			autos.put(auto.getModel(), auto);
		} 
		catch (AutoException e) {
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
		}
	}
	public String modelsList(){
		StringBuffer modelsList = new StringBuffer();
		for(String key:autos.keySet()){
			modelsList.append(key + "/n");
		}
		return modelsList.toString();
	}
	public Object sendObjectToClient(String modelName){
		try{
			if(!autos.containsKey(modelName)){
				throw new AutoException(10);
			}
			else{
				return autos.get(modelName);
			}
		}
		catch(AutoException e){
			e.log();
			System.out.println("Error -- " + e.toString());
			fix(e.getErrorNo(), a1);
			return null;
		}
	}
	/* the helper method of fixing exceptions */
	public void fix(int errorNo, Automotive auto){
		FixHelper f1 = new FixHelper();
		switch(errorNo){
		case 1: 
			f1.fix1(errorNo, auto);
			break;
		case 2: 
			f1.fix2(errorNo, auto);
			break;
		case 3: 
			f1.fix3(errorNo, auto);
			break;
		case 4: 
			try{
				f1.fix4(errorNo, auto);
			}
			catch(AutoException e){
				e.log();
				System.out.println("Error -- " + e.toString());
				fix(e.getErrorNo(), a1);
			}
			break;
		case 5: 
			f1.fix5(errorNo, auto);
			break;
		case 6: 
			f1.fix6(errorNo, auto);
			break;
		case 7: 
			f1.fix7(errorNo, auto);
			break;
		case 8: 
			f1.fix8(errorNo, auto);
			break;
		case 9: 
			f1.fix9(errorNo, auto);
			break;
		case 10: 
			f1.fix10(errorNo, auto);
			break;
		}
	}
}
