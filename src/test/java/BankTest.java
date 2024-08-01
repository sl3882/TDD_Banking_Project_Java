import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	private Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();

	}

	@Test
	public void deposit_300_twice_by_account_id() {
		Account checking = new Account(0);
		bank.addAccount(checking);
		Account checking2 = new Account(0);
		bank.addAccount(checking2);
		Account saving = new Account(0);
		bank.addAccount(saving);

		int accountNumber = checking.getId();
		bank.depositById(accountNumber, 300);
		bank.depositById(accountNumber, 300);
		assertEquals(600.0, checking.getBalance());
	}

	@Test
	public void withdrawing_300_twice_by_account_id() {

		Account saving = new Account(0.1);
		bank.addAccount(saving);
		Account CD = new Account(0, 1000);
		bank.addAccount(CD);
		Account checking = new Account(0);
		bank.addAccount(checking);
		Account saving3 = new Account(0);
		bank.addAccount(saving3);

		int accountNumber = CD.getId();

		bank.withdrawById(accountNumber, 300);
		bank.withdrawById(accountNumber, 300);
		assertEquals(400.0, CD.getBalance());
	}

	@Test
	public void retrieving_1_account_from_the_bank() {
		Account checking1 = new Account(0);
		bank.addAccount(checking1);
		Account testChecking1 = bank.retrieving(0);
		assertEquals(checking1, testChecking1);
	}

	@Test
	public void test_add_two_account() {
		Account checking2 = new Account(0);
		Account CD = new Account(.05, 1050);
		bank.addAccount(checking2);
		bank.addAccount(CD);

		assertEquals(2, bank.list.size());
	}

}
