package fr.insee.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import fr.insee.async.sirene.service.CalculeService;
import fr.insee.async.sirene.service.SireneService;

public class CallableTest {
	
	private SireneService sireneService = SireneService.getInstance();
	private CalculeService calculeService = CalculeService.getInstance();
	
	@Test
	public void callableTest() throws InterruptedException, ExecutionException {
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
	public void callableIsDoneTest() throws InterruptedException, ExecutionException {
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
}
