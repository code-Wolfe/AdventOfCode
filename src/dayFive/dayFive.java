package dayFive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class dayFive {

    public static void main(String[] args) {
        try {
            int result = partTwo("src/dayFive/rules.txt","src/dayFive/input.txt");
            System.out.println(result);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int partOne(String filePath1, String filePath2) throws FileNotFoundException{

        int result = 0;

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        Scanner scanner = new Scanner(new File(filePath1));

        //create hashMap of key/value pairs
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");

            int key = Integer.parseInt(parts[0]);
            int value = Integer.parseInt(parts[1]);

            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(value);
        }

        scanner.close();

        /**
        System.out.println("\nFormatted Map Output:");
        for (Integer key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
         **/
        Scanner input = new Scanner(new File(filePath2));

        while(input.hasNextLine()){
            String line = input.nextLine();
            int[] nums = parseLine(line);

            boolean isValidLine = true;

            //iterates through nums, and checks index of i vs index of every Y value associated with i
            for(int i = 0; i < nums.length; i++){
                if(map.containsKey(nums[i])){
                    ArrayList<Integer> yValues = map.get(nums[i]);
                    for(int y: yValues){
                        int yPosition = findIndex(nums, y);

                        if(yPosition!= -1 && yPosition <= i){
                            //rule broken, y shows up before x
                            isValidLine = false;
                            break;
                        }

                    }
                }
            }

            if(isValidLine) { result += nums[nums.length/2];}

        }

        input.close();
        return result;

    }

    public static int partTwo(String filePath1, String filePath2) throws FileNotFoundException{
        int result = 0;

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        Scanner scanner = new Scanner(new File(filePath1));

        //create hashMap of key/value pairs
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");

            int key = Integer.parseInt(parts[0]);
            int value = Integer.parseInt(parts[1]);

            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(value);
        }

        scanner.close();

        /**
         System.out.println("\nFormatted Map Output:");
         for (Integer key : map.keySet()) {
         System.out.println(key + ": " + map.get(key));
         }
         **/
        Scanner input = new Scanner(new File(filePath2));

        while (input.hasNextLine()) {
            String line = input.nextLine();
            int[] nums = parseLine(line);
            boolean changed = false;

            // Look for swaps needed
            for (int i = 0; i < nums.length - 1; i++) {
                int root = i;

                for (int j = root + 1; j < nums.length; j++) {

                    //if nums[j] is in our rules map AND nums[root] is in nums[j]'s list of values
                    //"if nums[j] should come before nums[i]"
                    //finds the last number in the sequence that breaks the rule and swaps it
                    if (map.containsKey(nums[j]) && map.get(nums[j]).contains(nums[root])) {
                        root = j;
                    }


                }
                // If we found a better position, swap
                if (root != i) {
                    int temp = nums[i];
                    nums[i] = nums[root];
                    nums[root] = temp;
                    changed = true;
                }
            }


            if (changed) {
                result += nums[nums.length/2];
            }
        }

        input.close();
        return result;

    }
    public static int findIndex(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // Return -1 if value not found
    }
    public static int[] parseLine(String line) {
        String[] parts = line.split(",");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

}
