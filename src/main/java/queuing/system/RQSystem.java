package queuing.system;

public class RQSystem extends QueuingSystem {
	public void controller() {
		switch (mode) {
		case 1:
			mode++;
			break;
		case 2:
			if (firstQueue.size() < secondQueue.size()) {
				mode++;
			}
			break;
		case 3:
			mode++;
			break;
		case 4:
			if (firstQueue.size() > secondQueue.size()) {
				mode = 1;
			}
			break;
		}

	}
}
