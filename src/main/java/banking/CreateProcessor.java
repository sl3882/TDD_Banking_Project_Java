package banking;

public class CreateProcessor extends Processor {
	private final Bank myBank;
	Account account;

	public CreateProcessor(Bank myBank) {
		super(myBank);
		this.myBank = myBank;
	}

	public void process(String[] command) {

		String accountType = command[1];
		String accountId = command[2];

		String aprString = command[3];
		double apr = Double.parseDouble(aprString);

		if (accountType.equalsIgnoreCase("checking")) {
			account = new Checking(apr);
			myBank.addAccount(accountId, account);
		}
		if (accountType.equalsIgnoreCase("savings")) {
			account = new Savings(apr);
			myBank.addAccount(accountId, account);
		}
		if (accountType.equalsIgnoreCase("cd")) {
			String balanceString = command[4];
			double initBalance = Double.parseDouble(balanceString);
			account = new Cd(apr, initBalance);
			myBank.addAccount(accountId, account);
		}
	}

}
