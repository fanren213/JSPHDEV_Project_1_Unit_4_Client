/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 22. 2015
 * The interface of creating automobile.
 */
package adapter;
public interface CreateAuto {
	public void buildAuto(String fileName, String fileType);
	public void printAuto(String modelName);
}
