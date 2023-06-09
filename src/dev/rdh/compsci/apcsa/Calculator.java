package dev.rdh.compsci.apcsa;/* REPLIT 8.2 */
import java.util.Scanner;

class Calculator {
    public static void run() {
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
        System.out.println(switch (operator) {
            case ("divide") -> Integer.parseInt(operand1) / Integer.parseInt(operand2);
            case ("multiply") -> Integer.parseInt(operand1) * Integer.parseInt(operand2);
            case ("add") -> Integer.parseInt(operand1) + Integer.parseInt(operand2);
            case ("subtract") -> Integer.parseInt(operand1) - Integer.parseInt(operand2);
            case ("modulo") -> Integer.parseInt(operand1) % Integer.parseInt(operand2);
            case ("exponent") -> (int)Math.pow(Integer.parseInt(operand1), Integer.parseInt(operand2));
            default -> "Invalid input!";
        });
    }
}