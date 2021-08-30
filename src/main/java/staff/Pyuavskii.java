package staff;

import java.util.Arrays;

public class Pyuavskii {
	double m = 5.0;
	double[] x = new double[2];
	double Rmax;
	int t;

	public double f(double x) {
		return Math.abs(x);
	}

	public double nextX(double x1, double x0) {
		return 0.5 * (x1 + x0) - (f(x1) - f(x0)) / (2 * m);
	}

	public double getR(double x1, double x0) {
		return 0.5 * m * (x1 - x0) - 0.5 * (f(x1) + f(x0));
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
		Pyuavskii str = new Pyuavskii();
		str.x[0] = -4;
		str.x[1] = 8;
		Arrays.sort(str.x);
		str.getX();
		str.getX();
		str.getX();
	}
}
