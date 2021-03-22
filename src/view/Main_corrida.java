package view;

import java.util.concurrent.Semaphore;

import controller.Corrida;

public class Main_corrida {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int permissao = 5;
		boolean escuderia = true;
		
		Semaphore semaforo = new Semaphore(permissao);
		
		for (int idT = 0; idT < 14 ; idT ++) {
			Thread IDT = new Corrida(idT, escuderia, semaforo );
			 IDT.start();
		}
	} // main
} // fim classe
