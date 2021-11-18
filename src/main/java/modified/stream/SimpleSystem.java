package modified.stream;

import java.util.ArrayList;
import java.util.Queue;

import random.stream.Puasson;

public class SimpleSystem {
	protected double runningTime = 10000;
	protected ArrayList<Queue<Double>> requests = new ArrayList<>();
	protected double mashineTime = 0;
	protected double[] waitingMode = { 1, 1 };
	protected double[] servingMode = { 6, 6 };
	protected double[] servingTime = { 1, 1 };
	protected double[] l = { 0.01, 0.01 };
	protected double[] y = { 0, 0 };
	protected double[] servedRequests = { 0, 0 };
	protected double result = 0;
	protected double[] quequeLength;

	SimpleSystem() {
		requests.add((new Puasson(l[0], runningTime)).getQueue());
		requests.add((new Puasson(l[1], runningTime)).getQueue());
		quequeLength[0] = requests.get(0).size();
		quequeLength[1] = requests.get(1).size();
	}

	SimpleSystem(double servingModeF, double servingModeS) {
		Puasson puasson1 = new Puasson(l[0], runningTime);
		requests.add((new Puasson(l[0], runningTime)).getQueue());
		requests.add((new Puasson(l[1], runningTime)).getQueue());
		this.servingMode[0] = servingModeF;
		this.servingMode[1] = servingModeS;
		quequeLength[0] = puasson1.getMas().length;
		quequeLength[1] = requests.get(1).size();
	}

	public void waitingPhase(int stream) {
		mashineTime += waitingMode[stream];
	}

	public void servingPhase(int stream) {
		double realTime = mashineTime;
		mashineTime += servingMode[stream];
		while ((realTime + servingTime[stream] < mashineTime) && (!requests.get(stream).isEmpty())) {
			double waitingTime;
			if (requests.get(stream).peek() <= realTime) {
				waitingTime = realTime - requests.get(stream).peek() + servingTime[stream];
				realTime += servingTime[stream];
			} else {
				realTime = requests.get(stream).peek() + servingTime[stream];
				waitingTime = servingTime[stream];
			}
			requests.get(stream).poll();
			servedRequests[stream]++;
			y[stream] = (y[stream] * (servedRequests[stream] - 1) + waitingTime) / servedRequests[stream];
		}
	}

	public void launch() {
		int mod = 0;
		while (mashineTime < runningTime) {
			switch (mod) {
			case 0:
				waitingPhase(0);
				servingPhase(0);
				mod++;
				break;
			case 1:
				waitingPhase(1);
				servingPhase(1);
				mod = 0;
				break;
			}
		}
		result = (l[0] * y[0] + y[1] * l[1]) / (l[0] + l[1]);
		if ((quequeLength[0] / 2 > requests.get(0).size()) && (quequeLength[1] / 2 > requests.get(1).size())) {
			result = 10000;
		}
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
}
