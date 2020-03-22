package ro.tuc.pt.assig2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.swing.JTextField;

public class Queue implements Runnable {

	private BlockingQueue<Client> clients;
	private AtomicInteger totalTime;
	private JTextField text;
	private final static Logger LOGGER = Logger.getLogger(Queue.class.getName());

	public Queue(JTextField text) {
		super();
		this.clients = new LinkedBlockingQueue<Client>();
		totalTime = new AtomicInteger(0);
		this.text = text;
	}

	public BlockingQueue<Client> getClients() {
		return clients;
	}

	public void setClients(BlockingQueue<Client> clients) {
		this.clients = clients;
	}

	public AtomicInteger getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(AtomicInteger totalTime) {
		this.totalTime = totalTime;
	}

	public void run() {
		while (true) {
			
			synchronized (this) {
				Client c = new Client();
				
				try {
					c = this.getClients().take();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					int time = 0;
					while (time < c.getServiceTime()) {
						
						Thread.sleep(1000);
						totalTime.addAndGet(-1);
						time++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.listQueue();
				LOGGER.info("Clientul cu ID-ul " + c.getId() + ", arrivalTime: " + c.getArrivedTime()
						+ " si serviceTime: " + c.getServiceTime() + " a iesit din coada");
				
			}
		}
		

	}

	public void addClient(Client c) {
		//System.out.println("------"+c);
		totalTime.addAndGet(c.getServiceTime());
		c.setExitTime(totalTime.intValue() + c.getArrivedTime() - 1);
		clients.add(c);
		this.listQueue();
	}

	public void listQueue() {
		String s="";
		for (Client c : this.getClients()) {
			s += c.toString()+" ";
			
		}
		text.setText(s);
		//System.out.println("--------------------------------"+s);
	}
	/*public void listQueue1() {
		String s="";
		for (Client c : this.getClients()) {
			s += c.toString()+" ";
			
		}
		text.setText(s);
		//System.out.println("--------------------------------"+s);
	}*/

}
