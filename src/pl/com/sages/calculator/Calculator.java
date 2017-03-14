package pl.com.sages.calculator;

import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;

public class Calculator implements Calculable {

	int wynik = 0;

	@Override
	public Integer calculate(String formula) {
		
		StringTokenizer st = new StringTokenizer(formula, "+-*/", true);

		int liczba;
		Character operator = null;
		
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
				operator = resolveOperator(operator, nextToken);
			}
		}
		return this.wynik;
	}

	private Character resolveOperator(Character operator, String nextToken) {
		switch (nextToken) {
		case "+":
			operator = '+';
			break;
		case "-":
			operator = '-';
			break;
		case "*":
			operator = '*';
			break;
		case "/":
			operator = '/';
			break;
		}
		return operator;
	}

	private void count(Character operator, int liczba) {
		switch (operator) {
		case '+':
			this.wynik += liczba;
			break;
		case '-':
			this.wynik -= liczba;
			break;
		case '*':
			this.wynik *= liczba;
			break;
		case '/':
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
