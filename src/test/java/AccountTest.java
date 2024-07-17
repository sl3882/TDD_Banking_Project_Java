import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	Account checking;

	@BeforeEach
	public void setUp() {
		checking = new Checking(0);
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
