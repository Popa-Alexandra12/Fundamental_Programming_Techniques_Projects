package ro.tuc.pt.assig1;

public class Result  {
	private Polinom rezultat;

	public Result(Polinom rezultat) {
		super();
		this.rezultat = rezultat;
	}
	public Result() {
		super();
	}

	public Polinom getRezultat() {
		return rezultat;
	}

	public void setRezultat(Polinom rezultat) {
		this.rezultat = rezultat;
	}

	

}