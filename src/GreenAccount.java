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
	public static final int AWARD_SIZE = 100;
	
	private String account_name;
	// a boolean array list that marks the awards
	private boolean[] awards = new boolean[AWARD_SIZE];
	// a string that marks the password
	private String password;
	// a map that maps from questions to answers
	private HashMap<String, String> security_questions
	 = new HashMap<String, String>();
	
	// empty constructor
	public GreenAccount() { 
		
	}
	
	// constructor with only name and password
	public GreenAccount(String account_name, String password) {
		this.account_name = account_name;
		this.password = password;
	}
	
	// a fully profiled account constructor
	public GreenAccount(String account_name,
						String password,
						boolean[] awards,
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
			return false;
		}
		// get the new password
		// maybe use a different input method to get the user input
		System.out.println("Your new Password:");
		this.password = getPassword();
		System.out.println("Successfully set password");
		return true;
	}
	
	private String getPassword() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String pass = scan.nextLine();
		return pass;
	}
	
	@SuppressWarnings("resource")
	public boolean setAccountName(boolean confirm_identity, HashMap<String, GreenAccount> map) {
		if (!confirm_identity) {
			return false;
		}
		Scanner scan = new Scanner(System.in);
		String name;
		System.out.println("Your new account name:");
		while (true) {
			name = scan.nextLine();
			if (!isDupName(map, name)) {
				break;
			}
			System.out.println("The name is taken, pick another one:");
		}
		this.account_name = name;
		System.out.println("Successfully set account name");
		return true;
	}
	
	public boolean setSecurityQuestions(boolean confirm_identity) {
		if (!confirm_identity) {
			return false;
		}
		if (this.security_questions == null) {
			this.security_questions = new HashMap<String, String>();
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		for (int i = 1; i <= MAX_NUM; i++) {
			System.out.println("Question #" + i);
			String question = scan.nextLine();
			System.out.println("Answer to this question");
			String answer = scan.nextLine();
			this.security_questions.put(question, answer);
		}
		return true;
	}
	
	@SuppressWarnings("resource")
	public boolean verifyID() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your password:");
		String user_password = getPassword();
		if (this.password.equals(user_password)) {
			return true;
		} else {
			System.out.println("Wrong password, answer your security"
					+ " questions");
			for (String s : this.security_questions.keySet()) {
				System.out.println(s);
				System.out.println("Enter the answer:");
				String a = scan.nextLine();
				if (a.equals(this.security_questions.get(s))) {
					return true;
				}
			}
		}
		System.out.println("Failed to verify");
		return false;
	}
	
	public boolean isDupName(HashMap<String, GreenAccount> map, String name) {
		return map.containsKey(name);
	}
	
	public String getAccountName() {
		return this.account_name;
	}
	
	public ArrayList<String> getAwards(HashMap<Integer, String> map) {
		ArrayList<String> awards_string = new ArrayList<String>();
		for (int i = 0; i < AWARD_SIZE; i++) {
			if (this.awards[i]) {
				awards_string.add(map.get(i));
			}
		}
		return awards_string;
	}
}