package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawalValidatorTest {
	private WithdrawalValidator withdrawalValidator;
	private CommandProcessor commandProcessor;
	private PassTimeProcessor passTimeProcessor;

	@BeforeEach
	void setUp() {

		Bank myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);
		passTimeProcessor = new PassTimeProcessor(myBank);
		PassTimeValidator passTimeValidator = new PassTimeValidator(myBank);
		withdrawalValidator = new WithdrawalValidator(myBank);

	}

	@Test
	void withdraw_300_from_checking_is_valid() {
		String[] command = commandProcessor.convertCommandToArray("Withdraw 55691275 300");

		boolean actual = withdrawalValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void withdraw_500_from_saving_is_valid() {
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 1"));
		String[] command = commandProcessor.convertCommandToArray("Withdraw 25786632 500");

		boolean actual = withdrawalValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void withdraw_whole_amount_from_cd_is_valid() { // current balance is 3000
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 12"));
		String[] command = commandProcessor.convertCommandToArray("Withdraw 99326632 3100");

		boolean actual = withdrawalValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void withdraw_0_from_checking_is_valid() {
		String[] command = commandProcessor.convertCommandToArray("Withdraw 55691275 0");

		boolean actual = withdrawalValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void withdraw_0_from_saving_is_valid() {
		passTimeProcessor.process(commandProcessor.convertCommandToArray("Pass 1"));
		String[] command = commandProcessor.convertCommandToArray("Withdraw 25786632 0");

		boolean actual = withdrawalValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void withdraw_negative_amount_is_invalid() {
		String[] command = commandProcessor.convertCommandToArray("Withdraw 55691275 -300");

		boolean actual = withdrawalValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void invalid_amount_format_is_invalid() {
		String[] command = commandProcessor.convertCommandToArray("Withdraw 55691275 abc");

		boolean actual = withdrawalValidator.validate(command);
		assertFalse(actual);
	}
}