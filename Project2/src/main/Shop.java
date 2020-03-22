package ro.tuc.pt.assig2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;

public class Shop implements Runnable {
		private int nrClients;
		private int nrQueue;
	    private int simulationTime;
		private int minIntervalOfArrivigTime;
		private int maxIntervalOfArrivigTime;
		private int minIntervalOfServiceTime;
		private int maxIntervalOfServiceTime;
		private List<JTextField> listText;
	
		//constructor
		public Shop(int nrClients, int nrQueue, int simulationTime,
				int minIntervalOfArrivigTime, int maxIntervalOfArrivigTime, int minIntervalOfServiceTime,
				int maxIntervalOfServiceTime,List<JTextField> listText) {
			super();
			this.nrClients = nrClients;
			this.nrQueue = nrQueue;
			this.simulationTime = simulationTime;
			this.minIntervalOfArrivigTime = minIntervalOfArrivigTime;
			this.maxIntervalOfArrivigTime = maxIntervalOfArrivigTime;
			this.minIntervalOfServiceTime = minIntervalOfServiceTime;
			this.maxIntervalOfServiceTime = maxIntervalOfServiceTime;
			this.listText=listText;
		}
		
		public void run() {
			
			Generator gen= new Generator(nrClients,minIntervalOfArrivigTime,maxIntervalOfArrivigTime,minIntervalOfServiceTime,maxIntervalOfServiceTime);
			List<Client> listOfClients=gen.generateNrClients();
			Manager m=new Manager(nrQueue,listText);
			for(int currentTime=1;currentTime<=simulationTime;currentTime++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Timp actual: "+currentTime);
				for(Client c:listOfClients) {
				if(c.getArrivedTime()==currentTime)
				{
					m.addClientInRightQueue(c);
					
				}
			}
				
		}
			}
		
}
			
		
	
		
		
