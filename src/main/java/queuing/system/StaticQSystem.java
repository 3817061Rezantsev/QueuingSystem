package queuing.system;

import java.util.LinkedList;
import java.util.Queue;


public class StaticQSystem {
	protected Queue<Double> firstQueue;
	protected Queue<Double> secondQueue;
	protected double mashineTime = 0;
	protected double waitingModeF = 1;
	protected double waitingModeS = 1;
	protected double servingModeF = 4;
	protected double servingModeS = 4;
	protected double servingTime = 1;
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

	public StaticQSystem() {
		firstQueue = new LinkedList<>();
		secondQueue = new LinkedList<>();
		fillQueues();
	}

	public void launch() {
		queueCountF = 0;
		queueCountS = 0;
		servedF = 0;
		servedS = 0;
		yF = 0;
		yS = 0;
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
	}

	public void controller() {
		if (mode == 4) {
			mode = 0;
		}
		mode++;
	}

	public void waitingPhase(double time) {
		mashineTime += time;
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
					wait = t;
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
			while ((t + servingTime < mashineTime) && (!secondQueue.isEmpty())
					&& (secondQueue.peek() + servingTime < mashineTime)) {
				double wait;
				if (t - secondQueue.peek() > 0) {
					wait = t - secondQueue.peek() + servingTime;
					t += servingTime;
				} else {
					wait = t;
					t = secondQueue.peek() + servingTime;
				}
				serve(secondQueue);
				servedS++;
				yS = (yS * (servedS - 1) + wait) / servedS;
			}
			if (!secondQueue.isEmpty() && (secondQueue.peek() < mashineTime) && (t < secondQueue.peek())) {
				double wait;
				wait = servingTime;
				t = secondQueue.peek() + servingTime;
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
		for (int i = 0; i < 1000; i++) {
			if (i % 2 == 0) {
				firstQueue.add((double) (i * 5));
			}
			if (i % 2 == 1) {
				secondQueue.add((double) (i * 5));
			}
		}
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
