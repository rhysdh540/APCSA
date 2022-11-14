import java.io.PrintStream;
import java.util.Arrays;

public class Main {
    static PrintStream so = System.out;
    public static void main(String[] args) {
        Date d = new Date("6/14/1991", true);
        so.println(d.dayOfWeekButItDoesntMakeAnySense() + ", " + d.toString());

    }
}