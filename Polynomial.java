public class Polynomial {
	double[] coeffs;
	
	public Polynomial() {
		coeffs = new double[1];
	}
	public Polynomial(double[] coeffs) {
		this.coeffs = coeffs;
	}
	
	public Polynomial add(Polynomial p) {
		int length = Math.max(coeffs.length, p.coeffs.length);
		Polynomial result = new Polynomial(new double[length]);
		for (int i = 0; i < length; i++) {
			if (i >= coeffs.length) {
				result.coeffs[i] = p.coeffs[i];
			}
			else if (i >= p.coeffs.length) {
				result.coeffs[i] = coeffs[i];
			}
			else {
				result.coeffs[i] = coeffs[i] + p.coeffs[i];
			}
		}
		return result;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coeffs.length; i++) {
			result += coeffs[i] * Math.pow(x, i);
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
	}
}