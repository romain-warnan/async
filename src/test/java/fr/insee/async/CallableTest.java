package fr.insee.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import fr.insee.async.sirene.service.CalculService;
import fr.insee.async.sirene.service.SireneService;

public class CallableTest {
	
	private SireneService sireneService = SireneService.getInstance();
	private CalculService calculeService = CalculService.getInstance();
	
	@Test
	public void callable() throws InterruptedException, ExecutionException {
		// Exécuter la fonction fetchOne()
		// Lancer la fonction longCalcul() sans attendre le résultat de la fonction fetchOne.
		// Imprimer le résultat de la fonction fetchOne
	}
	
	@Test
	public void callableIsDone() throws InterruptedException, ExecutionException {
		// Exécuter la fonction fetchOne()
		// Tant que le résultat n'est pas arivé, imprimer un message chaque 20 millisecondes.
		// Imprimer le résultat de la fonction fetchOne
	}
	
	@Test
	public void invokeAll() throws InterruptedException, ExecutionException {
		// Exécuter en parallèle 5 fois la fonction fetchOne en faisant varier le paramètre rank.
		// Essayer de faire varier la taille du pool de threads
	}
	
	@Test
	public void invokeAny() throws InterruptedException, ExecutionException {
		// Exécuter en parallèle 5 fois la fonction fetchOne en faisant varier le paramètre rank.
		// Imprimer le premier établissement à être retourné
	}
}
