package dayFour;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class dayFour {

    public static void main(String[] args) {
        try {
            int result = partTwo("src/dayFour/input.txt");
            System.out.println(result);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int partOne(String fileName) throws FileNotFoundException{

        Scanner scanner = new Scanner(new File(fileName));
        char[][] grid = new char[140][140];
        int row = 0;
        String target = "XMAS";

        int count = 0;

        //create 2d array
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            for(int i = 0; i < line.length();i++){
                grid[row][i] = line.charAt(i);
            }
            row++;
        }

        int R = grid.length;
        int C = grid[0].length;

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){

                //right check
                if(j + 3 < C){
                    String found = ""+grid[i][j]+grid[i][j+1]+grid[i][j+2]+grid[i][j+3];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //left check
                if(j - 3 >= 0){
                    String found = ""+grid[i][j]+grid[i][j-1]+grid[i][j-2]+grid[i][j-3];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //up check
                if(i - 3 >= 0){
                    String found = ""+grid[i][j]+grid[i-1][j]+grid[i-2][j]+grid[i-3][j];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //down check
                if(i + 3 < R){
                    String found = ""+grid[i][j]+grid[i+1][j]+grid[i+2][j]+grid[i+3][j];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //down right check
                if (i + 3 < R && j + 3 < C){
                    String found = ""+grid[i][j]+grid[i+1][j+1]+grid[i+2][j+2]+grid[i+3][j+3];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //down left check
                if (i + 3 < R && j - 3 >= 0){
                    String found = ""+grid[i][j]+grid[i+1][j-1]+grid[i+2][j-2]+grid[i+3][j-3];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //up right check
                if (i - 3 >= 0 && j + 3 < C){
                    String found = ""+grid[i][j]+grid[i-1][j+1]+grid[i-2][j+2]+grid[i-3][j+3];
                    if (found.equals(target)) {
                        count++;
                    }
                }

                //up left check
                if (i - 3 >= 0 && j - 3 >= 0){
                    String found = ""+grid[i][j]+grid[i-1][j-1]+grid[i-2][j-2]+grid[i-3][j-3];
                    if (found.equals(target)) {
                        count++;
                    }
                }

            }
        }

        scanner.close();
        return count;
    }

    public static int partTwo(String fileName) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(fileName));
        char[][] grid = new char[140][140];
        int row = 0;
        String target = "XMAS";

        int count = 0;

        //create 2d array
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            for(int i = 0; i < line.length();i++){
                grid[row][i] = line.charAt(i);
            }
            row++;
        }



        int R = grid.length;
        int C = grid[0].length;

        for(int i = 1; i < R-1; i++){
            for(int j = 1; j < C-1; j++) {
                if(grid[i][j] == 'A'){
                    //check formation 1
                    //m m
                    // a
                    //s s
                    if(grid[i-1][j-1] == 'M'
                        && grid[i-1][j+1] == 'M'
                        && grid[i+1][j-1] == 'S'
                        && grid[i+1][j+1] == 'S'){
                        count++;
                    }


                    //check formation 2
                    //m s
                    // a
                    //m s
                    if(grid[i-1][j-1] == 'M'
                            && grid[i-1][j+1] == 'S'
                            && grid[i+1][j-1] == 'M'
                            && grid[i+1][j+1] == 'S'){
                        count++;
                    }

                    //check formation 3
                    //s s
                    // a
                    //m m
                    if(grid[i-1][j-1] == 'S'
                            && grid[i-1][j+1] == 'S'
                            && grid[i+1][j-1] == 'M'
                            && grid[i+1][j+1] == 'M'){
                        count++;
                    }

                    //check formation 4
                    //s m
                    // a
                    //s m
                    if(grid[i-1][j-1] == 'S'
                            && grid[i-1][j+1] == 'M'
                            && grid[i+1][j-1] == 'S'
                            && grid[i+1][j+1] == 'M'){
                        count++;
                    }
                }


            }
        }

        scanner.close();
        return count;
    }

}
