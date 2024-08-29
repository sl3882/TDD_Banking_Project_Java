package banking;

public class Checking extends Account {

	public Checking(double apr) {
		super(apr);
		super.accountType = "Checking";
	}

}
