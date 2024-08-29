package banking;

import static org.junit.jupiter.api.Assertions.*;

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
		checking = new Checking(0);
		saving = new Savings(0.03);
		CD = new Cd(.05, 1000);
	}

	@Test
	public void deposit_300_twice_by_account_id() {
		myBank.addAccount("19887759", checking);
		myBank.depositById("19887759", 300);
		myBank.depositById("19887759", 300);
		double actual = myBank.getBalanceById("19887759");
		assertEquals(600.0, actual);
	}

	@Test
	public void withdrawing_300_twice_by_account_id() {
		myBank.addAccount("19887753", CD);
		myBank.withdrawById("19887753", 300);
		myBank.withdrawById("19887753", 300);
		double actual = myBank.getBalanceById("19887753");
		assertEquals(400.0, actual);
	}

	@Test
	public void retrieving_1_account_from_the_myBank() {
		myBank.addAccount("23654569", checking);
		Account retrievedAccount = myBank.getAccountById("23654569");
		assertEquals(checking, retrievedAccount);
	}

	@Test
	public void test_add_two_accounts() {
		myBank.addAccount("23654569", checking);
		myBank.addAccount("25645589", CD);
		assertEquals(2, myBank.getNumberOfAccounts());
	}

	@Test
	public void test_transfer_money_by_ID() {
		Account fromAccount = new Checking(0.0);
		Account toAccount = new Savings(0.03);
		fromAccount.deposit(200);
		myBank.addAccount("11223344", fromAccount);
		myBank.addAccount("99887766", toAccount);
		myBank.transferByID("11223344", "99887766", 200.0);
		double fromBalance = myBank.getBalanceById("11223344");
		double toBalance = myBank.getBalanceById("99887766");
		assertEquals(0, fromBalance);
		assertEquals(200, toBalance);
	}

	@Test
	public void search_for_account_by_amount() {
		Account checking3 = new Checking(0.0);
		checking3.deposit(336655);
		myBank.addAccount("33669988", checking3);
		boolean actual = myBank.isAccountBalanceEqual(336655);
		assertTrue(actual);
	}

	@Test
	public void search_for_account_by_amount_not_exist() {
		Account checking3 = new Checking(0.0);
		checking3.deposit(336655);
		myBank.addAccount("33669988", checking3);
		boolean actual = myBank.isAccountBalanceEqual(153226841);
		assertFalse(actual);

	}

	@Test
	public void test_calculate_interest_for_savings() {
		myBank.addAccount("12345678", saving);
		saving.deposit(1000);
		myBank.calculateInterest();
		assertEquals(1000.025, myBank.getBalanceById("12345678"));

	}

	@Test
	public void test_calculate_interest_for_checking() {

		myBank.addAccount("12345678", checking);
		checking.deposit(100);
		myBank.calculateInterest();
		assertEquals(100, myBank.getBalanceById("12345678"));
	}

	@Test
	public void test_calculate_interest_for_cd() {
		myBank.addAccount("12345678", CD);
		myBank.calculateInterest();
		assertEquals(1000.1666666666666, myBank.getBalanceById("12345678"), 0.01);
	}

	@Test
	public void test_deduct_fee_for_balance_under_25() {
		myBank.addAccount("123456789", checking);
		checking.deposit(24.99);
		myBank.deductFeeForBalanceUnder25();
		assertEquals(0, myBank.getBalanceById("123456789"), 0.01);
	}

	@Test
	public void test_no_fee_deducted_for_balance_25_or_more() {
		myBank.addAccount("123456789", checking);
		checking.deposit(25.00);
		myBank.deductFeeForBalanceUnder25();
		assertEquals(0, myBank.getBalanceById("123456789"), 0.01);
	}
}
