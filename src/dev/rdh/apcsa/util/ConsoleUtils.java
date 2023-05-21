package dev.rdh.apcsa.util;

public class ConsoleUtils {
    public static final int BOLD = 1,
    FAINT = 2,
    ITALIC = 3,
    UNDERLINE = 4,
    STRIKETHROUGH = 9,
    DOUBLE_UNDERLINE = 21,

    BLACK = 30,
    RED = 31,
    GREEN = 32,
    YELLOW = 33,
    BLUE = 34,
    MAGENTA = 35,
    CYAN = 36,
    WHITE = 37;

    public static final String RESET = "\033[0m";

    /**
     *
     * @param code the int code of the desired color
     * @param bright whether to make the color brighter (adds 60 to the code)
     * @return a String that can be printed out to make text after it that specific color
     */
    public static String text(int code, boolean bright) {
        if(bright)
            code += 60;
        return "\033[" + code + 'm';
    }

    /**
     *
     * @param code
     * @param bright
     * @return
     */
    public static String highlight(int code, boolean bright) {
        return text(code + 10, bright);
    }
    public static String rgb(int red, int green, int blue, boolean highlight) {
        return "\033[" + (highlight ? 48 : 38) + ';' + red + ';' + green + ';' + blue + 'm';
    }

    public static String hideCursor() {
        System.out.print("\033[?25l");
        return "";
    }
    public static String showCursor() {
        System.out.print("\033[?25h");
        return "";
    }
    public static String clrScrn() {
        System.out.print("\033[H\033[J"); // clears the screen and resets the cursor to line 0 column 0
        return "";
    }
    public static void err(String s) {
        System.err.print("\033[K\033[31m" + s + "\033[39m");
    }
    public static void wait(double secs) {
        try {
            Thread.sleep((long) (secs * 1000L));
        } catch (InterruptedException ignored) {}
    }
    public static void wait(int secs) {
        wait((double) secs);
    }
    public static void waitForEnter(String message) {
        System.out.println(clrScrn() + hideCursor() + message);
        System.console().readPassword(); // wait for enter and don't echo anything else typed
        showCursor();
    }

    public static native int terminalWidth();
    public static native int terminalHeight();
    static {
        System.load("/Users/rhys/coding/csa/APCSA/lib/libconsoleutils.dylib");
    }
}
