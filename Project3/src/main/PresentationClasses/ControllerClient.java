package PresentationClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BusinessLogicClasses.ClientBLL;
import Model.Clientul;

public class ControllerClient {
	private ViewClient viewClient = new ViewClient();

	public ControllerClient(ViewClient viewClient) {
		super();
		this.viewClient = viewClient;
		viewClient.setVisible(true);
	
		
		this.viewClient.addInsertClientBListener(new ViewClientListener());
		this.viewClient.addStergereClientBListener(new ViewClientListener());
		this.viewClient.addEditareClientBListener(new ViewClientListener());
		this.viewClient.addVizualizareClientiBListener(new VizualizareTabelListener());
	}
	class ViewClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				int id=Integer.parseInt(viewClient.getIdClientTF().getText());
				String nume=viewClient.getNumeClientTF().getText();
				String adresa=viewClient.getAdresaClientTF().getText();
				String email=viewClient.getEmailClientTF().getText();
				Clientul client=new Clientul(id,nume,adresa,email);
				ClientBLL c=new ClientBLL();  
				String buton = e.getActionCommand();
				//System.out.println(e.getSource());
				switch(buton)
		    	{
		    		case "Adaugare Client":
		    			c.insertNewClient(client);
		    			break;
		    		case "Eliminare Client":
		    			c.deleteClient(client.getIdClient());
		    			break;
		    		case "Editare Client":
		    			c.updateClient(client);
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
			
			ClientBLL c=new ClientBLL();
			System.out.println("I'm here");
			Object[][] rows = new Object[1000][];
			rows = c.viewTabel();
			viewClient.getModel().setRowCount(0);
			for(int i=0;i<rows.length;i++)
			{
				viewClient.getModel().addRow(rows[i]);
			
			}
		}
	}
}
