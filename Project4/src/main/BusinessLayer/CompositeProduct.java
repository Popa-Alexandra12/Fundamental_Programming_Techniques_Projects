package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable{
	private List<MenuItem> compositeProduct;

	public CompositeProduct(int idItem, String name,List<MenuItem> menuItem) {
		super(idItem, name,0);
		compositeProduct=menuItem;
		
	}
	public int computePrice()
	{
		int price=0;
		for(MenuItem i:compositeProduct)
		{
			price+=i.getPrice();
		}
		
		this.setPrice(price);
		return price;
	}
	public List<MenuItem> getCompositeProduct() {
		return compositeProduct;
	}
	public void setCompositeProduct(List<MenuItem> compositeProduct) {
		this.compositeProduct = compositeProduct;
	}
	
	
	
	
	
	

}
