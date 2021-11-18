package queuing.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainClass {
	public static void main(String args[]) throws IOException {
		FileWriter writer = new FileWriter("note.txt", true);
		QueuingSystem q = new QueuingSystem();
		System.out.println(q.toString() + "\n");
		//writer.write(q.toString() + "\n");
		for (int j = 1; j < 100; j++) {

			System.out.print(" " + j * 1 + "   ");
			//writer.write(" " + j * 1 + "   ");
			if (j % 3 == 0) {
				System.out.print(" ");
				//writer.write(" ");
			}
		}
		System.out.println();
		writer.write("\n");
		for (int i = 2; i < 125; i++) {
			if (i < 10) {
				System.out.print(" ");
				//writer.write(" ");
			}
			//System.out.print(i * 5 + "    ");
			//writer.write(i * 5 + "    ");
			for (int j = 2; j < 125; j++) {
				QueuingSystem qs = new QueuingSystem();
				// StaticQSystem qs = new StaticQSystem();
				qs.setServingModeF(4*i);
				qs.setServingModeS(4*j);
				qs.launch();
				double d1 = Math.round(qs.result);
				double d2 = Math.round(qs.yS * 1000);
				if ((d1 >= 1000) || (d1 <= 99)) {
					System.out.print("----" + "| ");
					writer.write("-" + "| ");
				} else {
					System.out.print(d1 + "| ");
					writer.write(0 + "| ");
				}
			}

			System.out.println();
			writer.write("\n");
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
