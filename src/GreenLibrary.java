import java.util.HashMap;

/**
 * Note: the file that the reward system will be stored in should
 * have the format of:
 * award_number : award_name
 * 
 * i.e.
 * 1 : little seed
 * 2 : sprout
 * etc.
 * 
 * Note: the item_list should have the following format:
 * item_index : item_weight
 * 
 * i.e.
 * 1 : 1.0
 * 2 : 2.5
 * etc.
 * 
 * Note: the item_indexer should have the following format:
 * item_name : item_index
 * 
 * i.e.
 * plastic-bottle : 1
 * bottle : 1
 * can : 2
 * etc.
 * 
 * there should be no space between two words, use - instead
 */

/**
 * @author Shen Wang, Yilun Hua
 * it's the center of the project
 * it records the users infomation as well as
 * some of the IValGreen Library infomation such
 * as awards list and calculation of each item's
 * weight etc.
 *
 */
public class GreenLibrary {
	private HashMap<String, GreenAccount> user_info
	= new HashMap<String, GreenAccount>();
	// may be upgraded to some fancier representation such as
	// a hashmap of int and another map that has a string mapped
	// to a visual representation, such as a growing tree of each stage
	private HashMap<Integer, String> award_system
	= new HashMap<Integer, String>();
	private HashMap<Integer, Double> item_list
	= new HashMap<Integer, Double>();
	private HashMap<String, Integer> item_indexer
	= new HashMap<String, Integer>();
	
	public GreenLibrary() {
		
	}
	
	// this method reads in a file that stores any kind of file described above
	// it puts the first string (before ":") as the key and the second as the value
	// into the hashmap (both key and value will be type string) and return
	// the hashmap to the caller. And it's up to the caller to decide specific action
	// for the hashmap returned
	private HashMap<String, String> readFiles() {
		HashMap<String, String> map = new HashMap<String, String>();
		return map;
	}

}
