
public class CommandValidator {
	private static final double MAX_DAILY_WITHDRAWAL_LIMIT = 100000.00;
	private final Bank myBank;

	public CommandValidator(Bank myBank) {
		this.myBank = myBank;
	}

	public boolean validate(double amount) {
		return myBank.isAccountBalanceEqual(amount);
	}

	public boolean validateAccountApr(double apr) {
		return apr >= 0 && apr <= 1;
	}

	public boolean validateWithdrawalAmount(Account account, double amount) {

		return amount > 0 && amount <= account.getBalance() && amount <= MAX_DAILY_WITHDRAWAL_LIMIT;
	}
}
