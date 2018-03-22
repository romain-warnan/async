package fr.insee.async;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import fr.insee.async.sirene.service.SireneService;

public class ResquestTest {
	
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
}
