package ro.tuc.pt.assig1;

public class IntegOp implements IOperation{

	public Result compute(Polinom p1, Polinom p2) {
		Result r=new Result();
		p2=new Polinom();
		for(Monom i:p1.getElem())
		{
			p2.addMonom(new Monom(i.getCoef()/(i.getDegree()+1),i.getDegree()+1));  
		}
		p1=p2;
		r.setRezultat(p1);
		return r;
		
	}

}
