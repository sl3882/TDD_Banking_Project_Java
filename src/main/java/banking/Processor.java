package banking;

public class Processor {
	private final Bank myBank;

	public Processor(Bank myBank) {
		this.myBank = myBank;
	}

	protected Bank getBank() {
		return myBank;
	}
}
