package PresentationClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BusinessLogicClasses.ClientBLL;
import BusinessLogicClasses.ProdusBLL;
import Model.Clientul;
import Model.Produs;
import PresentationClasses.ControllerClient.ViewClientListener;

public class ControllerProdus {
	private ViewProdus viewProdus=new ViewProdus();

	public ControllerProdus(ViewProdus viewProdus) {
		super();
		this.viewProdus = viewProdus;
		viewProdus.setVisible(true);
		
		this.viewProdus.addInsertProdusBListener(new ViewProdusListener());
		this.viewProdus.addStergereProdusBListener(new ViewProdusListener());
		this.viewProdus.addEditareProdusBListener(new ViewProdusListener());
		this.viewProdus.addVizualizareProduseBListener(new VizualizareTabelListener());
	}
	class ViewProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			try {
				int id=Integer.parseInt(viewProdus.getIdProdusTF().getText());
				String denumire=viewProdus.getDenumireProdusTF().getText();
				int cantitate=0,pret=0;
				if(!viewProdus.getCantitateProdusTF().getText().equals("")) 
				{cantitate=Integer.parseInt(viewProdus.getCantitateProdusTF().getText());}
				if(!viewProdus.getPretProdusTF().getText().equals("")) 
				{pret=Integer.parseInt(viewProdus.getPretProdusTF().getText());}
				Produs produs=new Produs(id,denumire,cantitate,pret);
				ProdusBLL p=new ProdusBLL();   
				String buton = e.getActionCommand();
				switch(buton)
		    	{
		    		case "Adaugare Produs":
		    			p.insertNewProdus(produs);
		    			break;
		    		case "Eliminare Produs":
		    			System.out.println("Sterge odata!!");
		    			p.deleteProdus(id);
		    			break;
		    		case "Editare Produs":
		    			p.updateProdus(produs);
		    			break;
			}
			} catch (Exception e2) {
				e2.fillInStackTrace();
				// TODO: handle exception
			}
		}
}
	class VizualizareTabelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			ProdusBLL p=new ProdusBLL();
			Object[][] rows = new Object[1000][];
			rows = p.viewTabel();
			viewProdus.getModel().setRowCount(0);
			for(int i=0;i<rows.length;i++)
			{
				viewProdus.getModel().addRow(rows[i]);
			
			}
			
			
		}
	
	}
	
	
}
