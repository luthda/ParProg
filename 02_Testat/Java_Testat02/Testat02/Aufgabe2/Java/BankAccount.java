package Testat02.Aufgabe2.Java;

import java.util.concurrent.atomic.AtomicInteger;

// quickWithdraw mit atomaren Operationen ca. 3 mal schneller als withdraw mit do-while compareAndSet
public class BankAccount {
	private AtomicInteger balance = new AtomicInteger(0);

	public void deposit(int amount) {
		balance.getAndAdd(amount);
	}

	public boolean quickWithdraw(int amount) {
		if (amount <= balance.get()) {
			balance.getAndAdd(-amount);
			return true;
		} else {
			return false;
		}
	}

	public boolean withdraw(int amount) {
		int newValue = 0;
		int oldValue = 0;
		do {
			oldValue = balance.get();
			if (amount <= oldValue) {
				newValue = oldValue - amount;
			} else {
				return false;
			}
		} while (!balance.compareAndSet(oldValue, newValue));
		return true;
	}

	public int getBalance() {
		return balance.get();
	}
}
