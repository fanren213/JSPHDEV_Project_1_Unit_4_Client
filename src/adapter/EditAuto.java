/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 29. 2015
 * Interfaces for synchronized methods.
 */
package adapter;

import java.util.LinkedHashMap;
import model.Automotive;

public interface EditAuto {
	public LinkedHashMap<String, Automotive> getAutos();
	public void updateOptionSetName(String modelName, String optionSetName, String newName,
									LinkedHashMap<String, Automotive> autos);
	public void updateOptionName(String modelName, String optionSetName, String optionName, String newName,
								 LinkedHashMap<String, Automotive> autos);
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice,
								  LinkedHashMap<String, Automotive> autos);
}
