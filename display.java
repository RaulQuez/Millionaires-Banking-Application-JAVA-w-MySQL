/*
 *	 
 */
import java.util.*;
public class display  {

	public static void getCredentials() {
		Scanner std = new Scanner(System.in);
		System.out.println("=====================================");
		System.out.print("Welcome Back." + "\nPlease Enter Your Credentials: " + "\nUsername: ");
		String username = std.next();
		System.out.print("Enter Account Number: ");
		int accountNumber = std.nextInt();
    	std.nextLine();			// Clearing buffer 
		DB.existingUser(username, accountNumber);
		std.close();
	}
	
	public static void accountInfoDisplay(String username, int accountNumber, double balance) {
		// Create object
		Scanner std = new Scanner(System.in);
		
		System.out.println("=====================================");
	    System.out.println("   MILLIONARE BANKING SYSTEMS");
	    System.out.println("=====================================");
	    System.out.println("User: " + username);
	    System.out.println("Account Number: " + accountNumber);
	    System.out.printf("Balance: $%.2f\n", balance);
	    System.out.println("-------------------------------------");
	    System.out.println("1. Deposit");
	    System.out.println("2. Withdraw");
	    System.out.println("3. Logout");
	    System.out.println("=====================================");
	    
	    // Options
	    int choice;
	    System.out.print("Enter Option (1), (2), or (3): ");
	    choice = std.nextInt();
    	std.nextLine();			// Clearing buffer 
    	// Input validation
    	while(choice < 0 || choice > 4) {
    		System.out.println("Invalid Option. Select one of the following: ");
    		System.out.println("1. Deposit");
    		System.out.println("2. Withdraw");
    		System.out.println("3. Logout & Exit");
    		System.out.println("=====================================");
    		choice = std.nextInt();
    	    	std.nextLine();			// Clearing buffer 
    	}
    	switch (choice) {
    	case 1:
    		double amount;
    		System.out.println("How much would you like to deposit?");
    		System.out.print("Enter: $");
    		amount = std.nextDouble();
	    	std.nextLine();			// Clearing buffer 
	    	
    		System.out.println("=====================================");
	    	
    		balance = BankAccount.deposit(amount, balance);
	    	
	    	DB.updateBalance(username,accountNumber,balance);
	    	
	    	askContinue(username,accountNumber,balance);	// Ask user if they want to do something else
	    	
    		break;
    	case 2:
    		double amount2;
    		System.out.println("How much would you like to withdraw?");
    		System.out.print("Enter: $"); 
    		amount2 = std.nextDouble(); 
	    	std.nextLine();			// Clearing buffer 

	    	System.out.println("=====================================");

	    	balance = BankAccount.withdraw(amount2, balance);
	    	
	    	DB.updateBalance(username,accountNumber,balance);
	    	
	    	askContinue(username,accountNumber,balance);	// Ask user if they want to do something else

    		break;
    	case 3:
    		System.out.println("Logging out and Exiting ...");
    		break;
    	default:
    			System.out.println("Invalid choice ...");
        		System.out.println("=====================================");
    			System.out.println("Try Again: ");
    			System.out.println("1. Deposit");
        		System.out.println("2. Withdraw");
        		System.out.println("3. Logout & Exit");
        		System.out.println("=====================================");
        		choice = std.nextInt();
        	    std.nextLine();			// Clearing buffer 
    			break;
    	}
	    std.close();
	}


	public static void askContinue(String username, int accountNumber, double balance) {
	// Ask user if there' s anything else they would like to do
	Scanner std = new Scanner(System.in);
	System.out.println("Would you like to continue and do something else?");
	System.out.print("Continue(1) Exit(0): ");
	int choice = std.nextInt();
	
	// Input Validation
	if (choice == 0) 
	{
		System.out.print("Exiting ....");
		std.close();
	} else if (choice == 1) 
	{
		System.out.println("Transfering you to the main menu ....");
		accountInfoDisplay(username, accountNumber, balance);
	} else 
	{
		System.out.println("Invalid Option... \n GoodBye!");
		std.close();
	}
 }
	
}
