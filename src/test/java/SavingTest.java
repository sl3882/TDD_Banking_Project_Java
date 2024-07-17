import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingTest {
	Saving saving;

	@BeforeEach
	public void setUp() {
		saving = new Saving(0.03);
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
}
