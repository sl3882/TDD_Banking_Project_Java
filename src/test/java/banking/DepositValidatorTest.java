package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositValidatorTest {
	DepositValidator depositValidator;
	CommandProcessor commandProcessor;
	private Account checking;
	private Bank myBank;

	@BeforeEach
	void setUp() {
		myBank = new Bank();
		depositValidator = new DepositValidator(myBank);
		commandProcessor = new CommandProcessor(myBank);
		checking = new Checking(0);
		Account savings = new Savings(0.03);
		Account cd = new Cd(0.02, 3000);
		myBank.addAccount("55691275", checking);
		myBank.addAccount("25786632", savings);
		myBank.addAccount("99326632", cd);
	}

	@Test
	void deposit_large_amount_to_checking_account_invalid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 10000000");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_500_to_checking_account_valid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 500");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_500_to_savings_account_valid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 25786632 500");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_amount_to_checking_account_invalid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 10000000");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_checking_small_amount_multiple_times_valid() {
		for (int i = 0; i < 3; i++) {
			checking.deposit(0.02);
		}
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 0.06");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_large_amount_to_savings_invalid() {
		Account saving2 = new Savings(0.05);
		myBank.addAccount("36985236", saving2);
		saving2.deposit(200000000);
		String[] command = commandProcessor.convertCommandToArray("deposit 36985236 200000000");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_savings_small_amount_multiple_times_valid() {
		Account saving3 = new Savings(0.05);
		myBank.addAccount("36912236", saving3);
		for (int i = 0; i < 3; i++) {
			saving3.deposit(0.01);
		}
		String[] command = commandProcessor.convertCommandToArray("deposit 36912236 0.03");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_cd_invalid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 99326632 200003000");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_checking_non_digit_amount_invalid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 two-hundred");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_saving_non_digit_amount_invalid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 25786632 two-hundred");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_cd_non_digit_amount_invalid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 99326632 two-hundred");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_checking_invalid_amount_special_characters() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 200!");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_saving_invalid_amount_special_characters() {
		String[] command = commandProcessor.convertCommandToArray("deposit 25786632 200!");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_to_cd_invalid_amount_special_characters() {
		String[] command = commandProcessor.convertCommandToArray("deposit 99326632 200!");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_checking_invalid_amount_negative_value() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 -100");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_checking_amount_zero_value_valid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 55691275 0");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_saving_invalid_amount_negative_value() {
		String[] command = commandProcessor.convertCommandToArray("deposit 25786632 -100");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_saving_amount_zero_value_valid() {
		String[] command = commandProcessor.convertCommandToArray("deposit 25786632 0");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_cd_invalid_amount_negative_value() {
		String[] command = commandProcessor.convertCommandToArray("deposit 99326632 -100");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_cd_invalid_amount_zero_value() {
		String[] command = commandProcessor.convertCommandToArray("deposit 99326632 0");
		boolean actual = depositValidator.validate(command);
		assertFalse(actual);
	}

	@Test
	void deposit_at_boundary_amount_to_savings_valid() {

		Account savings = new Savings(0.03);
		myBank.addAccount("12345678", savings);
		String[] command = commandProcessor.convertCommandToArray("deposit 12345678 2500");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

	@Test
	void deposit_just_below_boundary_amount_to_savings_valid() {

		Account savings = new Savings(0.03);
		myBank.addAccount("12345679", savings);

		String[] command = commandProcessor.convertCommandToArray("deposit 12345679 2499.99");
		boolean actual = depositValidator.validate(command);
		assertTrue(actual);
	}

}