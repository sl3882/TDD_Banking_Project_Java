package banking;

public class Account {

	private final double apr;
	String accountType;
	private double balance;

	public Account(double apr) {
		this(apr, 0);

	}

	protected Account(double apr, double balance) {
		this.apr = apr;
		this.balance = balance;
		this.accountType = " ";// Initialize with empty string, subclasses should set it appropriately

	}

	public double getBalance() {
		return balance;
	}

	public double getApr() {
		return apr;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		if (balance <= amount) {
			balance = 0;

		} else {
			balance -= amount;
		}

	}

	public String getAccountType() {
		return accountType;
	}

}
