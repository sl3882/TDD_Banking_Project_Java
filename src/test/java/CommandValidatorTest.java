import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	private Account checking;
	private Account saving;
	private Account cd;
	private Bank myBank;

	@BeforeEach
	void setUp() {
		myBank = new Bank();
		commandValidator = new CommandValidator(myBank);
		checking = new Account(0);
		saving = new Account(0.03);
		cd = new Account(0.02);

	}

	@Test
	void valid_command() {
		boolean actual = commandValidator.validate(415);
		assertFalse(actual);
	}

	@Test
	public void transfer_money_from_checking_to_saving_is_valid() {
		myBank.addAccount(checking);
		myBank.addAccount(saving);
		checking.deposit(500);
		int fromAccountID = checking.getId();
		int toAccountID = saving.getId();
		myBank.transferByID(fromAccountID, toAccountID, 415);
		boolean actual = commandValidator.validate(415);
		assertTrue(actual);
	}

	@Test
	void transfer_money_from_account_the_low_balance_is_invalid() {
		Account checking2 = new Account(0);
		myBank.addAccount(checking2);
		checking2.deposit(15);
		boolean actual = commandValidator.validate(15);
		assertTrue(actual);
	}

	@Test
	void transfer_money_from_checking_to_cd_valid() {
		myBank.addAccount(checking);
		myBank.addAccount(cd);
		checking.deposit(1000);
		int fromAccountID = checking.getId();
		int toAccountID = cd.getId();
		myBank.transferByID(fromAccountID, toAccountID, 500);
		boolean actual = commandValidator.validate(500);
		assertTrue(actual);
	}

	@Test
	void create_account_with_negative_apr_invalid() {
		boolean actual = commandValidator.validateAccountApr(-0.01);
		assertFalse(actual);
	}

	@Test
	void create_account_with_apr_greater_than_100_percent_invalid() {
		boolean actual = commandValidator.validateAccountApr(1.01);
		assertFalse(actual);
	}

	@Test
	void withdraw_large_amount_from_account_invalid_due_to_security() {

		myBank.addAccount(checking);
		checking.deposit(20000);
		boolean actual = commandValidator.validateWithdrawalAmount(checking, 150000.00);
		assertFalse(actual);
	}

}
