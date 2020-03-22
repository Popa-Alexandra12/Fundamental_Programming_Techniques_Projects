package ro.tuc.pt.assig4;

import BusinessLayer.Restaurant;
import PresentationLayer.ControllerPrincipal;
import PresentationLayer.ViewPrincipal;


public class App 
{
    public static void main( String[] args )
    {
    	Restaurant rest=new Restaurant();
		Restaurant restaurant = new Restaurant();
		restaurant = rest.deserialization();
		ViewPrincipal v=new ViewPrincipal();
		v.setVisible(true);
		ControllerPrincipal c=new ControllerPrincipal(v,restaurant);
    }
}
