package calculator;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculatorFunctions {
    public static Function<Double, CalculationResult> createCalculationPipeline(
            String operation, 
            double secondOperand) {
        return firstOperand -> CalculatorOperations.OPERATIONS.get(operation)
            .apply(firstOperand, secondOperand)
            .map(result -> new CalculationResult(operation, result))
            .orElse(new CalculationResult(operation + " failed", 0.0));
    }

    public static List<CalculationResult> processNumbers(
            List<Double> numbers, 
            String operation, 
            double secondOperand) {
        return numbers.stream()
            .filter(n -> n > 0)
            .map(createCalculationPipeline(operation, secondOperand))
            .collect(Collectors.toUnmodifiableList());
    }
}
