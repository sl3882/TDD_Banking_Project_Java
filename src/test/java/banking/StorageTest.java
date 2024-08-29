package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {
	Storage storage;
	Bank myBank;

	@BeforeEach
	void setUp() {
		storage = new Storage(myBank);
	}

	@Test
	void add_invalid_command() {

		storage.storeInvalidCommand("creat checking 1535997 1.0");

		List<String> actual = storage.getInvalidCommand();
		assertEquals(1, actual.size());
		assertEquals("creat checking 1535997 1.0", actual.get(0));
	}

	@Test
	void store_valid_transfer_command() {
		storage.storeValidCommand("transfer 1535997 1535998 100");

		Map<String, List<String>> validCommandMap = storage.getValidCommand();
		assertTrue(validCommandMap.containsKey("1535997"));
		assertTrue(validCommandMap.containsKey("1535998"));
		List<String> commandsForAcc1 = validCommandMap.get("1535997");
		List<String> commandsForAcc2 = validCommandMap.get("1535998");
		assertEquals(1, commandsForAcc1.size());
		assertEquals(1, commandsForAcc2.size());
		assertEquals("transfer 1535997 1535998 100", commandsForAcc1.get(0));
		assertEquals("transfer 1535997 1535998 100", commandsForAcc2.get(0));
	}

	@Test
	void getValidCommand_returns_correct_map() {
		storage.storeValidCommand("create checking 1535997 1.0");
		storage.storeValidCommand("transfer 1535997 1535998 100");

		Map<String, List<String>> validCommandMap = storage.getValidCommand();
		assertTrue(validCommandMap.containsKey("1535997"));
		assertTrue(validCommandMap.containsKey("1535998"));
		assertEquals(1, validCommandMap.get("1535997").size());
		assertEquals(1, validCommandMap.get("1535998").size());
	}
}
