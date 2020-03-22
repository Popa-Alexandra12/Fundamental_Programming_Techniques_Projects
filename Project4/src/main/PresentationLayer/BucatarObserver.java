package PresentationLayer;

import java.io.Serializable;

import BusinessLayer.Restaurant;

public class BucatarObserver extends Observer implements Serializable {
	private ViewBucatar viewBucatar;
	
	public BucatarObserver(Restaurant rest, ViewBucatar view){
		super(rest);
	      this.viewBucatar = view;
	      System.out.println("I'm here");
	      this.rest.attach(this);
	      
	   }
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		Object[][] rows = new Object[1000][];
		rows = rest.viewTabelRestaurant();
		viewBucatar.getModel().setRowCount(0);
		for(int i=0;i<rows.length;i++)
		{
			viewBucatar.getModel().addRow(rows[i]);
		
		}
		
	}

	


}
