package Testat02.Aufgabe3;

import java.util.concurrent.atomic.AtomicReference;

// Vorlage: ca. 2500ms
// Thread.yield(): ca. 8700 ms -> 3.5 mal langsamer (pro Thread.yield verdoppelt)
// Thread.onSpinWait(): ca. 2300 ms -> ca 10% schneller
// getAndUpdate/updateAndGet: ca. 2500 ms -> Lösung ist gleich schnell, da die Methode eigentlich als do-while mit compareAndSet implementiert ist.
// Es gibt keine Lambda im Prozessor
public class LockFreeStack<T> {
	private AtomicReference<Node<T>> top = new AtomicReference<>();

	public void push(T value) {
		top.updateAndGet(x -> x = new Node<>(value, x));
	}

	public T pop() {
		var newTop = top.getAndUpdate(x -> (x == null) ? null : x.getNext());
		if (newTop == null) return null;
		return newTop.getValue();
	}
}
