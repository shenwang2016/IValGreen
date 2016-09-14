import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
	public static void main(String[] args) throws FileNotFoundException {
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
		System.out.println("Load or not?: \"yes\" or \"no\"");
		String res = scan.nextLine();
		// if user answer no, then do nothing
		if (res.toLowerCase().charAt(0) != 'n') {
			System.out.println("File path?");
			String path = scan.nextLine();
			File f = new File(path);
			try {
				Scanner scanfile = new Scanner(f);
				while (scanfile.hasNextLine()) {
					String s = scanfile.nextLine();
					// split with respect to file style
					String[] parts = s.split(":");
					if (parts.length == 6) {
						String[] awards = parts[3].split("|");
						boolean[] aw = new boolean[awards.length];
						for (int i = 0; i < awards.length; i++) {
							if (awards[i] == "1") {
								aw[i] = true;
							} else {
								aw[i] = false;
							}
						}
						HashMap<String, String> hm = new HashMap<String, String>();
						String[] q = parts[4].split("|");
						String[] a = parts[5].split("|");
						for (int i = 0; i < q.length; i++) {
							hm.put(q[i], a[i]);
						}
						GreenAccount ga = new GreenAccount(parts[0], parts[1], aw, hm);
						ga.increment_contribution(Double.parseDouble(parts[2]));
						gl.add_account(ga);
					}
				}
				scanfile.close();
			} catch (FileNotFoundException e) {
				System.out.println("Invalid file path, reenter a file path");
			}
		}
		outputData();
		scan.close();
	}
	
	public static void outputData() {
		HashMap<String, GreenAccount> mp = gl.getAllUser();
		PrintWriter writer;
		try {
			writer = new PrintWriter("data.txt", "UTF-8");
			for (String s : mp.keySet()) {
				GreenAccount ga = mp.get(s);
				String an = ga.getAccountName();
				String pw = ga.getPW();
				boolean[] aw = ga.getA();
				HashMap<String, String> mp2 = ga.getMP();
				// ga.getAll(an, pw, aw, mp2);
				double sum = ga.get_sum();
				writer.print(an + ":" + pw + ":" + Double.toString(sum) + ":");
				String aws = "";
				String sqq = "";
				String sqa = "";
				if (aw[0]) {
					aws += "1";
				} else {
					aws += "0";
				}
				for (int i = 1; i < aw.length; i++) {
					if (aw[i]) {
						aws += "|1";
					} else {
						aws += "|0";
					}
				}
				for (String a : mp2.keySet()) {
					String b = mp2.get(a);
					sqq += a + "|";
					sqa += b + "|";
				}
				writer.println(aws + ":" + sqq.substring(0, sqq.length())
				+ ":" + sqa.substring(0, sqa.length()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static GreenLibrary getGL() {
		return gl;
	}

}
