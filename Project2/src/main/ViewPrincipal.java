package ro.tuc.pt.assig2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class ViewPrincipal extends JFrame {

	private JTextField nrClientsF = new JTextField(15);
	private JTextField nrQueueF = new JTextField(15);
	private JTextField minArrivingTimeF = new JTextField(15);
	private JTextField maxArrivingTimeF = new JTextField(15);
	private JTextField minServiceTimeF = new JTextField(15);
	private JTextField maxServiceTimeF = new JTextField(15);
	private JTextField timpSimulareF = new JTextField(15);
	private JTextField queue1 = new JTextField(100);
	private JTextField queue2 = new JTextField(100);
	private JTextField queue3 = new JTextField(100);
	private JTextField queue4 = new JTextField(100);
	private JTextField queue5 = new JTextField(100);
	private JLabel labelOptiuni = new JLabel("Introduceti optiunile");
	private JLabel labelNrC = new JLabel("Numar clienti :");
	private JLabel labelNrQ = new JLabel("Numar cozi :");
	private JLabel labelMinAT = new JLabel("Timp de sosire minim :");
	private JLabel labelMaxAT = new JLabel("Timp de sosire maxm :");
	private JLabel labelMinST = new JLabel("Timp de procesare minim :");
	private JLabel labelMaxST = new JLabel("Timp de procesare maixim :");
	private JLabel labelTimpSimulare = new JLabel("Timp simulare :");
	private JLabel labelQ1 = new JLabel("Coada 1:");
	private JLabel labelQ2 = new JLabel("Coada 2:");
	private JLabel labelQ3 = new JLabel("Coada 3:");
	private JLabel labelQ4 = new JLabel("Coada 4:");
	private JLabel labelQ5 = new JLabel("Coada 5:");
	private JButton startBtn = new JButton("Start Simulare");
	
	

	public ViewPrincipal() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 500);

		nrClientsF.setMaximumSize(nrClientsF.getPreferredSize());
		nrQueueF.setMaximumSize(nrQueueF.getPreferredSize());
		minArrivingTimeF.setMaximumSize(minArrivingTimeF.getPreferredSize());
		maxArrivingTimeF.setMaximumSize(maxArrivingTimeF.getPreferredSize());
		minServiceTimeF.setMaximumSize(minServiceTimeF.getPreferredSize());
		maxServiceTimeF.setMaximumSize(maxServiceTimeF.getPreferredSize());
		timpSimulareF.setMaximumSize(timpSimulareF.getPreferredSize());
		
		queue1.setMaximumSize(queue1.getPreferredSize());
		queue2.setMaximumSize(queue2.getPreferredSize());
		queue3.setMaximumSize(queue3.getPreferredSize());
		queue4.setMaximumSize(queue4.getPreferredSize());
		queue5.setMaximumSize(queue5.getPreferredSize());
		
		queue1.setEditable(false);
		queue2.setEditable(false);
		queue3.setEditable(false);
		queue4.setEditable(false);
		queue5.setEditable(false);

		JPanel pPrincipal = new JPanel();
		pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.X_AXIS));
		add(pPrincipal);

		JPanel pStanga = new JPanel();
		pStanga.setLayout(new BoxLayout(pStanga, BoxLayout.Y_AXIS));

		JPanel pDreapta = new JPanel();
		pDreapta.setLayout(new BoxLayout(pDreapta, BoxLayout.Y_AXIS));

		JPanel pStDr = new JPanel();
		JPanel pVertSt = new JPanel();
		JPanel pVertDr = new JPanel();
		JPanel pOrizontalSus = new JPanel();
		JPanel pOrizontalJos = new JPanel();
		pStDr.setLayout(new BoxLayout(pStDr, BoxLayout.X_AXIS));
		pVertSt.setLayout(new BoxLayout(pVertSt, BoxLayout.Y_AXIS));
		pVertDr.setLayout(new BoxLayout(pVertDr, BoxLayout.Y_AXIS));
		pOrizontalSus.setLayout(new BoxLayout(pOrizontalSus, BoxLayout.X_AXIS));
		pOrizontalJos.setLayout(new BoxLayout(pOrizontalJos, BoxLayout.X_AXIS));

		

		JPanel pSimulare = new JPanel();
		pSimulare.setLayout(new BoxLayout(pSimulare, BoxLayout.X_AXIS));
		 
		JPanel pSimSt = new JPanel();
		pSimSt.setLayout(new BoxLayout(pSimSt, BoxLayout.Y_AXIS));
		
		pSimSt.add(labelQ1);
		pSimSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimSt.add(labelQ2);
		pSimSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimSt.add(labelQ3);
		pSimSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimSt.add(labelQ4);
		pSimSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimSt.add(labelQ5);
		
		
		
		JPanel pSimDr = new JPanel();
		pSimDr.setLayout(new BoxLayout(pSimDr, BoxLayout.Y_AXIS));
		
		pSimDr.add(queue1);
		pSimDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimDr.add(queue2);
		pSimDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimDr.add(queue3);
		pSimDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimDr.add(queue4);
		pSimDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pSimDr.add(queue5);

		pSimulare.add(Box.createRigidArea(new Dimension(20, 0)));
		pSimulare.add(pSimSt);
		pSimulare.add(Box.createRigidArea(new Dimension(20, 0)));
		pSimulare.add(pSimDr);
		pSimulare.add(Box.createRigidArea(new Dimension(20, 0)));
		
		
		
		
		pDreapta.add(Box.createRigidArea(new Dimension(0, 10)));
		pDreapta.add(pSimulare);
		pDreapta.add(Box.createRigidArea(new Dimension(0, 10)));
		

		pStDr.add(pVertSt);
		pStDr.add(Box.createRigidArea(new Dimension(20, 0)));
		pStDr.add(pVertDr);

		pStanga.add(Box.createRigidArea(new Dimension(0, 20)));
		pStanga.add(pOrizontalSus);
		pStanga.add(Box.createRigidArea(new Dimension(0, 20)));
		pStanga.add(pStDr);
		pStanga.add(Box.createRigidArea(new Dimension(0, 20)));
		pStanga.add(pOrizontalJos);
		pStanga.add(Box.createRigidArea(new Dimension(0, 20)));

		pOrizontalSus.add(labelOptiuni);
		pOrizontalSus.add(Box.createRigidArea(new Dimension(20, 0)));

		pVertSt.add(labelNrC);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(labelNrQ);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(labelMinAT);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(labelMaxAT);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(labelMinST);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(labelMaxST);
		pVertSt.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertSt.add(labelTimpSimulare);

		pVertDr.add(nrClientsF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(nrQueueF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(minArrivingTimeF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(maxArrivingTimeF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(minServiceTimeF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(maxServiceTimeF);
		pVertDr.add(Box.createRigidArea(new Dimension(0, 20)));
		pVertDr.add(timpSimulareF);

		pOrizontalJos.add(startBtn);
		pOrizontalJos.add(Box.createRigidArea(new Dimension(20, 0)));

		pPrincipal.add(Box.createRigidArea(new Dimension(20, 0)));
		pPrincipal.add(pStanga);
		pPrincipal.add(Box.createRigidArea(new Dimension(20, 0)));
		pPrincipal.add(pDreapta);
		pPrincipal.add(Box.createRigidArea(new Dimension(20, 0)));

		this.setContentPane(pPrincipal);
		this.setTitle("Magazin");
		this.setVisible(true);

	}

	public String getNrClientsF() {
		return nrClientsF.getText();
	}

	public String getNrQueueF() {
		return nrQueueF.getText();
	}

	public String getMinArrivingTimeF() {
		return minArrivingTimeF.getText();
	}

	public String getMaxArrivingTimeF() {
		return maxArrivingTimeF.getText();
	}

	public String getMinServiceTimeF() {
		return minServiceTimeF.getText();
	}

	public String getMaxServiceTimeF() {
		return maxServiceTimeF.getText();
	}

	public String getTimpSimulareF() {
		return timpSimulareF.getText();
	}
	
	public JTextField getQueue1() {
		return queue1;
	}

	public JTextField getQueue2() {
		return queue2;
	}

	public JTextField getQueue3() {
		return queue3;
	}

	public JTextField getQueue4() {
		return queue4;
	}

	public JTextField getQueue5() {
		return queue5;
	}

	public String getStartBtn() {
		return startBtn.getText();
	}

	// action listener
	void addStartBtnListener(ActionListener l) {
		startBtn.addActionListener(l);
	}
	
	
}
