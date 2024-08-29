package banking;

public class DepositValidator extends Validator {
	private final Bank myBank;

	public DepositValidator(Bank myBank) {
		super(myBank);
		this.myBank = myBank;
	}

	public boolean validate(String[] command) {

		String accountId = command[1];
		double amount;
		try {
			amount = Double.parseDouble(command[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (myBank.getAccountById(accountId) instanceof Checking) {
			return amount >= 0 && amount <= 1000;
		} else if (myBank.getAccountById(accountId) instanceof Savings) {
			return amount >= 0 && amount <= 2500;

		} else {
			return !(myBank.getAccountById(accountId) instanceof Cd);
		}
	}
}
