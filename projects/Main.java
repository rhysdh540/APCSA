/**
 * testing class
 */
public class Main {
    public static void main(String[] args) {
        Date d = new Date(1, 1, 2000);
        System.out.println(d);
        d.advance(1, 1, 1);
        System.out.println(d);
        System.out.println(Date.advancedDate(d, 1, 1, 1));
    }
}
