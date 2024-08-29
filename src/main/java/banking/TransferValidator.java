
package banking;

public class TransferValidator extends Validator {
	private final Bank myBank;

	public TransferValidator(Bank myBank) {
		super(myBank);
		this.myBank = myBank;
	}

	public boolean validate(String[] command) {

		String fromId = command[1];
		String toId = command[2];
		Account fromAccount = myBank.getAccountById(fromId);
		Account toAccount = myBank.getAccountById(toId);

		if (fromAccount == null || toAccount == null) {
			return false;
		}

		if (fromAccount instanceof Cd || toAccount instanceof Cd) {
			return false;
		}

		return true;
	}
}