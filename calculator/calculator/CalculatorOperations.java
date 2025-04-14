package calculator;

import java.util.Map;
import java.util.Optional;

@FunctionalInterface
interface CalculatorOperation {
    Optional<Double> apply(double a, double b);
}

class CalculatorOperations {
    public static final Map<String, CalculatorOperation> OPERATIONS = Map.of(
            "add", (a, b) -> Optional.of(a + b),
            "subtract", (a, b) -> Optional.of(a - b),
            "multiply", (a, b) -> Optional.of(a * b),
            "divide", (a, b) -> b == 0 ? Optional.empty() : Optional.of(a / b),
            "power", (a, b) -> Optional.of(Math.pow(a, b)));

    public static long factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }
}