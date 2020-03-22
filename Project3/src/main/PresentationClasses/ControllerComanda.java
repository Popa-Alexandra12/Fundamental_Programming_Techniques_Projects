package PresentationClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BusinessLogicClasses.ClientBLL;
import BusinessLogicClasses.ComandaBLL;
import BusinessLogicClasses.ProdusBLL;
import Model.Comanda;
import Model.Produs;
import PresentationClasses.ControllerProdus.ViewProdusListener;

public class ControllerComanda {
	private ViewComanda viewComanda=new ViewComanda();

	public ControllerComanda(ViewComanda viewComanda) {
		super();
		this.viewComanda = viewComanda;
		viewComanda.setVisible(true);
		
		this.viewComanda.addInsertComandaBListener(new ViewComandaListener());
		this.viewComanda.addStergereComandaBListener(new ViewComandaListener());
		this.viewComanda.addEditareComandaBListener(new ViewComandaListener());
		this.viewComanda.addVizualizareComenziBListener(new VizualizareTabelListener());
	}
	class ViewComandaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int idClient=0,idProdus=0,cantitate=0;
				int idComanda=Integer.parseInt(viewComanda.getIdComandaTF().getText());
				if(viewComanda.getIdCClientTF().getText().equals("")==false)
				{idClient=Integer.parseInt(viewComanda.getIdCClientTF().getText());}
				if(viewComanda.getIdCProdusTF().getText().equals("")==false)
				{idProdus=Integer.parseInt(viewComanda.getIdCProdusTF().getText());}
				if(viewComanda.getCantitateComandaTF().getText().equals("")==false)
				{cantitate=Integer.parseInt(viewComanda.getCantitateComandaTF().getText());}
				Comanda comanda=new Comanda(idComanda,idClient,idProdus,cantitate);
				ComandaBLL c=new ComandaBLL();   
				String buton = e.getActionCommand();
				switch(buton)
		    	{
		    		case "Adaugare Comanda":
		    			c.insertNewComanda(comanda);
		    			break;
		    		case "Eliminare Comanda":
		    			c.deleteComanda(idComanda);
		    			break;
		    		case "Editare Comanda":
		    			c.updateComanda(comanda);
		    			break;	
			}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	class VizualizareTabelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			ComandaBLL c=new ComandaBLL();
			Object[][] rows = new Object[1000][];
			rows = c.viewTabel();
			viewComanda.getModel().setRowCount(0);
			for(int i=0;i<rows.length;i++)
			{
				viewComanda.getModel().addRow(rows[i]);
			
			}
		}
	}
}
