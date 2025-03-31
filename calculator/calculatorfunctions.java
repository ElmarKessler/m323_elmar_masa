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
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division durch null ist nicht erlaubt.");
        }
        return a / b;
    }
    
    // Potenzierung
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    // Wurzelberechnung
    public static double root(double value, double root) {
        if (value < 0 && root % 2 == 0) {
            throw new ArithmeticException("Gerade Wurzeln aus negativen Zahlen sind nicht erlaubt.");
        }
        return Math.pow(value, 1.0 / root);
    }
}
