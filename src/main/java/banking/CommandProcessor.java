package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CommandProcessor extends Processor {

	private final CreateProcessor createProcessor;
	private final DepositProcessor depositProcessor;
	private final TransferProcessor transferProcessor;
	private final PassTimeProcessor passTimeProcessor;
	private final WithdrawalProcessor withdrawalProcessor;

	public CommandProcessor(Bank myBank) {
		super(myBank);
		createProcessor = new CreateProcessor(myBank);
		depositProcessor = new DepositProcessor(myBank);
		transferProcessor = new TransferProcessor(myBank);
		passTimeProcessor = new PassTimeProcessor(myBank);
		withdrawalProcessor = new WithdrawalProcessor(myBank);

	}

	private String formatNumber(double value) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);
		return decimalFormat.format(value);
	}

	public double formatDouble(double value) {
		return Double.parseDouble(formatNumber(value));
	}

	public String formatString(double value) {

		return formatNumber(value);
	}

	// Converts command string into an array of arguments
	public String[] convertCommandToArray(String commandAsString) {
		return commandAsString.stripTrailing().split(" ");
	}

	public void process(String[] command) {
		switch (command[0].toLowerCase()) {
		case "create":
			createProcessor.process(command);
			break;
		case "withdraw":
			withdrawalProcessor.process(command);
			break;
		case "deposit":
			depositProcessor.process(command);
			break;
		case "transfer":
			transferProcessor.process(command);
			break;
		case "pass":
			passTimeProcessor.process(command);
			break;
		default:
			throw new IllegalArgumentException("Unknown command: " + command[0]);
		}
	}
}
