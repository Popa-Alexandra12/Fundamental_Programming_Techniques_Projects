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

import Model.Produs;

public class ViewProdus extends JFrame {
	/// Produs
	// Lables
	private JLabel produsL = new JLabel("Introduceti Date Produs: ");
	private JLabel idProdusL = new JLabel("ID: ");
	private JLabel denumireProdusL = new JLabel("Denumire: ");
	private JLabel cantitateProdusL = new JLabel("Cantitate disponibila: ");
	private JLabel pretProdusL = new JLabel("Pret: ");
	// TextFields
	private JTextField idProdusTF = new JTextField(30);
	private JTextField denumireProdusTF = new JTextField(30);
	private JTextField cantitateProdusTF = new JTextField(30);
	private JTextField pretProdusTF = new JTextField(30);
	// Buttons
	private JButton insertProdusB = new JButton("Adaugare Produs");
	private JButton stergereProdusB = new JButton("Eliminare Produs");
	private JButton editareProdusB = new JButton("Editare Produs");
	private JButton vizualizareProduseB = new JButton("Vizualizare Produse");
	// Table
	private JTable tabel = new JTable();
	private JScrollPane scrollPane = new JScrollPane(tabel);
	private DefaultTableModel model=new DefaultTableModel();

	public ViewProdus() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 800);

		idProdusTF.setMaximumSize(idProdusTF.getPreferredSize());
		denumireProdusTF.setMaximumSize(denumireProdusTF.getPreferredSize());
		cantitateProdusTF.setMaximumSize(cantitateProdusTF.getPreferredSize());
		pretProdusTF.setMaximumSize(pretProdusTF.getPreferredSize());
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
		pPrincipal.add(produsL);
		produsL.setAlignmentX(Component.CENTER_ALIGNMENT);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pStDr);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pOrizontal);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pTabel);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

		pVertSt.add(idProdusL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(denumireProdusL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(cantitateProdusL);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(pretProdusL);

		pVertDr.add(idProdusTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(denumireProdusTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(cantitateProdusTF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(pretProdusTF);

		pOrizontal.add(insertProdusB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(stergereProdusB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(editareProdusB);
		pOrizontal.add(Box.createRigidArea(new Dimension(20, 0)));
		pOrizontal.add(vizualizareProduseB);
		
		this.setContentPane(pPrincipal);
		this.setTitle("Produs");
	}

	// getters/setter
	public JTextField getIdProdusTF() {
		return idProdusTF;
	}

	public JTextField getDenumireProdusTF() {
		return denumireProdusTF;
	}

	public JTextField getCantitateProdusTF() {
		return cantitateProdusTF;
	}

	public JTextField getPretProdusTF() {
		return pretProdusTF;
	}
	
	public void setTabel(JTable tabel) {
		
		this.tabel = tabel;
		String[] tableHeader=new String[Produs.class.getDeclaredFields().length];
		int i=0;
		for(Field field:Produs.class.getDeclaredFields())
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

	// action listener
	void addInsertProdusBListener(ActionListener l) {
		insertProdusB.addActionListener(l);
	}

	void addStergereProdusBListener(ActionListener l) {
		stergereProdusB.addActionListener(l);
	}

	void addEditareProdusBListener(ActionListener l) {
		editareProdusB.addActionListener(l);

	}

	void addVizualizareProduseBListener(ActionListener l) {
		vizualizareProduseB.addActionListener(l);

	}

}
