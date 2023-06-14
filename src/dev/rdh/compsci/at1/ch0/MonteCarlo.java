package dev.rdh.compsci.at1.ch0;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class MonteCarlo {

	/**
	 * Not-quite-industrial-strength™ program to calculate π using the Monte Carlo method. Usually gets pretty close, up to 4 or 5 decimal places in my testing.
	 * <p>For reference, π = 3.14159265358979323846264338327950... as far as I can remember.</p>
	 * <p>The non-strict method is about as fast as I can get it. I even tried multithreading, which (for some reason) made it slower instead of faster.</p>
	 * @param followAssignment whether to follow the assigment strictly (print out all the points and store them in an array). Not doing this has the advantage of being faster and using less memory. Based on my testing, with this set to true, the maximum input is around 60 million, whereas with it set to false, I was able to run it with 100 billion iterations.
	 * @author Rhys de Haan
	 */
	public static void run(boolean followAssignment) {
		// setup
		//noinspection DuplicatedCode
		Scanner in = new Scanner(System.in);

		// when the jvm ends (either if the program ends or if it runs out of memory), wait to exit until the user presses enter
		Thread hook = new Thread(() -> {
			try {
				// console() returns null in the intellij integrated terminal
				if(System.console() == null) {
					System.out.print("Press enter to exit");
					in.nextLine();
				} else {
					System.console().readPassword("Press enter to exit");
				}
				in.close();
				System.out.println("\033[?25h\033[?1049lFinished!");
			} catch(Exception e){
				/*
				 * for some reason readPassword works fine
				 * but it throws an exception after printing "Finished!"
				 * so this just gets rid of it
				 */
			}
		});
		Runtime.getRuntime().addShutdownHook(hook);

		System.out.print("\033[?1049h\033[?25h");
		System.out.println("\033[4;1mMonte Carlo\033[0m\n");
		String input = "This string is clearly not a number.";
		long iterations = 0;

		// get input
		while(!input.matches("\\d+|\\d+(\\.\\d+)?[kmb]")){ // regex to check if the input is a number or a number followed by a k, m, or b
			System.out.print("\r\033[1A\033[2K" + "Enter the number of iterations to run: ");
			input = in.nextLine();
			try {
				iterations = (long)parseInputNum(input);
			} catch(NumberFormatException e){
				input = "Uh oh.";
			}
		}

		// hide cursor
		System.out.print("\033[?25l");
		long numInCircle = 0;
		if (followAssignment) {
			double[] coords = new double[(int) (iterations * 2)];
			for (int i = 0; i < iterations; i++) {
				coords[i * 2] = 2 * Math.random() - 1; // x coordinate
				coords[i * 2 + 1] = 2 * Math.random() - 1; // y coordinate
			}

			for (int i = 0; i < iterations; i++) {
				double x = coords[i * 2];
				double y = coords[i * 2 + 1];
				if (Math.sqrt(x * x + y * y) <= 1)
					numInCircle++;
				System.out.println("Point " + i + ": (" + x + ", " + y + ")");
			}
		} else {
			long startTime = System.nanoTime();
			for (long j = 0; j < iterations; j++) {
				double x = 2 * Math.random() - 1;
				double y = 2 * Math.random() - 1;
				if (Math.sqrt(x * x + y * y) <= 1)
					numInCircle++;
			}
			System.out.println("Time taken: " + (System.nanoTime() - startTime) / 1e9 + " seconds");
		}
		System.out.println("\nPoints in circle: " + numInCircle + '/' + iterations);
		System.out.println("π ≈ " + 4 * (double) numInCircle / iterations);
		System.out.print("Do you want to go again? (y/n) ");

		Runtime.getRuntime().removeShutdownHook(hook); // remove the shutdown hook so that the program can exit without waiting for the user to press enter
		if (in.nextLine().toLowerCase().startsWith("y")) {
			System.out.print("\033[0;0H\033[J");
			run(followAssignment);
		}

		in.close();
		System.out.println("\033[?25h\033[?1049lFinished!");
	}
	static double parseInputNum(String str) {
		if(str.length() == 1) return Double.parseDouble(str);
		double num = Double.parseDouble(str.substring(0, str.length() - 1));
		char lastChar = Character.toLowerCase(str.charAt(str.length() - 1));
		return num * switch(lastChar){
			case 'k' -> 1e3; // alright, reasonable
			case 'm' -> 1e6; // getting a little big there
			case 'b' -> 1e9; // this is gonna take a while
			case 't' -> 1e12; // why
			case 'q' -> 1e15; // what are you doing
			case 's' -> 1e18; // stop
			case 'o' -> 1e21; // please
			case 'n' -> 1e24; // :((((((((((((
			case 'd' -> 1e27; // i'm begging you
			case 'u' -> 1e30; // please stop
			case 'e' -> 1e33; // look at this cute cat -> https://images.hdqwalls.com/wallpapers/cute-kitten-4k-im.jpg maybe if you stop now you can save it from the explosion that your computer is about to create
			case 'z' -> 1e36; // i give up
			case 'y' -> 1e39; // have fun destroying the universe (for reference this number is 1000000000000000000000000000000000000000 times num)
			default -> Double.parseDouble(str);
		};
	}

	public static void runIndef() {
		// setup
		Scanner in = new Scanner(System.in);

		// when the jvm ends (either if the program ends or if it runs out of memory), wait to exit until the user presses enter
		Thread hook = new Thread(() -> {
			try {
				// console() returns null in the intellij integrated terminal
				if(System.console() == null) {
					System.out.print("Press enter to exit");
					in.nextLine();
				} else {
					System.console().readPassword("Press enter to exit");
				}
				in.close();
				System.out.println("\033[?25h\033[?1049lFinished!");
			} catch (Exception ignoreme){/*since when was _ a keyword*/}
		});
		Runtime.getRuntime().addShutdownHook(hook);

		System.out.print("\033[?1049h\033[?25h");
		System.out.println("\033[4;1mMonte Carlo\033[0m\n");

		// hide cursor
		System.out.print("\033[?25l");
		long numInCircle = 0;
		long iterations = 0;

		while(true) {
			iterations++;
			double x = 2 * Math.random() - 1;
			double y = 2 * Math.random() - 1;
			if (Math.sqrt(x * x + y * y) <= 1)
				numInCircle++;

			if(iterations % 1e9 == 0) {
				System.out.println("\033[2A\033[Jπ ≈ " + 4 * (double) numInCircle / iterations);
				System.out.print("You have run " + (int)(iterations/1e9) + " billion times. Continue? (y/n) ");
				if(in.nextLine().toLowerCase().startsWith("n")) break;
			}
		}
		Runtime.getRuntime().removeShutdownHook(hook);
		in.close();
		System.out.println("\033[?25h\033[?1049lFinished!");
	}
}