package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferValidatorTest {
	TransferValidator transferValidator;
	CommandProcessor commandProcessor;
	private Account checking;
	private Account savings;
	private Bank myBank;

	@BeforeEach
	void setUp() {
		myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);

		transferValidator = new TransferValidator(myBank);

		checking = new Checking(0);
		savings = new Savings(0.03);
		Account cd = new Cd(0.02, 3000);
		myBank.addAccount("55691275", checking);
		myBank.addAccount("25786632", savings);
		myBank.addAccount("99326632", cd);
	}

	@Test
	void transfer_200_from_checking_to_saving_valid() {
		checking.deposit(500);
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 55691275 25786632 200"));
		assertTrue(actual);
	}

	@Test
	void transfer_200_from_saving_to_checking_valid() {
		savings.deposit(500);
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 25786632 55691275 200"));
		assertTrue(actual);
	}

	@Test
	void transfer_100_from_checking_to_checking_valid() {
		checking.deposit(300);
		myBank.addAccount("78901234", new Checking(0));
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 55691275 78901234 100"));
		assertTrue(actual);
	}

	@Test
	void transfer_450_from_saving_to_saving_valid() {
		savings.deposit(1500);
		Account savings2 = new Savings(0.04);
		myBank.addAccount("34567890", savings2);
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 25786632 34567890 450"));
		assertTrue(actual);
	}

	@Test
	void transfer_from_cd_to_checking_invalid() {
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 99326632 55691275 1000"));
		assertFalse(actual);
	}

	@Test
	void transfer_from_checking_to_cd_invalid() {
		checking.deposit(200);
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 55691275 99326632 10"));
		assertFalse(actual);
	}

	@Test
	void transfer_from_cd_to_saving_invalid() {
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 99326632 25786632 105"));
		assertFalse(actual);
	}

	@Test
	void transfer_from_saving_to_cd_invalid() {
		savings.deposit(200);
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 25786632 99326632 106"));
		assertFalse(actual);
	}

	@Test
	void transfer_from_cd_to_cd_invalid() {
		Account cd2 = new Cd(0.02, 3000);
		myBank.addAccount("19827364", cd2);
		boolean actual = transferValidator
				.validate(commandProcessor.convertCommandToArray("transfer 99326632 19827364 1000"));
		assertFalse(actual);
	}

}
