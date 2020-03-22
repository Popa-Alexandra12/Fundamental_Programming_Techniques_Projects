package PresentationClasses;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
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

import Model.Clientul;
import Model.Comanda;

public class ViewComanda extends JFrame {
	/// Comanda
	// Lables
	private JLabel comandaL = new JLabel("Introduceti Date Comanda: ");
	private JLabel idComandaL = new JLabel("ID Comanda: ");
	private JLabel idCClientL = new JLabel("ID Client: ");
	private JLabel idCProdusL = new JLabel("ID Produs: ");
	private JLabel cantitateComandaL = new JLabel("Cantitate dorita: ");
	
	// TextFields
	private JTextField idComandaTF = new JTextField(30);
	private JTextField idCClientTF = new JTextField(30);
	private JTextField idCProdusTF = new JTextField(30);
	private JTextField cantitateComandaTF = new JTextField(30);
	
	// Buttons
	private JButton insertComandaB = new JButton("Adaugare Comanda");
	private JButton stergereComandaB = new JButton("Eliminare Comanda");
	private JButton editareComandaB = new JButton("Editare Comanda");
	private JButton vizualizareComenziB = new JButton("Vizualizare Comenzi");
	//Table
	private JTable tabel=new JTable();
	private JScrollPane scrollPane = new JScrollPane(tabel);
	private DefaultTableModel model=new DefaultTableModel();

	public ViewComanda() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);

		idComandaTF.setMaximumSize(idComandaTF.getPreferredSize());
		idCClientTF.setMaximumSize(idCClientTF.getPreferredSize());
		idCProdusTF.setMaximumSize(idCProdusTF.getPreferredSize());
		cantitateComandaTF.setMaximumSize(cantitateComandaTF.getPreferredSize());
		tabel.setMaximumSize(tabel.getPreferredSize());
		scrollPane.setMaximumSize(scrollPane.getPreferredSize());
		

		JPanel pPrincipal = new JPanel();
		pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
		add(pPrincipal);

		JPanel pStDr = new JPanel();
		JPanel pVertSt = new JPanel();
		JPanel pVertDr = new JPanel();
		JPanel pOrizontal = new JPanel();
		JPanel pTabel = new JPanel();
		pStDr.setLayout(new BoxLayout(pStDr, BoxLayout.X_AXIS));
		pVertSt.setLayout(new BoxLayout(pVertSt, BoxLayout.Y_AXIS));
		pVertDr.setLayout(new BoxLayout(pVertDr, BoxLayout.Y_AXIS));
		pOrizontal.setLayout(new BoxLayout(pOrizontal, BoxLayout.X_AXIS));
		pTabel.setLayout(new BoxLayout(pTabel, BoxLayout.X_AXIS));

		
		pTabel.add(scrollPane);
		this.setTabel(tabel);
		
		pStDr.add(pVertSt);
		pStDr.add(Box.createRigidArea(new Dimension(20, 0)));
		pStDr.add(pVertDr);

		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(comandaL);
		comandaL.setAlignmentX(Component.CENTER_ALIGNMENT);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pStDr);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pOrizontal);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pTabel);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

		pVertSt.add(idComandaL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(idCClientL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(idCProdusL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(cantitateComandaL);

		pVertDr.add(idComandaTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(idCClientTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(idCProdusTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(cantitateComandaTF);

		pOrizontal.add(insertComandaB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(stergereComandaB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(editareComandaB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(vizualizareComenziB);

		this.setContentPane(pPrincipal);
		this.setTitle("Comanda");
	}
	//getters/setter
	public JTextField getIdComandaTF() {
		return idComandaTF;
	}

	public JTextField getIdCClientTF() {
		return idCClientTF;
	}

	public JTextField getIdCProdusTF() {
		return idCProdusTF;
	}

	public JTextField getCantitateComandaTF() {
		return cantitateComandaTF;
	}
	
	public void setTabel(JTable tabel) {
		this.tabel = tabel;
		String[] tableHeader=new String[Comanda.class.getDeclaredFields().length];
		int i=0;
		for(Field field:Comanda.class.getDeclaredFields())
		{
			tableHeader[i]=field.getName();
			i++;
		}
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
	void addInsertComandaBListener(ActionListener l) {
		insertComandaB.addActionListener(l);
	}

	void addStergereComandaBListener(ActionListener l) {
		stergereComandaB.addActionListener(l);
	}

	void addEditareComandaBListener(ActionListener l) {
		editareComandaB.addActionListener(l);

	}

	void addVizualizareComenziBListener(ActionListener l) {
		vizualizareComenziB.addActionListener(l);

	}
	

}
