/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 23. 2015
 * Class OptionSet contains the data of a feature of an automobile.
 */
package model;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import exception.AutoException;
public class OptionSet implements Serializable{
	//private Option[] options; // the array of options
	private ArrayList<Option> options; // an ArrayList is used to replace the array
	private Option choice;
	private String name; // the name of feature/option set
	protected OptionSet(){
		this.name = new String();
		this.options = new ArrayList<Option>();
		this.choice = new Option();
	}
	/* This constructor will construct an option set with an ArrayList of option 
	 * of which length is set according to the input. */
	protected OptionSet(String name, int optionsSize){
		this.name = name;
		this.options = new ArrayList<Option>(optionsSize);
		for(Option option : options){
			option = new Option(); // initializes the elements
		}
		this.choice = new Option();
	}
	protected OptionSet(String name){
		this.name = name;
		this.options = new ArrayList<Option>();
		for(Option option : options){
			option = new Option(); // initializes the elements
		}
		this.choice = new Option();
	}
	/* This method returns the name. */
	protected String getName(){
		return name;
	}
	/* This method returns the options ArrayList. */
	protected ArrayList<Option> getOptions(){
		return options;
	}
	/* This method returns an option. */
	protected Option getOption(int index){
		return options.get(index);
	}
	protected Option getChoice(){
		return choice;
	}
	/* This method sets the name. */
	protected void setName(String name){
		this.name = name;
	}
	/* This method sets the ArrayList of options. */
	protected void setOptions(ArrayList<Option> options){
		this.options = options;
	}
	/* This method sets an option. */
	protected void setOption(Option option, int index){
		this.options.set(index, option);
	}
	protected void setChoice(int index){
		this.choice = options.get(index);
	}
	/* This method the index of the option with given name. If not found, 
	 * return -1. */
	protected int findOption(String optionName){
		for(int i = 0; i < options.size(); i++){
			if(options.get(i).getName().equals(optionName))
				return i;
		}
		return -1;
	}
	/* This method inserts an option to this option set. */
	protected void insertOption(String optionName, double optionPrice) throws AutoException{
		if(findOption(optionName) >= 0){
			throw new AutoException(6);
		}
		options.add(new Option(optionName, optionPrice));
	}
	/* This method updates the name of an option. */
	protected void updateOptionName(String optionName, String oldOptionName) throws AutoException{
		int index = findOption(oldOptionName);
		if(index < 0)
			throw new AutoException(3);
		else
			options.get(index).setName(optionName);
	}
	/* This method updates the price of an option. */
	protected void updateOptionPrice(String optionName, double optionPrice) throws AutoException{
		int index = findOption(optionName);
		if(index < 0)
			throw new AutoException(3);
		else
			options.get(index).setPrice(optionPrice);
	}
	/* This method deletes the option with given name. */
	protected void deleteOption(String optionName) throws AutoException{
		int index = findOption(optionName);
		if(index < 0){
			throw new AutoException(3);
		}
		else{
			options.remove(index); // erase the name
		}
	}
	/* This method parses the option set and returns String. */
	protected String optionSetToString(){
		StringBuffer optionSetSB = new StringBuffer();
		DecimalFormat priceFormat = new DecimalFormat("####0.00");
		optionSetSB.append("Feature Name: " + name + "\n");
		optionSetSB.append("Options:\n");
		for(int i = 0; i < options.size(); i++){
			optionSetSB.append("(" + Integer.toString(i + 1) + ").\t" 
							   + options.get(i).getName() 
							   + "\n\tPrices: "
							   + priceFormat.format(options.get(i).getPrice())
							   + "\n");
		}
		return optionSetSB.toString();
	}
	/* The inner class - Option - contains the properties of an option of the 
	 * automobile. */
	public class Option implements Serializable{
		private String name; // the name of the option
		private double price; // the price of the option
		/* Constructs an empty option. */
		protected Option(){
			name = new String();
			price = 0;
		}
		/* Constructs an option with given name and price. */
		protected Option(String name, double price){
			this.name = name;
			this.price = price;
		}
		/* This method returns name. */
		protected String getName() {
			return name;
		}
		/* This method sets name. */
		protected void setName(String name) {
			this.name = name;
		}
		/* This method returns price. */
		protected double getPrice() {
			return price;
		}
		/* This method sets price. */
		protected void setPrice(double price) {
			this.price = price;
		}
		/* This method parses the option to a string and return the string. */
		protected String optionToString(){
			StringBuffer optionSB = new StringBuffer();
			DecimalFormat priceFormat = new DecimalFormat("####0.00");
			optionSB.append("Option Name:\t" + name + "\n");
			optionSB.append("Option Price:\t" + priceFormat.format(price) + "\n");
			return optionSB.toString();
		}
	}
}
