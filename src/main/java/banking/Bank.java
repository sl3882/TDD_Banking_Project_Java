package banking;

import java.util.*;

public class Bank {
	private final Map<String, Account> myBank;
	private final List<String> accountTransaction = new ArrayList<>();

	public Bank() {
		myBank = new HashMap<>();
	}

	public List<String> getAccountTransaction() {
		return accountTransaction;
	}

	public void addAccount(String accountNumber, Account account) {

		myBank.put(accountNumber, account);
		accountTransaction.add(accountNumber);
	}

	public Account getAccountById(String accountNumber) {

		return myBank.get(accountNumber);
	}

	Map<String, Account> getMyBank() {
		return myBank;
	}

	public double getBalanceById(String accountNumber) {

		Account account = myBank.get(accountNumber);
		return account.getBalance();
	}

	public void depositById(String accountNum, double amount) {
		Account account = myBank.get(accountNum);
		if (account != null) {
			account.deposit(amount);
		}
	}

	public void withdrawById(String accountNumber, double amount) {
		Account account = myBank.get(accountNumber);
		if (account != null) {
			account.withdraw(amount);
		}
	}

	public void transferByID(String fromNumber, String toNumber, double amount) {
		Account fromAccount = myBank.get(fromNumber);
		Account toAccount = myBank.get(toNumber);
		if (fromAccount != null && toAccount != null) {
			double availableAmount = Math.min(fromAccount.getBalance(), amount);
			fromAccount.withdraw(availableAmount);
			toAccount.deposit(availableAmount);
		}
	}

	public boolean isAccountBalanceEqual(double amount) {
		for (Account account : myBank.values()) {
			if (account.getBalance() == amount) {
				return true;
			}
		}
		return false;

	}

	public int getNumberOfAccounts() {
		return myBank.size();
	}

	public void removeAccountsWithZeroBalance() {
		Iterator<Map.Entry<String, Account>> iterator = myBank.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Account> entry = iterator.next();
			Account account = entry.getValue();
			String accountNumber = entry.getKey();
			if (account.getBalance() == 0) {
				iterator.remove();
				accountTransaction.remove(accountNumber);
			}
		}
	}

	public boolean doesAccountExist(String accountID) {

		return myBank.get(accountID) != null;
	}

	public void calculateInterest() {
		for (Account account : myBank.values()) {
			double apr = account.getApr();
			double monthlyRate = apr / 100.0 / 12.0;
			double interest = account.getBalance() * monthlyRate;
			if (account instanceof Checking || account instanceof Savings) {
				account.deposit(interest);
			}
			if (account instanceof Cd) {
				double interestCd = interest * 4.0;
				account.deposit(interestCd);
			}
		}
	}

	public void deductFeeForBalanceUnder25() {
		for (Account account : myBank.values()) {
			double balance = account.getBalance();
			if (balance < 100.0) {
				account.withdraw(25);
			}

		}
	}

}