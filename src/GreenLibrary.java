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

// class manages to load award file, item file, and item index file
public class GreenLibrary {
	// map user name -> corresponding user account
	private HashMap<String, GreenAccount> user_info = new HashMap<String, GreenAccount>();
	// map award number -> award name
	private HashMap<String, String> award_system = new HashMap<String, String>();
	// map item index-> item weight
	private HashMap<String, String> item_list = new HashMap<String, String>();
	// map item name -> item index
	private HashMap<String, String> item_indexer = new HashMap<String, String>();
	
	// method to load file recording award system
	public void set_awards(String awards_file) {
		try {
			this.award_system = readFiles(awards_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			String name = reenterName();
			if (name == null) {
				return;
			}
			set_awards(name);
		}
	}
	
	// method to load item-index file
	public void set_item_to_category(String item_to_category) {
		try {
			this.item_indexer = readFiles(item_to_category);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			String name = reenterName();
			if (name == null) {
				return;
			}
			set_item_to_category(name);
		}
	}
	
	// method to load item index-weight file
	public void set_category_to_weight(String category_to_weight) {
		try {
			this.item_list = readFiles(category_to_weight);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			String name = reenterName();
			if (name == null) {
				return;
			}
			set_category_to_weight(name);
		}
	}
	
	public String reenterName() {
		// ask user whether he is willing to reenter a file name
		System.out.println("Would you like to enter another file?");
		System.out.print("\"yes\" to reenter, \"no\" to quit");
		Scanner scan = new Scanner(System.in);
		String res = scan.nextLine();
		if (res.toLowerCase().charAt(0) == 'n') {
			scan.close();
			return null;
		}
		// prompt user to enter the full path of a file
		System.out.println("Please enter the name of file");
		res = scan.nextLine();
		scan.close();
		return res;
	}
	
	// need to parse each type into string accordingly
	public void add_item(String item_name, int category, double weight) {
		String cat = Integer.toString(category);
		this.item_indexer.put(item_name, cat);
		this.item_list.put(cat, Double.toString(weight));
	}
	
	// add award name -> award index into award_system
	public void add_awards(String award_name, int award_index) {
		this.award_system.put(Integer.toString(award_index), award_name);
	}
	
	// add account name -> green account into user_info
	public void add_account(GreenAccount ga) {
		// sanitary check
		if (ga == null) {
			System.out.println("ERROR: account not exist");
			return;
		}
		this.user_info.put(ga.getAccountName(), ga);
	}
	
	// get green account with corresponding account name
	public GreenAccount get_usr_info(String accountname) {
		if (!this.user_info.containsKey(accountname)) {
			return null;
		} else {
			return this.user_info.get(accountname);
		}
	}
	
	// return award_system
	public HashMap<String, String> getAwards() {
		return this.award_system;
	}
	
	// return item_list
	public HashMap<String, String> getList() {
		return this.item_list;
	}
	
	// return item_indexer
	public HashMap<String, String> getIndexer() {
		return this.item_indexer;
	}
	
	// this method reads in a file that stores any kind of file described above
	// it puts the first string (before ":") as the key and the second as the value
	// into the hash map (both key and value will be type string) and return
	// the hash map to the caller. And it's up to the caller to decide specific action
	// for the hash map returned
	private HashMap<String, String> readFiles(String file_path) throws FileNotFoundException {
		HashMap<String, String> map = new HashMap<String, String>();
		File file = new File(file_path);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				// split with respect to file style
				String[] parts = s.split(":");
				map.put(parts[0], parts[1]);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path, reenter a file path");
			return null;
		}
		return map;
	}

}
