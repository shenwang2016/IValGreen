import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @authors Shen Wang, Yilun Hua
 * it's the user information build up
 * it constructs a new user as well as
 * manage the user account
 */

public class GreenAccount {
	
	// change this number to customize
	public static final int MAX_NUM = 5;
	
	private String account_name;
	// a boolean array list that marks the awards
	private ArrayList<Boolean> awards;
	// a int array list that marks the password
	private ArrayList<Integer> password;
	// a map that maps from questions to answers
	private HashMap<String, String> security_questions;
	
	// empty constructor
	public GreenAccount() { 
		
	}
	
	// constructor with only name and password
	public GreenAccount(String account_name, ArrayList<Integer> password) {
		this.account_name = account_name;
		this.password = password;
	}
	
	// a fully profiled account constructor
	public GreenAccount(String account_name,
						ArrayList<Integer> password,
						ArrayList<Boolean> awards,
						HashMap<String, String> security_questions) {
		this.account_name = account_name;
		this.password = password;
		this.awards = awards;
		this.security_questions = security_questions;
	}
	
	/*
	 * In parameters: a boolean value that confirms the user is eligible to
	 * change password (if false, return false)
	 */
	public boolean setPassWord(boolean confirm_old) {
		if (!confirm_old) {
			System.out.println("Failed to verify");
			return false;
		}
		// get the new password
		// maybe use a different input method to get the user input
		Scanner scan = new Scanner(System.in);
		System.out.println("Your new Password: (Numbers only)");
		String c = scan.next();
		int i = 0;
		ArrayList<Integer> new_password = new ArrayList<Integer>();
		while (c != "\n") {
			try {
		        new_password.add(i, Integer.parseInt(c));
		        i++;
		        c = scan.next();
		    } catch (NumberFormatException nfe) {
		        System.out.println("Not a number, enter again!");
		        c = scan.next();
		        continue;
		    };
		}
		this.password = new_password;
		System.out.println("Successfully set password");
		return true;
	}
	
	public boolean setAccountName(boolean confirm_identity) {
		if (!confirm_identity) {
			System.out.println("Failed to verify");
			return false;
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("Your new account name:");
		this.account_name = scan.nextLine();
		System.out.println("Successfully set account name");
		return true;
	}
	
	public boolean setSecurityQuestions(boolean confirm_identity) {
		if (!confirm_identity) {
			System.out.println("Failed to verify");
			return false;
		}
		return true;
		
	}
	
	public String getAccountName() {
		return this.account_name;
	}
	
	public ArrayList<Boolean> getAwards() {
		return this.awards;
	}
}
