package PresentationClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ControllerPrincipal {
	private ViewPrincipal viewPrincipal = new ViewPrincipal();
	private String optiune;

	public ControllerPrincipal(ViewPrincipal viewPrincipal) {
		super();
		this.viewPrincipal = viewPrincipal;
		viewPrincipal.setVisible(true);

		this.viewPrincipal.addListenerOptiuni(new ComboBoxListener());
		this.viewPrincipal.addListenerButton(new ViewPrincipalListener());
	}
	class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			switch (viewPrincipal.getOptiuni().getSelectedItem().toString()) {
			case "Client":
				optiune = "Client";
				break;
			case "Produs":
				optiune = "Produs";
				break;
			case "Comanda":
				optiune = "Comanda";
				break;
			}
		}
	
	}
	
	class ViewPrincipalListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch(optiune) {
			case "Client" : 
				viewPrincipal.setVisible(false);
				ViewClient viewClient=new ViewClient();
				ControllerClient ctrlClient=new ControllerClient(viewClient);
				break;
			case "Produs" :
				viewPrincipal.setVisible(false);
				ViewProdus viewProdus=new ViewProdus();
				ControllerProdus ctrlProdus=new ControllerProdus(viewProdus);
				break;
			case "Comanda" :
				viewPrincipal.setVisible(false);
				ViewComanda viewComanda=new ViewComanda();
				ControllerComanda ctrlComanda=new ControllerComanda(viewComanda);
				break;
			}
		}
		
	}
	
	

}
