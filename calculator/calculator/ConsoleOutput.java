package calculator;

class ConsoleOutput {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void printResult(String operation, double result) {
        System.out.printf("%s = %.2f%n", operation, result);
    }
}
