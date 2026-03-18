/*
 * Fetch Database data & Query data
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.*;
import java.util.Random;
public class DB {

/*
* Existing User method that searches for a user by their user name and account number to bring up the 
* balance. Later in the accountInfoDisplay method the user is prompted to withdraw or deposit.
*  
*/
public static void existingUser(String username, int accountNumber) {
		try {
	        Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/millionares_banking_DB", "root", "Shadic123_");
	        
	        PreparedStatement stmt = conn.prepareStatement(
	        		"SELECT * FROM accounts WHERE username = ? AND accountNumber = ?"
	        	);
	        // Fetch user name and account Number from database
	        stmt.setString(1, username);
	        stmt.setInt(2,  accountNumber);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	        	// Data Found in the Database
	        	double balance = rs.getDouble("balance");
	        	
	    		System.out.println("=====================================");
	        	System.out.println("Successful Login.");
	    		System.out.println("=====================================");
	    		// Display Account Information with DB Data & object inside method
	    		display.accountInfoDisplay(username, accountNumber, balance);
	    		
	        } else {	// IF DATA IS NOT FOUND IN DATABASE OR CREDENTIALS ARE INVALID
	        	int choice;
	        	Scanner std = new Scanner(System.in);
	        	System.out.print("\nAccount Not Found. Try Again?\n"
	        				+ "Yes (1), No(0)" + 
	        			"\nEnter (1) or (0): ");
	        	choice = std.nextInt();
	        	std.nextLine();			// Clearing buffer 
	        	
	        	if (choice == 0) 
	        	{
	        		System.out.println("Exiting ....");
	        		std.close();
	        	} else if (choice == 1) 
	        	{
	        		display.getCredentials();
	        		std.close();
	        	} else 
	        	{
	        		System.out.println("Invalid Option... \n GoodBye!");
	        		std.close();
	        	}
	        }
	        conn.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

/*
 * New User method that adds a new user to the database, new user name, new balance and a new account number.
 * 
 */
public static void newUser() {
	Scanner std = new Scanner(System.in);

    System.out.println("===== CREATE NEW ACCOUNT =====");

    // Ask user for a user name and validate that it is UNIQUE and not already taken
    System.out.print("Enter username: ");
    String username = std.next();
    boolean check = checkUsername(username);	// Checking to see if user name is taken in the database
    
    // Loop until valid user name
    while(check==true) {
    	System.out.println("Username is taken. Try again");
    	System.out.print("Enter username: ");
        username = std.next();
        check = checkUsername(username);	// Loop validation
    }
    // Using random class to give user a random balance (THIS IS A PROTOTYPE CAN BE UPDATED LATER)
    Random random = new Random();
    double balance = random.nextDouble()*100;
    BigDecimal rounded = new BigDecimal(balance)
            .setScale(2, RoundingMode.HALF_UP);

    System.out.println("Your random starting balance is: $" + rounded);
    
    // Establish connection to mySQL & Insert new values and retrieve new account number
   try { 
	   Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/millionares_banking_DB", "root", "Shadic123_");
    PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts (username, balance) VALUES (?, ?)",
    		PreparedStatement.RETURN_GENERATED_KEYS);
    stmt.setString(1, username);
    stmt.setBigDecimal(2, rounded);
    
    stmt.executeUpdate();
    
    // Get generated account number
    ResultSet rs = stmt.getGeneratedKeys();
    if (rs.next()) 
    {
    	int newAccountNumber = rs.getInt(1);
    	System.out.println("Account Succesfully created and stored.");
    	System.out.println("Account number: " + newAccountNumber);
    	System.out.println("Would you like to deposit/withdraw? (1) or Exit (0)");
    	System.out.print("Enter: ");
    	// Help to validate input and clear buffer/invalid values causing program to crash
    	while (!std.hasNextInt()) {
    	    System.out.println("Invalid input. Please enter 1 or 0:");
    	    std.next(); // discard invalid input
    	}

    	int choice = std.nextInt();    	
    	std.nextLine();			// Clearing buffer 
		System.out.println("=====================================");
    	if (choice == 0) 
    	{
    		System.out.println("Exiting ....");
    		std.close();
    	} else if (choice == 1) 
    	{
    		
    		display.accountInfoDisplay(username, newAccountNumber, balance);
    		std.close();
    	} else 
    	{
    		System.out.println("Invalid Option... \n GoodBye!");
    		std.close();
    	}
    } else 
    {
    	System.out.println("Account creation unsuccessful.");
    }
   } catch (Exception e) 
   {
		e.printStackTrace();
	}
}

public static void updateBalance(String username, int accountNumber, double balance) {

	try {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/millionares_banking_DB", "root", "Shadic123_");
        
        PreparedStatement stmt = conn.prepareStatement(
        	    "UPDATE accounts SET balance = ? WHERE username = ? AND accountNumber = ?"
        	);
        
        stmt.setDouble(1, balance);
        stmt.setString(2,  username);
        stmt.setInt(3, accountNumber);
        
        int rowsUpdated = stmt.executeUpdate();
        if(rowsUpdated == 1) {
        	System.out.println("Balance successfully updated.");
        	System.out.printf("New Balance: $%.2f%n", balance);
        } else if (rowsUpdated > 1) {
        	System.out.println("An error has occured");
        } else {
        	System.out.println("Action unsuccessful.");
        }
	
} catch (Exception e) {
			e.printStackTrace();
		}

}

public static boolean checkUsername(String username) {
	try {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/millionares_banking_DB", "root", "Shadic123_");
        PreparedStatement stmt = conn.prepareStatement(
        		"SELECT * FROM accounts WHERE username = ?"
        		);
        stmt.setString(1, username);
        
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
        	return true;	// The database already holds this user name
        } else {
        	return false;	// User name is available
        }
} catch (Exception e) {
	e.printStackTrace();
	return false;
}
	}



}



