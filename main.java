/*
 * Raul Henriquez
 * 
 */
import java.util.Scanner;
public class main extends DB{

	public static void main(String[] args) {
		
		// Opening screen prototype
		System.out.println("=====================================\n" +
						   "WELCOME TO MILLIONARE BANKING SYSTEMS\n" +
						   "=====================================\n");
		
		
		// Prompt user for their user name or to create an account
		Scanner std = new Scanner(System.in);
		int choice;
		System.out.print("Existing (1) or New User (2)?" + 
							"\nEnter (1) or (2): ");
		choice = std.nextInt();
    	std.nextLine();			// Clearing buffer 
		System.out.println("=====================================");
		// Input Validation into loop until input is valid
		while (choice > 2 || choice < 0) 
		{
			System.out.println("Invalid Choice. Try Again or Enter -1 to Exiit.");
			System.out.print("Exit (0) Existing (1) New User (2)?" + 
					"\nEnter (0), (1) or (2): ");
		choice = std.nextInt();
    	std.nextLine();			// Clearing buffer 
		System.out.println("=====================================");
		// Exit program
		if(choice==0) 
		{
			System.out.println("Exiting ....");
			break;
		}
		}
		// Switch statement based on choice 1 or 0
		switch(choice) {
		case 1:		// Existing User
			System.out.println("=====================================");
			System.out.print("Welcome Back." + "\nPlease Enter Your Credentials: " + "\nUsername: ");
			String username = std.next();
			System.out.print("Enter Account Number: ");
			int accountNumber = std.nextInt();
	    	std.nextLine();			// Clearing buffer 
			existingUser(username, accountNumber);
			
			break;
		case 2:		// New User
			System.out.println("Welcome New User!");
			newUser();
			break;
		}
	
		
		std.close();
	}

}
