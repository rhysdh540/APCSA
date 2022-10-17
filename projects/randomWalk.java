import java.util.Scanner;
import java.util.Formatter;
class randomWalk {
  public static void main(String[] args) {
    System.out.println("\033[?25l\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m");
    Scanner sc = new Scanner(System.in);
    System.out.print("How many tests would you like to run? \033[?25h");
    int tests=-1;
    String placeholder = "";
    /* error handling yay */
    while(tests==-1){
      try{
        placeholder = sc.nextLine();
        tests = parseInputNum(placeholder);
        if(tests<1)
          throw new Exception();
      }catch(Exception e){
        System.out.print("\033[31mAn error occured.\n\033[0m\"" + placeholder + "\" is invalid!\nHow many tests would you like to run? ");
        tests = -1;
        sc = new Scanner(System.in);
      }
    }
    tests*=10;
    System.out.println("\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m\nRunning tests...");

    /* logic */
    double avg = 0.0;
    int greatest = 0, j;
    /* this line creates a timer to show later how long running the tests took */
    long startTime = System.nanoTime();
    for(int i = 0; i < tests; i++) {
      j = walk();
      if (j > greatest) greatest = j;
      avg += j;
    }
    /* timer end */
    double timePassed = (System.nanoTime()-startTime)/1000000000.0; // the divide changes nanoseconds to seconds
    avg /= tests;

    /* variables to display on the print */
    int mins = (int)timePassed/60;
    timePassed -=mins*60;
    String plural = (mins==1) ? "" : "s";
    String minDisplay = (mins==0) ? "" : mins + " minute" + plural + " and ";
    Formatter f = new Formatter();
    f.format("%,d", tests);
    
    System.out.print("\033[1;35mTests Complete\n\033[0mTook " + minDisplay + timePassed + " seconds.\nResults:\n\n" + f + " tests run.\nTests averaged "
    + (Math.round(avg*10000)/10000.0) + " steps per run.\nThe greatest number of steps taken in a single test was " + greatest + ".\033[?25h\n\n");
  }

  /* just a seperate method to run 1 walk */
  static int walk() {
    int pos = 3;
    int steps = 0;
    while(pos>=0 && pos<=6){
      pos += (((int)Math.round(Math.random()))*2)-1;
      // random rounded to closest int --> 0 or 1 --> *2 --> 0 or 2 --> -1 --> -1 or 1
      // basically instead of random 0 or 1 it does random -1 or +1 and adds that to the pos variable
      steps++;
    }
    return steps;
  }
  static int parseInputNum(String str){
    String number = str.substring(0,str.length()-1);
    int num = Integer.parseInt(number);
    if(str.endsWith("k"))
      return num*1000;
    if(str.endsWith("m"))
      return num*1000000;
    if(str.endsWith("b"))
      return num*1000000000;
    return num;
  }
}