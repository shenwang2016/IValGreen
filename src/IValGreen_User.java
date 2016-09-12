import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ylh96
 *
 */
public class IValGreen_User {
    double trace_contribution = 0;
    HashMap<String, Integer> green_record = new HashMap<String, Integer>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GreenLibrary gl = new GreenLibrary();
	}
	
	public static boolean login_user(GreenLibrary gl) {
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		GreenAccount ga = gl.get_usr_info(username);
		if (ga == null) {
			System.out.println("Account not exist");
			System.out.println("Would you like to create a new account?");
			System.out.print("Enter \"yes\" or \"no\": ");
			String answer = scan.nextLine();
			answer = answer.toLowerCase();
			if (answer.charAt(0) == 'y') {
				create_account(gl);
				scan.close();
				return true;
			} else {
				scan.close();
				return false;
			}
		}
		ga.log_in();
		scan.close();
		return true;
	}
	
	public static boolean create_account(GreenLibrary gl) {
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		String password = scan.nextLine();
		GreenAccount ga = new GreenAccount(username, password, true);
		ga.setSecurityQuestions();
		gl.add_account(ga);
		scan.close();
		return true;
	}
	
	public static boolean check_status() { 
		return true;
	}
	
	public static void add_contribution(String item_name, int num) {
		
	}
	
	public static int calculate_contribution() {
		return 0;
	}

}

