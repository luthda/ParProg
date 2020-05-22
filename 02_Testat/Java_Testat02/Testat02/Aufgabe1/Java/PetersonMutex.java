package Testat02.Aufgabe1.Java;

/*
* Aufgabe 1a: Die Threads sind nicht synchronisiert.
* Speicherzugriff können von Threads in verschiedener Reihenfolge gesehen werden, darum werden Änderungen nicht oder sehr spät sichtbar.
* Instruktionen können von Threads umgeordnet werden, zusätzlich können Dataraces entstehen, durch unsynchrones lesen/schreiben.
* Als Lösung können die Fields mit dem volatile-Keyword modifiziert werden, um Atomicity, Visibility & Ordering zu garantieren.
* */
public class PetersonMutex {
	private volatile boolean state0 = false;
	private volatile boolean state1 = false;
	private volatile int turn = 0;

	public void thread0Lock() {
		state0 = true;
		turn = 1;
		while (turn == 1 && state1);
	}

	public void thread0Unlock() {
		state0 = false;
	}

	public void thread1Lock() {
		state1 = true;
		turn = 0;
		while (turn == 0 && state0);
	}

	public void thread1Unlock() {
		state1 = false;
	}
}
