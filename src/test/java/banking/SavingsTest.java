package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsTest {
	Savings savings;

	@BeforeEach
	public void setUp() {
		savings = new Savings(0.03);
	}

	@Test
	public void balance_increases_by_the_amount_deposited_300_twice() {
		savings.deposit(300);
		savings.deposit(300);
		double actual = savings.getBalance();
		assertEquals(600, actual);
	}

	@Test
	public void balance_400_decreases_by_the_amount_withdrawn_300_twice() {
		savings.deposit(400);
		savings.withdraw(300);
		savings.withdraw(300);
		double actual = savings.getBalance();
		assertEquals(0, actual);
	}
}
