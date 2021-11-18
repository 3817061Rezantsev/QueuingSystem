package random.stream;

import java.util.Random;

public class Bartlett extends Puasson {
	private double r = 0.5;
	private double g = 0.5;
	private int[] carsPack;
	private int emptyPacks;
	private int fastCars;
	private int slowCars;

	public String toString() {
		return "r and g: " + r + " " + g;
	}

	public Bartlett() {
		super();
		generateCars();
		calculateCars();
	}

	public Bartlett(double lyambda, double time) {
		super(lyambda, time);
		generateCars();
		calculateCars();
	}

	public Bartlett(double lyambda, double time, double r, double g) {
		super(lyambda, time);
		this.r = r;
		this.g = g;
		generateCars();
		calculateCars();
	}

	public void calculateCars() {
		emptyPacks = 0;
		slowCars = 0;
		fastCars = 0;
		for (int i = 0; i < carsPack.length; i++) {
			emptyPacks = carsPack[i] == 0 ? emptyPacks + 1 : emptyPacks;
			slowCars = carsPack[i] == 1 ? slowCars + 1 : slowCars;
			fastCars += carsPack[i];
		}
		fastCars = fastCars - carsPack.length + emptyPacks;
	}

	public void generateCars() {
		carsPack = new int[mas.length];
		for (int i = 0; i < mas.length; i++) {
			carsPack[i] = getBartlettValue();
		}
	}

	public int getBartlettValue() {
		double s = 1.0;
		double f = 1.0;
		Random random = new Random();
		double p = random.nextDouble();
		f = 1 - r;
		s = r * (1 - g);
		if (f > p)
			return 0;
		int i = 0;
		while (f < p) {
			f += s;
			s *= g;
			if (s < 0.0000001)
				return i;
			i++;
		}
		return i;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public int[] getCars() {
		return carsPack;
	}

	public void setCars(int[] cars) {
		this.carsPack = cars;
	}

	public int getEmptyPacks() {
		return emptyPacks;
	}

	public void setEmptyPacks(int emptyPacks) {
		this.emptyPacks = emptyPacks;
	}

	public int getFastCars() {
		return fastCars;
	}

	public void setFastCars(int fastCars) {
		this.fastCars = fastCars;
	}

	public int getSlowCars() {
		return slowCars;
	}

	public void setSlowCars(int slowCars) {
		this.slowCars = slowCars;
	}

}
