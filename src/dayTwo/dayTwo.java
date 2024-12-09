package dayTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.math.*;


public class dayTwo {

    private static boolean checkArray(int[] numbers) {
        boolean shouldIncrease = numbers[1] > numbers[0];
        boolean firstStep = Math.abs(numbers[1] - numbers[0]) <= 3 && numbers[1] != numbers[0];
        boolean increaseCount = true;

        for(int i = 2; i < numbers.length; i++) {
            if(shouldIncrease && numbers[i] < numbers[i-1]) increaseCount = false;
            if(!shouldIncrease && numbers[i] > numbers[i-1]) increaseCount = false;
            if(Math.abs(numbers[i] - numbers[i-1]) > 3 || (numbers[i] - numbers[i-1]) == 0) {
                increaseCount = false;
            }
        }

        return increaseCount && firstStep;
    }

    public static int part1(String filepath) throws FileNotFoundException {

        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int safe = 0;

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            String[] numberString = line.split("\\s+"); //splits by spaces

            int[] numbers = new int[numberString.length];

            for(int i = 0; i < numberString.length; i++) {
                numbers[i] = Integer.parseInt(numberString[i]);
            } //convert array into ints

            //parse array
            //check if whole array is increasing OR decreasing
            //  check if increasing or decreasing,
            //value can only change by 1-3
            boolean shouldIncrease = numbers[1] > numbers[0]; //true if index 1 is more than index 0
            boolean firstStep = Math.abs(numbers[1] - numbers[0]) <= 3 && numbers[1] != numbers[0];
            boolean increaseCount = true;

            for(int i = 2; i < numbers.length; i++){
                //checks if values go either all up or all down
                if(shouldIncrease && numbers[i] < numbers[i-1]){ increaseCount = false; }
                if(!shouldIncrease && numbers[i] > numbers[i-1]){ increaseCount = false; }

                //checks that steps are between 1-3
                if(Math.abs(numbers[i] - numbers[i-1]) > 3 || (numbers[i] - numbers[i-1]) == 0){
                    increaseCount = false;
                }
            }


            if(increaseCount && firstStep) {
                System.out.println("Safe");
                safe++;
            }else{
                System.out.println("Unsafe");
            }
        }
        scanner.close();
        return safe;
    }

    public static int part2(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int safe = 0;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] numberString = line.split("\\s+");
            int[] numbers = new int[numberString.length];

            for(int i = 0; i < numberString.length; i++) {
                numbers[i] = Integer.parseInt(numberString[i]);
            }

            if(checkArray(numbers)) {
                System.out.println("Safe");
                safe++;
            } else {
                // If unsafe, try removing each number one at a time
                boolean canBeSafe = false;
                for(int skip = 0; skip < numbers.length; skip++) {
                    // Create new array without the skipped number
                    int[] shortened = new int[numbers.length - 1];
                    int index = 0;
                    for(int i = 0; i < numbers.length; i++) {
                        if(i != skip) {
                            shortened[index++] = numbers[i];
                        }
                    }

                    if(checkArray(shortened)) {
                        canBeSafe = true;
                        break;
                    }
                }

                if(canBeSafe) {
                    System.out.println("Safe (with dampener)");
                    safe++;
                } else {
                    System.out.println("Unsafe");
                }
            }
        }
        scanner.close();
        return safe;
    }

    public static void main(String[] args) {
        try {
            int result = part2("src/dayTwo/input.txt");
            System.out.println(result);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
