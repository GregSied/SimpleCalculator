package pl.com.sages.calculator;

import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;

public class Calculator implements Calculable {

	int wynik = 0;

	@Override
	public Integer calculate(String formula) {
		
		StringTokenizer st = new StringTokenizer(formula, "+-*/", true);

		int liczba;
		CalculateOperator operator = null;
		
		while (st.hasMoreElements()) {
			
			String nextToken = st.nextToken();
			
			if (NumberUtils.isCreatable(nextToken)) {
				liczba = NumberUtils.toInt(nextToken);
				if (operator != null) {
					count(operator, liczba);
				} else {
					wynik = liczba;
				}
			} else {
				operator = resolveOperator(nextToken);
			}
		}
		return this.wynik;
	}

	private CalculateOperator resolveOperator(String nextToken) {
		
		CalculateOperator operator = null;
		
		switch (nextToken) {
		case "+":
			operator = CalculateOperator.SUM;
			break;
		case "-":
			operator = CalculateOperator.SUBSTRACT;
			break;
		case "*":
			operator = CalculateOperator.MULTIPLY;
			break;
		case "/":
			operator = CalculateOperator.DIVIDE;
			break;
		}
		return operator;
	}

	private void count(CalculateOperator operator, int liczba) {
		switch (operator) {
		case SUM:
			this.wynik += liczba;
			break;
		case SUBSTRACT:
			this.wynik -= liczba;
			break;
		case MULTIPLY:
			this.wynik *= liczba;
			break;
		case DIVIDE:
			this.wynik /= liczba;
			break;
		}

	}

	public static void main(String[] args) {
		Calculable calculator = new Calculator();
		String formula = "60+50/2";
		Integer result = calculator.calculate(formula);
		System.out.println("WYNIK : " + formula + "=" + result);
	}

}
