package dev.rdh.apcsa;

import java.util.*;
import java.io.PrintStream;

import dev.rdh.apcsa.excercise.battleship.Battleship;
import lombok.experimental.*;

/**
 * testing class
 */
@SuppressWarnings({"unused", "NonAsciiCharacters"}) @UtilityClass
public class Main {
    public PrintStream so = System.out;
    public Scanner sc = new Scanner(System.in);
    public void main(String...ඞ) {
        new Battleship().run();
    }
}