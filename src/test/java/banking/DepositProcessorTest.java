package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositProcessorTest {

	private CommandProcessor commandProcessor;
	private DepositProcessor depositProcessor;
	private CreateProcessor createProcessor;
	private Bank myBank;

	@BeforeEach
	void setUp() {
		myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);
		depositProcessor = new DepositProcessor(myBank);
		createProcessor = new CreateProcessor(myBank);
		createProcessor.process(commandProcessor.convertCommandToArray("create cd 44856321 0.05 1000"));
		createProcessor.process(commandProcessor.convertCommandToArray("create savings 98210100 0.03"));
		createProcessor.process(commandProcessor.convertCommandToArray("create checking 23469612 0.0"));
		createProcessor.process(commandProcessor.convertCommandToArray("create checking 77771111 0.0"));
		createProcessor.process(commandProcessor.convertCommandToArray("create savings 11113333 0.03"));

		depositProcessor.process(commandProcessor.convertCommandToArray("deposit 77771111 500"));
		depositProcessor.process(commandProcessor.convertCommandToArray("deposit 11113333 300"));
	}

	@Test
	void deposit_into_existing_checking_account() {
		String[] command = commandProcessor.convertCommandToArray("deposit 77771111 250");

		depositProcessor.process(command);
		double currentBalance = myBank.getAccountById("77771111").getBalance();

		assertEquals(750.0, currentBalance, 0.01); // Using tolerance for floating-point comparison
	}

	@Test
	void deposit_into_existing_saving_account() {
		String[] command = commandProcessor.convertCommandToArray("deposit 11113333 30");
		depositProcessor.process(command);
		double currentBalance = myBank.getAccountById("11113333").getBalance();

		assertEquals(330.0, currentBalance, 0.01);
	}

	@Test
	void deposit_into_new_checking_account() {
		String[] command = commandProcessor.convertCommandToArray("deposit 23469612 100");
		depositProcessor.process(command);
		double currentBalance = myBank.getAccountById("23469612").getBalance();

		assertEquals(100.0, currentBalance, 0.01);
	}

	@Test
	void deposit_into_new_saving_account() {
		String[] command = commandProcessor.convertCommandToArray("deposit 98210100 50");
		depositProcessor.process(command);
		double currentBalance = myBank.getAccountById("98210100").getBalance();

		assertEquals(50.0, currentBalance, 0.01);
	}

	@Test
	void deposit_into_new_cd_account() {
		String[] command = commandProcessor.convertCommandToArray("deposit 44856321 200");
		depositProcessor.process(command);
		double currentBalance = myBank.getAccountById("44856321").getBalance();

		assertEquals(1200.0, currentBalance, 0.01);
	}

	@Test
	void deposit_multiple_times_into_same_checking_account() {
		String[] createCommand = commandProcessor.convertCommandToArray("create checking 88552200 0.0");
		createProcessor.process(createCommand);

		String[] depositCommand1 = commandProcessor.convertCommandToArray("deposit 88552200 100");
		String[] depositCommand2 = commandProcessor.convertCommandToArray("deposit 88552200 100");

		depositProcessor.process(depositCommand1);
		depositProcessor.process(depositCommand2);
		double currentBalance = myBank.getAccountById("88552200").getBalance();

		assertEquals(200.0, currentBalance, 0.01);
	}

	@Test
	void deposit_multiple_times_into_same_saving_account() {
		String[] createCommand = commandProcessor.convertCommandToArray("create savings 33224477 0.0");
		createProcessor.process(createCommand);

		String[] depositCommand1 = commandProcessor.convertCommandToArray("deposit 33224477 200");
		String[] depositCommand2 = commandProcessor.convertCommandToArray("deposit 33224477 200");

		depositProcessor.process(depositCommand1);
		depositProcessor.process(depositCommand2);
		double currentBalance = myBank.getAccountById("33224477").getBalance();

		assertEquals(400.0, currentBalance, 0.01);
	}

	@Test
	void deposit_multiple_times_into_same_cd_account() {
		String[] createCommand = commandProcessor.convertCommandToArray("create cd 22004411 0.0 1000");
		createProcessor.process(createCommand);

		String[] depositCommand1 = commandProcessor.convertCommandToArray("deposit 22004411 1000");
		String[] depositCommand2 = commandProcessor.convertCommandToArray("deposit 22004411 1000");

		depositProcessor.process(depositCommand1);
		depositProcessor.process(depositCommand2);
		double currentBalance = myBank.getAccountById("22004411").getBalance();

		assertEquals(3000.0, currentBalance, 0.01);
	}
}