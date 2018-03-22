package fr.insee.async;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import fr.insee.async.sirene.service.SireneService;

public class RequestTest {
	
	private SireneService service = SireneService.getInstance();
	
	@Test
	public void quickRequest() {
		Etablissements etablissements = service.quickRequest();
		assertThat(etablissements).hasSize(20);
	}
	
	@Test
	public void averageRequest() {
		Etablissements etablissements = service.averageRequest();
		assertThat(etablissements).hasSize(20);
	}
	
	@Test
	public void slowRequest() {
		Etablissements etablissements = service.slowRequest();
		assertThat(etablissements).hasSize(20);
	}
	
	@Test
	public void fetchOne() {
		Etablissement etablissement = service.fetchOne(12345);
		assertThat(etablissement).isNotNull();
	}
	
	@Test
	public void fetchFirst() {
		Etablissements etablissements = service.fetchFirst();
		assertThat(etablissements).hasSize(20);
	}
	
	
	@Test
	public void fetchNext() {
		Etablissements etablissements = service.fetchFirst();
		etablissements = service.fetchNext(etablissements);
		assertThat(etablissements).hasSize(20);
	}
}
