package fr.insee.async;

import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.CompletableFuture.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import fr.insee.async.sirene.service.CalculService;
import fr.insee.async.sirene.service.SireneService;

public class CompletableFutureTest {
	
	private SireneService sireneService = SireneService.getInstance();
	private CalculService calculService = CalculService.getInstance();
	
	@Test
	public void thenAccept() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		supplyAsync(() -> sireneService.fetchOne(1500))
			.thenAccept(e -> calculService.longCalcul(e))
			.thenRun(() -> System.out.println("Terminé"));
		executor.shutdown();
		calculService.longCalcul();
		executor.awaitTermination(1, TimeUnit.MINUTES);
	}
	
	@Test
	public void thenApply() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Etablissement> etablissement = supplyAsync(sireneService::fetchFirst)
			.thenApply(sireneService::fetchNext)
			.thenApply(sireneService::fetchNext)
			.thenApply(e -> e.getValues().get(0));
		calculService.longCalcul();
		System.out.println(etablissement.get(1, TimeUnit.MINUTES));
	}
	
	@Test
	public void allOfTest() throws InterruptedException, ExecutionException, TimeoutException {
		allOf(
			supplyAsync(() -> sireneService.fetchOne(100)).thenAccept(System.out::println),
			runAsync(calculService::longCalcul)
		).get(1, TimeUnit.MINUTES);
	}
}
