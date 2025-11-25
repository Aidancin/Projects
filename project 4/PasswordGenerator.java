import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    // Character pools
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}<>?/";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("🔐 Password Generator");

        // -----------------------
        // USER INPUT
        // -----------------------
        System.out.print("Enter password length: ");
        int length = getValidInt(input);

        while (length < 1) {
            System.out.print("Length must be at least 1. Try again: ");
            length = getValidInt(input);
        }

        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUpper = getYesNo(input);

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLower = getYesNo(input);

        System.out.print("Include digits? (y/n): ");
        boolean includeDigits = getYesNo(input);

        System.out.print("Include symbols? (y/n): ");
        boolean includeSymbols = getYesNo(input);

        // At least one type must be chosen
        if (!includeUpper && !includeLower && !includeDigits && !includeSymbols) {
            System.out.println("❌ ERROR: You must select at least one character type!");
            return;
        }

        // -----------------------
        // GENERATE PASSWORD
        // -----------------------
        String password = generatePassword(length, includeUpper, includeLower, includeDigits, includeSymbols);

        // -----------------------
        // OUTPUT RESULT
        // -----------------------
        System.out.println("\nGenerated Password: " + password);
        System.out.println("Strength: " + evaluateStrength(password));

        input.close();
    }

    // --------------------------------------------------------------------
    // INPUT VALIDATION HELPERS
    // --------------------------------------------------------------------

    public static int getValidInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            input.next();
        }
        return input.nextInt();
    }

    public static boolean getYesNo(Scanner input) {
        String s = input.next().trim().toLowerCase();
        while (!s.equals("y") && !s.equals("n")) {
            System.out.print("Enter y or n: ");
            s = input.next().trim().toLowerCase();
        }
        return s.equals("y");
    }

    // --------------------------------------------------------------------
    // PASSWORD GENERATION LOGIC
    // --------------------------------------------------------------------

    public static String generatePassword(int length, boolean upper, boolean lower, boolean digits, boolean symbols) {
        StringBuilder pool = new StringBuilder();
        StringBuilder password = new StringBuilder();

        // Build allowed characters pool
        if (upper) pool.append(UPPER);
        if (lower) pool.append(LOWER);
        if (digits) pool.append(DIGITS);
        if (symbols) pool.append(SYMBOLS);

        // Ensure each chosen type appears at least once
        if (upper) password.append(getRandomChar(UPPER));
        if (lower) password.append(getRandomChar(LOWER));
        if (digits) password.append(getRandomChar(DIGITS));
        if (symbols) password.append(getRandomChar(SYMBOLS));

        // Fill the rest randomly
        while (password.length() < length) {
            password.append(getRandomChar(pool.toString()));
        }

        // Shuffle the password so required chars are not always at the beginning
        return shuffleString(password.toString());
    }

    // Random char picker
    public static char getRandomChar(String s) {
        return s.charAt(random.nextInt(s.length()));
    }

    // Shuffle using Fisher-Yates algorithm
    public static String shuffleString(String input) {
        char[] a = input.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }

    // --------------------------------------------------------------------
    // BONUS: PASSWORD STRENGTH EVALUATION
    // --------------------------------------------------------------------

    public static String evaluateStrength(String pw) {
        int score = 0;

        if (pw.length() >= 12) score++;
        if (pw.matches(".*[A-Z].*")) score++;
        if (pw.matches(".*[a-z].*")) score++;
        if (pw.matches(".*\\d.*")) score++;
        if (pw.matches(".*[!@#$%^&*()\\-_=+\\[\\]{}<>?/].*")) score++;

        return switch (score) {
            case 0, 1, 2 -> "Weak ❌";
            case 3, 4 -> "Medium ⚠️";
            case 5 -> "Strong ✅";
            default -> "Unknown";
        };
    }

}
