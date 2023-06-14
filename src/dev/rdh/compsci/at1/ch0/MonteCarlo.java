package dev.rdh.compsci.at1.ch0;

import java.util.ArrayList;
import java.util.Scanner;

public class MonteCarlo {

	/**
	 * Simple™ program to calculate π using the Monte Carlo method. Requires Java 16.
	 * <p>The biggest flaw with this is that the assignment requires me to store each coordinate. Because I elected to store them in an {@code ArrayList}, the JVM runs out of memory extremely quickly, and any value for the {@code iterations} variable above 60,000,000 (that i've tested) burns through the 4GB of RAM that I allocated to it.</p>&nbsp;
	 * @author Rhys de Haan
	 */
	public static void run() {
		// setup
		Scanner in = new Scanner(System.in);

		// when the jvm ends (either if the program ends or if it runs out of memory), wait to exit until the user presses enter
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			// console() returns null for some reason on some integrated terminals (namely intellij), works fine on vs code though
			if(System.console() != null)
				System.console().readPassword("Press enter to exit");
			else {
				System.out.print("Press enter to exit");
				in.nextLine();
			}
			in.close();
			System.out.print("\033[?25h\033[?1049l");
		}));

		System.out.print("\033[?1049h\033[?25h");
		System.out.println("Monte Carlo\n");
		String input = "This string is clearly not a number.";
		int iterations = 0;

		// grab input
		while(!input.substring(0, input.length()-1).matches("\\d+")){
			System.out.print("\r\033[1A\033[2K" + "Enter the number of iterations to run: ");
			input = in.nextLine();
			try {
				iterations = (int)parseInputNum(input);
			} catch (NumberFormatException e) {
				input = "Uh oh.";
			}
		}

		// hide cursor
		System.out.print("\033[?25l");

		// generate and store random coordinates
		ArrayList<Coordinate> coords = new ArrayList<>();
		for(int i = 0; i < iterations; i++)
			coords.add(new Coordinate(rand(), rand()));

		// calculate how many points are in the circle
		int numInCircle = 0;

		for(Coordinate coord : coords)
			if(Math.sqrt(Math.pow(coord.x(), 2) + Math.pow(coord.y(), 2)) <= 1)
				++numInCircle;

		System.out.println("π ≈ " + 4 * (double)numInCircle / iterations);
	}
	public static double rand() {
		return 2 * Math.random() - 1;
	}
	static double parseInputNum(String str) {
		if (str.length() == 1)
			return Double.parseDouble(str);
		double num = Double.parseDouble(str.substring(0, str.length() - 1));
		char lastChar = Character.toLowerCase(str.charAt(str.length() - 1));
		return switch(lastChar){
			case 'k' -> num * 1000;
			case 'm' -> num * 1000000;
			case 'b' -> num * 1000000000;
			default -> Double.parseDouble(str);
		};
	}
	// just holds 2 final doubles
	public record Coordinate(double x, double y){}
}