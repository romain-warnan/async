package fr.insee.async;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.junit.Test;

import fr.insee.async.sirene.service.CalculService;
import fr.insee.async.sirene.service.SireneService;

public class CallableTest {
	
	private SireneService sireneService = SireneService.getInstance();
	private CalculService calculeService = CalculService.getInstance();
	
	@Test
	public void callable() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Etablissement> future = executor.submit(() -> sireneService.fetchOne(1000));
		calculeService.longCalcul();
		Etablissement etablissement = future.get();
		System.out.println(etablissement);
	}
	
	@Test
	public void callableIsDone() throws InterruptedException, ExecutionException {
		// Exécuter la fonction fetchOne()
		// Tant que le résultat n'est pas arivé, imprimer un message chaque 20 millisecondes.
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Etablissement> future = executor.submit(() -> sireneService.fetchOne(1000));
		while(!future.isDone()){
			System.out.println("en attente");
			TimeUnit.MILLISECONDS.sleep(200);
		}
		System.out.println(future.get());
		// Imprimer le résultat de la fonction fetchOne
	}
	
	@Test
	public void invokeAll() throws InterruptedException, ExecutionException {
		// Exécuter en parallèle 5 fois la fonction fetchOne en faisant varier le paramètre rank.
		// Essayer de faire varier la taille du pool de threads
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Integer> rank = Arrays.asList(10000,11000,12000,13000,14000);
		List<Future<Etablissement>> listeFuture = executor.invokeAll(
				rank.stream().map(r -> (Callable<Etablissement>) () -> sireneService.fetchOne(r))
				.collect(Collectors.toList()));
		for (Future<Etablissement> future : listeFuture) {
			System.out.println(future.get());
		}
	
	}
	
	@Test
	public void invokeAny() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Callable<Etablissement>> callables = Arrays.asList(
				() -> sireneService.fetchOne(10),
				() -> sireneService.fetchOne(11000),
				() -> sireneService.fetchOne(45),
				() -> sireneService.fetchOne(13000),
				() -> sireneService.fetchOne(14000));
		Etablissement etb = executor.invokeAny(callables);
		System.out.println(etb);

		
		// Exécuter en parallèle 5 fois la fonction fetchOne en faisant varier le paramètre rank.
		// Imprimer le premier établissement à être retourné
	}
}
