package random.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import queuing.system.QueuingSystem;
import random.stream.Bartlett;
import random.stream.Puasson;

public class QueuingSystemTest {

	@Test
	void getQueueTest() {
		Puasson puasson1 = new Puasson(0.1, 50);
		Puasson puasson2 = new Puasson(0.1, 50);
		assertNotSame(puasson1.getQueue(), puasson2.getQueue());
	}

	@Test
	void correctPuassonStream() {
		Puasson puasson = new Puasson(0.1, 50);
		double lyambdat = 0.1, timeLength = 50.0, time = 0.0;
		double lyambdast = 0.0;
		double mod = 1.0;
		int M = 0;
		while ((mod > 0.1 * lyambdat) && (time < 10000000)) {
			time += timeLength;
			M += puasson.getMas().length;
			lyambdast = (double) M / time;
			mod = Math.abs(lyambdat - lyambdast);
			puasson.generateStream();
		}
		assertTrue(mod < 0.05);
	}

	@Test
	void correctPuassonInBtStream() {
		Bartlett puasson = new Bartlett(0.1, 50);
		double lyambdat = 0.1, timeLength = 50.0, time = 0.0;
		double lyambdast = 0.0;
		double mod = 1.0;
		int M = 0;
		while (((mod > 0.1 * lyambdat)) && (time < 10000000)) {
			time += timeLength;
			M += puasson.getMas().length;
			lyambdast = (double) M / time;
			mod = Math.abs(lyambdat - lyambdast);
			puasson.generateStream();
		}
		assertTrue(mod < 0.05);
	}

	@Test
	void checkCorrectStreams() {
		double lyambdat = 0.1, timeLength = 50.0, time = 0.0;
		Bartlett bt = new Bartlett();
		double Mnt = 1.0 + bt.getR() / (1.0 - bt.getG());
		double lyambdabt = lyambdat / Mnt;
		double mod = 1.0;
		int M = 0;
		double mod1 = 10.0, mod2 = 10.0, mod3 = 10.0;
		double lyambdast = 0.0;
		double lyambdabst = 0.0;
		double Mnst = 0.0, rst = 0.0;
		double fc = 0, zero = 0, cc = 0;
		while (((mod > 0.1 * lyambdat) || (mod1 > 0.1 * lyambdabt) || (mod2 > 0.1 * Mnt) || (mod3 > 0.1 * bt.getR()))
				&& (time < 10000000)) {
			time += timeLength;
			M += bt.getMas().length;
			fc += bt.getFastCars();
			zero += bt.getEmptyPacks();
			cc += bt.getSlowCars();
			lyambdast = (double) M / time;
			mod = Math.abs(lyambdat - lyambdast);
			lyambdabst = (M - zero) / time;
			Mnst = (double) (M - zero + fc) / (double) (M - zero);
			rst = (double) cc / (double) (M - zero);
			mod1 = Math.abs(lyambdabt - lyambdabst);
			mod2 = Math.abs(Mnst - Mnt);
			mod3 = Math.abs(bt.getR() - rst);
			bt.generateStream();
			bt.generateCars();
			bt.calculateCars();
		}
		assertTrue(mod < 0.05);
		assertTrue(mod1 < 0.05);
		assertTrue(mod2 < 0.05);
		assertTrue(mod3 < 0.05);
	}

	@Test
	void checkQueuingTime() {
		QueuingSystem qs = new QueuingSystem();
		Queue<Double> queue = new LinkedList<>();
		
	}

}
