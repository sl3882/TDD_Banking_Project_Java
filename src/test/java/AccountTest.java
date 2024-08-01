import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	Account checking;
	Account saving;
	Account CD;

	@BeforeEach
	public void setUp() {
		checking = new Account(0);
		saving = new Account(0.03);
		CD = new Account(.05, 1000);
	}

	@Test
	public void balance_increases_by_the_amount_deposited_200() {
		checking.deposit(200);
		double actual = checking.getBalance();
		assertEquals(200, actual);
	}

	@Test
	public void balance_increases_by_the_amount_deposited_300_twice() {
		saving.deposit(300);
		saving.deposit(300);
		double actual = saving.getBalance();
		assertEquals(600, actual);
	}

	@Test
	public void balance_400_decreases_by_the_amount_withdrawn_300_twice() {
		saving.deposit(400);
		saving.withdraw(300);
		saving.withdraw(300);
		double actual = saving.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void balance_300_decreases_by_the_amount_withdrawn_200() {
		checking.deposit(300);
		checking.withdraw(200);

		double actual = checking.getBalance();
		assertEquals(100, actual);
	}

	@Test
	public void open_cd_with_1000_balance() {
		double actual = CD.getBalance();
		assertEquals(1000, actual);
	}

	@Test
	public void open_checking_with_zero_balance() {
		double actual = checking.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void apr_0() {
		double actual = checking.getApr();
		assertEquals(0, actual);
	}

	@Test
	public void balance_increases_by_the_amount_deposited_200_twice() {
		checking.deposit(200);
		checking.deposit(200);
		double actual = checking.getBalance();
		assertEquals(400, actual);
	}

	@Test
	public void balance_300_decreases_by_the_amount_withdrawn_200_twice() {
		checking.deposit(300);
		checking.withdraw(200);
		checking.withdraw(200);
		double actual = checking.getBalance();
		assertEquals(0, actual);
	}

}
