import java.io.*;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException, IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int [] e1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2,-9};
		int [] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		double [] c3 = {3, 5, 2, -1};
		int [] e3 = {0, 1, 3, 5};
		Polynomial p3 = new Polynomial(c3, e3);
		double [] c4 = {-2, -4, 5,-9};
		int [] e4 = {0, 2, 5, 7};
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial t = p3.multiply(p4);
		System.out.println("t(2) = " + t.evaluate(2));
		p1.saveToFile("C:\\Users\\shyam\\Downloads\\Java\\test.txt");
		File f = new File("C:\\Users\\shyam\\Downloads\\Java\\test.txt");
		p1 = new Polynomial(f);
		p2.saveToFile("C:\\Users\\shyam\\Downloads\\Java\\test.txt");
		p2 = new Polynomial(f);
		Polynomial u = p1.add(p2);
		System.out.println(u.evaluate(0.1) + ": " + (s.evaluate(0.1) == u.evaluate(0.1)));
		p3.saveToFile("C:\\Users\\shyam\\Downloads\\Java\\test.txt");
		p3 = new Polynomial(f);
		p4.saveToFile("C:\\Users\\shyam\\Downloads\\Java\\test.txt");
		p4 = new Polynomial(f);
		Polynomial v = p3.multiply(p4);
		System.out.println(v.evaluate(2) + ": " + (t.evaluate(2) == v.evaluate(2)));
		File f1 = new File("C:\\Users\\shyam\\Downloads\\Java\\test1.txt");
		File f2 = new File("C:\\Users\\shyam\\Downloads\\Java\\test2.txt");
		Polynomial a = new Polynomial(f1);
		Polynomial b = new Polynomial(f2);
		Polynomial c = a.add(b);
		Polynomial d = a.multiply(b);
		System.out.println(c.evaluate(4.7));
		System.out.println(d.evaluate(-2.3));
		System.out.println(b.evaluate((1 + Math.sqrt(22.2))/2));
		System.out.println(b.hasRoot((1 + Math.sqrt(22.2))/2));
	}
}