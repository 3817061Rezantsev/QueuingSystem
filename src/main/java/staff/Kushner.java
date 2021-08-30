package staff;

import java.util.Arrays;

public class Kushner {
	double m = 2.0;
	double[] x = new double[2];
	double Rmax;
	int t;
	double fMin;
	double b = 1;

	public double f(double x) {
		return x;
	}

	public double nextX(double x1, double x0) {
		return x0 + (x1 - x0) * (fMin - b - f(x1)) / (2 * (fMin - b) - f(x1) - f(x0));
	}

	public double getR(double x1, double x0) {
		return -4 * (fMin - b - f(x1)) * (fMin - b - f(x0)) / (x1 - x0);
	}

	public void getFMin() {
		fMin = f(x[0]);
		for (int i = 0; i < x.length; i++) {
			if (fMin > f(x[i])) {
				fMin = f(x[i]);
			}
		}
	}

	public void getX() {
		getRmax();
		double[] tmp = new double[x.length + 1];
		for (int i = 0; i < x.length; i++) {
			tmp[i] = x[i];
		}
		tmp[x.length] = nextX(x[t], x[t - 1]);
		x = tmp;
		Arrays.sort(x);
		print(x);
	}

	public void getRmax() {
		Rmax = getR(x[1], x[0]);
		t = 1;
		double R;
		for (int i = 1; i < x.length; i++) {
			R = getR(x[i], x[i - 1]);
			System.out.print(R + ", ");
			if (Rmax < R) {
				Rmax = R;
				t = i;
			}
		}
		System.out.println();
	}

	public void print(double[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + ", ");
		}
		System.out.println();
	}

	public static void main(String args[]) {
		Kushner str = new Kushner();
		str.x[0] = 0;
		str.x[1] = 1;
		Arrays.sort(str.x);
		str.getX();
		str.getX();
		str.getX();
	}
}
