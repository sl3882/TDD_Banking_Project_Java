package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateValidatorTest {
	private CommandProcessor commandProcessor;
	private CreateValidator createValidator;

	@BeforeEach
	void setUp() {
		Bank myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);
		createValidator = new CreateValidator(myBank);
	}

	@Test
	void create_checking_with_valid_apr() {
		String[] command = commandProcessor.convertCommandToArray("create checking 50691275 0.0");
		boolean actual = createValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void create_checking_with_negative_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create checking 99874456 -0.05");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_checking_with_nonnumerical_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create checking 99874456 abc");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_checking_apr_over_10_percent_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create checking 97874456 13.0");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_checking_without_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create checking 97874456");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_saving_with_valid_apr() {
		String[] command = commandProcessor.convertCommandToArray("create saving 25786132 0.03");
		boolean actual = createValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void create_saving_with_negative_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create saving 11336578 -0.07");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_saving_with_nonnumerical_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create saving 11336578 abc");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_saving_apr_over_10_percent_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create saving 11336578 14.0");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_saving_without_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create saving 11336578");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_cd_with_valid_apr() {
		String[] command = commandProcessor.convertCommandToArray("create cd 55748613 0.02 3000");
		boolean actual = createValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void create_cd_with_negative_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create cd 66554432 -0.02 3000");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_cd_with_nonnumerical_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create cd 66554432 abc 3000");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_cd_apr_over_10_percent_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create cd 66554432 11.5 3000");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_cd_without_apr_invalid() {
		String[] command = commandProcessor.convertCommandToArray("create cd 66554432");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_cd_initial_balance_over_10000() {
		String[] command = commandProcessor.convertCommandToArray("create cd 55748613 0.02 30000");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_cd_initial_balance_under_1000() {
		String[] command = commandProcessor.convertCommandToArray("create cd 55748613 0.02 100");
		boolean actual = createValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void create_checking_with_apr_exactly_10_valid() {
		String[] command = commandProcessor.convertCommandToArray("create checking 97874456 10.0");
		boolean actual = createValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void create_cd_initial_balance_at_1000_valid() {
		String[] command = commandProcessor.convertCommandToArray("create cd 55748613 0.02 1000");
		boolean actual = createValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void create_cd_initial_balance_at_10000_valid() {
		String[] command = commandProcessor.convertCommandToArray("create cd 55748613 0.02 10000");
		boolean actual = createValidator.validate(command);
		assertTrue(actual);
	}
}
