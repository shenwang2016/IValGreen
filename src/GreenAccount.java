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
	//max number of security questions
	public static final int MAX_NUM = 5;
	// size of awards
	public static final int AWARD_SIZE = 100;
	private boolean login = false; // variable to trace the login status
	private String account_name;
	private double sum_contribution = 0.0;
	private String password; // a string that marks the password
	// a boolean array list that marks the awards
	private boolean[] awards = new boolean[AWARD_SIZE];
	// a map that maps from security questions to corresponding answers
	private HashMap<String, String> security_questions = new HashMap<String, String>();
	
	// constructor with only name and password
	public GreenAccount(String account_name, String password) {
		this.account_name = account_name;
		this.password = password;
	}
	
	public GreenAccount(String account_name, String password, boolean login) {
		this.account_name = account_name;
		this.password = password;
		this.login = true;
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
	// helper method to handle the case while user is not in login status
	// if user is not login, then verify its ID
	// if not pass, then return false; otherwise, return true
	private boolean loginAndVerify() {
		if (!this.login) {
			if (!verifyID()) {
				return false;
			}
		}
		return true;
	}
	/*
	 * In parameters: a boolean value that confirms the user is eligible to
	 * change password (if false, return false)
	 */
	public boolean setPassWord() {
        boolean check = loginAndVerify();
        if (!check) return false;
        this.login = true;
		// get the new password
		// maybe use a different input method to get the user input
		this.password = getPassword();
		System.out.println("Successfully set password");
		return true;
	}
	
	// helper method to prompt password from user
	private String getPassword() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Your new Password:");
		String pass = scan.nextLine();
		scan.close();
		return pass;
	}
	
	// method to help user get an account name
	public boolean setAccountName(HashMap<String, GreenAccount> map) {
		// check login status first
		boolean check = loginAndVerify();
		// if not verified, user are not allowed to set account name
        if (!check) return false;
        this.login = true;
		Scanner scan = new Scanner(System.in);
		String name;
		System.out.println("Your new account name:");
		while (true) {
			// prompt user to enter an account name until the entered name doesn't conflict with other existing name
			name = scan.nextLine();
			// if name is not a duplicate, then break out while loop
			if (!isDupName(map, name)) {
				break;
			}
			// prompt user to enter a new account name
			System.out.println("The name is taken, pick another one:");
		}
		// save account_name into map latter
		this.account_name = name;
		System.out.println("Successfully set account name");
		scan.close();
		return true;
	}
	
	// method to help user set security questions
	public boolean setSecurityQuestions() {
		// check login status first
		boolean check = loginAndVerify();
		// if not verified, user is not allowed to set security questions
        if (!check) return false;
        this.login = true;
		// sanitary check
		if (this.security_questions == null) {
			this.security_questions = new HashMap<String, String>();
		}
		// prompt user to enter security questions and corresponding answer
		Scanner scan = new Scanner(System.in);
		for (int i = 1; i <= MAX_NUM; i++) {
			System.out.println("Question #" + i);
			String question = scan.nextLine();
			System.out.println("Answer to this question");
			String answer = scan.nextLine();
			this.security_questions.put(question, answer);
		}
		scan.close();
		return true;
	}
	
	// set user's login status
	public void log_in() {
		this.login = verifyID();
	}
	
	// method to verify userID
	// 1) verify via user name and password
	// 2) if first step failed, let user answer security questions.
	// if user pass this step, then ID is verified
	public boolean verifyID() {
		Scanner scan = new Scanner(System.in);
		// prompt user to enter password
		System.out.println("Enter your password:");
		String user_password = getPassword();
		// if password match with the record
		// return true
		if (this.password.equals(user_password)) {
			scan.close();
			return true;
		} else { // enter here means password match failed
			System.out.println("Wrong password, answer your security"
					+ " questions");
			// prompt user to answer security questions
			for (String s : this.security_questions.keySet()) {
				// print questions
				System.out.println(s);
				// prompt user to enter answer
				System.out.println("Enter the answer:");
				String a = scan.nextLine();
				// check whether answer match with records
				if (a.equals(this.security_questions.get(s))) {
					scan.close();
					return true;
				}
			}
		}
		// reach here means verification failure
		System.out.println("Failed to verify");
		scan.close();
		return false;
	}
	
	// check whether we have duplicate account name
	public boolean isDupName(HashMap<String, GreenAccount> map, String name) {
		return map.containsKey(name);
	}
	
	// get account name
	public String getAccountName() {
		return this.account_name;
	}
	
	// get total contribution
	public double get_sum() {
		return this.sum_contribution;
	}
	
	// increase the amount of total contribution
	public void increment_contribution(double increment) {
		this.sum_contribution += increment;
	}
	
	// get a list of awards
	public ArrayList<String> getAwards(HashMap<String, String> map) {
		ArrayList<String> awards_string = new ArrayList<String>();
		for (int i = 0; i < AWARD_SIZE; i++) {
			if (this.awards[i]) {
				awards_string.add(map.get(Integer.toString(i)));
			}
		}
		return awards_string;
	}
	
	public void getAll(String an, String pw, boolean[] aw, HashMap<String, String> mp) {
		an = this.account_name;
		pw = this.password;
		aw = this.awards;
		mp = this.security_questions;
	}
}