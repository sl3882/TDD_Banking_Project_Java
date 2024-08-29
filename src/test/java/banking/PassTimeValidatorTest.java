package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeValidatorTest {

	CommandProcessor commandProcessor;
	private PassTimeValidator passTimeValidator;

	@BeforeEach
	public void setUp() {

		Bank myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);

		passTimeValidator = new PassTimeValidator(myBank);

	}

	@Test
	void pass_time_with_validCommand() {
		String[] command = commandProcessor.convertCommandToArray("Pass 1");

		boolean actual = passTimeValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void test_pass_time_invalid_command() {
		// Convert invalid command to array format
		String[] command = commandProcessor.convertCommandToArray("past 1");

		boolean actual = passTimeValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void pass_time_with_invalid() {
		String[] command1 = commandProcessor.convertCommandToArray("Pass 0");
		boolean actual = passTimeValidator.validate(command1);
		assertFalse(actual);

		String[] command2 = commandProcessor.convertCommandToArray("Pass 61");
		actual = passTimeValidator.validate(command2);
		assertFalse(actual);
	}

	@Test
	public void passTime_with_month_1_is_valid() {
		String[] command = { "pass", "1" };
		assertTrue(passTimeValidator.validate(command));
	}

	@Test
	public void passTime_with_month_60_is_valid() {
		String[] command = { "pass", "60" };
		assertTrue(passTimeValidator.validate(command));
	}

	@Test
	public void passTime_with_month_0_is_invalid() {
		String[] command = { "pass", "0" };
		assertFalse(passTimeValidator.validate(command));
	}

	@Test
	public void passTime_with_month_61_is_invalid() {
		String[] command = { "pass", "61" };
		assertFalse(passTimeValidator.validate(command));
	}

	@Test
	public void passTime_with_month_30_is_valid() {
		String[] command = { "pass", "30" };
		assertTrue(passTimeValidator.validate(command));
	}

	@Test
	void pass_time_with_boundary_condition_minus_one_is_invalid() {
		String[] command = { "pass", "-1" };
		boolean actual = passTimeValidator.validate(command);
		assertFalse(actual);
	}

}