package ro.tuc.pt.assig1;

import java.util.*;

public class DivOp implements IOperation {

	public Result compute(Polinom p1, Polinom p2) {
		Polinom s = new Polinom();
		Bipolinom r = new Bipolinom();
		if(p1.getElem().get(0).getCoef()==0) {//daca coeficientul primului polinom este 0=>r=0
			r.setRest(new Polinom());
			r.setRezultat(new Polinom());
			return r;}
		else {while (p1.getElem().get(0).getDegree() >= p2.getElem().get(0).getDegree()) { //impartirea se face cat timp gradullui p1>=gradul lui p2
				Monom m = new Monom(p1.getElem().get(0).getCoef() / p2.getElem().get(0).getCoef(),p1.getElem().get(0).getDegree() - p2.getElem().get(0).getDegree());
				Polinom p = new Polinom(); 
				p.addMonom(m);
				s.addMonom(m); //polinom folosit pt stocarea rezultatului
				IOperation mul = new MulOp();
				p= mul.compute(p, p2).getRezultat();  //se inmulteste p2 cu polinomul p(format din monomul obtinut prin impartirea primelor monoame din p1 si p2)
				IOperation sub = new SubOp();
				p1 = sub.compute(p1, p).getRezultat(); //se scade rezultatul obtinut anterior din p1
				if (p1.getElem().isEmpty()) {//daca rezultatul scaderii este 0 =>restul=0
					List<Monom> l = new ArrayList<Monom>();
					l.add(new Monom(0, 0));
					p1.setElem(l);
				}
				r.setRest(p1);
				r.setRezultat(s);
				if (p1.getElem().get(0).getCoef() == 0 && p1.getElem().size() == 1) { 
					r.setRest(new Polinom());
				}	 
			}
			return r;
		}
	}
}
