package banking;

import java.util.List;

public class MasterControl {
	private final CommandValidator commandValidator;
	private final CommandProcessor commandProcessor;
	private final Storage storage;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor, Storage storage) {

		this.commandValidator = commandValidator;
		this.commandProcessor = commandProcessor;
		this.storage = storage;
	}

	public List<String> start(List<String> input) {

		for (String command : input) {
			String[] commandArr = commandProcessor.convertCommandToArray(command);
			if (commandValidator.validate(commandArr)) {
				commandProcessor.process(commandArr);
				storage.storeValidCommand(command);
			} else {
				storage.storeInvalidCommand(command);
			}
		}
		return storage.getOutput();
	}
}
