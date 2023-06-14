package dev.rdh.compsci.at1.ch0;

import java.util.Scanner;

public class MonteCarlo {

	/**
	 * Not-quite-industrial-strength™ program to calculate π using the Monte Carlo method. Usually gets pretty close, up to 4 or 5 decimal places in my testing.
	 * <p>
	 * For reference, π = 3.14159265358979323846264338327950... as far as I can remember.
	 * </p>
	 *
	 * @param followAssignment whether to follow the assigment strictly (print out all the points and store them in an array). Not doing this has the advantage of being faster and using less memory.
	 * @author Rhys de Haan
	 */
	public static void run(boolean followAssignment) {
		// setup
		Scanner in = new Scanner(System.in);

		// when the jvm ends (either if the program ends or if it runs out of memory), wait to exit until the user presses enter
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				// console() returns null for some reason on some integrated terminals (namely intellij), works fine on vs code though
				if(System.console() != null) System.console().readPassword("Press enter to exit");
				else {
					System.out.print("Press enter to exit");
					in.nextLine();
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
		}));

		System.out.print("\033[?1049h\033[?25h");
		System.out.println("\033[4;1mMonte Carlo\033[0m\n");
		String input = "This string is clearly not a number.";
		long iterations = 0;

		// grab input
		while(!input.matches("\\d+|\\d+(\\.\\d+)?[kmb]")){ // basically either a number or a number followed by a k, m or b - decimal point is optional
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
		if(followAssignment){
			// generate and store random coordinates
			double[] coords = new double[(int)(iterations * 2)];
			for(int i = 0; i < (iterations > Integer.MAX_VALUE ? Integer.MAX_VALUE : iterations); i++){
				coords[i * 2] = 2 * Math.random() - 1; // x coordinate
				coords[i * 2 + 1] = 2 * Math.random() - 1; // y coordinate
			}

			for(int i = 0; i < iterations; i++){
				double x = coords[i * 2];
				double y = coords[i * 2 + 1];
				if(Math.sqrt(x * x + y * y) <= 1) numInCircle++;
				System.out.println("Point " + i + ": (" + x + ", " + y + ")");
			}

		} else {
			// generate and check random coordinates
			for(long i = 0; i < iterations; i++){
				double x = 2 * Math.random() - 1, y = 2 * Math.random() - 1;
				if(Math.sqrt(x * x + y * y) <= 1) numInCircle++;
			}
		}
		System.out.println("\nPoints in circle: " + numInCircle + '/' + iterations);
		System.out.println("π ≈ " + 4 * (double)numInCircle / iterations);
		System.out.print("Do you want to go again? (y/n) ");
		var response = in.nextLine();
		if(response.toLowerCase().startsWith("y")){
			System.out.print("\033[0;0H\033[J");
			run(followAssignment);
		}
	}

	static double parseInputNum(String str) {
		if(str.length() == 1) return Double.parseDouble(str);
		double num = Double.parseDouble(str.substring(0, str.length() - 1));
		char lastChar = Character.toLowerCase(str.charAt(str.length() - 1));
		return switch(lastChar){
			case 'k' -> num * 1000;
			case 'm' -> num * 1000000;
			case 'b' -> num * 1000000000;
			default -> Double.parseDouble(str);
		};
	}
}