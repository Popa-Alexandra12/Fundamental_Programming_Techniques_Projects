package ro.tuc.pt.assig2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Generator {
	private int  nrClients;
	private int minIntervalOfArrivigTime;
	private int maxIntervalOfArrivigTime;
	private int minIntervalOfServiceTime;
	private int maxIntervalOfServiceTime;
	
	public Generator(int nrClients,int minIntervalOfArrivigTime, int maxIntervalOfArrivigTime, int minIntervalOfServiceTime,
			int maxIntervalOfServiceTime) {
		super();
		this.nrClients=nrClients;
		this.minIntervalOfArrivigTime = minIntervalOfArrivigTime;
		this.maxIntervalOfArrivigTime = maxIntervalOfArrivigTime;
		this.minIntervalOfServiceTime = minIntervalOfServiceTime;
		this.maxIntervalOfServiceTime = maxIntervalOfServiceTime;
	}
	public int getMinIntervalOfArrivigTime() {
		return minIntervalOfArrivigTime;
	}
	public int getMaxIntervalOfArrivigTime() {
		return maxIntervalOfArrivigTime;
	}
	public int getMinIntervalOfServiceTime() {
		return minIntervalOfServiceTime;
	}
	public int getMaxIntervalOfServiceTime() {
		return maxIntervalOfServiceTime;
	}
	public void setMinIntervalOfArrivigTime(int minIntervalOfArrivigTime) {
		this.minIntervalOfArrivigTime = minIntervalOfArrivigTime;
	}
	public void setMaxIntervalOfArrivigTime(int maxIntervalOfArrivigTime) {
		this.maxIntervalOfArrivigTime = maxIntervalOfArrivigTime;
	}
	public void setMinIntervalOfServiceTime(int minIntervalOfServiceTime) {
		this.minIntervalOfServiceTime = minIntervalOfServiceTime;
	}
	public void setMaxIntervalOfServiceTime(int maxIntervalOfServiceTime) {
		this.maxIntervalOfServiceTime = maxIntervalOfServiceTime;
	}
	
	public int generateArivalTime()
	{
		Random r=new Random();
		int arrTime=this.getMinIntervalOfArrivigTime()+r.nextInt((this.getMaxIntervalOfArrivigTime()-this.getMinIntervalOfArrivigTime())+1);
		return arrTime;
	}
	public int generateServiceTime()
	{
		Random r=new Random();
		int servTime=this.getMinIntervalOfServiceTime()+r.nextInt((this.getMaxIntervalOfServiceTime()-this.getMinIntervalOfServiceTime())+1);
		return servTime;
	}
	public List<Client> generateNrClients() {
		List<Client> listOfClients=new ArrayList<Client>();
		for(int i=1;i<=nrClients;i++) {
			Client c=new Client(i,this.generateArivalTime(),this.generateServiceTime());
			listOfClients.add(c);
			//System.out.println("Clientul cu ID-ul "+c.getId()+" ,arrivalTime: "+c.getArrivedTime()+" si serviceTime: "+c.getServiceTime()+" a fost adaugat in coada de clienti");
		}
		Collections.sort(listOfClients);
		return listOfClients;

	}
}
