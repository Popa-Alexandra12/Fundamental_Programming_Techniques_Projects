package ro.tuc.pt.assig1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddOp implements IOperation {

	public Result compute(Polinom p1, Polinom p2) {
		Polinom p = new Polinom();
		for (Monom i : p1.getElem()){//parcurgerea primului polinom si compararea gradului fiecarui monom din polinomul1 cu gradul fiecarui monom din polinomul2
			int ok = 0;
			for (Monom j : p2.getElem()) {
				if (i.getDegree() == j.getDegree()) {//in caz de egalitate se aduna coeficientii monoamelor,doar daca suma acestora difera de 0 
					if (i.getCoef() + j.getCoef() != 0)p.addMonom(new Monom(i.getCoef() + j.getCoef(), i.getDegree())); 
					ok = 1;
				}
			}
			if (ok == 0) {//daca semaforul ok nu s-a schimbat=>nu s-a gasit nici un monom cu acelasi grad ca al monomului verificat
				p.addMonom(new Monom(i.getCoef(), i.getDegree())); //=> trebuie adaugat in rezultat
			}
		}
		for (Monom j : p2.getElem()) {//parcurgerea celui de al doilea polinom 
			int ok = 0;
			for (Monom i : p1.getElem()) {
				if (i.getDegree() == j.getDegree()) {//compararea gradului fiecarui monom in parte cu monoamele ce alcatuiesc p1
					ok = 1;							//in caz de egalitate se schimba semaforul ok
				}
			}
			if (ok == 0) { //daca semaforul ok nu s-a schimbat=>nu s-a gasit nici un monom cu acelasi grad ca al monomului verificat
				p.addMonom(new Monom(j.getCoef(), j.getDegree())); //=> trebuie adaugat in rezultat
			}
		}
		Collections.sort(p.getElem());  //sortarea monoamelor in polinomul rezultat
		Result r = new Result(p);
		return r;
	}
}
