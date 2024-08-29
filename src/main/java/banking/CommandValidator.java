package banking;

public class CommandValidator extends Validator {

	private final CreateValidator createValidator;
	private final DepositValidator depositValidator;
	private final TransferValidator transferValidator;
	private final WithdrawalValidator withdrawalValidator;
	private final PassTimeValidator passTimeValidator;

	public CommandValidator(Bank myBank) {
		super(myBank);
		PassTimeProcessor passTimeProcessor = new PassTimeProcessor(myBank);
		createValidator = new CreateValidator(myBank);
		depositValidator = new DepositValidator(myBank);
		transferValidator = new TransferValidator(myBank);
		withdrawalValidator = new WithdrawalValidator(myBank);
		passTimeValidator = new PassTimeValidator(myBank);
	}

	public boolean validate(String[] command) {
		switch (command[0].toLowerCase()) {
		case "create":
			return createValidator.validate(command);
		case "deposit":
			return depositValidator.validate(command);
		case "transfer":
			return transferValidator.validate(command);
		case "withdraw":
			return withdrawalValidator.validate(command);
		case "pass":
			return passTimeValidator.validate(command);
		default:
			return false;
		}
	}
}
