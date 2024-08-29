package banking;

public class WithdrawalProcessor extends Processor {

	public WithdrawalProcessor(Bank myBank) {
		super(myBank);
	}

	public void process(String[] command) {

		String accountId = command[1];
		String amountString = command[2];
		double amount = Double.parseDouble(amountString);

		Account account = getBank().getAccountById(accountId);

		account.withdraw(amount);

	}
}
