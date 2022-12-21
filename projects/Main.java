import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * testing class
 */
public class Main {
    static PrintStream so = System.out;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] áŗģß) {
        int s = Character.getNumericValue((Character)(char)(int)(double)(Double)(double)(Integer)(int)'e');
        so.println(s);
        Email m = new Email("rhysdh7258@gmail.com", SECRETDONOTCOMMITTOGITHUB.PASSWORD, "shaurya_grover@ryecountryday.org", "do the history project", "pls");
        m.print();
        for(int i : new int[15]){}
            //m.send(false);
    }
    public static ArrayList<String> shellSort(ArrayList<String> list) {
        int n = list.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                String temp = list.get(i);
                int j;
                for (j = i; j >= gap && list.get(j - gap).compareTo(temp) > 0; j -= gap)
                    list.set(j, list.get(j - gap));
                list.set(j, temp);
        }} return list;
    }
}