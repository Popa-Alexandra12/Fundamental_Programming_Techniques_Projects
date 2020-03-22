package PresentationLayer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.Order;

public class ViewAdministrator extends JFrame{
			// Lables
			private JLabel optiuniAdministratorL = new JLabel("Alegeti optiunea dorita: ");
			private JLabel dateProdusL = new JLabel("Adaugati informatii produs: ");
			private JLabel idProdusL = new JLabel("ID : ");
			private JLabel denumireProdusL = new JLabel("Denumire : ");
			private JLabel pretProdusL = new JLabel("Pret : ");
			
			// TextFields
			private JTextField idProdusTF = new JTextField(30);
			private JTextField denumireProdusTF = new JTextField(30);
			private JTextField pretProdusTF = new JTextField(30);
			
			// Buttons
			private JButton addProdusBazaB = new JButton("Adaugare in Produs Compus");
			private JButton finalizareAdaugareB = new JButton("Finalizare Adaugare");
			private JButton stergereProdusB = new JButton("Stergere Produs");
			private JButton editareDenumireProdusB = new JButton("Editare Denumire Produs");
			private JButton editarePretProdusB = new JButton("Editare Pret Produs");
			private JButton vizualizareProduseB = new JButton("Vizualizare Produse");
			
			//ComboBox
			private JComboBox<String> optiuniAdaugare;
			
			//Table
			private JTable tabel=new JTable();
			private JScrollPane scrollPane = new JScrollPane(tabel);
			private DefaultTableModel model=new DefaultTableModel();
			
			public ViewAdministrator()
			{
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setSize(900, 600);

				idProdusTF.setMaximumSize(idProdusTF.getPreferredSize());
				denumireProdusTF.setMaximumSize(denumireProdusTF.getPreferredSize());
				pretProdusTF.setMaximumSize(pretProdusTF.getPreferredSize());
				tabel.setMaximumSize(tabel.getPreferredSize());
				scrollPane.setMaximumSize(scrollPane.getPreferredSize());
				

				JPanel pPrincipal = new JPanel();
				pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
				add(pPrincipal);

				JPanel pStDr = new JPanel();
				JPanel pVertSt = new JPanel();
				JPanel pVertDr = new JPanel();
				JPanel pVertical = new JPanel();
				JPanel pButoane = new JPanel();
				JPanel pAdaugare = new JPanel();
				JPanel pSus = new JPanel();
				JPanel pTabel = new JPanel();
				pStDr.setLayout(new BoxLayout(pStDr, BoxLayout.X_AXIS));
				pVertSt.setLayout(new BoxLayout(pVertSt, BoxLayout.Y_AXIS));
				pVertDr.setLayout(new BoxLayout(pVertDr, BoxLayout.Y_AXIS));
				pVertical.setLayout(new BoxLayout(pVertical, BoxLayout.Y_AXIS));
				pButoane.setLayout(new BoxLayout(pButoane, BoxLayout.Y_AXIS));
				pAdaugare.setLayout(new BoxLayout(pAdaugare, BoxLayout.X_AXIS));
				pSus.setLayout(new BoxLayout(pSus, BoxLayout.X_AXIS));
				pTabel.setLayout(new BoxLayout(pTabel, BoxLayout.X_AXIS));

				
				pTabel.add(scrollPane);
				this.setTabel(tabel);
				
				pStDr.add(pVertSt);
				pStDr.add(Box.createRigidArea(new Dimension(20, 0)));
				pStDr.add(pVertDr);

				pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
				pPrincipal.add(dateProdusL);
				dateProdusL.setAlignmentX(Component.RIGHT_ALIGNMENT);
				pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
				
				pPrincipal.add(pSus);
				pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
				pPrincipal.add(pTabel);
				pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

				pVertSt.add(idProdusL);
				pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
				pVertSt.add(denumireProdusL);
				pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
				pVertSt.add(pretProdusL);

				pVertDr.add(idProdusTF);
				pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
				pVertDr.add(denumireProdusTF);
				pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
				pVertDr.add(pretProdusTF);
			
				optiuniAdaugare = new JComboBox<String>(new String[]{"Adaugare produs de baza", "Adaugare produs compus"});
				
				optiuniAdaugare.setMaximumSize( optiuniAdaugare.getPreferredSize());
				
				pAdaugare.add(optiuniAdaugare);
				pAdaugare.add(Box.createRigidArea(new Dimension(5,0)));
				pAdaugare.add(addProdusBazaB);
				pAdaugare.add(Box.createRigidArea(new Dimension(5,0)));
				pAdaugare.add(finalizareAdaugareB);
				pAdaugare.add(Box.createRigidArea(new Dimension(5,0)));
				pButoane.add(stergereProdusB);
				pButoane.add(Box.createRigidArea(new Dimension(0, 5)));
				pButoane.add(editareDenumireProdusB);
				pButoane.add(Box.createRigidArea(new Dimension(0, 5)));
				pButoane.add(editarePretProdusB);
				pButoane.add(Box.createRigidArea(new Dimension(0, 5)));
				pButoane.add(vizualizareProduseB);
				
				pVertical.add(pAdaugare);
				pVertical.add(Box.createRigidArea(new Dimension(0, 10)));
				pVertical.add(pButoane);
				pVertical.add(Box.createRigidArea(new Dimension(0, 10)));
				
				pSus.add(pStDr);
				pSus.add(Box.createRigidArea(new Dimension(10, 0)));
				pSus.add(pVertical);
				pSus.add(Box.createRigidArea(new Dimension(10, 0)));

				this.setContentPane(pPrincipal);
				this.setTitle("Administrator");
				
			}
			//getters/setters
			public JTextField getIdProdusTF() {
				return idProdusTF;
			}

			public JTextField getDenumireProdusTF() {
				return denumireProdusTF;
			}

			public JTextField getPretProdusTF() {
				return pretProdusTF;
			}
			public JComboBox<String> getOptiuniAdaugare() {
				return optiuniAdaugare;
			}
			public void setTabel(JTable tabel) {
				this.tabel = tabel;
				String[] tableHeader=new String[Order.class.getDeclaredFields().length];
				int i=0;
				tableHeader[0]="ID ";
				tableHeader[1]="denumire ";
				tableHeader[2]="pret ";
				model.setColumnIdentifiers(tableHeader);
				tabel.setModel(model);
				tabel.setVisible(true);
				 
		     
			}
			public JTable getTabel() {
				return tabel;
			}
			public DefaultTableModel getModel() {
				return model;
			}
			//action listener
			public void addOptiuniAdaugareListener(ActionListener l)
			{
				optiuniAdaugare.addActionListener(l);
			}
			void addAddProdusBazaBListener(ActionListener l) {
				addProdusBazaB.addActionListener(l);
			}

			void addFinalizareAdaugareBListener(ActionListener l) {
				finalizareAdaugareB.addActionListener(l);
			}

			void addStergereProdusBListener(ActionListener l) {
				stergereProdusB.addActionListener(l);

			}
			void addEditareDenumireProdusBListener(ActionListener l) {
				editareDenumireProdusB.addActionListener(l);
			}

			void addEditarePretProdusBListener(ActionListener l) {
				editarePretProdusB.addActionListener(l);
			}

			void addVizualizareProduseBListener(ActionListener l) {
				vizualizareProduseB.addActionListener(l);

			}
			public static void main(String[] args) {
				ViewAdministrator v=new ViewAdministrator();
				v.setVisible(true);
			}
			
}
