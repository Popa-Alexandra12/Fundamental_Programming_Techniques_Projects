package ro.tuc.pt.assig2;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Comparable<Client>{
	private int id;
	private int arrivedTime;
	private int serviceTime;
	private int exitTime;
	public Client(int id, int arrivedTime, int serviceTime) {
		super();
		this.id = id;
		this.arrivedTime = arrivedTime;
		this.serviceTime = serviceTime;
		this.exitTime=arrivedTime+serviceTime;
	}
	public Client() {
		super();
		this.id = 0;
		this.arrivedTime = 0;
		this.serviceTime = 0;
		this.exitTime=0;
	}
	
	public int getId() {
		return id;
	}
	public int getArrivedTime() {
		return arrivedTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setArrivedTime(int arrivedTime) {
		this.arrivedTime = arrivedTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	public int getExitTime() {
		return exitTime;
	}
	
	public void setExitTime(int exitTime) {
		this.exitTime = exitTime;
	}
	public int compareTo(Client c) {
		if(this.getArrivedTime()<c.getArrivedTime())
			return -1;
		else return 1;
	}
	
	public String toString() {
		return "Client [id=" + id + "]";
	}
	
	
}
