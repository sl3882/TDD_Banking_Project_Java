import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {
	Checking checking;

	@BeforeEach
	public void setUp() {
		checking = new Checking(0);
	}

	@Test
	public void balance_increases_by_the_amount_deposited_200() {
		checking.deposit(200);
		double actual = checking.getBalance();
		assertEquals(200, actual);
	}

	@Test
	public void balance_300_decreases_by_the_amount_withdrawn_200() {
		checking.deposit(300);
		checking.withdraw(200);

		double actual = checking.getBalance();
		assertEquals(100, actual);
	}

}
