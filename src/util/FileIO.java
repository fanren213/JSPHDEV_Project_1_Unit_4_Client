/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 22. 2015
 * Class FileIO reads text files with information of 
 * automobiles and can serialize and deserialize 
 * Automotive.
 */
package util;
import java.io.*;
import java.util.Properties;
import exception.AutoException;
import model.*;
public class FileIO {
	/* The method reads text file and returns an Automotive */
	public Automotive readFile(String fileName, Automotive auto) throws AutoException{
		String line;
		try{
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			line = buff.readLine();
			while(line != null){
				String[] optionSet = line.split(":"); // split the name part and the options part
				if(optionSet[0].equals("Name")){
					auto.setModel(optionSet[1]); // set the name of model
				}
				else if(optionSet[0].equals("Base Price")){
					if(optionSet.length == 2)
						auto.setBasePrice(Float.parseFloat(optionSet[1])); //set the base price of model
					else
						auto.setBasePrice(0);
				}
				else{
					String[] options = optionSet[1].split(";"); //  split the options
					auto.insertOptionSet(optionSet[0], options.length); //set the optionSet name
					for(int i = 0; i < options.length; i++){
						String[] option = options[i].split(","); //split the option name and price
						auto.insertOption(option[0], Float.parseFloat(option[1]), optionSet[0]); // insert option
					}
				}
				line = buff.readLine();
			}
			buff.close();
		}
		catch(FileNotFoundException e){
			throw new AutoException(4);
		}
		catch(IOException e){ // File IO exception
			throw new AutoException(7);
		}
		catch(NumberFormatException e){ // number format exception when parsing float number
			throw new AutoException(8);
		}
		return auto;
	}
	/* This method serializes an Automotive and writes the output in a .dat file */
	public void serializeAuto(Automotive auto) throws AutoException{
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(auto.getModel() + ".dat"));
			out.writeObject(auto);
			out.close();
		}
		catch(IOException e){ // File IO exception
			throw new AutoException(7);
		}
	}
	/* The method reads a .dat file and deserializes it to get the return which is an Automotive */
	public Automotive deserializeAuto(String fileName) throws AutoException{
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			Automotive auto = (Automotive)in.readObject(); // the object has to be casted to an Automotive
			in.close();
			return auto;
		}
		// exceptions
		catch(IOException e){ 
			throw new AutoException(7);
		}
		catch(ClassNotFoundException e){
			throw new AutoException(9);
		}
	}
	public Properties readPropertiesFile(String fileName) throws AutoException{
		Properties props = new Properties();
		try{
			FileInputStream in = new FileInputStream(fileName);
			props.load(in);
		}
		catch(IOException e){
			throw new AutoException(7);
		}
		return props;
	}
	public Automotive parseProperties(Properties props) throws AutoException{
		Automotive auto = new Automotive();
		String CarMake = props.getProperty("CarMake");
		String option = new String();
		String optionName = new String();
		String optionPrice = new String();
		if(!CarMake.equals(null)){
			auto.setMake(props.getProperty("CarMake"));
			auto.setModel(props.getProperty("CarModel"));
			auto.setBasePrice(Double.parseDouble(props.getProperty("BasePrice")));
			for(int i = 1; ; i++) {
				option = props.getProperty("Option" + i);
				if(option.equals(null))
					break;
				auto.insertOptionSet(option);
				for (int j = 0; ; ++j) {
					char optionCode = (char) ('a' + j);
					optionName = props.getProperty("OptionValue" + i + optionCode);
					if (optionName == null) {
						break;
					}
					optionPrice = props.getProperty("OptionPrice" + i + optionCode);
					if (optionPrice != null) {
						auto.insertOption(optionName, Double.parseDouble(optionPrice), option);
					}
				}
			}
		}
		if(auto.getMake().equals(null)){
			return null;
		}
		return auto;
	}
}
