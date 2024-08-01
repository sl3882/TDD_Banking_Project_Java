import java.util.ArrayList;
import java.util.List;

public class Bank {
	public List<Account> myBank;

	public Bank() {
		myBank = new ArrayList<>();
	}

	public void addAccount(Account account) {
		myBank.add(account);
	}

	public Account retrieving(int index) {
		return myBank.get(index);
	}

	public void depositById(int id, double money) {
		for (Account account : myBank) {
			if (account.getId() == id) {
				account.deposit(money);
			}
		}
	}

	public void withdrawById(int id, double money) {
		for (Account account : myBank) {
			if (account.getId() == id) {
				account.withdraw(money);
			}
		}
	}

	public void transferByID(int fromID, int toID, double money) {
		for (Account fromAccount : myBank) {
			if (fromAccount.getId() == fromID) {
				fromAccount.withdraw(money);
			}
		}

		for (Account toAccount : myBank) {
			if (toAccount.getId() == toID) {
				toAccount.deposit(money);
			}

		}
	}

	public boolean savingAccountBalance415(double balance) {
		for (Account account : myBank) {
			if (account.getBalance() == balance) {
				return true;
			}

		}
		return false;
	}

}