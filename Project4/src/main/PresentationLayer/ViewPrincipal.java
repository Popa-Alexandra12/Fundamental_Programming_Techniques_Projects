package PresentationLayer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewPrincipal extends JFrame{
	private JComboBox<String> optiuni;
	private JButton butonContinua;
	private JLabel optiuneLogare=new JLabel("Logare :");
	public ViewPrincipal()  {
		this.setTitle("Logare restaurant");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 300);
	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		panel.add( Box.createRigidArea(new Dimension(0,20)) );
		optiuneLogare.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(optiuneLogare);
		
		panel.add( Box.createRigidArea(new Dimension(0,20)) );
		optiuni = new JComboBox<String>(new String[]{"Chelner", "Administrator","Bucatar"});
		optiuni.setAlignmentX(Component.CENTER_ALIGNMENT);
		optiuni.setMaximumSize( optiuni.getPreferredSize() );
		panel.add(optiuni);
		
		panel.add( Box.createRigidArea(new Dimension(0,20)) );
	    butonContinua = new JButton("Continua");
		butonContinua.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(butonContinua);
		
		this.setContentPane(panel);
		
	}
	public void addListenerOptiuni(ActionListener l)
	{
		optiuni.addActionListener(l);
	}
	public void addListenerButton(ActionListener l)
	{
		butonContinua.addActionListener(l);
	}
	public JComboBox<String> getOptiuni()
	{
		return optiuni;
	}
	public  JButton getButonContinua()
	{
		return butonContinua;
	}
	
	public static void main(String[] args) {
		ViewPrincipal v=new ViewPrincipal();
		v.setVisible(true);
	}

}
