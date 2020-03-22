package PresentationLayer;

import java.io.Serializable;

import BusinessLayer.Restaurant;

public abstract class Observer implements Serializable{
	 protected Restaurant rest;
	 
	 public Observer(Restaurant rest) {
		super();
		this.rest = rest;
	}

	public abstract void update();
		

}
