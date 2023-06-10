package dev.rdh.compsci;

import java.util.*;
import java.io.PrintStream;

import lombok.experimental.*;

import static dev.rdh.compsci.util.ConsoleUtils.terminalWidth;

/**
 * testing class
 */
@SuppressWarnings({"unused", "NonAsciiCharacters"}) @UtilityClass
public class Main {
    public PrintStream so = System.out;
    public Scanner sc = new Scanner(System.in);
    public void main(String...ඞ) {
        so.println(terminalWidth());
    }
}