package modified.stream;

import java.io.FileWriter;
import java.io.IOException;

public class Runner {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {
		FileWriter writer = new FileWriter("note.txt", true);
		for (int i = 2; i < 125; i++) {
			for (int j = 2; j < 125; j++) {
				SimpleSystem qs = new SimpleSystem(4 * i, 4 * j);
				qs.launch();
				double d1 = Math.round(qs.result);
				if (d1 > 50) {
					System.out.print("--" + "| ");
					writer.write("-" + "| ");
				} else {
					System.out.print((int) d1 + "| ");
					writer.write(0 + "| ");
				}
			}
			System.out.println();
			writer.write("\n");
		}
	}
}
