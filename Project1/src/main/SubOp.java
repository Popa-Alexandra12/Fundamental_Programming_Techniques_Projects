package ro.tuc.pt.assig1;

import java.util.*;


public class SubOp implements IOperation  {

	public Result compute(Polinom p1, Polinom p2) {
		Result r=new Result();
		for(Monom i:p2.getElem()) {   //inmultirea coeficientului fiecarui monom din p2 cu -1
			i.setCoef(i.getCoef()*(-1));
		}
		IOperation add=new AddOp(); 
		r=add.compute(p1,p2); //se face adunarea cu (-1)*p2
		return r;
	
	}

}
