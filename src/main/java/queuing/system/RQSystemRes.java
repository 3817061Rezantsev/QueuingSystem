package queuing.system;

public class RQSystemRes extends RQSystem {
	protected int res1 = 5;
	protected int res2 = 5;
	protected int load1 = 8;
	protected int load2 = 8;

	public void calculateRes(double T) {
		res1 += T;
		res2 += T;
	}

	public void waitingPhase(double time) {
		mashineTime += time;
		firstStream.setTime(time);
		secondStream.setTime(time);
		calculateRes(time);
		fillQueues();
	}

	public void servingPhase(int steamNum) {
		if (steamNum == 1) {
			waitingPhase(servingModeF);
			double t = mashineTime - servingModeF;
			while ((t + servingTime < mashineTime) && (!firstQueue.isEmpty())
					&& (firstQueue.peek() + servingTime < mashineTime) && (res1 > load1)) {
				double wait;
				res1 -= load1;
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
			if (!firstQueue.isEmpty() && (firstQueue.peek() < mashineTime) && (t < firstQueue.peek())
					&& (res2 > load2)) {
				double wait;
				wait = servingTime;
				res2 -= load2;
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

	public void controller() {
		switch (mode) {
		case 1:
			mode++;
			break;
		case 2:
			if ((firstQueue.size() < secondQueue.size()) || (res1 < load1)) {
				mode++;
			}
			break;
		case 3:
			mode++;
			break;
		case 4:
			if ((firstQueue.size() > secondQueue.size()) || (res2 < load2)) {
				mode = 1;
			}
			break;
		}

	}
}
