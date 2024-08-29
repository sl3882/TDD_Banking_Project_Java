//package banking;
//
//public class WithdrawalValidator extends Validator {
//
//	public WithdrawalValidator(Bank myBank) {
//		super(myBank);
//	}
//
//	public boolean validate(String[] command) {
//		if (command.length < 3) {
//			return false;
//		}
//		double amount = Double.parseDouble(command[2]);
//		return !(amount < 0);
//
//	}
//}
package banking;

public class WithdrawalValidator extends Validator {

	public WithdrawalValidator(Bank myBank) {
		super(myBank);
	}

	public boolean validate(String[] command) {
		if (command.length < 3) {
			return false;
		}
		try {
			double amount = Double.parseDouble(command[2]);
			return amount >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}