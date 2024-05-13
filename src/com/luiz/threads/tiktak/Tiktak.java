package com.luiz.threads.tiktak;

public class TikTak {

	boolean tique;

	synchronized void tik(boolean estaExecutando) {
		if (!estaExecutando) {
			tique = true;
			notify();
			return;
		}

		System.out.print("Tique ");
		tique = true;
		notify();

		try {
			while (tique) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized void tak(boolean estaExecutando) {
		if (!estaExecutando) {
			tique = false;
			notify();
			return;
		}

		System.out.println("Taque");
		tique = false;
		notify();

		try {
			while (!tique) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}