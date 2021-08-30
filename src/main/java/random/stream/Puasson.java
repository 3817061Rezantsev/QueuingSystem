package random.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Puasson {
	protected double lyambda = 0.01;
	protected double time = 5;
	protected double[] mas;

	public Puasson() {
		generateStream();
	}

	public Puasson(double lyambda) {
		this.lyambda = lyambda;
		generateStream();
	}

	public Puasson(double lyambda, double time) {
		this.lyambda = lyambda;
		this.time = time;
		generateStream();
	}

	public void generateStream() {
		int numOfMoments;
		double[] t;
		double f = 0;
		double s = 1;
		Random random = new Random();
		double p = random.nextDouble() * Math.exp(lyambda * time);
		int i = 0;
		while (f < p) {
			f += s;
			i++;
			s = s * lyambda * time / i;
		}
		numOfMoments = i;
		if (numOfMoments != 0) {
			t = new double[numOfMoments];
			for (i = 0; i < numOfMoments; i++) {
				t[i] = random.nextDouble() * time;
			}
			Arrays.sort(t);
			mas = t;
		}
	}

	public double getLyambda() {
		return lyambda;
	}

	public void setLyambda(double lyambda) {
		this.lyambda = lyambda;
	}

	public Queue<Double> getQueue() {
		Queue<Double> q = new LinkedList<>();
		for (int i = 0; i < mas.length; i++) {
			q.add(mas[i]);
		}
		return q;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double[] getMas() {
		return mas;
	}

	public void setMas(double[] mas) {
		this.mas = mas;
	}

}
