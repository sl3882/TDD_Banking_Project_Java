public class Account {

	private static int nextId = 10000000;
	private final int id;
	private final double apr;
	private double balance;

	protected Account(double apr) {
		this.apr = apr;
		this.id = nextId++;
	}

	protected Account(double apr, double balance) {
		this.apr = apr;
		this.balance = balance;
		this.id = nextId++;
	}

	public double getBalance() {
		return balance;
	}

	public double getApr() {
		return apr;
	}

	public int getId() {
		return id;
	}

	public void deposit(double money) {
		balance += money;
	}

	public void withdraw(double m) {
		if (balance >= m) {
			balance -= m;
		} else {
			balance = 0;
		}
	}
}
