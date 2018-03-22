package fr.insee.async;

public class Etablissement {

	private String siren, nic, denomination;

	public Etablissement(String siren, String nic, String denomination) {
		super();
		this.siren = siren;
		this.nic = nic;
		this.denomination = denomination;
	}

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

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	@Override
	public String toString() {
		return siren + " " + nic + " " + denomination;
	}
}
