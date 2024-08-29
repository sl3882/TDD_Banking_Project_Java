package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandValidatorTest {

	private CommandValidator commandValidator;
	private CommandProcessor commandProcessor;

	@BeforeEach
	void setUp() {
		Bank myBank = new Bank();
		commandValidator = new CommandValidator(myBank);
		commandProcessor = new CommandProcessor(myBank);
	}

	@Test
	void create_command_with_valid_input() {
		String[] commandArr = commandProcessor.convertCommandToArray("create checking 56894512 0.1");

		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void create_command_in_uppercase() {
		String[] commandArr = commandProcessor.convertCommandToArray("CREATE checking 56894512 0.1");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void create_command_typo_invalid() {
		String[] commandArr = commandProcessor.convertCommandToArray("creat checking 56894512 0.1");
		assertFalse(commandValidator.validate(commandArr));
	}

	@Test
	void deposit_command_with_valid_input() {
		String[] commandArr = commandProcessor.convertCommandToArray("deposit 56894512 250");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void deposit_command_in_uppercase() {
		String[] commandArr = commandProcessor.convertCommandToArray("DEPOSIT 56894512 500");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void deposit_command_typo_is_invalid() {
		String[] commandArr = commandProcessor.convertCommandToArray("depsoit 56894512 500");
		assertFalse(commandValidator.validate(commandArr));
	}

	@Test
	void withdraw_command_with_valid_input() {
		String[] commandArr = commandProcessor.convertCommandToArray("withdraw 56894512 400");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void withdraw_command_in_uppercase() {
		String[] commandArr = commandProcessor.convertCommandToArray("WITHDRAW 56894512 400");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void withdraw_command_with_typo_is_invalid() {
		String[] commandArr = commandProcessor.convertCommandToArray("withdraer 56894512 500");
		assertFalse(commandValidator.validate(commandArr));
	}

	@Test
	void transfer_command_typo_is_invalid() {
		String[] commandArr = commandProcessor.convertCommandToArray("tansfer 56894512 12345677 300");
		assertFalse(commandValidator.validate(commandArr));
	}

	@Test
	void pass_time_command_with_valid() {
		String[] commandArr = commandProcessor.convertCommandToArray("pass 1");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void pass_time_command_in_uppercase() {
		String[] commandArr = commandProcessor.convertCommandToArray("PASS 1");
		assertTrue(commandValidator.validate(commandArr));
	}

	@Test
	void pass_time_command_typo_invalid() {
		String[] commandArr = commandProcessor.convertCommandToArray("pas 1");
		assertFalse(commandValidator.validate(commandArr));
	}

	@Test
	void transfer_command_with_nonexistent_destination_account_is_invalid() {
		String[] commandArr = commandProcessor.convertCommandToArray("transfer 55691275 99999999 50");
		assertFalse(commandValidator.validate(commandArr));
	}

}