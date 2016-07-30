/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 23. 2015
 * Class Automotive contains the data, such as name, base price and other 
 * features, of an automobile.
 */
package model;
import exception.*;
import model.OptionSet.Option;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
public class Automotive implements Serializable{
	private static final long serialVersionUID = 1L;
	private String model; // the name of the model
	private double basePrice; // the base price of the model without changing features of the model
	private ArrayList<OptionSet> optionSets; // the ArrayList takes the place of the former array
	private String make; // the name of the make
		public Automotive(){
		this.model = new String(); // model name
		this.make = new String(); // make name
		this.optionSets = new ArrayList<OptionSet>(); // option sets
	}
	/* This constructor will construct an Automotive with an ArrayList of option 
	 * set of which length is set according to the input. */
	public Automotive(String name, double basePrice, int optionSetsSize){
		this.model = name; // model name
		this.make = new String();
		this.basePrice = basePrice; // model base price
		this.optionSets = new ArrayList<OptionSet>(optionSetsSize);
		for(OptionSet optionSet : optionSets){
			optionSet = new OptionSet(); // initializes the elements
		}
	}
	/* This method returns model name. */
	public String getModel(){
		return model;
	}
	/* This method sets model name. */
	public synchronized void setModel(String name){
		this.model = name;
	}
	public synchronized String getMake(){
		return make;
	}
	public synchronized void setMake(String make){
		this.make = make;
	}
	/* This method returns model base price. */
	public synchronized double getBasePrice(){
		return basePrice;
	}
	/* This method sets model base price. */
	public synchronized void setBasePrice(double basePrice){
		this.basePrice = basePrice;
	}
	/* This method returns an ArrayList of option sets. */
	public synchronized ArrayList<OptionSet> getOptionSets(){
		return optionSets;
	}
	/* This method returns the option set with the input index. */
	public synchronized OptionSet getOptionSet(int index){
		return optionSets.get(index);
	}
	/* This method returns an ArrayList of options in the option set with the 
	 * input index. */
	public synchronized ArrayList<Option> getOptions(int index){
		return optionSets.get(index).getOptions();
	}
	/* This method returns the option with the input index. */
	public synchronized Option getOption(int optionSetIndex, int optionIndex){
		return optionSets.get(optionSetIndex).getOption(optionIndex);
	}
	/* This method returns the name of option set with the input index. */
	public synchronized String getOptionSetName(int optionSetIndex){
		return optionSets.get(optionSetIndex).getName();
	}
	/* This method returns the name of option with the input index. */
	public synchronized String getOptionName(int optionSetIndex, int optionIndex){
		return optionSets.get(optionSetIndex).getOption(optionIndex).getName();
	}
	/* This method returns the price of option with the input index. */
	public synchronized double getOptionPrice(int optionSetIndex, int optionIndex){
		return optionSets.get(optionSetIndex).getOption(optionIndex).getPrice();
	}
	/* This method sets an ArrayList of option sets. */
	public synchronized void setOptionSets(ArrayList<OptionSet> optionSets){
		this.optionSets = optionSets;
	}
	/* This method sets the option set with the input index. */
	public synchronized void setOptionSet(OptionSet optionSet, int index){
		this.optionSets.set(index, optionSet);
	}
	/* This method sets an ArrayList of options in the option set with the 
	 * input index. */
	public synchronized void setOptions(ArrayList<Option> options, int optionSetIndex){
		OptionSet optionSet = this.optionSets.get(optionSetIndex);
		optionSet.setOptions(options);
		this.optionSets.set(optionSetIndex, optionSet);
	}
	/* This method sets the option with the input index. */
	public synchronized void setOption(Option option, int optionSetIndex, int optionIndex){
		OptionSet optionSet = this.optionSets.get(optionSetIndex);
		optionSet.setOption(option, optionIndex);
		this.optionSets.set(optionSetIndex, optionSet);
	}
	/* This method returns the chosen option name of given set. */
	public synchronized String getOptionChoice(String setName){
		int setIndex = this.findOptionSet(setName);
		if(optionSets.get(setIndex).getChoice() != null)
			return optionSets.get(setIndex).getChoice().getName();
		else
			return null;
	}
	/* This method sets the chosen option name of given set. */
	public synchronized void setOptionChoice(String setName, String optionName){
		int toChooseIndex = this.findOptionSet(setName);
		int chosenIndex = this.findOption(optionName, toChooseIndex);
		OptionSet optionSet = optionSets.get(toChooseIndex);
		optionSet.setChoice(chosenIndex);
		optionSets.set(toChooseIndex, optionSet);
		
	}
	/* This method returns the chosen option price of given set. */
	public synchronized double getOptionChoicePrice(String setName){
		int setIndex = this.findOptionSet(setName);
		return optionSets.get(setIndex).getChoice().getPrice();
	}
	/* This method returns the total price. */
	public synchronized double getTotalPrice(){
		double sum = basePrice;
		for(OptionSet optionSet : optionSets){
			sum = sum + optionSet.getChoice().getPrice();
		}
		return sum;
		
	}
	/* This method finds the option set with given name and returns the index
	 * of that option set. If such option set not found, returns -1. */
	public synchronized int findOptionSet(String optionSetName){
		for(int i = 0; i < optionSets.size(); i++){
			if(optionSets.get(i).getName().equals(optionSetName))
				return i;
		}
		return -1;
	}
	/* This method finds the option with given name which belongs to an option 
	 * set with given index and returns that option. */
	public synchronized int findOption(String optionName, int optionSetIndex){
		return optionSets.get(optionSetIndex).findOption(optionName);
	}
	/* This method finds the option with given name which belongs to an option 
	 * set with given name and returns that option. */
	public synchronized int findOption(String optionName, String optionSetName){
		return optionSets.get(findOptionSet(optionSetName)).findOption(optionName);
	}
	/* This method inserts an option set with certain size to the Automotive. */
	public synchronized void insertOptionSet(String optionSetName, int size) throws AutoException{
		if(findOptionSet(optionSetName) >= 0){
			// no option sets with same name allowed
			// if try to insert an option set with existed name, will throw an exception
			throw new AutoException(5); 
		}
		else{
			optionSets.add(new OptionSet(optionSetName, size));
		}
	}
	/* This method inserts an option set with default size to the Automotive. */
	public synchronized void insertOptionSet(String optionSetName) throws AutoException{
		if(findOptionSet(optionSetName) >= 0){
			throw new AutoException(5);
		}
		else{
			optionSets.add(new OptionSet(optionSetName));
		}
	}	
	/* This method inserts an option to an option set. */
	public synchronized void insertOption(String optionName, double optionPrice, String optionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(optionSetName);
		if(optionSetIndex < 0){
			throw new AutoException(2);
		}
		else{
			OptionSet optionSet = optionSets.get(optionSetIndex);
			optionSet.insertOption(optionName, optionPrice);
			optionSets.set(optionSetIndex, optionSet);
		}
	}
	/* This method deletes the option set with given name. */
	public synchronized void deleteOptionSet(String optionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(optionSetName);
		if(optionSetIndex < 0){
			throw new AutoException(2);
		}
		else{
			optionSets.remove(optionSetIndex); 
		}
	}
	/* This method deletes the option with given name in the given option set. */
	public synchronized void deleteOption(String optionName, String optionSetName) throws AutoException{
		int optionSetIndex = 0;
		optionSetIndex = findOptionSet(optionSetName);
		if(optionSetIndex < 0){
			throw new AutoException(2);
		}
		else{
			OptionSet optionSet = optionSets.get(optionSetIndex);
			optionSet.deleteOption(optionName);
			optionSets.set(optionSetIndex, optionSet);
		}
	}
	/* This method updates the name of an option set. */
	public synchronized void updateOptionSet(String optionSetName, String oldOptionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(oldOptionSetName);
		if(optionSetIndex < 0)
			throw new AutoException(2);
		else{
			OptionSet optionSet = optionSets.get(optionSetIndex);
			optionSet.setName(optionSetName);
			optionSets.set(optionSetIndex, optionSet);
		}
	}
	/* This method updates the name of an option. */
	public synchronized void updateOptionName(String optionName, String oldOptionName, String optionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(optionSetName);
		if(optionSetIndex < 0)
			throw new AutoException(2);
		else{
			OptionSet optionSet = optionSets.get(optionSetIndex);
			optionSet.updateOptionName(optionName, oldOptionName);
			optionSets.set(optionSetIndex, optionSet);
		}
	}
	/* This method updates the price of an option. */
	public synchronized void updateOptionPrice(String optionName, double optionPrice, String optionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(optionSetName);
		if(optionSetIndex < 0)
			throw new AutoException(2);
		else{
			OptionSet optionSet = optionSets.get(optionSetIndex);
			optionSet.updateOptionPrice(optionName, optionPrice);
			optionSets.set(optionSetIndex, optionSet);
		}
	}
	/* This method prints the information of this Automotive. */
	public void printAutomotive(){
		System.out.print(this.toString());
	}
	/* This method prints the choices. */
	public void printChoices(){
		System.out.print(this.choicesToString());
	}
	/* This method prints the total price. */
	public void printTotalPrice(){
		StringBuffer automotive = new StringBuffer();
		DecimalFormat price = new DecimalFormat("####0.00"); // the price will be shown in this format
		automotive.append("Auto name:\t" + model +"\n"); // the name of the model
		automotive.append("Base price:\t" + price.format(basePrice) + "\n"); // the base price of the model
		automotive.append("Total price:\t" + price.format(this.getTotalPrice()) + "\n"); 
		System.out.print(automotive.toString());
	}
	/* This method prints the information of an option set with given name. */
	public void printOptionSet(String optionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(optionSetName);
		if(optionSetIndex < 0)
			throw new AutoException(2);
		else
			System.out.print(this.getOptionSet(optionSetIndex).optionSetToString());
	}
	/* This method prints the information of an option with given option name
	 * and option set name. */
	public void printOption(String optionName, String optionSetName) throws AutoException{
		int optionSetIndex = findOptionSet(optionSetName);
		int optionIndex = findOption(optionName, optionSetIndex);
		if(optionSetIndex < 0)
			throw new AutoException(2);
		else if(optionIndex < 0)
			throw new AutoException(3);
		else{
			StringBuffer optionSetNameSB = new StringBuffer();
			optionSetNameSB.append("Feature Name:\t" + optionSetName + "\n");
			optionSetNameSB.append(this.getOption(optionSetIndex, optionIndex).optionToString());
			System.out.print(optionSetNameSB.toString());
		}
	}
	public String choicesToString(){
		StringBuffer automotive = new StringBuffer();
		DecimalFormat price = new DecimalFormat("####0.00"); // the price will be shown in this format
		automotive.append("Auto name:\t" + model +"\n"); // the name of the model
		automotive.append("Base price:\t" + price.format(basePrice) + "\n"); // the base price of the model
		automotive.append("Features:\n");
		for(int i = 0; i < optionSets.size(); i++){
			automotive.append(Integer.toString(i + 1) + ".\t" + optionSets.get(i).getName() 
							  + "\n"); // the name of option sets
			automotive.append("\tChosen option:");
			automotive.append("\t" 
							  + optionSets.get(i).getChoice().getName() // the name of the option
							  + "\n\tPrices:\t\t"
							  + price.format(optionSets.get(i).getChoice().getPrice()) // the price of the option
							  + "\n");
		}
		return automotive.toString();
	}
	/* This method parses the data of Automotive to String. */
	public String toString(){
		StringBuffer automotive = new StringBuffer();
		DecimalFormat price = new DecimalFormat("####0.00"); // the price will be shown in this format
		automotive.append("Auto name:\t" + model +"\n"); // the name of the model
		automotive.append("Base price:\t" + price.format(basePrice) + "\n"); // the base price of the model
		automotive.append("Features:\n");
		for(int i = 0; i < optionSets.size(); i++){
			automotive.append(Integer.toString(i + 1) + ".\t" + optionSets.get(i).getName() 
							  + "\n"); // the name of option sets
			automotive.append("\tOptions:\n");
			for(int j = 0; j < optionSets.get(i).getOptions().size(); j++){
				automotive.append("\t(" + Integer.toString(j + 1) + ").\t" 
								  + optionSets.get(i).getOption(j).getName() // the name of the option
								  + "\n\t\tPrices: "
								  + price.format(optionSets.get(i).getOption(j).getPrice()) // the price of the option
								  + "\n");
			}
		}
		return automotive.toString();
	}
}
