package calculator;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculatorFunctions {
    public static Function<Double, CalculationResult> createCalculationPipeline( //<-- pure function, higher order function because it returns a function
            String operation, 
            double secondOperand) {
        return firstOperand -> CalculatorOperations.OPERATIONS.get(operation)
            .apply(firstOperand, secondOperand)
            .map(result -> new CalculationResult(operation, result))
            .orElse(new CalculationResult(operation + " failed", 0.0));
    }

    public static List<CalculationResult> processNumbers( //<-- pure function, Pipeline
            List<Double> numbers, 
            String operation, 
            double secondOperand) {
        return numbers.stream()
            .filter(n -> n > 0)
            .map(createCalculationPipeline(operation, secondOperand))
            .collect(Collectors.toUnmodifiableList()); //<-- immutable list
    }
}
