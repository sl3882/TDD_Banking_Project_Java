package banking;

public class PassTimeValidator extends Validator {

	public PassTimeValidator(Bank myBank) {
		super(myBank);
	}

	public boolean validate(String[] command) {

		int month = Integer.parseInt(command[1]);
		return (month >= 1 && month <= 60);
	}

}
