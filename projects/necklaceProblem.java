import java.util.Scanner;
import java.util.ArrayList;

class necklaceProblem {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[2J\033[H\033[1;4;32mNECKLACE PROBLEM:\n\033[0mInput number 1: ");

        int num1=-1, num2=-1;
        String str1="", str2="";

        while(!(num1>-1&&num1<10)){
            try{
                str1 = sc.nextLine();
                num1 = Integer.parseInt(str1);
                if(num1>9||num1<0)
                    throw new java.util.InputMismatchException("amogus");
            }
            catch(Exception e){
                System.out.print("\n\033[31mPlease enter a valid number!\033[0m\n" + str1 + " is not a number between 0 and 9\n\nInput number 1: ");
                sc = new Scanner(System.in);
            }
        }

        while(!(num2>-1&&num2<10)){
            try{
                System.out.print("\nInput number 2: ");
                str2 = sc.nextLine();
                num2 = Integer.parseInt(str2);
                if(num2>9||num2<0)
                    throw new java.util.InputMismatchException();
            }
            catch(Exception e){
                System.out.print("\n\033[31mPlease enter a valid number!\033[0m\n" + str2 + " is not a number between 0 and 9");
                sc = new Scanner(System.in);
            }
        }

        sc.close();
        System.out.println();
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(num1);
        nums.add(num2);
        nums.add((num1+num2)%10);

        while((nums.get(nums.size()-2)!=num1) || (nums.get(nums.size()-1)!=num2))
            nums.add((nums.get(nums.size()-2)+nums.get(nums.size()-1))%10);

        nums.forEach(n -> System.out.print(n + " "));
        System.out.println("\b, and it took " + (nums.size()-2) + " steps");
    }
}