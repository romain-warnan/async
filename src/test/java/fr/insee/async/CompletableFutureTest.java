package fr.insee.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import fr.insee.async.sirene.service.CalculService;
import fr.insee.async.sirene.service.SireneService;

public class CompletableFutureTest {
	
	private SireneService sireneService = SireneService.getInstance();
	private CalculService calculService = CalculService.getInstance();
	
	@Test
	public void completableFutureTest() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CompletableFuture
			.supplyAsync(() -> sireneService.fetchOne(1500), executor)
			.thenAccept(e -> calculService.longCalcul(e))
			.thenRun(() -> System.out.println("Terminé"));
		executor.shutdown();
		calculService.longCalcul();
		executor.awaitTermination(1, TimeUnit.MINUTES);
	}
}
