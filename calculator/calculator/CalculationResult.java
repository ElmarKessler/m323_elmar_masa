package calculator;

record CalculationResult(String operation, double value) {
    public static String describeResult(CalculationResult result) {
        switch (result.operation()) {
            case "add":
                return "Addition result: " + result.value();
            case "divide":
                if (result.value() == Double.POSITIVE_INFINITY) {
                    return "Division by zero attempted";
                }
                break;
            case "power":
                if (result.value() > 1000) {
                    return "Large power result: " + result.value();
                }
                break;
            default:
                return "Result of " + result.operation() + ": " + result.value();
        }
        return "Invalid operation";
    }
}
