package controller;

import java.util.concurrent.Semaphore;

public class Corrida extends Thread{
	private int idThread;
	private int voltas;
//	private boolean escuderia;
	private Semaphore semaforo;
	private int [] libPista = new int [5];
	private static int classificacao [][] = new int [14][2];
	
	public Corrida(int idThread, boolean escuderia, Semaphore semaforo) {
		this.idThread = idThread;
//		this.escuderia = escuderia;
		this.semaforo = semaforo;
	} // fim corrida
	
	@Override
	public void run() {
		try {
			semaforo.acquire();
			liberacaopista();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		try {
			semaforo.acquire();
			pista ();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		classificacao();
	} // fim run
	
	public void liberacaopista() {
		for (int i = 0; i < 5 ; i++) {
			if (idThread == libPista[i]) {
			} // fim if
			else {
				pista();
			} // fim else
		} // fim for
	} // fim classe
	
	public void  pista() {
		int menortempo = 0;
		int tempo = 0;
		int volta = 0;
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				libPista[i] = idThread;
			} 			// fim if
		} 				//fim for
		
		while (volta <= 3) {
		tempo = (int)(Math.random()* 80 + 40);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		volta +=1;
		if (volta == 1) {
			menortempo = tempo;
		}			// fim if
		else {
			if (tempo < menortempo) {
				menortempo = tempo;
			}		// fim if
		}			// fim else		
		}	// while
		
//		classificacao();
		for (int i = 0 ; i < 5 ; i++) {
			if (i == idThread) {
				libPista[i] = 0;
			}		// fim if
		} 			// fim for 
		for (int i = 0 ; i < 14; i++) {
			if (i == 0) {
				classificacao[i][0] = menortempo;
				classificacao[i][1] = idThread;
			}
		}			// fim for
	} 				// fim pista 
	
	public void classificacao() {
		for (int i = 0 ; i < 14 ; i++) {
			int menor = i;				// menor tempo
			for (int j = i+1; j < 14; j ++) {
				if (classificacao[j][0] < classificacao[menor][0]) {
					menor = j;
				}		// fim if
			}			// fim for
			int temp = classificacao[menor][0];
			classificacao[menor][0] = classificacao[i][0];
			classificacao[menor][1] = classificacao[i][1];

			classificacao[i][0] = temp;
		} 				// fim for 
		
		for (int i =0 ; i < 14; i++) {
 			System.out.println("Classificacao final carro " + classificacao[i][1] + " tempo " + classificacao[i][0] );
 		}				// fim for 	
	} // fim metodo
}  // fim classe
