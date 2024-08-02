
public class CommandValidator {
	private final Bank myBank;

	public CommandValidator(Bank myBank) {
		this.myBank = myBank;
	}

	public boolean validate(double amount) {
		return myBank.isAccountBalanceEqual(amount);
	}
}
