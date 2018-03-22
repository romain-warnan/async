package fr.insee.async.sirene.service;

import java.util.concurrent.TimeUnit;

import fr.insee.async.Etablissement;

public class CalculService {

	private static CalculService instance = null;

	private CalculService() { }

	public static CalculService getInstance() {
		if (instance == null) {
			instance = new CalculService();
		}
		return instance;
	}

	public void rapideCalcul() {
		System.out.println("Début du rapide calcul");
		System.out.println("Fin du rapide calcul");
	}

	public void rapideCalcul(Etablissement etablissement) {
		System.out.println(String.format("Début du rapide calcul sur %s", etablissement.getSiren()));
		System.out.println(String.format("Fin du rapide calcul sur %s", etablissement.getSiren()));
	}
	
	public void longCalcul() {
		System.out.println("Début du long calcul");
		try {
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du long calcul");
	}
	
	public void longCalcul(Etablissement etablissement) {
		System.out.println(String.format("Début du long calcul sur %s", etablissement.getSiren()));
		try {
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("Fin du long calcul sur %s", etablissement.getSiren()));
	}
}
