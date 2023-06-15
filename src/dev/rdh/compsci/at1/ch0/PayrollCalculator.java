package dev.rdh.compsci.at1.ch0;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Input file should be structured in the following format:<br>
 * <p>{@code 101A; Cruté, Darien; 173.45; 4; 8; 3; 3; 7;}</p><br>
 * Fields Represent:<br>
 * Employee ID; Name; Hourly Rate; [Hours worked per day(3-7 days)]<br>
 * </p><p>
 * Output file should be structured in the following format:<br>
 * <p>{@code 101A; Cruté, Darien; 4336.25;}</p><br>
 * Fields Represent:<br>
 * Employee ID; Name; Amount Earned;<br>
 * </p>
 */
public class PayrollCalculator {
	public static void run(File input, File output) {
		// setup input
		Scanner sc;
		try{
			sc = new Scanner(input);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		// read input
		List<String> lines = new ArrayList<>();
		while(sc.hasNext())
			lines.add(sc.nextLine());
		sc.close();

		// setup output
		if(!output.exists()) {
			output.getParentFile().mkdirs();
			try {
				output.createNewFile();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		PrintStream out;
		try {
			out = new PrintStream(output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// process input and write output
		for(String line : lines) {
			if(line.startsWith("#") || line.startsWith("//")) continue; // ignore comments
			String[] fields = line.substring(0, line.length()-1).split("; ");
			String id = fields[0];
			String name = fields[1];
			double rate = Double.parseDouble(fields[2]);
			double total = 0;
			for(int i = 3; i < fields.length; i++)
				total += Integer.parseInt(fields[i]) * rate;
			out.println(id + "; " + name + "; " + new Formatter().format("%.2f", total) + ";");
		}
	}

	/**
	 * {@link #run(File, File)} but strings instead of files
	 * @param input path to input file
	 * @param output path to output file
	 */
	public static void run(String input, String output) {
		run(new File(input), new File(output));
	}
}
