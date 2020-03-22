package Model;

public class Comanda {
	private int idComanda;
	private int idClient;
	private int idProdus;
	private int cantitate;
	private int pretComanda;
	public Comanda(int idComanda, int idClient, int idProdus,int cantitate) {
		super();
		this.idComanda = idComanda;
		this.idClient = idClient;
		this.idProdus = idProdus;
		this.cantitate = cantitate;
	}
	public Comanda() {
		super();
	}
	public int getIdComanda() {
		return idComanda;
	}
	public int getIdClient() {
		return idClient;
	}
	public int getIdProdus() {
		return idProdus;
	}
	public int getPretComanda() {
		return pretComanda;
	}
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
	public void setPretComanda(int pretComanda) {
		this.pretComanda = pretComanda;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	@Override
	public String toString() {
		return "Comanda [idComanda=" + idComanda + ", idClient=" + idClient + ", idProdus=" + idProdus + ", cantitate="
				+ cantitate + ", pretComanda=" + pretComanda + "]";
	}
	
	
	

}
