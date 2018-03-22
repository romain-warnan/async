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
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		System.out.println("Soumission du callable");
		Future<Etablissement> future = executorService.submit(() -> sireneService.fetchOne(1000));
		executorService.shutdown();
		
		System.out.println("Exécution d'un calcul");
		calculeService.longCalcul();
		
		System.out.println("Récupération du contenu du future");
		Etablissement etablissement = future.get();
		System.out.println(etablissement);
	}
	
	@Test
	public void callableIsDone() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		System.out.println("Soumission du callable");
		Future<Etablissement> future = executorService.submit(() -> sireneService.fetchOne(1000));
		executorService.shutdown();
		
		while(!future.isDone()) {
			System.out.println("Requête en cours...");
			TimeUnit.MILLISECONDS.sleep(200);
		}
		
		System.out.println("Récupération du contenu du future");
		Etablissement etablissement = future.get();
		System.out.println(etablissement);
	}
	
	@Test
	public void callableThenApply() throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		System.out.println("Soumission du callable");
		Future<Etablissement> future = executorService.submit(() -> sireneService.fetchOne(1500));
		executorService.shutdown();
		calculeService.longCalcul();
		calculeService.longCalcul(future.get(1, TimeUnit.MINUTES));
		System.out.println("Terminé");
	}
	
	@Test
	public void invokeAll() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		System.out.println("Soumission des callables");
		List<Future<Etablissement>> futures = executorService.invokeAll(Arrays.asList(
			() -> sireneService.fetchOne(0),
			() -> sireneService.fetchOne(100),
			() -> sireneService.fetchOne(10000),
			() -> sireneService.fetchOne(10001),
			() -> sireneService.fetchOne(10002)
		));
		executorService.shutdown();
		
		System.out.println("Récupération du contenu des futures");
		for (Future<Etablissement> future : futures) {
			System.out.println(future.get());
		}
	}
	
	@Test
	public void invokeAny() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		System.out.println("Soumission des callables");
		Etablissement etablissement = executorService.invokeAny(Arrays.asList(
			() -> sireneService.fetchOne(0),
			() -> sireneService.fetchOne(100),
			() -> sireneService.fetchOne(10000),
			() -> sireneService.fetchOne(10001),
			() -> sireneService.fetchOne(10002)
		));
		executorService.shutdown();
		System.out.println(etablissement);
	}
}
