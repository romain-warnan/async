package fr.insee.async.sirene.service;

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

	public Etablissement fetchOne(int rank) {
		SireneReponse reponse = client.target("https://qfapisirenlht02.ad.insee.intra")
			.path("apisirene-web/ws/siret")
			.queryParam("champs", "Siren,Nic,Denomination")
			.queryParam("q", "Denomination:* AND Siren:*")
			.queryParam("debut", rank)
			.queryParam("nombre", 1)
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(SireneReponse.class);
		Etablissements etablissements = new Etablissements(reponse);
		if(etablissements.iterator().hasNext()) {
			return etablissements.getValues().get(0);
		}
		return null;
	}
	
	public Etablissements fetchFrom(int start) {
		SireneReponse reponse = client.target("https://qfapisirenlht02.ad.insee.intra")
			.path("apisirene-web/ws/siret")
			.queryParam("champs", "Siren,Nic,Denomination")
			.queryParam("q", "Denomination:*")
			.queryParam("debut", start)
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(SireneReponse.class);
		return new Etablissements(reponse);
	}
	
	public Etablissements fetchFirst() {
		SireneReponse reponse = client.target("https://qfapisirenlht02.ad.insee.intra")
			.path("apisirene-web/ws/siret")
			.queryParam("champs", "Siren,Nic,Denomination")
			.queryParam("q", "Denomination:*")
			.queryParam("curseur", "*")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(SireneReponse.class);
		return new Etablissements(reponse);
	}
	
	public Etablissements fetchNext(Etablissements previous) {
		SireneReponse reponse = client.target("https://qfapisirenlht02.ad.insee.intra")
			.path("apisirene-web/ws/siret")
			.queryParam("champs", "Siren,Nic,Denomination")
			.queryParam("q", "Denomination:*")
			.queryParam("curseur", previous.curseurSuivant())
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(SireneReponse.class);
		return new Etablissements(reponse);
	}
	
	public Etablissements quickRequest() {
		return fetchFrom(0);
	}
	
	public Etablissements averageRequest() {
		return fetchFrom(5_000);
	}
	
	public Etablissements slowRequest() {
		return fetchFrom(50_000);
	}
}
