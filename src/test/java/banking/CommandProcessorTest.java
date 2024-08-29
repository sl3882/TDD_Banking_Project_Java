package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {

	Account checking;
	private Bank myBank;
	private CommandProcessor commandProcessor;
	private WithdrawalProcessor withdrawalProcessor;

	@BeforeEach
	public void setUp() {
		myBank = new Bank();
		withdrawalProcessor = new WithdrawalProcessor(myBank);
		commandProcessor = new CommandProcessor(myBank);
		checking = new Checking(0.3);
		myBank.addAccount("12345678", checking);

	}

	@Test
	void withdraw_money_existing_checking_account() {
		myBank.depositById("12345678", 500);
		withdrawalProcessor.process(commandProcessor.convertCommandToArray("withdraw 12345678 250"));
		double currentBalance = myBank.getAccountById("12345678").getBalance();
		assertEquals(250.0, currentBalance, 0.01);
	}

	@Test
	void test_formatNumber_with_rounding_floor() {
		double valueToFormat = 123.456;
		String formattedValue = commandProcessor.formatString(valueToFormat);
		assertEquals("123.45", formattedValue);
	}
}
