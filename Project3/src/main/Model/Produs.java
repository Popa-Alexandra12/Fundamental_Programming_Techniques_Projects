package Model;

public class Produs {
	
	private int idProdus;
	private String denumire;
	private int cantitate;
	private int pretProdus;
	public Produs(int idProdus, String denumire, int cantitate, int pretProdus) {
		super();
		this.idProdus = idProdus;
		this.denumire = denumire;
		this.cantitate = cantitate;
		this.pretProdus = pretProdus;
	}
	public Produs() {
		super();
		
	}
	public int getIdProdus() {
		return idProdus;
	}
	public String getDenumire() {
		return denumire;
	}
	public int getCantitate() {
		return cantitate;
	}
	public int getPretProdus() {
		return pretProdus;
	}
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public void setPretProdus(int pretProdus) {
		this.pretProdus = pretProdus;
	}
	@Override
	public String toString() {
		return "Produs [idProdus=" + idProdus + ", denumire=" + denumire + ", cantitate=" + cantitate + ", pretProdus="
				+ pretProdus + "]";
	}
	

}
