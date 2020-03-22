package ro.tuc.pt.assig1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Polinom {
	private List<Monom> elem;

	//constructori clasa Polinom
	public Polinom(List<Monom> elem) {
		super();
		this.elem = elem;
	}
	
	public Polinom(){
	      elem=new ArrayList<Monom>();

	   }
	
	//getter
	public List<Monom> getElem() {
		return elem;
	}

	//setter
	public void setElem(List<Monom> elem) {
		this.elem = elem;
	}
	
	//metoda de adaugare a unui monom in polinom
	public void addMonom(Monom m)
	{
		elem.add(m);
	}
	
	public String toString() {
		
		String s="";
		if(elem.isEmpty() )  s="0";
		else {
		for(Monom i:elem) {
			
			s=s+i.toString();
		}
		if(s.charAt(0)=='+')
			s=s.substring(1,s.length());
		}
		if(s.charAt(0)==' ') s="0";
		return s;
		
	}


	
	
}
