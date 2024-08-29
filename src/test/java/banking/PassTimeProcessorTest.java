package banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeProcessorTest {
	private CommandProcessor commandProcessor;
	private PassTimeProcessor passTimeProcessor;
	private Bank myBank;
	private Account checking;
	private Account savings;

	@BeforeEach
	public void setUp() {
		myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);

		passTimeProcessor = new PassTimeProcessor(myBank);
		checking = new Checking(3);
		savings = new Savings(3);

		myBank.addAccount("15691205", checking);
		myBank.addAccount("25786602", savings);

	}

	@Test
	void pass_1_valid_command() {
		savings.deposit(1000);
		String[] command = commandProcessor.convertCommandToArray("Pass 1");

		passTimeProcessor.process(command);
		double actual = myBank.getBalanceById("25786602");
		assertEquals(1002.5, actual);
	}

	@Test
	void pass_2_with_account_closure() {
		checking.deposit(30);
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 2"));
		double balance = checking.getBalance();
		assertEquals(0, balance);
		boolean actual2 = myBank.doesAccountExist("15691205");
		assertFalse(actual2);
	}

	@Test
	void pass_1_without_account_closure() {
		savings.deposit(30);
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 1"));
		double balance = commandProcessor.formatDouble(savings.getBalance());
		assertEquals(5.07, balance);
		boolean actual2 = myBank.doesAccountExist("25786602");
		assertTrue(actual2);
	}

	@Test
	void test_interest_calculation_checking() {
		checking.deposit(100);
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 1"));
		double actual = myBank.getBalanceById("15691205");
		assertEquals(100.25, actual);
	}

	@Test
	void test_interest_calculation_savings() {
		savings.deposit(800);
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 1"));
		double actual = myBank.getBalanceById("25786602");
		assertEquals(802, actual);
	}

}
