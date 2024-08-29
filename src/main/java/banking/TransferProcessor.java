package banking;

public class TransferProcessor extends Processor {
	private final Bank myBank;

	public TransferProcessor(Bank myBank) {

		super(myBank);
		this.myBank = myBank;
	}

	public void process(String[] command) {

		String fromId = command[1];
		String toId = command[2];

		double amount;

		amount = Double.parseDouble(command[3]);

		myBank.transferByID(fromId, toId, amount);

	}

}
