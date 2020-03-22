package ro.tuc.pt.assig1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SingleSelectionModel;

public class ControllerCalculator {

	private ViewCalculator view;

	public ControllerCalculator(ViewCalculator view) {
		this.view = view;
		this.view.addAddBtnListener(new CalcListener());
		this.view.addSubBtnListener(new CalcListener());
		this.view.addMulBtnListener(new CalcListener());
		this.view.addDivBtnListener(new CalcListener());
		this.view.addDerivBtnListener(new CalcListener());
		this.view.addIntegBtnListener(new CalcListener());
	}

	class CalcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			IOperation op = this.getInstance(s);
			try {
				String p1S = view.getFirstPolinom();
				String p2S = view.getSecondPolinom();
				Polinom p1 = transformaInput(p1S);
				Polinom p2 = transformaInput(p2S);
				if (s.equals("∶")) {
					if ((p2.getElem().get(0).getCoef() == 0 && p2.getElem().get(0).getDegree() == 0)
							|| p2.getElem().get(0).getDegree() > p1.getElem().get(0).getDegree()) {
						if (p1.getElem().get(0).getDegree() == 0 && p1.getElem().get(0).getCoef() == 0) {
							setareBipolinom(op, p1, p2);
						} else
							inputNecorespunzator();
					} else {
						setareBipolinom(op, p1, p2);
					}
				} else {
					setareRezultat(op, p1, p2);
				}
			} catch (NumberFormatException ex) {
				System.err.println("An  Exception occured. Details are provided below:");
				ex.printStackTrace(System.err);
			}

		}

		public IOperation getInstance(String s) {
			switch (s) {
			case "+":
				return new AddOp();
			case "-":
				return new SubOp();
			case "∗":
				return new MulOp();
			case "∶":
				return new DivOp();
			case "'":
				return new DerivOp();
			case "∫":
				return new IntegOp();
			default:
				return null;

			}
		}

		public Polinom transformaInput(String s) {
			Pattern p = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
			Matcher m = p.matcher(s);
			Monom monom;
			Polinom pol = new Polinom();
			float c = 0;
			int d = 0;
			while (m.find()) {
				c = Float.valueOf(m.group(1));
				d = Integer.valueOf(m.group(2));
				monom = new Monom(c, d);
				pol.addMonom(monom);

			}
			return pol;

		}

		public void setareRezultat(IOperation op, Polinom p1, Polinom p2) {
			Result r = new Result();
			r = op.compute(p1, p2);
			view.setCalcRezultat(r.getRezultat().toString());
			view.setCalcRest(null);

		}

		public void setareBipolinom(IOperation op, Polinom p1, Polinom p2) {
			Bipolinom r = new Bipolinom();
			r = (Bipolinom) op.compute(p1, p2);
			view.setCalcRezultat(r.getRezultat().toString());
			view.setCalcRest(r.getRest().toString());
		}

		public void inputNecorespunzator() {
			System.out.println("Input necorespunzator !!!");
			view.setCalcRezultat(null);
			view.setCalcRezultat(null);
		}

	}

}
