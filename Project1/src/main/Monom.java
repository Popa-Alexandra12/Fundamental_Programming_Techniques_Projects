package ro.tuc.pt.assig1;

public class Monom implements Comparable<Monom> {
	private float coef;
	private int degree;

	//constructor clasa Monom
	public Monom(float coef, int degree) {
		super();
		this.coef = coef;
		this.degree = degree;
	}

	//getters
	public float getCoef() {
		return coef;
	}

	public int getDegree() {
		return degree;
	}

	//setters
	public void setCoef(float coef) {
		this.coef = coef;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int compareTo(Monom m) {
		if (this.getDegree() > m.getDegree())
			return -1;
		return 1;

	}

	public String toString() {
		String s ="";
		String coefS = Float.toString(getCoef());
		int coefI = 0;
		coefI = (int) getCoef();
		if (getCoef()-coefI==0.0f) {
			
		if(coefI==0) s = " ";
		else if(coefI>0) s="+"+coefI+"x^"+getDegree();
		else s=coefI+"x^"+getDegree();}
		else {
			if(getCoef()==0) s = " ";
			else if(getCoef()>0) s="+"+getCoef()+"x^"+getDegree();
			else s=getCoef()+"x^"+getDegree();
		}
		
		return s;
	}

}
