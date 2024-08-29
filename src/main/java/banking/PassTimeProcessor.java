package banking;

public class PassTimeProcessor extends Processor {
	private final Bank myBank;

	public PassTimeProcessor(Bank myBank) {
		super(myBank);
		this.myBank = myBank;
	}

	public void process(String[] command) {

		int month = Integer.parseInt(command[1]);

		for (int i = 0; i < month; i++) {
			myBank.calculateInterest();
			myBank.deductFeeForBalanceUnder25();
			myBank.removeAccountsWithZeroBalance();

		}

	}

}
