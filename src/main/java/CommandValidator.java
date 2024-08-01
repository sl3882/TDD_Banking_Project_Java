public class CommandValidator {
	private final Bank myBank;

	public CommandValidator(Bank myBank) {
		this.myBank = myBank;
	}

	public boolean validate(String s) {
		if (myBank.savingAccountBalance415(415)) {
			return false;
		}

		return true;
	}
}
