package BusinessLogicClasses;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import DAOClasses.ClientDAO;
import DAOClasses.ComandaDAO;
import DAOClasses.ProdusDAO;
import Model.Clientul;
import Model.Comanda;
import Model.Produs;

public class ComandaBLL {
	ComandaDAO comanda;
	ClientDAO client;
	ProdusDAO produs;

	public ComandaBLL() {
		super();
		comanda=new ComandaDAO();
		client=new ClientDAO();
		produs=new ProdusDAO();
	}

	public ComandaDAO getComanda() {
		return comanda;
	}

	public void setComanda(ComandaDAO comanda) {
		this.comanda = comanda;
	}
	public boolean validareDate(Comanda c) {
		if(client.findById(c.getIdClient())==null)
		{
			System.out.println("Client inexistent !\n");
			return false;
		}
		if(produs.findById(c.getIdProdus())==null)
		{
			System.out.println("Produs inexistent !\n");
			return false;
		}
		return true;
	}
	public void insertNewComanda(Comanda c) {
		if(this.validareDate(c)==true)
		{
		if (comanda.findById(c.getIdComanda()) != null) {
			System.out.println("Comanda existenta !\n");
		} else {
			ProdusBLL produsBll=new ProdusBLL(); 
			Produs p=produs.findById(c.getIdProdus());
			if(p.getCantitate()-c.getCantitate()<0)
			{
				System.out.println("Stoc insuficient !\n");
			}
			else {
				c.setPretComanda(p.getPretProdus()*c.getCantitate());
				p.setCantitate(p.getCantitate()-c.getCantitate());
				produsBll.updateProdus(p);
				comanda.insertItem(c);
			}	
		}
		}
		
	}
	public void deleteComanda(int id) {
		if (comanda.findById(id) == null) {
			System.out.println("Comanda inexistenta !");
		} else {
			ProdusBLL produsBll=new ProdusBLL();                          
			Comanda c=comanda.findById(id);
			Produs p=produs.findById(c.getIdProdus());
			p.setCantitate(p.getCantitate()+c.getCantitate());
			produsBll.updateProdus(p);
			comanda.deleteItem(id);   
		}
	}
	public void updatePretComanda(Comanda c,int pretProdus)
	{
		String field1 = "", field2 = "", val1 = "", val2 = "";
		c.setPretComanda(pretProdus*c.getCantitate());
		field1 = "pretComanda";
		val1 = String.valueOf(c.getPretComanda());
		field2 = "idComanda";
		val2 = String.valueOf(c.getIdComanda());
		comanda.updateItems(field1, field2, val1, val2);
	}
	public void updateComanda(Comanda cNoua) {
		if (comanda.findById(cNoua.getIdComanda()) == null) {
			System.out.println("Comanda inexistenta !");
		} else {
			String field1 = "", field2 = "", val1 = "", val2 = "";
			Comanda cVeche = comanda.findById(cNoua.getIdComanda());
			if(validareDate(cNoua)==true) {
				ProdusBLL produsBll=new ProdusBLL();
				Produs p=produs.findById(cNoua.getIdProdus());
			if (cVeche.getIdClient()!=cNoua.getIdClient()) {
				field1 = "idClient";
				val1 = String.valueOf(cNoua.getIdClient());
			} else if (cVeche.getIdProdus()!=cNoua.getIdProdus()) {
				field1 = "idProdus";
				val1 = String.valueOf(cNoua.getIdProdus());
				this.updatePretComanda(cNoua,p.getPretProdus());
			} else if (cVeche.getCantitate()!=cNoua.getCantitate()) {
				if(p.getCantitate()+cVeche.getCantitate()-cNoua.getCantitate()<0)
				{
					System.out.println("Stoc insuficient !\n");
					return;
				}
				else {
					p.setCantitate(p.getCantitate()+cVeche.getCantitate()-cNoua.getCantitate());
					produsBll.updateProdus(p);
					field1 = "cantitate";
					val1 = String.valueOf(cNoua.getCantitate());
					this.updatePretComanda(cNoua,p.getPretProdus());
				}	
			}
			field2 = "idComanda";
			val2 = String.valueOf(cVeche.getIdComanda());
			comanda.updateItems(field1, field2, val1, val2);
			}
		}

	}
	public Object[][] viewTabel()
	{
		List<Comanda> comenzi=new ArrayList<Comanda>();
		Object[][] tabel=new Object[1000][1000];
		comenzi=comanda.listTable();
		int linie=0;
		for(Comanda c:comenzi)
		{	int coloana=0;
			for (Field field : Comanda.class.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					tabel[linie][coloana]=field.get(c);
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
