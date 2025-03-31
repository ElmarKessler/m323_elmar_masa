package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class calculatorfunctions {
    // Addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Subtraktion
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Multiplikation
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Division
    public static Optional<Double> divide(double a, double b) {
        if (b == 0) {
            return Optional.empty(); // Division by zero is not allowed
        }
        return Optional.of(a / b);
    }

    // Potenzierung
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Wurzelberechnung
    public static Optional<Double> root(double value, double root) {
        if (value < 0 && root % 2 == 0) {
            return Optional.empty(); // Even roots of negative numbers are not allowed
        }
        return Optional.of(Math.pow(value, 1.0 / root));
    }

    // ------ Pipelines ------

    // Example: Squaring and then adding 5
    public static double squareAndAddFive(double x) {
        return add(5, power(x, 2)); // Using power for squaring
    }

    // ------ IO (using Optional for a simplified example) ------

    // Simulates an impure random number generation, could be used for simulating a
    // cost for calculation
    public static Optional<Integer> randomCostImpure() {
        Random random = new Random();
        try {
            return Optional.of(random.nextInt(10) + 1); // Cost between 1 and 10
        } catch (Exception e) {
            return Optional.empty(); // Representing a failure in cost calculation.
        }
    }

    // Pure function that uses the impure cost calculation.
    public static Optional<Double> calculateDiscountedPrice(double price, double discountPercentage) {
        return randomCostImpure().map(cost -> {
            double discountedPrice = price * (1 - (discountPercentage / 100.0));
            return discountedPrice + cost; // Add cost to simulate real-world scenario
        });
    }

    // ------ Streams (simulated with Java Streams) ------

    public static Stream<Integer> integerSeries() {
        return Stream.iterate(0, n -> n + 1); // Infinite series starting from 0
    }

    public static Stream<Double> powerSeries(double base) {
        return Stream.iterate(1.0, n -> n * base); // Infinite series of powers of base
    }

    public static Stream<Double> randomNumbers() {
        Random random = new Random();
        return Stream.generate(random::nextDouble);
    }

    // ------ Parallel Processes ------

    public static double parallelSumOfSquares(List<Double> numbers) {
        ExecutorService executor = Executors
                .newFixedThreadPool(Math.min(numbers.size(), Runtime.getRuntime().availableProcessors()));
        List<CompletableFuture<Double>> futures = new ArrayList<>();

        numbers.forEach(num -> {
            futures.add(CompletableFuture.supplyAsync(() -> power(num, 2), executor));
        });

        return futures.stream()
                .map(CompletableFuture::join)
                .reduce(0.0, Double::sum);
    }

    // ------ Handling Errors with Optional ------
    public static Optional<Integer> safeParseInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // ------ Working with Lists (simulating for-comprehension) ------
    public static List<String> calculatorOperations(List<Double> numsA, List<Double> numsB) {
        return numsA.stream()
                .flatMap(a -> numsB.stream()
                        .map(b -> a + " + " + b + " = " + add(a, b)))
                .collect(Collectors.toList());
    }

    // ------ Working with Sets (simulating for-comprehension) ------
    public static Set<String> uniqueCalculations(List<Double> nums) {
        return nums.stream()
                .map(n -> n + " squared = " + power(n, 2))
                .collect(Collectors.toSet());
    }

    // ------ Working with Maps ------
    public static Map<String, Double> updateCalculationResults(Map<String, Double> oldMap, String operation,
            double result) {
        Map<String, Double> newMap = new HashMap<>(oldMap);
        newMap.put(operation, result);
        return newMap;
    }

    public static Map<String, Double> removeCalculation(Map<String, Double> oldMap, String operation) {
        Map<String, Double> newMap = new HashMap<>(oldMap);
        newMap.remove(operation);
        return newMap;
    }

    // ------ Filter, Map, Reduce ------
    public static List<Double> filterPositiveNumbers(List<Double> numbers) {
        return numbers.stream()
                .filter(n -> n > 0)
                .collect(Collectors.toList());
    }

    public static List<Double> squareNumbers(List<Double> numbers) {
        return numbers.stream()
                .map(n -> power(n, 2))
                .collect(Collectors.toList());
    }

    public static double sumNumbers(List<Double> numbers) {
        return numbers.stream()
                .reduce(0.0, Double::sum);
    }

    // ------ Tuples and Pattern Matching (simulated) ------
    public static void calculatorOperationInfo(List<Tuple<String, Integer>> operations) {
        operations.forEach(operation -> {
            String op = operation.getFirst();
            int cost = operation.getSecond();

            if (op.equals("Addition")) {
                System.out.println("Performing addition with cost: " + cost);
            } else if (cost > 5) {
                System.out.println("High cost operation: " + op);
            } else {
                System.out.println("Operation: " + op + ", cost: " + cost);
            }
        });
    }

    // ------ Zip Function (simulated) ------
    public static <A, B> List<Tuple<A, B>> zipLists(List<A> listA, List<B> listB) {
        int size = Math.min(listA.size(), listB.size());
        List<Tuple<A, B>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(new Tuple<>(listA.get(i), listB.get(i)));
        }
        return result;
    }

    // ------ Higher-Order Functions ------
    public static double applyFunction(Function<Double, Double> f, double value) {
        return f.apply(value);
    }

    public static Function<Double, Double> createMultiplier(double factor) {
        return x -> x * factor;
    }

    public static Function<Double, Double> combineFunctions(Function<Double, Double> f,
            Function<Double, Double> g) {
        return x -> g.apply(f.apply(x));
    }
}