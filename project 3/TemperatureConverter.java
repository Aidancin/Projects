import java.util.*;

public class TemperatureConverter {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("🌡️ Welcome to the Temperature Converter!");

        while (keepRunning) {

            try {
                // --- Read a temperature value ---
                System.out.print("\nEnter temperature value: ");
                double tempValue = getValidDouble(input);

                // --- Select input unit ---
                System.out.println("Select the INPUT unit (C/F/K): ");
                String fromUnit = getUnit(input);

                // --- Select output unit ---
                System.out.println("Select the OUTPUT unit (C/F/K): ");
                String toUnit = getUnit(input);

                // --- Validate absolute zero ---
                if (!isValidTemperature(tempValue, fromUnit)) {
                    System.out.println("⚠️ WARNING: Value is below absolute zero for " + fromUnit + "!");
                }

                // --- Perform conversion ---
                double result = convertTemperature(tempValue, fromUnit, toUnit);

                // --- Display result with 2 decimals ---
                System.out.printf("Result: %.2f %s → %.2f %s\n",
                        tempValue, fromUnit.toUpperCase(),
                        result, toUnit.toUpperCase());

                // --- Extra credit: allow multiple conversions ---
                System.out.print("\nDo another conversion? (y/n): ");
                keepRunning = input.next().equalsIgnoreCase("y");

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        System.out.println("\nThanks for using the Temperature Converter! Goodbye! 👋");
        input.close();
    }

    // --------------------------------------------------------------------------
    // VALIDATION METHODS
    // --------------------------------------------------------------------------

    public static double getValidDouble(Scanner input) {
        while (!input.hasNextDouble()) {
            System.out.print("Invalid number. Try again: ");
            input.next();
        }
        return input.nextDouble();
    }

    public static String getUnit(Scanner input) {
        String unit = input.next().toUpperCase();
        while (!(unit.equals("C") || unit.equals("F") || unit.equals("K"))) {
            System.out.print("Invalid unit! Enter C, F, or K: ");
            unit = input.next().toUpperCase();
        }
        return unit;
    }

    // Validate based on absolute zero
    public static boolean isValidTemperature(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "C": return value >= -273.15;
            case "F": return value >= -459.67;
            case "K": return value >= 0;
        }
        return false;
    }

    // --------------------------------------------------------------------------
    // CONVERSION LOGIC
    // --------------------------------------------------------------------------

    public static double convertTemperature(double temp, String from, String to) {

        // If units match, no conversion needed
        if (from.equalsIgnoreCase(to)) {
            return temp;
        }

        // Convert from any unit → Celsius (as the "hub")
        double tempInCelsius = switch (from.toUpperCase()) {
            case "F" -> fahrenheitToCelsius(temp);
            case "K" -> kelvinToCelsius(temp);
            default  -> temp; // "C"
        };

        // Convert Celsius → target unit
        return switch (to.toUpperCase()) {
            case "F" -> celsiusToFahrenheit(tempInCelsius);
            case "K" -> celsiusToKelvin(tempInCelsius);
            default  -> tempInCelsius; // "C"
        };
    }

    // --------------------------------------------------------------------------
    // TEMPERATURE FORMULAS (Each in its own method)
    // --------------------------------------------------------------------------

    // Celsius ↔ Fahrenheit
    public static double celsiusToFahrenheit(double c) { return (c * 9/5) + 32; }
    public static double fahrenheitToCelsius(double f) { return (f - 32) * 5/9; }

    // Celsius ↔ Kelvin
    public static double celsiusToKelvin(double c) { return c + 273.15; }
    public static double kelvinToCelsius(double k) { return k - 273.15; }

}
