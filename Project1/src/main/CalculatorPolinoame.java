package ro.tuc.pt.assig1;

public class CalculatorPolinoame {

	public static void main(String[] args) {

		ViewCalculator view=new ViewCalculator();
		ControllerCalculator controller=new ControllerCalculator(view);
		view.setVisible(true);
		

	}

}
