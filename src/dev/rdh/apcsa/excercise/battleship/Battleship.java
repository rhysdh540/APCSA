package dev.rdh.apcsa.excercise.battleship;

import dev.rdh.apcsa.util.ConsoleUtils;

import static dev.rdh.apcsa.util.ConsoleUtils.*;

public class Battleship {
    char[] aToj = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
    public static final int[] SHIP_LENGTHS = { 2, 3, 3, 4, 5 };
    public static final String[] SHIP_NAMES = { "Destroyer", "Submarine", "Cruiser", "Battleship", "Carrier" };
    Player p1 = new Player(), p2 = new Player(), current, other;
    static java.util.Scanner sc = new java.util.Scanner(System.in);
    public void run() {
        onStartup();
        /*
         * init current to p1 and other to p2 (since current == null, it's not p1, so it becomes p1)
         * then init p1 ships, flip, and init p2 ships
         * then the game can begin
         */
        flip();
        initShips();
        waitForEnter("Your turn is over! Press enter to continue.");
        flip();
        initShips();
        int ignore = 0;
        do {
            if(++ignore != 1)
                ConsoleUtils.wait(1);
            waitForEnter("Your turn is over! Press enter to continue.");
            flip();
            askForGuess();
        } while (!p1.allSunk() && !p2.allSunk());
        clrScrn();
        System.out.println("\033[95mPlayer " + getCurrentPlayer() + " wins!\033[39m");
        System.out.println("\nPlayer 1's ships:");
        p1.printMyShips();
        System.out.println("\nPlayer 2's ships:");
        p2.printMyShips();
        System.out.println("\nPlayer 1's guesses:");
        p1.printOpponentGuesses();
        System.out.println("\nPlayer 2's guesses:");
        p2.printOpponentGuesses();
        System.out.println("\n\033[95mThanks for playing!\033[39m");
        sc.close();
    }

    public void askForGuess() {
        clrScrn();
        System.out.println("Hello, Player " + getCurrentPlayer());
        other.printOpponentGuesses();
        System.out.println("Sink your opponent's ships!");
        while(true) {
            int y = (Character.toUpperCase(getInputChar("Enter a row for your guess (A-J)", false, aToj)) - 'A');
            System.out.print("\033[1A\r");
            int x = getInputInt("Enter a column for your guess (1-10)", 1, 10);
            int didItWork = other.recordOpponentGuess(x - 1, y);
            if(didItWork == -1){
                System.out.print("\033[31mYou already guessed that!\033[39m\033[1F\033[K");
                continue;
            } else if(didItWork==1)
                System.out.println("\033[92mHit!\033[39m\033[J");
            else System.out.println("\033[31mMiss!\033[39m\033[J");
            break;
        }
    }
    public void initShips() {
        clrScrn();
        showCursor();
        System.out.println("Hello, Player " + getCurrentPlayer());
        current.printMyShips();
        int thisCount = 0;
        shipLoop: for(int i = 0; i < 5; i++) {
            int len = SHIP_LENGTHS[i];
            String name = SHIP_NAMES[i];
            System.out.println("You are placing a " + name.toLowerCase() + " (length " + len + ')');
            if(i!=0 && thisCount == 0)
                System.out.print("\n\033[96mShip placed!\033[39m\033[1A\r");
            int dir = getInputChar("What direction do you want your ship to be in? \033[4mH\033[0morizontal or \033[4mV\033[0mertical", true, 'H', 'h', 'V', 'v');
            clrBk();
            int dirInt = dir == 'h' || dir == 'H' ? 0 : 1;
            int x = -1, yInt = -1;
            char y = ' ';
            clrBk();
            while(y == ' '){
                y = getInputChar("Enter a row for the top left of your ship (A-J)", false, aToj);
                yInt = Character.toUpperCase(y) - 'A';
                if(dirInt==1) {
                    if (yInt-2 + len >= Grid.NUM_ROWS) {
                        err("Your ship is too long to fit there!\033[1F\033[K");
                        y = ' ';
                    }
                }
            }
            System.out.print("\033[J\033[1A\r\033[K");
            while(x == -1){
                x = getInputInt("Enter an column for the top left of your ship (1-10)", 1, 10);
                if(dirInt==0) {
                    if (x-2 + len >= Grid.NUM_COLS) {
                        err("Your ship is too long to fit there!\033[1F\033[K");
                        x = -1;
                    }
                }
            }
            x--;
            for(int j = 0; j < len; j++) {
                if(dirInt == 0) {
                    if(current.getMyGrid().hasShip(x+j, yInt)) {
                        err("\033[1AYour ship overlaps another ship!\033[1F\033[K\033[1F\033[K");
                        i--;
                        thisCount++;
                        continue shipLoop;
                    }
                } else {
                    if(current.getMyGrid().hasShip(x, yInt+j)) {
                        err("\033[1AYour ship overlaps another ship!\033[1F\033[K\033[1F\033[K");
                        i--;
                        thisCount++;
                        continue shipLoop;
                    }
                }
            }
            current.chooseShipLocation(new Ship(len), x, yInt, dirInt);
            clrBk();
            System.out.print("\033[2;0H\033[J");
            current.printMyShips();
            thisCount = 0;
        }
    }
    public void flip() {
        if(current == p1){
            other = p1;
            current = p2;
        } else {
            current = p1;
            other = p2;
        }
    }
    public static int getInputInt(String q, int lower, int higher) {
        System.out.print(q + ": ");
        String s = null;
        int i = 0;
        while (s == null) {
            try {
                s = sc.nextLine();
                i = Integer.parseInt(s);
                if (i < lower || i > higher) {
                    throw new NumberFormatException("big fish");
                }
                return i;
            } catch (NumberFormatException e) {
                s = null;
                err("Invalid input!\033[1F\033[K");
                System.out.print(q + ": ");
            }
        }
        return i;
    }
    public static char getInputChar(String q, boolean allowLong, char... allowed) {
        System.out.print(q + ": ");
        String s = null;
        char c = 0;
        //noinspection ConstantValue
        while (s == null) { // intellij isnt smart enough to realize that s can be not null thanks to our funky try/catch shenanigans
            try {
                s = sc.nextLine();
                if (!(s.length() == 1 || allowLong)) {
                    throw new NumberFormatException("big fish");
                }
                c = s.charAt(0);
                if(java.util.Arrays.binarySearch(allowed, Character.toLowerCase(c)) < 0)
                    throw new NumberFormatException("bigger fish");
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                s = null; // aforementioned funky try/catch shenanigans - if we get here, s is null, so the while loop will continue
                          // but if no error is thrown, s is not null, so the while loop will end
                err("Invalid input!\033[1F\033[K");
                System.out.print(q + ": ");
                continue;
            }
            return c;
        }
        return c;
    }
    public int getCurrentPlayer() { return current == p1 ? 1 : 2; }
    private static void clrBk() {
        System.out.print("\033[14;0H\033[0J"); // resets cursor to line 14 column 0 and clear the screen after that point
    }
    private static void onStartup() {
        hideCursor();
        for (int i = 0; i < 20; i++) {
            System.out.print("Initializing Battleship ");
            switch (i % 4) {
                case 0 -> {
                    System.out.print('|');
                    ConsoleUtils.wait(0.1);
                }
                case 1 -> System.out.print('/');
                case 2 -> System.out.print('—');
                case 3 -> System.out.print('\\');
            }
            ConsoleUtils.wait(0.2);
            System.out.print('\r');
        }
        if (terminalWidth() < 80) {
            System.out.println("Your terminal is too thin to play this game. Please make it at least this wide.|");
            System.exit(0);
        }
        System.out.println(clrScrn() + "Welcome to Battleship");
        ConsoleUtils.wait(3);
    }
    public static void main(String[] args) {
        new Battleship().run();
    }
}