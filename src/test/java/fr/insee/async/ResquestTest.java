package fr.insee.async;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import fr.insee.async.sirene.service.SireneService;

public class ResquestTest {
	
	private SireneService service = SireneService.getInstance();
	
	
	@Test
	public void quickRequest() {
		List<Etablissement> etablissements = service.quickRequest();
		assertThat(etablissements).hasSize(20);
	}
	
	@Test
	public void averageRequest() {
		List<Etablissement> etablissements = service.averageRequest();
		assertThat(etablissements).hasSize(20);
	}
	
	@Test
	public void slowRequest() {
		List<Etablissement> etablissements = service.slowRequest();
		assertThat(etablissements).hasSize(20);
	}
}
