import java.util.HashMap;

/**
 * 
 */

/**
 * @author Shen Wang, Yilun Hua
 *
 */
public class IValGreen_Admin {

	static GreenLibrary gl = getGL();
	
	
	public static void main(String[] args, String accountname, GreenAccount ga,
			HashMap<String, String> am, HashMap<String, String> lm,
			HashMap<String, String> im) {
		getMaps(am, lm, im);
		ga = getGA(accountname);

	}
	
	// add green account into green library
	public static void addAccount(GreenAccount ga) {
		gl.add_account(ga);
	}
	
	// get green account with respect to account name
	public static GreenAccount getGA(String accountname) {
		return gl.get_usr_info(accountname);
	}
	
	// set am: award_system
	// set lm: item_list
	// set im: item_indexer
	public static void getMaps(HashMap<String, String> am, HashMap<String, String> lm,
			HashMap<String, String> im) {
		am = gl.getAwards();
		lm = gl.getList();
		im = gl.getIndexer();
	}
	
	// get award_system map
	public static HashMap<String, String> getAwardMap() {
		return gl.getAwards();
	}
	
	// get item_list map
	public static HashMap<String, String> getItemList() {
		return gl.getList();
	}
	
	// get item_indexer map
	public static HashMap<String, String> getItemIndexer(GreenLibrary gl) {
		return gl.getIndexer();
	}
	
	// get green library
	private static GreenLibrary getGL() {
		return gl;
	}

}
