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
	
	/**
	 * @param args
	 */
	public static void main(String[] args, String accountname, GreenAccount ga,
			HashMap<String, String> am, HashMap<String, String> lm,
			HashMap<String, String> im) {
		// TODO Auto-generated method stub
		getMaps(am, lm, im);
		ga = getGA(accountname);

	}
	
	public static void addAccount(GreenAccount ga) {
		gl.add_account(ga);
	}
	
	public static GreenAccount getGA(String accountname) {
		return gl.get_usr_info(accountname);
	}
	
	public static void getMaps(HashMap<String, String> am, HashMap<String, String> lm,
			HashMap<String, String> im) {
		am = gl.getAwards();
		lm = gl.getList();
		im = gl.getIndexer();
	}
	
	public static HashMap<String, String> getAwardMap() {
		return gl.getAwards();
	}
	
	public static HashMap<String, String> getItemList() {
		return gl.getList();
	}
	
	public static HashMap<String, String> getItemIndexer(GreenLibrary gl) {
		return gl.getIndexer();
	}
	
	private static GreenLibrary getGL() {
		return null;
	}

}
