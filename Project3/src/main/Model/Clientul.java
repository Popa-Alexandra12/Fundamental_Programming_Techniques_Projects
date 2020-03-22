package Model;

public class Clientul {
	private int idClient;
	private String nume;
	private String adresa;
	private String email;
	public Clientul(int idClient, String nume, String adresa, String email) {
		super();
		this.idClient = idClient;
		this.nume = nume;
		this.adresa = adresa;
		this.email = email;
	}
	public Clientul() {
		
	}
	public int getIdClient() {
		return idClient;
	}
	public String getNume() {
		return nume;
	}
	public String getAdresa() {
		return adresa;
	}
	public String getEmail() {
		return email;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//@Override
	public String toString() {
		return "Clientul [idClient=" + idClient + ", nume=" + nume + ", adresa=" + adresa + ", email=" + email + "]";
	}
	

}
