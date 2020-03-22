package ro.tuc.pt.assig1;

public class DerivOp implements IOperation{

	public Result compute(Polinom p1, Polinom p2) {
		Result r=new Result();
		p2=new Polinom();
		if(p1.getElem().get(0).getDegree()!=0){
			for(Monom i:p1.getElem()) {
				Monom m=new Monom(i.getCoef()*i.getDegree(),i.getDegree()-1);//se face direct 0,puterea ramane-1 dar nu se afiseaza pt ca are coef 0
				p2.addMonom(m);
			}
		}
		p1=p2;
		r.setRezultat(p1);
		return r;
	}
	
}
