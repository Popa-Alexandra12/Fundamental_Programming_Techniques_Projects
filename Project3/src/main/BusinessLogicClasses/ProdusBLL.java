package BusinessLogicClasses;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import DAOClasses.ProdusDAO;
import Model.Clientul;
import Model.Produs;

public class ProdusBLL {
	ProdusDAO produs;

	public ProdusBLL() {
		super();
		produs=new ProdusDAO();
	}

	public ProdusDAO getProdus() {
		return produs;
	}

	public void setProdus(ProdusDAO produs) {
		this.produs = produs;
	}

	public boolean validareDate(Produs p) {
		if (p.getDenumire().length() > 30) {
			System.out.println("Numar caractere prea mare pentru denumire !\n");
			return false;
		}
		return true;
	}
	public void insertNewProdus(Produs p) {
		if (validareDate(p)) {
		if (produs.findById(p.getIdProdus()) != null) {
			System.out.println("Produs existent !");
		} else {
			produs.insertItem(p);
		}
		}
	}
	public void deleteProdus(int id) {
		if (produs.findById(id) == null) {
			System.out.println("Produs inexistent !");
		} else {
			produs.deleteItem(id);
		}
	}
	public void updateProdus(Produs pNou) {

		if (this.getProdus().findById(pNou.getIdProdus()) == null) {
			System.out.println("Produs inexistent !");
		} else {
			String field1 = "", field2 = "", val1 = "", val2 = "";
			Produs pVechi = produs.findById(pNou.getIdProdus());
			if(validareDate(pNou)==true) {
			if (pVechi.getDenumire().equals(pNou.getDenumire()) == false) {
				field1 = "denumire";
				val1 = pNou.getDenumire();
			} else if (pVechi.getCantitate()!=pNou.getCantitate()) {
				field1 = "cantitate";
				val1 = String.valueOf(pNou.getCantitate());
			} else if (pVechi.getPretProdus()!=pNou.getPretProdus()) {
				field1 = "pretProdus";
				val1 = String.valueOf(pNou.getPretProdus());
			}
			field2 = "idProdus";
			val2 = String.valueOf(pVechi.getIdProdus());
			produs.updateItems(field1, field2, val1, val2);
			}
		}
	}
	public Object[][] viewTabel()
	{
		List<Produs> produse=new ArrayList<Produs>();
		Object[][] tabel=new Object[1000][1000];
		produse=produs.listTable();
		int linie=0;
		for(Produs p:produse)
		{	int coloana=0;
			for (Field field : Produs.class.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					tabel[linie][coloana]=field.get(p);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				coloana++;	
			}
			linie++;
		}
		return tabel;
	}
}
