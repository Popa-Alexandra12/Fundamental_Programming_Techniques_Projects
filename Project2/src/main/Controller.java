package ro.tuc.pt.assig2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;


public class Controller {
	
	private ViewPrincipal view;
	public Controller(ViewPrincipal view) {
		this.view = view;
		this.view.addStartBtnListener(new FrameListener());
		
	}
	class FrameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			    int nrC,nrQ,minAT,maxAT,minST,maxST,simT;
	            nrC=Integer.parseInt(view.getNrClientsF());
	            nrQ=Integer.parseInt(view.getNrQueueF());
	            minAT=Integer.parseInt(view.getMinArrivingTimeF());
	            maxAT=Integer.parseInt(view.getMaxArrivingTimeF());
	            minST=Integer.parseInt(view.getMinServiceTimeF());
	            maxST=Integer.parseInt(view.getMaxServiceTimeF());
	            simT=Integer.parseInt(view.getTimpSimulareF());
	            List<JTextField> textL=new ArrayList<JTextField>();
	            textL.add(view.getQueue1());
	            textL.add(view.getQueue2());
	            textL.add(view.getQueue3());
	            textL.add(view.getQueue4());
	            textL.add(view.getQueue5());
	            Shop shop=new Shop(nrC,nrQ,simT,minAT,maxAT,minST,maxST,textL);
	            Thread t=new Thread(shop);
	            t.start();
			
		}

	}
}