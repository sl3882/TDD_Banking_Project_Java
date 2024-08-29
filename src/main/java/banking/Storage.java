package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
	private final Bank myBank;
	private final Map<String, List<String>> validCommand = new HashMap<>();
	private final List<String> invalidCommand = new ArrayList<>();

	public Storage(Bank myBank) {
		this.myBank = myBank;
	}

	public Map<String, List<String>> getValidCommand() {
		return validCommand;
	}

	public void storeValidCommand(String command) {
		CommandProcessor commandProcessor = new CommandProcessor(myBank);
		String[] commandArr = commandProcessor.convertCommandToArray(command);

		if (commandArr[0].equalsIgnoreCase("create") || commandArr[0].equalsIgnoreCase("withdraw")
				|| commandArr[0].equalsIgnoreCase("deposit")) {
			storeToMap(validCommand, commandArr[1], command);
		} else if (commandArr[0].equalsIgnoreCase("transfer")) {
			storeToMap(validCommand, commandArr[1], command);
			storeToMap(validCommand, commandArr[2], command);
		}

	}

	public void storeInvalidCommand(String command) {
		invalidCommand.add(command);
	}

	public void storeToMap(Map<String, List<String>> map, String accountID, String command) {
		if (map.get(accountID) != null) {
			map.get(accountID).add(command);
		} else if (map.get(accountID) == null) {
			map.put(accountID, new ArrayList<>());
			map.get(accountID).add(command);
		}
	}

	public List<String> getInvalidCommand() {
		return invalidCommand;
	}

	public List<String> getOutput() {
		List<String> output = new ArrayList<>();
		for (String accountID : myBank.getAccountTransaction()) {
			output.add(getAccountReport(accountID));
			if (validCommand.get(accountID) != null) {
				output.addAll(validCommand.get(accountID));
			}
		}
		output.addAll(invalidCommand);
		return output;
	}

	private String getAccountReport(String accountID) {
		CommandProcessor commandProcessor = new CommandProcessor(myBank);
		Account account = myBank.getMyBank().get(accountID);
		String formattedBalance = commandProcessor.formatString(account.getBalance());
		String formattedAPR = commandProcessor.formatString(account.getApr());
		return account.getAccountType() + " " + accountID + " " + formattedBalance + " " + formattedAPR;
	}
}
