import java.util.Scanner;
public class JavaCalculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }
}

class Calculator {
    private Scanner input;

    public Calculator() {
        input = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to JS Team Calculator!");

        while (true) {
            System.out.print("Enter the first number: ");
            double num1 = input.nextDouble();

            System.out.print("Enter the operator (+, -, *, /): ");
            char operator = input.next().charAt(0);

            System.out.print("Enter the second number: ");
            double num2 = input.nextDouble();

            double result = calculate(num1, operator, num2);

            System.out.println("Result: " + result);

            System.out.println("Do you want to perform another calculation? (yes/no)");
            String choice = input.next().toLowerCase();
            if (!choice.equals("yes")) {
                break;
            }
        }

        System.out.println("Goodbye!");
        input.close();
    }

    private double calculate(double num1, char operator, double num2) {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero");
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                System.out.println("Error: Choose correct operation please!");
        }
        return result;
    }
}

