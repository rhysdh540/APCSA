package dev.rdh.compsci;

import java.util.*;
import java.io.PrintStream;

import dev.rdh.compsci.util.Meth;
import lombok.experimental.*;

/**
 * testing class
 */
@SuppressWarnings({"unused", "NonAsciiCharacters"}) @UtilityClass
public class Main {
    public PrintStream so = System.out;
    public Scanner sc = new Scanner(System.in);
    public void main(String...ඞ) {
        so.println(Meth.obfuscate("Hello, world!"));
        so.println(new String(new byte[]{72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33}));
    }
}