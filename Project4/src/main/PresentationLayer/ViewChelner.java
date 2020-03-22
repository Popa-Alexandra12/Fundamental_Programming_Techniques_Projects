package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.Order;


public class ViewChelner extends JFrame{
		// Lables
		private JLabel optiuniChelnerL = new JLabel("Alegeti optiunea dorita: ");
		private JLabel dateComandaL = new JLabel("Adaugati informatii comanda: ");
		private JLabel idComandaL = new JLabel("ID Comanda: ");
		private JLabel dataComandaL = new JLabel("Data : ");
		private JLabel masaComandaL = new JLabel("Masa: ");
		private JLabel idProdusL = new JLabel("ID Produs : ");
		
		
		// TextFields
		private JTextField idComandaTF = new JTextField(30);
		private JTextField dataComandaTF = new JTextField(30);
		private JTextField masaComandaTF = new JTextField(30);
		private JTextField idProdusTF = new JTextField(30);
		// Buttons
		private JButton addComandaB = new JButton("Adaugare Comanda");
		private JButton vizualizareComenziB = new JButton("Vizualizare Comenzi");
		private JButton generareFacturaB = new JButton("Generare Factura");
		private JButton addProdusB = new JButton("Adaugare Produs");
		
		
		
		//Table
		private JTable tabel=new JTable();
		private JScrollPane scrollPane = new JScrollPane(tabel);
		private DefaultTableModel model=new DefaultTableModel();
		
		public ViewChelner(){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(1000,600);

			idComandaTF.setMaximumSize(idComandaTF.getPreferredSize());
			dataComandaTF.setMaximumSize(dataComandaTF.getPreferredSize());
			masaComandaTF.setMaximumSize(masaComandaTF.getPreferredSize());
			idProdusTF.setMaximumSize(idProdusTF.getPreferredSize());
			tabel.setMaximumSize(tabel.getPreferredSize());
			scrollPane.setMaximumSize(scrollPane.getPreferredSize());
			
	      
			JPanel pPrincipal = new JPanel();
			pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
			add(pPrincipal);

			JPanel pStDr = new JPanel();
			JPanel pVertSt = new JPanel();
			JPanel pVertDr = new JPanel();
			JPanel pSus = new JPanel();
			JPanel pAddProdus = new JPanel();
			JPanel pAddProdusText = new JPanel();
			JPanel pOrizontal = new JPanel();
			JPanel pTabel = new JPanel();
			pStDr.setLayout(new BoxLayout(pStDr, BoxLayout.X_AXIS));
			pVertSt.setLayout(new BoxLayout(pVertSt, BoxLayout.Y_AXIS));
			pVertDr.setLayout(new BoxLayout(pVertDr, BoxLayout.Y_AXIS));
			pSus.setLayout(new BoxLayout(pSus, BoxLayout.X_AXIS));
			pAddProdus.setLayout(new BoxLayout(pAddProdus, BoxLayout.Y_AXIS));
			pAddProdusText.setLayout(new BoxLayout(pAddProdusText, BoxLayout.X_AXIS));
			pOrizontal.setLayout(new BoxLayout(pOrizontal, BoxLayout.X_AXIS));
			pTabel.setLayout(new BoxLayout(pTabel, BoxLayout.X_AXIS));

			
			pTabel.add(scrollPane, BorderLayout.CENTER);
			this.setTabel(tabel);
			
			
			pAddProdusText.add(idProdusL);
			pAddProdusText.add(Box.createRigidArea(new Dimension(20, 0)));
			pAddProdusText.add(idProdusTF);
			pAddProdusText.add(Box.createRigidArea(new Dimension(20, 0)));
			
			pAddProdus.add(pAddProdusText);
			pAddProdus.add(Box.createRigidArea(new Dimension(0,20)));
			pAddProdus.add(addProdusB);
			pAddProdus.add(Box.createRigidArea(new Dimension(0,20)));
			
			pSus.add(pStDr);
			pSus.add(Box.createRigidArea(new Dimension(20, 0)));
			pSus.add(pAddProdus);
			pSus.add(Box.createRigidArea(new Dimension(20, 0)));
			
			pStDr.add(pVertSt);
			pStDr.add(Box.createRigidArea(new Dimension(20, 0)));
			pStDr.add(pVertDr);

			pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
			pPrincipal.add(dateComandaL);
			dateComandaL.setAlignmentX(Component.CENTER_ALIGNMENT);
			pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
			pPrincipal.add(pSus);
			pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
			pPrincipal.add(pOrizontal);
			pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
			pPrincipal.add(pTabel);
			pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

			pVertSt.add(idComandaL);
			pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
			pVertSt.add(dataComandaL);
			pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
			pVertSt.add(masaComandaL);

			pVertDr.add(idComandaTF);
			pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
			pVertDr.add(dataComandaTF);
			pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
			pVertDr.add(masaComandaTF);

			pOrizontal.add(optiuniChelnerL);
			pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
			pOrizontal.add(addComandaB);
			pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
			pOrizontal.add(vizualizareComenziB);
			pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
			pOrizontal.add(generareFacturaB);
			

			this.setContentPane(pPrincipal);
			this.setTitle("Chelner");
			
			
		}
		//setters/getters
		public JTextField getIdComandaTF() {
			return idComandaTF;
		}

		public JTextField getDataComandaTF() {
			return dataComandaTF;
		}

		public JTextField getMasaComandaTF() {
			return masaComandaTF;
		}
		
		public JTextField getIdProdusTF() {
			return idProdusTF;
		}
		public void setTabel(JTable tabel) {
			this.tabel = tabel;
			String[] tableHeader=new String[4];
			int i=0;
			tableHeader[0]="ID ";
			tableHeader[1]="data comenzii ";
			tableHeader[2]="masa ";
			tableHeader[3]="produse comandate ";
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
		void addAddComandaBListener(ActionListener l) {
			addComandaB.addActionListener(l);
		}

		void addVizualizareComenziBListener(ActionListener l) {
			vizualizareComenziB.addActionListener(l);
		}

		void addGenerareFacturaBListener(ActionListener l) {
			generareFacturaB.addActionListener(l);

		}
		void addAddProdusBListener(ActionListener l) {
			addProdusB.addActionListener(l);

		}

		public static void main(String[] args) {
			ViewChelner v=new ViewChelner();
			v.setVisible(true);
		}
		
		
		
		
		
	

}
