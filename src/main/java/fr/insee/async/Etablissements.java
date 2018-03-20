package fr.insee.async;

import java.util.List;
import static java.util.stream.Collectors.*;

import fr.insee.async.sirene.service.SireneReponse;
import fr.insee.async.sirene.service.SireneReponse.SireneEtab;

public abstract class Etablissements {
	
	private static Etablissement from(SireneEtab etab) {
		return new Etablissement(etab.getSiren(), etab.getNic(), etab.getUniteLegale() == null ? "" : etab.getUniteLegale().getDenomination());
	}
	
	public static List<Etablissement> from(SireneReponse reponse) {
		return reponse.getEtablissements()
			.stream()
			.map(Etablissements::from)
			.collect(toList());
	}
}
