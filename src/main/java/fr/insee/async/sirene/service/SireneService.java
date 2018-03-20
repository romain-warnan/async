package fr.insee.async.sirene.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import fr.insee.async.Etablissement;
import fr.insee.async.Etablissements;

//https://qfapisirenlht02.ad.insee.intra/apisirene-web/ws/siret?debut=x 
//https://qfapisirenlht02.ad.insee.intra/apisirene-web/ws/siret?curseur=*
//https://qfapisirenlht02.ad.insee.intra/apisirene-web/ws/siret?champs=Siren,Nic,Denomination&q=Denomination:*
public class SireneService {

	private Client client;

	private static SireneService instance = null;

	private SireneService() {
		this.client = SireneClient.noSslClient();
	}

	public static SireneService getInstance() {
		if (instance == null) {
			instance = new SireneService();
		}
		return instance;
	}

	private List<Etablissement> etablissements(int debut) {
		SireneReponse reponse = client.target("https://qfapisirenlht02.ad.insee.intra")
			.path("apisirene-web/ws/siret")
			.queryParam("champs", "Siren,Nic,Denomination")
			.queryParam("q", "Denomination:*")
			.queryParam("debut", debut)
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(SireneReponse.class);
		return Etablissements.from(reponse);
	}
	
	public List<Etablissement> quickRequest() {
		return etablissements(0);
	}
	
	public List<Etablissement> averageRequest() {
		return etablissements(5_000);
	}
	
	public List<Etablissement> slowRequest() {
		return etablissements(50_000);
	}
}
