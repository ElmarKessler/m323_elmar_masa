package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbers = new ArrayList<>();

        ConsoleOutput.print("Welcome to Functional Calculator");
        ConsoleOutput.print("Enter numbers (type 'done' to finish):");

        while (true) {
            String input = scanner.nextLine();
            if ("done".equals(input)) break;
            Optional.ofNullable(input)
                .map(Double::parseDouble)
                .ifPresent(numbers::add);
        }

        ConsoleOutput.print("Enter operation (add/subtract/multiply/divide/power):");
        String operation = scanner.nextLine();
        
        ConsoleOutput.print("Enter second operand:");
        double secondOperand = scanner.nextDouble();

        List<CalculationResult> results = CalculatorFunctions.processNumbers(
            List.copyOf(numbers),
            operation,
            secondOperand
        );

        results.forEach(result -> 
            ConsoleOutput.print(CalculationResult.describeResult(result))
        );

        List<CalculationResult> combined = CalculatorFunctions.combineOperations(
            List.copyOf(numbers),
            List.of(1.0, 2.0, 3.0)
        );
        
        ConsoleOutput.print("Combined operations:");
        combined.forEach(result -> 
            ConsoleOutput.printResult(result.operation(), result.value())
        );

        scanner.close();
    }
}
