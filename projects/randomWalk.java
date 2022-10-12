import java.util.Scanner;
class Main {
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
        tests = Integer.parseInt(placeholder);
        if(tests<1)
          throw new Exception();
      }catch(Exception e){
        System.out.print("\033[31mAn error occured.\n\033[0m\"" + placeholder + "\" is invalid!\nHow many tests would you like to run?");
        tests = -1;
        sc = new Scanner(System.in);
      }
    }
    /* logic */
    double avg = 0.0;
    int greatest = 0, j;
    for(int i = 0; i < tests; i++){
      j = walk();
      if(j>greatest) greatest = j;
      avg += (double)j;
    }
    avg /= (double)tests;
    
    /* ignore the following lol 
    for(int i = 0; i < 3; i++){
      try{
        System.out.println("\033[?25l\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m\nRunning tests");
        Thread.sleep(500);
        System.out.println("\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m\nRunning tests.");
        Thread.sleep(500);
        System.out.println("\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m\nRunning tests..");
        Thread.sleep(500);
        System.out.println("\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m\nRunning tests...");
        Thread.sleep(500);
      }catch(InterruptedException e){}
    }
     ok pay attention now */
    
    System.out.println("\033[2J\033[H\033[34;1;4mRandom Walk Program\n\033[0m\nRunning tests...\n\033[1;35mTests Complete.\n\033[0mResults:\n\n" + tests + " tests run.\nThe average number of steps taken was "
    + (Math.round(avg*10000)/10000.0) + ".\nThe greatest number of steps taken in a single test was " + greatest + ".\033[?25h");
  }
  /* just a seperate method to run 1 walk */
  static int walk() {
    int pos = 3;
    int steps = 0;
    while(pos>=0 && pos<=6){
      pos += (((int)Math.round(Math.random()))*2)-1;
      // random rounded to closest int --> 0 or 1 | *2 --> 0 or 2 | -1 --> -1 or 1
      // basically instead of random 0 or 1 it does random -1 or +1 and adds that to the pos variable
      steps++;
    }
    return steps;
  }
}