package BusinessLayer;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private int idItem;
	private String name;
	private int price;
	public MenuItem(int idItem, String name,int price) {
		super();
		this.idItem = idItem;
		this.name = name;
		this.price=price;
	}
	

	public int getIdItem() {
		return idItem;
	}
	public String getName() {
		return name;
	}
	
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public int computePrice() {
		return price;
	}

	@Override
	public String toString() {
		return idItem + ". " + name +" : " + price + " lei";
	}
	
	

}
