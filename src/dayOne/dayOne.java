package dayOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class dayOne {
    public static int processFile2(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        int finalValue = 0;
        boolean leftOrRight = true; //true is left

        while(scanner.hasNextInt()){
            if(leftOrRight){
                int num = scanner.nextInt();
                //System.out.println("Adding to left: " + num);  // Debug each number as it's read
                left.add(num);
            }else{
                int num = scanner.nextInt();
                //System.out.println("Adding to right: " + num);  // Debug each number as it's read
                right.add(num);
            }
            leftOrRight = !leftOrRight;
        }


        for(int i = 0; i < left.size(); i++){
            int count = 0;
            for(int j = 0; j < right.size(); j++){
                if((left.get(i)).equals(right.get(j))){
                    count++;
                }
            }

            finalValue += (left.get(i) * count);
        }

        scanner.close();
        return finalValue;
    }

    public static int processFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        int finalValue = 0;
        boolean leftOrRight = true; //true is left

        while(scanner.hasNextInt()){
            if(leftOrRight){
                left.add(scanner.nextInt());
            }else{
                right.add(scanner.nextInt());
            }
            leftOrRight = !leftOrRight;
        }

        Collections.sort(left);
        Collections.sort(right);

        for(int i = 0; i < left.size(); i++){
            finalValue += Math.abs(left.get(i) - right.get(i));
        }

        scanner.close();
        return finalValue;
    }

    public static void main(String[] args) {
        try {
            int result = processFile2("src/dayOne/input.txt");
            System.out.println(result);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}