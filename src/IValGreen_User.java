import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Shen Wang, Yilun Hua
 *
 */
public class IValGreen_User {
	// record total recycle contribution
    static int trace_contribution = 0;
    // map to record item-> num
    static HashMap<String, Integer> green_record = new HashMap<String, Integer>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GreenAccount user_ga = null;
		HashMap<String, String> am = null;
		HashMap<String, String> lm = null;
		HashMap<String, String> im = null;
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		IValGreen_Admin.main(args, username, user_ga, am, lm, im);
		
		
		scan.close();
	}
	
	// check whether user name exists
	// if not, ask user whether he is willing to create one
	// if answer yes, create an account for user
	// otherwise, do nothing
	public static GreenAccount login_user(GreenAccount ga) {
		// if account not exist
		if (ga == null) {
			System.out.println("Account not exist");
			System.out.println("Would you like to create a new account?");
			System.out.print("Enter \"yes\" or \"no\": ");
			Scanner scan = new Scanner(System.in);
			String answer = scan.nextLine();
			// all account name will be set into lower case
			answer = answer.toLowerCase();
			// if user answer yes, create an account for him
			if (answer.charAt(0) == 'y') {
				ga = create_account();
				scan.close();
				return ga;
			} else { // user not willing to create account
				scan.close();
				return null;
			}
		}
		// set login status
		ga.log_in();
		return ga;
	}
	
	// create green account for user
	public static GreenAccount create_account() {
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		String password = scan.nextLine();
		GreenAccount ga = new GreenAccount(username, password, true);
		// set security questions
		ga.setSecurityQuestions();
		// add user account into green library
		IValGreen_Admin.addAccount(ga);
		scan.close();
		return ga;
	}
	
	// check whether user is logged in or not
	public static boolean check_status() {
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		// try to pull green account from user_info
		GreenAccount ga = IValGreen_Admin.getGA(username);
		scan.close();
		if (ga == null) {
			return false;
		}
		return ga.verifyID();
	}
	
	public static boolean check_awards(GreenAccount ga) {
		ga.getAwards(IValGreen_Admin.getAwardMap());
		return true;
	}
	
	// map item -> num
	// num should be > 0
	public static void add_contribution(String item_name, int num) {
		if(green_record.containsKey(item_name)) {
			int temp_num = green_record.get(item_name);
			temp_num += num;
			green_record.put(item_name, temp_num);
		} else {
			green_record.put(item_name, num);
		}
		trace_contribution += num;
		
	}
	
	// return total number of contribution
	public static int calculate_contribution() {
		return trace_contribution;
	}

}

