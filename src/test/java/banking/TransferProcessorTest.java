package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferProcessorTest {
	private CommandProcessor commandProcessor;

	private TransferProcessor transferProcessor;
	private PassTimeProcessor passTimeProcessor;
	private Account checking;
	private Account savings;
	private Bank myBank;

	@BeforeEach
	void setUp() {

		myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);
//		PassTimeValidator passTimeValidator = new PassTimeValidator(myBank);
		passTimeProcessor = new PassTimeProcessor(myBank);
//		DepositValidator depositValidator = new DepositValidator(myBank);
//		WithdrawalValidator withdrawalValidator = new WithdrawalValidator(myBank, passTimeValidator, passTimeProcessor);
//		DepositProcessor depositProcessor = new DepositProcessor(myBank);
//		WithdrawalProcessor withdrawalProcessor = new WithdrawalProcessor(myBank);
		transferProcessor = new TransferProcessor(myBank);
		checking = new Checking(0);
		savings = new Savings(0.03);
		Account cd = new Cd(0.02, 3000);
		myBank.addAccount("55691275", checking);
		myBank.addAccount("25786632", savings);
		myBank.addAccount("99326632", cd);
	}

	@Test
	void transfer_200_from_checking_to_saving_valid() {
		checking.deposit(800);
		String[] command = commandProcessor.convertCommandToArray("Transfer 55691275 25786632 200");
		transferProcessor.process(command);
		double fromAccountBalance = myBank.getBalanceById("55691275");
		double inAccountBalance = myBank.getBalanceById("25786632");

		assertEquals(600.0, fromAccountBalance);
		assertEquals(200.0, inAccountBalance);
	}

	@Test
	void transfer_200_from_saving_to_checking_valid() {
		savings.deposit(300);
		checking.deposit(200);
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 3"));
		String[] command = commandProcessor.convertCommandToArray("Transfer 25786632 55691275 100");

		transferProcessor.process(command);
		double fromAccountBalance = commandProcessor.formatDouble(myBank.getBalanceById("25786632"));
		double inAccountBalance = commandProcessor.formatDouble(myBank.getBalanceById("55691275"));

		assertEquals(200.02, fromAccountBalance);
		assertEquals(300.0, inAccountBalance);
	}

	@Test
	void transfer_100_from_checking_to_checking_valid() {
		checking.deposit(300);
		myBank.addAccount("78901234", new Checking(0));
		String[] command = commandProcessor.convertCommandToArray("Transfer 55691275 78901234 100");

		transferProcessor.process(command);
		double fromAccountBalance = myBank.getBalanceById("55691275");
		double inAccountBalance = myBank.getBalanceById("78901234");

		assertEquals(200.0, fromAccountBalance);
		assertEquals(100.0, inAccountBalance);
	}

	@Test
	void transfer_450_from_saving_to_saving_valid() {
		savings.deposit(1500);
		Account saving2 = new Savings(2);
		saving2.deposit(500);
		myBank.addAccount("34567890", saving2);

		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 3"));
		String[] command = commandProcessor.convertCommandToArray("Transfer 25786632 34567890 450");

		transferProcessor.process(command);
		double fromAccountBalance = commandProcessor.formatDouble(myBank.getBalanceById("25786632"));
		double inAccountBalance = commandProcessor.formatDouble(myBank.getBalanceById("34567890"));

		assertEquals(1050.11, fromAccountBalance);
		assertEquals(952.5, inAccountBalance);
	}

}
