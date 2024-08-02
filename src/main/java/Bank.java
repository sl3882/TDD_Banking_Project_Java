
import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<Integer, Account> accounts;

	public Bank() {
		accounts = new HashMap<>();
	}

	public void addAccount(Account account) {
		accounts.put(account.getId(), account);
	}

	public Account getAccountById(int id) {
		return accounts.get(id);
	}

	public void depositById(int id, double amount) {
		Account account = accounts.get(id);
		if (account != null) {
			account.deposit(amount);
		}
	}

	public void withdrawById(int id, double amount) {
		Account account = accounts.get(id);
		if (account != null) {
			account.withdraw(amount);
		}
	}

	public void transferByID(int fromId, int toId, double amount) {
		Account fromAccount = accounts.get(fromId);
		Account toAccount = accounts.get(toId);
		if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
			fromAccount.withdraw(amount);
			toAccount.deposit(amount);
		}
	}

	public boolean isAccountBalanceEqual(double amount) {
		for (Account account : accounts.values()) {
			if (account.getBalance() == amount) {
				return true;
			}
		}
		return false;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}
}
