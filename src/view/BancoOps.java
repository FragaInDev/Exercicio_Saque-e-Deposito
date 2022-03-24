package view;

import java.util.concurrent.Semaphore;

import controller.Banco;

public class BancoOps {

	public static void main(String[] args) {
		int contas = 20;
		Semaphore smf_saque = new Semaphore(1);
		Semaphore smf_deposito = new Semaphore (1);
		
		for (int i = 1; i <= contas; i++) {
			new Banco(i, smf_saque, smf_deposito).start();

		}
	}
}

