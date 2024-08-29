package banking;

public class Savings extends Account {

	public Savings(double apr) {
		super(apr);
		super.accountType = "Savings";

	}
}
