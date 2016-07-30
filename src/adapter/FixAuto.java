/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 22. 2015
 * The interfaces for fixing exceptions.
 */
package adapter;
import model.Automotive;
public interface FixAuto {
	public void fix(int errorNo, Automotive auto);
}
