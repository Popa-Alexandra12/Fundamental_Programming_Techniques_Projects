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


public class ViewClient extends JFrame {
	// Lables
	private JLabel clientL = new JLabel("Introduceti Date Client: ");
	private JLabel idClientL = new JLabel("ID: ");
	private JLabel numeClientL = new JLabel("Nume: ");
	private JLabel adreasaClientL = new JLabel("Adresa: ");
	private JLabel emailClientL = new JLabel("Email: ");
	// TextFields
	private JTextField idClientTF = new JTextField(30);
	private JTextField numeClientTF = new JTextField(30);
	private JTextField adresaClientTF = new JTextField(30);
	private JTextField emailClientTF = new JTextField(30);
	// Buttons
	private JButton insertClientB = new JButton("Adaugare Client");
	private JButton stergereClientB = new JButton("Eliminare Client");
	private JButton editateClientB = new JButton("Editare Client");
	private JButton vizualizareClientiB = new JButton("Vizualizare Clienti");
	// Table
	private JTable tabel = new JTable();
	private JScrollPane scrollPane = new JScrollPane(tabel);
	private DefaultTableModel model=new DefaultTableModel();

	public ViewClient() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);

		idClientTF.setMaximumSize(idClientTF.getPreferredSize());
		numeClientTF.setMaximumSize(numeClientTF.getPreferredSize());
		adresaClientTF.setMaximumSize(adresaClientTF.getPreferredSize());
		emailClientTF.setMaximumSize(emailClientTF.getPreferredSize());
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
		pPrincipal.add(clientL);
		clientL.setAlignmentX(Component.CENTER_ALIGNMENT);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pStDr);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pOrizontal);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pTabel);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

		pVertSt.add(idClientL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(numeClientL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(adreasaClientL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(emailClientL);

		pVertDr.add(idClientTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(numeClientTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(adresaClientTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(emailClientTF);

		pOrizontal.add(insertClientB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(stergereClientB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(editateClientB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(vizualizareClientiB);

		this.setContentPane(pPrincipal);
		this.setTitle("Client");
		this.setVisible(false);

	}

	// getters/setter
	public JTextField getIdClientTF() {
		return idClientTF;
	}

	public JTextField getNumeClientTF() {
		return numeClientTF;
	}

	public JTextField getAdresaClientTF() {
		return adresaClientTF;
	}

	public JTextField getEmailClientTF() {
		return emailClientTF;
	}
	
	
	public void setTabel(JTable tabel) {
		this.tabel = tabel;
		String[] tableHeader=new String[Clientul.class.getDeclaredFields().length];
		int i=0;
		for(Field field:Clientul.class.getDeclaredFields())
		{
			tableHeader[i]=field.getName();
			i++;
		}
		model.setColumnIdentifiers(tableHeader);
		tabel.setModel(model);
		tabel.setVisible(true);

	}
	

	public DefaultTableModel getModel() {
		return model;
	}

	// action listener
	void addInsertClientBListener(ActionListener l) {
		insertClientB.addActionListener(l);
	}

	void addStergereClientBListener(ActionListener l) {
		stergereClientB.addActionListener(l);
	}

	void addEditareClientBListener(ActionListener l) {
		editateClientB.addActionListener(l);
	}

	void addVizualizareClientiBListener(ActionListener l) {
		vizualizareClientiB.addActionListener(l);
	}


}
