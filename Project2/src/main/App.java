package ro.tuc.pt.assig2;

public class App 
{
    public static void main( String[] args )
    {
    	ViewPrincipal view=new ViewPrincipal();
		Controller controller=new Controller(view);
		view.setVisible(true);
    }
}
