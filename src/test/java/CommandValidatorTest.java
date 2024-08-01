import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	private Account checking;
	private Account saving;

	private Bank myBank;

	@BeforeEach
	void setUp() {
		myBank = new Bank();
		commandValidator = new CommandValidator(myBank);
		checking = new Account(0);
		saving = new Account(0.03);

	}

	@Test
	void valid_command() {
		boolean actual = commandValidator.validate("transfer 415 from checking to saving");
		assertTrue(actual);
	}

	@Test
	public void transfer_money_from_checking_to_saving_is_valid() {
		myBank.addAccount(checking);
		myBank.addAccount(saving);
		checking.deposit(500);
		int fromAccountID = checking.getId();
		int toAccountID = saving.getId();

		myBank.transferByID(fromAccountID, toAccountID, 415);

		boolean actual = commandValidator.validate("transfer 415 from checking to saving");
		assertFalse(actual);

	}

}
