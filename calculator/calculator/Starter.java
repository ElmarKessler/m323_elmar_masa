package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Starter {

    public void startCalculator() {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbers = new ArrayList<>();
        String operation;
        Optional<Double> maybeSecondOperand;

        try {
            ConsoleOutput.print("Welcome to Functional Calculator");
            ConsoleOutput.print("Enter numbers (type 'done' to finish):");

            while (true) {
                String input = scanner.nextLine();
                if ("done".equalsIgnoreCase(input)) {
                    break;
                }

                parseDoubleSafely(input).ifPresentOrElse(
                        numbers::add,
                        () -> ConsoleOutput.print("Invalid number, please try again."));
            }

            if (numbers.isEmpty()) {
                ConsoleOutput.print("No valid numbers entered. Exiting.");
                return;
            }

            while (true) {
                ConsoleOutput.print("Enter operation (add/subtract/multiply/divide/power):");
                operation = scanner.nextLine().trim().toLowerCase();
                if (CalculatorOperations.OPERATIONS.containsKey(operation)) {
                    break;
                } else
                    ConsoleOutput.print("Invalid operation. Try again.");
            }

            while (true) {
                ConsoleOutput.print("Enter second operand:");
                String secondInput = scanner.nextLine();
                maybeSecondOperand = parseDoubleSafely(secondInput);
                if (maybeSecondOperand.isPresent()) {
                    break;
                } else
                    ConsoleOutput.print("Invalid number. Try again.");
            }

            double secondOperand = maybeSecondOperand.get();

            List<CalculationResult> results = CalculatorFunctions.processNumbers(
                    List.copyOf(numbers), operation, secondOperand);

            results.forEach(result -> ConsoleOutput.print(CalculationResult.describeResult(result)));

            List<CalculationResult> combined = CalculatorFunctions.combineOperations(
                    List.copyOf(numbers), List.of(1.0, 2.0, 3.0));

            ConsoleOutput.print("Combined operations:");
            combined.forEach(result -> ConsoleOutput.printResult(result.operation(), result.value()));
        } finally {
            scanner.close();
        }
    }

    private Optional<Double> parseDoubleSafely(String input) {
        try {
            return Optional.of(Double.parseDouble(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
