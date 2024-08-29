package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawalProcessorTest {
	private CommandProcessor commandProcessor;

	private WithdrawalProcessor withdrawalProcessor;
	private Bank myBank;

	@BeforeEach
	void setUp() {
		myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);

		withdrawalProcessor = new WithdrawalProcessor(myBank);
		CreateProcessor createProcessor = new CreateProcessor(myBank);
		createProcessor.process(commandProcessor.convertCommandToArray("create checking 23460212 0.0"));
		createProcessor.process(commandProcessor.convertCommandToArray("create savings 93410100 0.03"));
		createProcessor.process(commandProcessor.convertCommandToArray("create cd 44888321 0.05 1000"));
		createProcessor.process(commandProcessor.convertCommandToArray("create checking 77771101 0.0"));
		createProcessor.process(commandProcessor.convertCommandToArray("create savings 11113033 0.03"));

	}

	@Test
	void withdraw_money_existing_checking_account() {

		myBank.depositById("23460212", 500);
		withdrawalProcessor.process(commandProcessor.convertCommandToArray("withdraw 23460212 250"));
		double currentBalance = myBank.getAccountById("23460212").getBalance();
		assertEquals(250.0, currentBalance);
	}

	@Test
	void withdraw_money_existing_savings_account() {
		myBank.depositById("93410100", 500);
		withdrawalProcessor.process(commandProcessor.convertCommandToArray("withdraw 93410100 450"));
		double currentBalance = myBank.getAccountById("93410100").getBalance();

		assertEquals(50.0, currentBalance);
	}

	@Test
	void withdraw_money_more_than_balance_existing_checking_account() {
		myBank.depositById("23460212", 200);
		withdrawalProcessor.process(commandProcessor.convertCommandToArray("withdraw 23460212 250"));
		double currentBalance = myBank.getAccountById("23460212").getBalance();

		assertEquals(0.0, currentBalance);
	}

	@Test
	void withdraw_money_more_than_balance_existing_savings_account() {
		myBank.depositById("93410100", 100);
		withdrawalProcessor.process(commandProcessor.convertCommandToArray("withdraw 93410100 450"));
		double currentBalance = myBank.getAccountById("93410100").getBalance();
		assertEquals(0.0, currentBalance);
	}
}