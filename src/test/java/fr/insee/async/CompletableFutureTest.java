package fr.insee.async;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.anyOf;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.assertj.core.util.Objects;
import org.junit.Test;

import fr.insee.async.sirene.service.CalculService;
import fr.insee.async.sirene.service.SireneService;

public class CompletableFutureTest {
	
	private SireneService sireneService = SireneService.getInstance();
	private CalculService calculService = CalculService.getInstance();
	
	@Test
	public void thenAccept() throws InterruptedException, ExecutionException {
		// Exécuter la fonction fetchOne
		// Une fois la fontion terminée, exécuter la fonction longCalcul avec en paramètre l'établissement retourné
		// Imprimer "Terminé" dans la console
		// En parallèle de ces opérations, exécuter la fonction longCalcul sans paramètre
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
		// Exécuter en parallèle 4 fois la fonction fetchOne en faisant varier le paramètre rank.
		// Imprimer le premier établissement à être retourné
	}
}
