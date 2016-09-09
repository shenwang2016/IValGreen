import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * ALL FILES READ IN SHOULD BE A .TXT FILE!!!
 * 
 * Note: the file that the reward system will be stored in should
 * have the format of:
 * award_number : award_name
 * 
 * i.e.
 * 1:little seed
 * 2:sprout
 * etc.
 * 
 * Note: the item_list should have the following format:
 * item_index : item_weight
 * 
 * i.e.
 * 1:1.0
 * 2:2.5
 * etc.
 * 
 * Note: the item_indexer should have the following format:
 * item_name : item_index
 * 
 * i.e.
 * plastic-bottle:1
 * bottle:1
 * can:2
 * etc.
 * 
 * there should be no space between two words, use - instead
 */

/**
 * @author Shen Wang, Yilun Hua
 * it's the center of the project
 * it records the users information as well as
 * some of the IValGreen Library information such
 * as awards list and calculation of each item's
 * weight etc.
 *
 */

// ACHTUNG!!! General frame of the project, many parts need to be
// replaced! all suggestions welcomed. It's just a skeleton that gives
// a general idea of what this thing needs to have
public class GreenLibrary {
	
	// private String authorize_info;
	
	private HashMap<String, GreenAccount> user_info
	= new HashMap<String, GreenAccount>();
	// may be upgraded to some fancier representation such as
	// a hashmap of int and another map that has a string mapped
	// to a visual representation, such as a growing tree of each stage
	private HashMap<String, String> award_system
	= new HashMap<String, String>();
	private HashMap<String, String> item_list
	= new HashMap<String, String>();
	private HashMap<String, String> item_indexer
	= new HashMap<String, String>();
	
	public GreenLibrary() {
		/*authorize_info = "";
		initialize_authority(authorize_info);*/
	}
	/*
	// for security reason, exactly how to implement and check still in
	// progress
	private void initialize_authority(String a_i) {
		
	}
	
	private boolean check_auth() {
		return true;
	}
	*/
	public void set_awards(String awards_file) {
		/*if (!check_auth()) {
			return;
		}*/
		try {
			this.award_system = readFiles(awards_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void set_item_to_category(String item_to_category) {
		/*if (!check_auth()) {
			return;
		}*/
		try {
			this.item_indexer = readFiles(item_to_category);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void set_category_to_weight(String category_to_weight) {
		/*if (!check_auth()) {
			return;
		}*/
		try {
			this.item_list = readFiles(category_to_weight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// need to parse each type into string accordingly
	public void add_item(String item_name, int category, double weight) {
		/*if (!check_auth()) {
			return;
		}*/
	}
	
	public void add_awards(String award_name, int award_index) {
		/*if (!check_auth()) {
			return;
		}*/
	}
	
	// need to handle things more delicately
	public void add_account(GreenAccount ga) {
		/*if (!check_auth()) {
			return;
		}*/
		if (ga == null) {
			System.out.println("ERROR: account not exist");
			return;
		}
		this.user_info.put(ga.getAccountName(), ga);
	}
	
	// this method reads in a file that stores any kind of file described above
	// it puts the first string (before ":") as the key and the second as the value
	// into the hashmap (both key and value will be type string) and return
	// the hashmap to the caller. And it's up to the caller to decide specific action
	// for the hashmap returned
	private HashMap<String, String> readFiles(String file_path)
			throws FileNotFoundException {
		HashMap<String, String> map = new HashMap<String, String>();
		File file = new File(file_path);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				String[] parts = s.split(":");
				map.put(parts[0], parts[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path, reenter a file path");
			return null;
		}
		Scanner scan = new Scanner(file);
		return map;
	}

}
