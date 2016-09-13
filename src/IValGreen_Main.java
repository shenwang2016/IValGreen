import java.util.Scanner;

/**
 * 
 */

/**
 * @author
 * Shen Wang, Yilun Hua
 *
 */
public class IValGreen_Main {
	
	static GreenLibrary gl = new GreenLibrary();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		// get path of award_system file
		System.out.println("Enter Award System:");
		String file_am = scan.nextLine();
		// get path of item_list file
		System.out.println("Enter Item List:");
		String file_lm = scan.nextLine();
		// get path of item_index file
		System.out.println("Enter Item Indexer:");
		String file_im = scan.nextLine();
		// set awards map
		gl.set_awards(file_am);
		// set item_list map
		gl.set_category_to_weight(file_lm);
		// set item_index map
		gl.set_item_to_category(file_im);
		scan.close();
	}
	
	public static GreenLibrary getGL() {
		return gl;
	}

}
