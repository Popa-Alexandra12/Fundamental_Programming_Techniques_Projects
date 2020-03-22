package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BusinessLayer.Restaurant;
import PresentationLayer.ViewPrincipal;

public class ControllerPrincipal {
	private ViewPrincipal viewPrincipal = new ViewPrincipal();
	private String optiune;
	Restaurant rest;
	
	public ControllerPrincipal(ViewPrincipal viewPrincipal,Restaurant rest) {
		super();
		this.viewPrincipal = viewPrincipal;
		viewPrincipal.setVisible(true);
		this.rest=rest;
		
		this.viewPrincipal.addListenerOptiuni(new ComboBoxListener());
		this.viewPrincipal.addListenerButton(new ViewPrincipalListener());
	}
	class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			switch (viewPrincipal.getOptiuni().getSelectedItem().toString()) {
			case "Chelner":
				optiune = "Chelner";
				break;
			case "Administrator":
				optiune = "Administrator";
				break;
			case "Bucatar":
				optiune = "Bucatar";
				break;
			}
		}
	
	}
	
	class ViewPrincipalListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch(optiune) {
			case "Chelner" : 
				viewPrincipal.setVisible(false);
				ViewChelner viewChelner=new ViewChelner();
				ControllerChelner ctrlChelner=new ControllerChelner(viewChelner,rest);
				break;
			case "Administrator" :
				viewPrincipal.setVisible(false);
				ViewAdministrator viewAdministrator=new ViewAdministrator();
				ControllerAdministrator ctrlAdministrator=new ControllerAdministrator(viewAdministrator,rest);
				break;
			case "Bucatar" :
				//viewPrincipal.setVisible(false);
				ViewBucatar viewBucatar=new ViewBucatar();
				ControllerBucatar ctrlBucatar=new ControllerBucatar(viewBucatar,rest);
				break;
			}
		}
		
	}
	

}
