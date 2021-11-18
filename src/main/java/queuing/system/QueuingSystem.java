package queuing.system;

import java.util.LinkedList;
import java.util.Queue;

import random.stream.Puasson;

public class QueuingSystem {
	protected Puasson firstStream;
	protected Puasson secondStream;
	protected double lF = 0.01;
	protected double lS = 0.01;
	protected Queue<Double> firstQueue;
	protected Queue<Double> secondQueue;
	protected double mashineTime = 0;
	protected double waitingModeF = 1;
	protected double waitingModeS = 1;
	protected double servingModeF = 6;
	protected double servingModeS = 6;
	protected double servingTime = 2;
	protected double servingTimeS = 2;
	protected int servedF = 0;
	protected int servedS = 0;
	protected int mode = 1;
	protected double yF = 0;
	protected double yS = 0;
	protected double queueCountF = 0;
	protected double queueCountS = 0;
	protected int serveQCountF = 0;
	protected int serveQCountS = 0;
	protected double result = 0;

	public QueuingSystem() {
		firstStream = new Puasson(lF);
		secondStream = new Puasson(lS);
		firstQueue = new LinkedList<>();
		secondQueue = new LinkedList<>();
	}

	public String toString() {
		return "Lyambda of the first and second stream: " + lF + " " + lS + "\n"
				+ "Waiting mode time of the first and second stream: " + waitingModeF + " " + waitingModeS + "\n" +
				"Serving time of the first and second stream: " + servingTime + " " + servingTimeS;
	}

	public void launch() {
		queueCountF = 0;
		queueCountS = 0;
		servedF = 0;
		servedS = 0;
		yF = 0;
		yS = 0;
		firstQueue.clear();
		secondQueue.clear();
		int iter = 0;
		while (iter < 1000) {
			iter++;
			switch (mode) {
			case 1:
				waitingPhase(waitingModeF);
				break;
			case 2:
				servingPhase(1);
				break;
			case 3:
				waitingPhase(waitingModeS);
				break;
			case 4:
				servingPhase(2);
			}
			controller();
		}
		result = (lF * yF + yS * lS) / (lF + lS);
	}

	public void controller() {
		if (mode == 4) {
			mode = 0;
		}
		mode++;
	}

	public void waitingPhase(double time) {
		mashineTime += time;
		firstStream.setTime(time);
		secondStream.setTime(time);
		fillQueues();
	}

	public void servingPhase(int steamNum) {
		if (steamNum == 1) {
			waitingPhase(servingModeF);
			double t = mashineTime - servingModeF;
			while ((t + servingTime < mashineTime) && (!firstQueue.isEmpty())
					&& (firstQueue.peek() + servingTime < mashineTime)) {
				double wait;
				if (t - firstQueue.peek() > 0) {
					wait = t - firstQueue.peek() + servingTime;
					t += servingTime;
				} else {
					wait = servingTime;
					t = firstQueue.peek() + servingTime;
				}
				serve(firstQueue);
				servedF++;
				yF = (yF * (servedF - 1) + wait) / servedF;
			}
			if (!firstQueue.isEmpty() && (firstQueue.peek() < mashineTime) && (t < firstQueue.peek())) {
				double wait;
				wait = servingTime;
				t = firstQueue.peek() + servingTime;
				servedF++;
				serve(firstQueue);
				yF = (yF * (servedF - 1) + wait) / servedF;
			}
			serveQCountF++;
			queueCountF = (queueCountF + firstQueue.size()) / serveQCountF;
		}
		if (steamNum == 2) {
			waitingPhase(servingModeS);
			double t = mashineTime - servingModeS;
			while ((t + servingTimeS < mashineTime) && (!secondQueue.isEmpty())
					&& (secondQueue.peek() + servingTimeS < mashineTime)) {
				double wait;
				if (t - secondQueue.peek() > 0) {
					wait = t - secondQueue.peek() + servingTimeS;
					t += servingTimeS;
				} else {
					wait = servingTimeS;
					t = secondQueue.peek() + servingTimeS;
				}
				serve(secondQueue);
				servedS++;
				yS = (yS * (servedS - 1) + wait) / servedS;
			}
			if (!secondQueue.isEmpty() && (secondQueue.peek() < mashineTime) && (t < secondQueue.peek())) {
				double wait;
				wait = servingTimeS;
				t = secondQueue.peek() + servingTimeS;
				servedS++;
				serve(secondQueue);
				yS = (yS * (servedS - 1) + wait) / servedS;
			}
			serveQCountS++;
			queueCountS = (queueCountS + secondQueue.size()) / serveQCountS;
		}

	}

	public void serve(Queue<Double> queue) {
		queue.poll();
	}

	public void fillQueues() {
		firstStream.generateStream();
		secondStream.generateStream();
		Queue<Double> q1 = firstStream.getQueue();
		Queue<Double> q2 = secondStream.getQueue();
		while (!q1.isEmpty()) {
			firstQueue.add(q1.poll() + mashineTime);
		}
		while (!q2.isEmpty()) {
			secondQueue.add(q2.poll() + mashineTime);
		}
	}

	public Puasson getFirstStream() {
		return firstStream;
	}

	public void setFirstStream(Puasson firstStream) {
		this.firstStream = firstStream;
	}

	public Puasson getSecondStream() {
		return secondStream;
	}

	public void setSecondStream(Puasson secondStream) {
		this.secondStream = secondStream;
	}

	public double getMashineTime() {
		return mashineTime;
	}

	public void setMashineTime(double mashineTime) {
		this.mashineTime = mashineTime;
	}

	public double getWaitingModeF() {
		return waitingModeF;
	}

	public void setWaitingModeF(double waitingModeF) {
		this.waitingModeF = waitingModeF;
	}

	public double getWaitingModeS() {
		return waitingModeS;
	}

	public void setWaitingModeS(double waitingModeS) {
		this.waitingModeS = waitingModeS;
	}

	public double getServingModeF() {
		return servingModeF;
	}

	public void setServingModeF(double servingModeF) {
		this.servingModeF = servingModeF;
	}

	public double getServingModeS() {
		return servingModeS;
	}

	public void setServingModeS(double servingModeS) {
		this.servingModeS = servingModeS;
	}

	public double getServingTime() {
		return servingTime;
	}

	public void setServingTime(double servingTime) {
		this.servingTime = servingTime;
	}

	public Queue<Double> getFirstQueue() {
		return firstQueue;
	}

	public void setFirstQueue(Queue<Double> firstQueue) {
		this.firstQueue = firstQueue;
	}

	public Queue<Double> getSecondQueue() {
		return secondQueue;
	}

	public void setSecondQueue(Queue<Double> secondQueue) {
		this.secondQueue = secondQueue;
	}
}
