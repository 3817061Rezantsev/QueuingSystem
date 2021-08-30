package queuing.system;

import java.util.Queue;

import random.stream.Bartlett;

public class RQSystemResB extends RQSystemRes {
	protected Bartlett firstB;
	protected Bartlett secondB;

	RQSystemResB() {
		firstB = new Bartlett();
		secondB = new Bartlett();
	}

	public void fillQueues() {
		firstB.generateStream();
		secondB.generateStream();
		Queue<Double> q1 = firstB.getQueue();
		Queue<Double> q2 = secondB.getQueue();
		while (!q1.isEmpty()) {
			firstQueue.add(q1.poll() + mashineTime);
		}
		while (!q2.isEmpty()) {
			secondQueue.add(q2.poll() + mashineTime);
		}
	}

	public void waitingPhase(double time) {
		mashineTime += time;
		firstB.setTime(time);
		secondB.setTime(time);
		calculateRes(time);
		fillQueues();
	}

}
