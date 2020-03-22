package BusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable{

	public BaseProduct(int idItem, String name,int price) {
		super(idItem, name,price);
		
	}
	
	
}
