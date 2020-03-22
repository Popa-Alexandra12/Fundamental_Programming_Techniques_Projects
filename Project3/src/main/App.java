package ro.tuc.pt.Tema3_pt;

import PresentationClasses.ControllerPrincipal;
import PresentationClasses.ViewPrincipal;

public class App 
{
    
    	public static void main(String[] args) {
    		ViewPrincipal vp = new ViewPrincipal();
    		ControllerPrincipal p=new ControllerPrincipal(vp);
    		
    		
    	}
    
}
