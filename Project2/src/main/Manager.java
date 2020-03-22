package ro.tuc.pt.assig2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Manager{
	private List<Queue> listOfQueues;
	private final static Logger LOGGER=Logger.getLogger(Queue.class.getName());
	
	public Manager(int nrQueue,List<JTextField> listText) { //deschide cozile deodata
		super();
	
	    listOfQueues= new ArrayList();  

		for(int i=0;i<nrQueue;i++)
				{
					Queue q=new Queue(listText.get(i));
					Thread t=new Thread(q,"Coada "+i+1);
					listOfQueues.add(q);
					t.start();
				//
				}
	}
	
	

	public List<Queue> getListOfQueues() {
		return listOfQueues;
	}

	

	public void setListOfQueues(List<Queue> listOfQueues) {
		this.listOfQueues = listOfQueues;
	}
	

	public void addClientInRightQueue(Client c) {
		
			
			int minWaitingTime=Integer.MAX_VALUE;
			
			for(Queue i:listOfQueues)
			{
				if(i.getTotalTime().intValue()<minWaitingTime) minWaitingTime=i.getTotalTime().intValue();
			}
			int j=0;
			int ok=0;
			for(Queue i:listOfQueues)
			{  j++;
				if(i.getTotalTime().intValue()==minWaitingTime && ok==0) {
					ok=1;
					
					LOGGER.info("Clientul cu ID-ul "+c.getId()+", arrivalTime: "+c.getArrivedTime()+", serviceTime: "+c.getServiceTime()+" a intrat in coada "+j);
				
					i.addClient(c);
				}

			}
	}
	
}