package fr.insee.async.sirene.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SireneReponse {

	@JsonProperty("Header")
	private SireneHeader header;
	
	@JsonProperty("Etablissements")
	private List<SireneEtab> etablissements;
	
	public SireneHeader getHeader() {
		return header;
	}

	public void setHeader(SireneHeader header) {
		this.header = header;
	}

	public List<SireneEtab> getEtablissements() {
		return etablissements;
	}

	public void setEtablissements(List<SireneEtab> etablissements) {
		this.etablissements = etablissements;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SireneHeader {

		@JsonProperty("Statut")
		private Integer statut;
		
		@JsonProperty("Message")
		private String message;
		
		@JsonProperty("CurseurSuivant")
		private String curseurSuivant;
		
		public Integer getStatut() {
			return statut;
		}
		
		public void setStatut(Integer statut) {
			this.statut = statut;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getCurseurSuivant() {
			return curseurSuivant;
		}
		
		public void setCurseurSuivant(String curseurSuivant) {
			this.curseurSuivant = curseurSuivant;
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SireneEtab {
		
		@JsonProperty("Siren")
		private String siren;
		
		@JsonProperty("Nic")
		private String nic;
		
		@JsonProperty("UniteLegale")
		private SireneUniteLegale uniteLegale;

		public String getSiren() {
			return siren;
		}

		public void setSiren(String siren) {
			this.siren = siren;
		}

		public String getNic() {
			return nic;
		}

		public void setNic(String nic) {
			this.nic = nic;
		}
		
		public SireneUniteLegale getUniteLegale() {
			return uniteLegale;
		}

		public void setUniteLegaLe(SireneUniteLegale uniteLegale) {
			this.uniteLegale = uniteLegale;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class SireneUniteLegale {
			
			@JsonProperty("Denomination")
			private String denomination;

			public String getDenomination() {
				return denomination;
			}

			public void setDenomination(String denomination) {
				this.denomination = denomination;
			}
		}
	}
}
