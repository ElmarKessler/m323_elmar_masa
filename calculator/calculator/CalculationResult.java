package calculator;

public record CalculationResult(String operation, double value) {

    public static String describeResult(CalculationResult result) {
        return switch (result.operation()) {
            case "add" -> "Addition result: " + result.value();
            case "subtract" -> "Subtraction result: " + result.value();
            case "multiply" -> "Multiplication result: " + result.value();
            case "divide" -> result.value() == 0.0
                    ? "Division by zero attempted"
                    : "Division result: " + result.value();
            case "power" -> result.value() > 1000
                    ? "Large power result: " + result.value()
                    : "Power result: " + result.value();
            case "factorial" -> "Factorial result: " + result.value();
            default -> "Result of " + result.operation() + ": " + result.value();
        };
    }
}
