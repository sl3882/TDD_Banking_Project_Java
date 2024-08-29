package banking;

public class Cd extends Account {

	public Cd(double apr, double balance) {
		super(apr, balance);
		super.accountType = "Cd";

	}

}
