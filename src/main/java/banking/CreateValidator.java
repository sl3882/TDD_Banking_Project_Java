package banking;

public class CreateValidator extends Validator {
	private final Bank myBank;

	public CreateValidator(Bank myBank) {
		super(myBank);
		this.myBank = myBank;
	}

	public boolean validate(String[] command) {

		double apr;

		String accountType = command[1];
		String accountId = command[2];

		try {
			String aprString = command[3];

			apr = Double.parseDouble(aprString);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return false;
		}
		if (apr < 0.0) {
			return false;
		}
		if (!(apr <= 10.0)) {
			return false;
		}
		if (accountType.equalsIgnoreCase("cd")) {
			String strBalance = command[4];
			double initBalance = Double.parseDouble(strBalance);
			if (!(initBalance >= 1000.0)) {
				return false;
			}
			if (!(initBalance <= 10000.0)) {
				return false;
			}
		}

		return myBank.getAccountById(accountId) == null;
	}
}