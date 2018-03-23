package fr.insee.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import org.junit.Test;

import fr.insee.async.sirene.service.CalculService;
import fr.insee.async.sirene.service.SireneService;

public class CompletableFutureTest {

	private SireneService sireneService = SireneService.getInstance();
	private CalculService calculService = CalculService.getInstance();

	@Test
	public void thenAccept() throws InterruptedException, ExecutionException {
		// Run a task specified by a Supplier object asynchronously
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CompletableFuture.supplyAsync(() -> sireneService.fetchOne(1000), executor)
				.thenAccept(calculService::longCalcul)
				.thenRun(()-> System.out.println("Terminé"));
		calculService.longCalcul();
		//pour attendre la fin du test
		executor.shutdown();//n'accepte pas de nouvelles tâches. à faire à chaque fois
		executor.awaitTermination(1, TimeUnit.MILLISECONDS);
//		future.get();
		// Exécuter la fonction fetchOne
		// Une fois la fontion terminée, exécuter la fonction longCalcul avec en
		// paramètre l'établissement retourné
		// Imprimer "Terminé" dans la console
		// En parallèle de ces opérations, exécuter la fonction longCalcul sans
		// paramètre
	}

	@Test
	public void thenApply() throws InterruptedException, ExecutionException, TimeoutException {
		// Exécuter la fonction fetchFirst
		// Enchainer la méthode fetchNext
		// Récupérer le premier établissement de la deuxième page
		// En parallèle, exécuter la fonction longCalcul sans paramètre
	}

	@Test
	public void allOfTest() throws InterruptedException, ExecutionException, TimeoutException {
		// Exécuter la fonction fetchOne et imprimer le résultat une fois obtenu
		// En parallèle, lancer la fonction longCalcul
	}

	@Test
	public void anyOfTest() throws InterruptedException, ExecutionException, TimeoutException {
		// Exécuter en parallèle 4 fois la fonction fetchOne en faisant varier
		// le paramètre rank.
		// Imprimer le premier établissement à être retourné
	}
}
