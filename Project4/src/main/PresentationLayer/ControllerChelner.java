package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

public class ControllerChelner {
	private ViewChelner viewChelner= new ViewChelner();
	private List<Integer> idList;
	public Restaurant rest;
	
	public ControllerChelner(ViewChelner viewChelner,Restaurant rest)
	{
		super();
		this.viewChelner=viewChelner;
		viewChelner.setVisible(true);
		this.rest=rest;
		idList=new ArrayList<Integer>();
		
		this.viewChelner.addAddComandaBListener(new AddComandaListener());
		this.viewChelner.addAddProdusBListener(new AddProdusListener());
		this.viewChelner.addGenerareFacturaBListener(new GenerareFacturaListener());
		this.viewChelner.addVizualizareComenziBListener(new VizualizareTabelListener());
	}
	class AddComandaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			int id=Integer.parseInt(viewChelner.getIdComandaTF().getText());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date data = format.parse(viewChelner.getDataComandaTF().getText());
			int masa=Integer.parseInt(viewChelner.getMasaComandaTF().getText());
			Order o=new Order(id, data, masa);
			rest.addNewOrder(o,idList);
			idList=new ArrayList<Integer>();
			rest.serialization(rest);
			rest.notifyAllObservers();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	
	}
		class AddProdusListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(viewChelner.getIdProdusTF().getText());
				idList.add(id);
				rest.serialization(rest);
				
			}
			
		}
		class GenerareFacturaListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id=Integer.parseInt(viewChelner.getIdComandaTF().getText());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					Date data = format.parse(viewChelner.getDataComandaTF().getText());
					int masa=Integer.parseInt(viewChelner.getMasaComandaTF().getText());
					Order o=new Order(id, data, masa);
					rest.generateBillForOrder(o);
					//rest.serialization(rest);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
			}
			
		}
		class VizualizareTabelListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object[][] rows = new Object[1000][];
				rows = rest.viewTabelRestaurant();
				viewChelner.getModel().setRowCount(0);
				for(int i=0;i<rows.length;i++)
				{
					viewChelner.getModel().addRow(rows[i]);
				
				}
				
			}
			
		}
}
