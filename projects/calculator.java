/* REPLIT 8.2 */
import java.util.Scanner;

class calculator {
    public static void main(String[] args) {
        String exp = "", operator = "", operand1 = "", operand2 = "";
        while (exp.equals("")) {
            Scanner inp = new Scanner(System.in);
            System.out.print("In: ");
            exp = inp.nextLine();
            // write your code below:
            if (exp.contains("/")) {
                operator = "divide";
                operand1 = exp.substring(0, exp.indexOf("/"));
                operand2 = exp.substring(exp.indexOf("/") + 1);
            } else if (exp.contains("*")) {
                operator = "multiply";
                operand1 = exp.substring(0, exp.indexOf("*"));
                operand2 = exp.substring(exp.indexOf("*") + 1);
            } else if (exp.contains("+")) {
                operator = "add";
                operand1 = exp.substring(0, exp.indexOf("+"));
                operand2 = exp.substring(exp.indexOf("+") + 1);
            } else if (exp.contains("-")) {
                operator = "subtract";
                operand1 = exp.substring(0, exp.indexOf("-"));
                operand2 = exp.substring(exp.indexOf("-") + 1);
            } else if (exp.contains("%")) {
                operator = "modulo";
                operand1 = exp.substring(0, exp.indexOf("%"));
                operand2 = exp.substring(exp.indexOf("%") + 1);
            } else if (exp.contains("^")) {
                operator = "exponent";
                operand1 = exp.substring(0, exp.indexOf("^"));
                operand2 = exp.substring(exp.indexOf("^") + 1);
            } else {
                System.out.println("Invalid input!");
                operator = "";
            }
        } // end while

        System.out.print("The result is ");
        switch (operator) {
            case ("divide") -> System.out.println(Integer.parseInt(operand1) / Integer.parseInt(operand2));
            case ("multiply") -> System.out.println(Integer.parseInt(operand1) * Integer.parseInt(operand2));
            case ("add") -> System.out.println(Integer.parseInt(operand1) + Integer.parseInt(operand2));
            case ("subtract") -> System.out.println(Integer.parseInt(operand1) - Integer.parseInt(operand2));
            case ("modulo") -> System.out.println(Integer.parseInt(operand1) % Integer.parseInt(operand2));
            case ("exponent") -> System.out.println((int)Math.pow(Integer.parseInt(operand1), Integer.parseInt(operand2)));
        }
    }
}
/*
I added exponents too for a cool challenge
and IntelliJ told me to put the weird arrow thingy instead of the regular ":" for the case (I don't really know what it is)
*/