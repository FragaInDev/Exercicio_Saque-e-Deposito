package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Banco extends Thread{
	private int contas;
	private Semaphore smf_saque;
	private Semaphore smf_deposito;
	private int saldo = new Random().nextInt(100000) + 1;
	
	public Banco (int _contas, Semaphore _smf_saque, Semaphore _smf_deposito) {
		contas = _contas;
		smf_saque = _smf_saque;
		smf_deposito = _smf_deposito;
	}
	
	@Override
	public void run() {
		int transacao = (int) ((Math.random()*2)+1);
		switch (transacao) {
		case 1:
			try {
				smf_saque.acquire();
				saque();
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				smf_saque.release();
			}
		
		case 2:
			try {
				smf_deposito.acquire();
				deposito();
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				smf_deposito.release();
			}
			
		}
	}
	
	public void saque() {
		int valor = new Random().nextInt(100000) + 1;
		if (valor > saldo) {
			System.err.println("============ Conta #" +contas+ "============ \n *Saldo insuficiente* \n Saldo em conta: R$ "+saldo+",00");
		}else {
			saldo -= valor;
			System.out.println("============ Conta #" +contas+ "============ \n *Você sacou R$ "+ valor +",00* \n Saldo em conta: R$ "+saldo+",00");
		}
	}
	
	public void deposito() {
		int valor = new Random().nextInt(100000) + 1;
		saldo += valor;
		System.out.println("============ Conta #" +contas+ "============ \n *Você depositou R$"+ valor +",00* \n Saldo em conta: R$"+saldo+",00");
	}
}
