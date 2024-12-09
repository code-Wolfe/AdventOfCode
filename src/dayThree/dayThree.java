package dayThree;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dayThree {

    public static long evaluateExpressionFromFile(String fileName) throws FileNotFoundException {
        // Correct regex for the given data format
        String regex = "\\(\\d+\\*\\d+\\)|\\bdo\\(\\)|\\bdon't\\(\\)";
        Pattern pattern = Pattern.compile(regex);

        long totalSum = 0;
        boolean adding = true; // Indicates whether to add results to the total sum

        // Open the file for reading
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        // Process the file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            // Skip empty lines
            if (line.isEmpty()) {
                continue;
            }

            // Debug: Print the line being processed
            System.out.println("Processing line: " + line);

            // Match the line against the regex
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String match = matcher.group();

                // Debug: Print the match found
                System.out.println("Matched: " + match);

                if (match.equals("do()")) {
                    // Turn adding back on
                    adding = true;
                    System.out.println("Adding turned ON");
                } else if (match.equals("don't()")) {
                    // Turn adding off
                    adding = false;
                    System.out.println("Adding turned OFF");
                } else if (match.matches("\\(\\d+\\*\\d+\\)")) {
                    // Parse and evaluate (x*y)
                    String[] numbers = match.substring(1, match.length() - 1).split("\\*");
                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);
                    long product = (long) num1 * num2;

                    if (adding) {
                        totalSum += product;
                    }

                    System.out.println("Evaluated: " + match + " = " + product + (adding ? " (Added)" : " (Skipped)"));
                }
            }
        }

        // Close the scanner
        scanner.close();
        return totalSum;
    }

    public static int partOne(String fileName) throws FileNotFoundException{

        int result = 0;

        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)|\\bdo\\(\\)|\\bdon't\\(\\)";

        Scanner scanner = new Scanner(new File(fileName));
        Pattern pattern = Pattern.compile(regex);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Create a Matcher for the current line
            Matcher matcher = pattern.matcher(line);

            // Find all occurrences of "mul("
            while (matcher.find()) {
                // Print only the matched string "mul("
                System.out.println(matcher.group());
            }
        }

        scanner.close();


        return result;
    }

    public static void main(String[] args) {

        // Define the input file
        String fileName = "src/dayThree/output.txt";

        try {
            //System.out.println(partOne("src/dayThree/input.txt"));

            long result = evaluateExpressionFromFile(fileName);
            System.out.println("The result of the evaluated expressions is: " + result);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

}
