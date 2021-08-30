package queuing.system;

public class MainClass {
	public static void main(String args[]) {
		for (int j = 6; j < 24; j++) {
			System.out.print(j * 1 + "    ");
			if (j % 3 == 0)
				System.out.print(" ");
		}
		System.out.println();

		for (int i = 7; i < 24; i++) {
			// QueuingSystem qs = new RQSystem();
			System.out.print(i * 1 + "    ");
			for (int j = 7; j < 24; j++) {
				// QueuingSystem qs = new RQSystemB();
				QueuingSystem qs = new RQSystemRes();
				qs.setServingModeF(1 * i);
				qs.setServingModeS(1 * j);
				qs.launch();
				double d1 = Math.round(qs.result * 10);
				double d2 = Math.round(qs.yS * 1000);
				if (d1 > 1000)
					System.out.print("----" + "| ");
				else
					System.out.print(d1 / 10 + "| ");
			}

			System.out.println();
		}

//		StaticQSystem qs = new StaticQSystem();
//		qs.launch();
//		double d1 = Math.round(qs.yF * 1000);
//		double d2 = Math.round(qs.yS * 1000);
//		System.out.print(d1 / 1000 + " " + d2 / 1000 + " | ");
//		QueuingSystem qs = new RQSystem();
//		qs.setServingModeF(20);
//		qs.setServingModeS(20);
//		qs.launch();
//		double d1 = Math.round(qs.yF * 1000);
//		double d2 = Math.round(qs.yS * 1000);
//		System.out.print(d1 / 1000 + " " + d2 / 1000 + " FQueue size: " + qs.firstQueue.size() + " SQueue size: " + qs.secondQueue.size() + "| ");
//		System.out.print(d1 / 1000 + " " + d2 / 1000 + " | ");

	}
}
