import java.io.*;

public class Polynomial {
	private double[] coeffs;
	private int[] exponents;
	
	public Polynomial() {
		coeffs = new double[0];
		exponents = new int[0];
	}
	
	public Polynomial(double[] coeffs, int[] exponents) {
		this.coeffs = coeffs;
		this.exponents = exponents;
	}
	
	public Polynomial(File f) throws FileNotFoundException, IOException {
		BufferedReader input = new BufferedReader(new FileReader(f));
		String line = input.readLine();
		String[] expression = line.split("");
		int i = 0;
		if (expression[i].equals("-")) {
			i++;
		}
		int length = 1;
		while (i < expression.length) {
			if (expression[i].equals("+") || expression[i].equals("-")) {
				length++;
			}
			i++;
		}
		coeffs = new double[length];
		exponents = new int[length];
		int c_index = 0;
		int e_index = 0;
		for (int j = 0; j < expression.length; j++) {
			if (expression[j].equals("+")) {
				j = parseExpression(j, line, expression, c_index, e_index);
			}
			else {
				j = parseExpression(j - 1, line, expression, c_index, e_index);
			}
			c_index++;
			e_index++;
		}
	}
	
	private int parseExpression(int j, String line, String[] expression, int c_index, int e_index) {
		int k = j + 1;
		if (expression[k].equals("-")) {
			k++;
		}
		while (k < expression.length && !(expression[k].equals("x") || expression[k].equals("+") || expression[k].equals("-"))) {
			k++;
		}
		if (line.substring(j + 1, k).equals("")) {
			coeffs[c_index] = 1;
		}
		else if (line.substring(j + 1, k).equals("-")) {
			coeffs[c_index] = -1;
		}
		else {
			coeffs[c_index] = Double.parseDouble(line.substring(j + 1, k));
		}
		if (k >= expression.length || expression[k].equals("+") || expression[k].equals("-")) {
			exponents[e_index] = 0;
			return k - 1;
		}
		j = k;
		k++;
		while (k < expression.length && !(expression[k].equals("+") || expression[k].equals("-"))) {
			k++;
		}
		if (line.substring(j + 1, k).equals("")) {
			exponents[e_index] = 1;
		}
		else {
			exponents[e_index] = Integer.parseInt(line.substring(j + 1, k));
		}
		return k - 1;
	}
	
	public Polynomial add(Polynomial p) {
		double[] result_coeffs = new double[p.coeffs.length + coeffs.length];
		int[] result_exponents = new int[p.coeffs.length + coeffs.length];
		int index = 0;
		int p_index = 0;
		while (index < coeffs.length && p_index < p.coeffs.length) {
			if (exponents[index] == p.exponents[p_index]) {
				if (coeffs[index] + p.coeffs[p_index] != 0) {
					result_coeffs[index + p_index] = coeffs[index] + p.coeffs[p_index];
					result_exponents[index + p_index] = exponents[index];
				}
				index++;
				p_index++;
			}
			else if (exponents[index] < p.exponents[p_index]) {
				result_coeffs[index + p_index] = coeffs[index];
				result_exponents[index + p_index] = exponents[index];
				index++;
			}
			else {
				result_coeffs[index + p_index] = p.coeffs[p_index];
				result_exponents[index + p_index] = p.exponents[p_index];
				p_index++;
			}
		}
		if (index == coeffs.length) {
			while (p_index < p.coeffs.length) {
				result_coeffs[index + p_index] = p.coeffs[p_index];
				result_exponents[index + p_index] = p.exponents[p_index];
				p_index++;
			}
		}
		else {
			while (index < coeffs.length) {
				result_coeffs[index + p_index] = coeffs[index];
				result_exponents[index + p_index] = exponents[index];
				index++;
			}
		}
		Polynomial result = new Polynomial(new double[index + p_index], new int[index + p_index]);
		for (int i = 0; i < result.coeffs.length; i++) {
			result.coeffs[i] = result_coeffs[i];
			result.exponents[i] = result_exponents[i];
		}
		return result;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coeffs.length; i++) {
			result += coeffs[i] * Math.pow(x, exponents[i]);
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
	}
	
	public Polynomial multiply(Polynomial p) {
		double[] multiplyArrays = new double[exponents[exponents.length - 1] + p.exponents[p.exponents.length - 1] + 1];
		for (int i = 0; i < exponents.length; i++) {
			for (int j = 0; j < p.exponents.length; j++) {
				multiplyArrays[exponents[i] + p.exponents[j]] += coeffs[i] * p.coeffs[j];
			}
		}
		int length = 0;
		for (int k = 0; k < multiplyArrays.length; k++) {
			if (multiplyArrays[k] != 0) {
				length++;
			}
		}
		Polynomial result = new Polynomial(new double[length], new int[length]);
		int index = 0;
		for (int l = 0; l < multiplyArrays.length; l++) {
			if (multiplyArrays[l] != 0) {
				result.coeffs[index] = multiplyArrays[l];
				result.exponents[index] = l;
				index++;
			}
		}
		return result;
	}
	
	public void saveToFile(String file) throws FileNotFoundException, IOException {
		PrintStream output = new PrintStream(file);
		String s = "";
		for (int i = 0; i < coeffs.length; i++) {
			if (i > 0 && coeffs[i] > 0) {
				s += "+";
			}
			s += coeffs[i];
			if (exponents[i] != 0) {
				s += "x" + exponents[i];
			}
		}
		output.println(s);
	}
}