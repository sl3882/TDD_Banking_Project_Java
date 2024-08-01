import java.util.ArrayList;
import java.util.List;

public class Bank {
	public List<Account> list;

	public Bank() {
		list = new ArrayList<>();
	}

	public void addAccount(Account account) {
		list.add(account);
	}

	public Account retrieving(int index) {
		return list.get(index);
	}

	public void depositById(int id, double money) {
		for (Account account : list) {
			if (account.getId() == id) {
				account.deposit(money);
			}
		}
	}

	public void withdrawById(int id, double money) {
		for (Account account : list) {
			if (account.getId() == id) {
				account.withdraw(money);
			}
		}
	}
}