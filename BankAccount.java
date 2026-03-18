/*
 * Bank Account class
 * 
 */
public class BankAccount {

	String accountName;
	int accountNumber;
	double balance;
	
	public BankAccount(String accountName, int accountNumber, double balance){
		accountName = this.accountName;
		accountNumber = this.accountNumber;
		balance = this.balance;
	}
	// Deposit method
	public static double deposit(double amount, double balance) {
		if(amount > 0) {
			balance += amount;
		} else 
		{
			System.out.println("Invalid Deposit amount...");
		}
		
		return balance;
	}
	// Withdraw method
	public static double withdraw(double amount, double balance) {
		if(balance < amount) {
			System.out.println("Insuffient Funds.");
		} else if (amount <= 0) {
			System.out.println("Invalid amount for withdrawl");
		} else {
			balance -= amount;
		}
		
		return balance;
	}
	
}
