//package banking;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class MasterControlTest {
//	private Bank myBank;
//	private MasterControl masterControl;
//	private List<String> input;
//	private CreateValidator createValidator;
//	private DepositValidator depositValidator;
//	private CreateProcessor createProcessor;
//	private DepositProcessor depositProcessor;
//	private TransferValidator transferValidator;
//	private PassTimeValidator passTimeValidator;
//	private PassTimeProcessor passTimeProcessor;
//	private Storage storage;
//	private TransferProcessor transferProcessor;
//	private WithdrawalValidator withdrawalValidator;
//	private WithdrawalProcessor withdrawalProcessor;
//
//	@BeforeEach
//	void setUp() {
//		myBank = new Bank();
//		input = new ArrayList<>();
//		withdrawalValidator = new WithdrawalValidator(myBank, passTimeValidator, passTimeProcessor);
//
//		withdrawalProcessor = new WithdrawalProcessor(myBank);
//		createValidator = new CreateValidator(myBank);
//		depositValidator = new DepositValidator(myBank);
//		createProcessor = new CreateProcessor(myBank);
//		depositProcessor = new DepositProcessor(myBank);
//		transferValidator = new TransferValidator(myBank);
//		transferProcessor = new TransferProcessor(myBank, depositValidator, withdrawalValidator, depositProcessor,
//				withdrawalProcessor);
//		passTimeValidator = new PassTimeValidator(myBank);
//		storage = new Storage(myBank);
//		passTimeProcessor = new PassTimeProcessor(myBank);
//
//		masterControl = new MasterControl(myBank, createValidator, depositValidator, depositProcessor, createProcessor,
//				transferValidator, transferProcessor, storage, passTimeValidator, passTimeProcessor,
//				withdrawalProcessor, withdrawalValidator);
//	}
//
//	private void assertSingleCommand(String command, List<String> actual) {
//		assertEquals(1, actual.size());
//		assertEquals(command, actual.get(0));
//	}
//
//	@Test
//	void typo_in_create_command_is_invalid() {
//
//		input.add("creat checking 12345678 0.1");
//
//		List<String> actual = masterControl.start(input);
//		assertSingleCommand("creat checking 12345678 0.1", actual);
//	}
//
//	@Test
//	void typo_in_deposit_command_is_invalid() {
//
//		input.add("depositt 12345678 0.1");
//
//		List<String> actual = masterControl.start(input);
//		assertSingleCommand("depositt 12345678 0.1", actual);
//	}
//
//	@Test
//	void two_typo_commands_both_invalid() {
//		input.add("creat checking 12345678 0.1");
//		input.add("depositt 12345678 1.0");
//		List<String> actual = masterControl.start(input);
//		assertEquals(2, actual.size());
//		assertEquals("creat checking 12345678 0.1", actual.get(0));
//		assertEquals("depositt 12345678 1.0", actual.get(1));
//	}
//
////	@Test
////	void invalid_to_create_accounts_with_same_ID() {
////		input.add("create checking 56660988 0.1");
////		input.add("create checking 56660988 0.1");
////		List<String> actual = masterControl.start(input);
////		assertSingleCommand("create checking 56660988 0.1", actual);
////
////	}
//
//}

package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MasterControlTest {
	MasterControl masterControl;
	List<String> input;
	Bank myBank;
	CommandProcessor commandProcessor;
	CommandValidator commandValidator;
	Storage storage;

	@BeforeEach
	void setup() {

		input = new ArrayList<>();
		myBank = new Bank();
		commandProcessor = new CommandProcessor(myBank);
		commandValidator = new CommandValidator(myBank);
		storage = new Storage(myBank);
		masterControl = new MasterControl(commandValidator, commandProcessor, storage);

	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void typo_in_create_command_is_invalid() {
		input.add("creat checking 12345678 0.1");
		List<String> actual = masterControl.start(input);
		assertSingleCommand("creat checking 12345678 0.1", actual);
	}

	@Test
	void typo_in_deposit_command_is_invalid() {

		input.add("depositt 12345678 0.1");

		List<String> actual = masterControl.start(input);
		assertSingleCommand("depositt 12345678 0.1", actual);
	}

	@Test
	void two_typo_commands_both_invalid() {
		input.add("creat checking 12345678 0.1");
		input.add("depositt 12345678 1.0");
		List<String> actual = masterControl.start(input);
		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 0.1", actual.get(0));
		assertEquals("depositt 12345678 1.0", actual.get(1));
	}

	@Test
	void invalid_to_create_accounts_with_same_ID() {
		input.add("create checking 56660988 0.1");
		input.add("create checking 56660988 0.1");
		List<String> actual = masterControl.start(input);

		assertEquals("Checking 56660988 0.00 0.10", actual.get(0));
		assertEquals("create checking 56660988 0.1", actual.get(1));
	}

	@Test
	void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 5000");
		input.add("creAte cHecKing 98765432 0.01");
		input.add("Deposit 98765432 300");
		input.add("Transfer 98765432 12345678 300");
		input.add("Pass 1");
		input.add("Create cd 23456789 1.2 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(5, actual.size());
		assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Transfer 98765432 12345678 300", actual.get(2));
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
		assertEquals("Deposit 12345678 5000", actual.get(4));
	}
}
