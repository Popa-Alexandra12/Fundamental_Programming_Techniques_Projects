package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

public class ControllerAdministrator {
	ViewAdministrator viewAdministrator=new ViewAdministrator();
	public Restaurant rest;
	List<MenuItem> listMI;
	MenuItem menuItem;
	
	ControllerAdministrator(ViewAdministrator viewAdministrator,Restaurant rest)
	{
		super();
		this.viewAdministrator=viewAdministrator;
		viewAdministrator.setVisible(true);
		this.rest=rest;
		
		this.viewAdministrator.addOptiuniAdaugareListener(new OptiuniAdaugareListener());
		this.viewAdministrator.addAddProdusBazaBListener(new AddProdusBazaListener());
		this.viewAdministrator.addFinalizareAdaugareBListener(new FinalizareAdaugareListener());
		this.viewAdministrator.addStergereProdusBListener(new StergereProdusListener());
		this.viewAdministrator.addEditareDenumireProdusBListener(new EditareDenumireProdusListener());
		this.viewAdministrator.addEditarePretProdusBListener(new EditarePretProdusListener());
		this.viewAdministrator.addVizualizareProduseBListener(new VizualizareTabelListener());
	}
	class OptiuniAdaugareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id=Integer.parseInt(viewAdministrator.getIdProdusTF().getText());
			String denumire=viewAdministrator.getDenumireProdusTF().getText();
			int pret=Integer.parseInt(viewAdministrator.getPretProdusTF().getText());
			menuItem=new MenuItem(id,denumire,pret);
			switch (viewAdministrator.getOptiuniAdaugare().getSelectedItem().toString()) {
			case "Adaugare produs de baza":
				rest.addNewMenuItem(menuItem);
				rest.serialization(rest);
				break;
			case "Adaugare produs compus":
				listMI=new ArrayList<MenuItem>();
				break;
			}
			}
			
		}
	class AddProdusBazaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id=Integer.parseInt(viewAdministrator.getIdProdusTF().getText());
			int ok=0;
			for(MenuItem iter:rest.getMenu())
			{
				if(id==iter.getIdItem())
				{
					ok=1;
					listMI.add(iter);
					rest.serialization(rest);
				}
			}
			if(ok==0)
				System.out.println("Produs inexistent !!!\n");
		}
		
	}
	class FinalizareAdaugareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MenuItem mi=new CompositeProduct(menuItem.getIdItem(),menuItem.getName(),listMI);
			rest.addNewMenuItem(mi);
			rest.serialization(rest);
		}
	}
	class StergereProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id=Integer.parseInt(viewAdministrator.getIdProdusTF().getText());
			rest.deleteMenuItem(id);
			rest.serialization(rest);
			
		}
		
	}
	class EditareDenumireProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id=Integer.parseInt(viewAdministrator.getIdProdusTF().getText());
			String newName=viewAdministrator.getDenumireProdusTF().getText();
			rest.editNameOfMenuItem(id, newName);
			rest.serialization(rest);
			
		}
		
	}
	class EditarePretProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id=Integer.parseInt(viewAdministrator.getIdProdusTF().getText());
			int newPrice=Integer.parseInt(viewAdministrator.getPretProdusTF().getText());
			rest.editPriceOfMenuItem(id, newPrice);
			rest.serialization(rest);
		}
		
	}
	class VizualizareTabelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[][] rows = new Object[1000][];
			rows = rest.viewTabelMenu();
			viewAdministrator.getModel().setRowCount(0);
			for(int i=0;i<rows.length;i++)
			{
				viewAdministrator.getModel().addRow(rows[i]);
			
			}
			
		}
		
	}
	

}
