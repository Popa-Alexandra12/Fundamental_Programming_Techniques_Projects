package ro.tuc.pt.assig1;

import java.util.ArrayList;
import java.util.List;

public class MulOp implements IOperation {

	public Result compute(Polinom p1, Polinom p2) {
		Result r = new Result();
		Polinom q = new Polinom();

		IOperation add = new AddOp();
		for (Monom i : p1.getElem()) {//inmultirea coeficientului fiecarui monom din p1 cu coeficientul fiecarui monom din p2 si adunarea gradelor lor 
			Polinom p = new Polinom();
			for (Monom j : p2.getElem()) {
				Monom monom = new Monom(i.getCoef() * j.getCoef(), i.getDegree() + j.getDegree());
				p.addMonom(monom);
			}
			r = add.compute(p, q);//adunarea rezultatelor partiale dupa inmultirea fiecarui monom din p1 cu monoamele din p2

			q = r.getRezultat();

		}
		return r;

	}

}
