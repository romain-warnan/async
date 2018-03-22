package fr.insee.async;

import java.util.List;
import static java.util.stream.Collectors.*;

import java.util.Iterator;

import fr.insee.async.sirene.service.SireneReponse;
import fr.insee.async.sirene.service.SireneReponse.SireneEtab;

public class Etablissements implements Iterable<Etablissement> {
	
	private String suivants;
	private List<Etablissement> etablissements;
	
	public Etablissements(SireneReponse reponse) {
		this.etablissements = reponse.getEtablissements()
			.stream()
			.map(Etablissements::from)
			.collect(toList());
		this.suivants = reponse.getHeader().getCurseurSuivant();
	}
	
	private static Etablissement from(SireneEtab etab) {
		return new Etablissement(etab.getSiren(), etab.getNic(), etab.getUniteLegale() == null ? "" : etab.getUniteLegale().getDenomination());
	}

	public List<Etablissement> getValues() {
		return etablissements;
	}
	
	public String curseurSuivant() {
		return suivants;
	}
	
	@Override
	public Iterator<Etablissement> iterator() {
		return etablissements.iterator();
	}
}
