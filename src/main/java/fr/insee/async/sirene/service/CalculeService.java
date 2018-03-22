package fr.insee.async.sirene.service;

import java.util.concurrent.TimeUnit;

import fr.insee.async.Etablissement;

public class CalculeService {

	private static CalculeService instance = null;

	private CalculeService() { }

	public static CalculeService getInstance() {
		if (instance == null) {
			instance = new CalculeService();
		}
		return instance;
	}

	public void rapideCalcul() {
		System.out.println("Début du calcul");
		System.out.println("Fin du calcul");
	}

	public void rapideCalcul(Etablissement etablissement) {
		System.out.println("Début du calcul.");
		System.out.println("Fin du calcul");
	}
	
	public void longCalcul() {
		System.out.println("Début du calcul");
		try {
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du calcul");
	}
	
	public void longCalcul(Etablissement etablissement) {
		System.out.println("Début du calcul");
		try {
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du calcul");
	}
}
