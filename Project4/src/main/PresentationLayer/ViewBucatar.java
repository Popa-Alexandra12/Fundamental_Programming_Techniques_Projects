package PresentationLayer;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.Order;

public class ViewBucatar extends JFrame{
	// Lables
	private  JLabel produseComandateL = new JLabel("Produse comandate : ");
	
	//Table
	private JTable tabel=new JTable();
	private JScrollPane scrollPane = new JScrollPane(tabel);
	private DefaultTableModel model=new DefaultTableModel();
     
     public ViewBucatar() {
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 500);
		scrollPane.setMaximumSize(scrollPane.getPreferredSize());
		
		this.setTabel(tabel);
        JPanel pPrincipal = new JPanel();
		pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
		add(pPrincipal);
		
		JPanel pSus = new JPanel();
		JPanel pTabel = new JPanel();
		pSus.setLayout(new BoxLayout(pSus, BoxLayout.X_AXIS));
		pTabel.setLayout(new BoxLayout(pTabel, BoxLayout.X_AXIS));
		
		

		pSus.add(produseComandateL);
		pSus.add(Box.createRigidArea(new Dimension(20, 0)));
		
		
		pTabel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pSus);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		pPrincipal.add(pTabel);
		pPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.setContentPane(pPrincipal);
		this.setTitle("Bucatar");
		this.setVisible(true);
		
        
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

	public static void main(String[] args) {
		ViewBucatar v=new ViewBucatar();
		v.setVisible(true);
	}

}
