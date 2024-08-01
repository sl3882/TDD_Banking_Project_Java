import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	private Account checking;
	private Account saving;
	private Account CD;
	private Bank myBank;

	@BeforeEach
	public void setUp() {
		myBank = new Bank();
		checking = new Account(0);
		saving = new Account(0.03);
		CD = new Account(.05, 1000);

	}

	@Test
	public void deposit_300_twice_by_account_id() {

		myBank.addAccount(checking);

		Account checking2 = new Account(0);
		myBank.addAccount(checking2);

		int accountNumber = checking.getId();
		myBank.depositById(accountNumber, 300);
		myBank.depositById(accountNumber, 300);
		assertEquals(600.0, checking.getBalance());
	}

	@Test
	public void withdrawing_300_twice_by_account_id() {

		myBank.addAccount(CD);

		Account saving3 = new Account(0);
		myBank.addAccount(saving3);

		int accountNumber = CD.getId();

		myBank.withdrawById(accountNumber, 300);
		myBank.withdrawById(accountNumber, 300);
		assertEquals(400.0, CD.getBalance());
	}

	@Test
	public void retrieving_1_account_from_the_myBank() {

		myBank.addAccount(checking);

		Account testChecking1 = myBank.retrieving(0);
		assertEquals(checking, testChecking1);
	}

	@Test
	public void test_add_two_account() {

		myBank.addAccount(checking);
		myBank.addAccount(CD);

		assertEquals(2, myBank.myBank.size());
	}

}
